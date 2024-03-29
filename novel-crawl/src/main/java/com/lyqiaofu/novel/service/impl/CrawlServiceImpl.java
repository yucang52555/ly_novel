package com.lyqiaofu.novel.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.lyqiaofu.novel.core.cache.CacheKey;
import com.lyqiaofu.novel.core.cache.CacheService;
import com.lyqiaofu.novel.core.crawl.CrawlParser;
import com.lyqiaofu.novel.core.crawl.RuleBean;
import com.lyqiaofu.novel.core.utils.IdWorker;
import com.lyqiaofu.novel.core.utils.SpringUtil;
import com.lyqiaofu.novel.core.utils.ThreadUtil;
import com.lyqiaofu.novel.entity.Book;
import com.lyqiaofu.novel.entity.BookContent;
import com.lyqiaofu.novel.entity.BookIndex;
import com.lyqiaofu.novel.entity.CrawlSource;
import com.lyqiaofu.novel.mapper.*;
import com.lyqiaofu.novel.service.BookService;
import com.lyqiaofu.novel.service.CrawlService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.lyqiaofu.novel.core.utils.HttpUtil.getByHttpClient;
import static com.lyqiaofu.novel.mapper.BookDynamicSqlSupport.crawlBookId;
import static com.lyqiaofu.novel.mapper.BookDynamicSqlSupport.crawlSourceId;
import static com.lyqiaofu.novel.mapper.CrawlSourceDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.update;
import static org.mybatis.dynamic.sql.select.SelectDSL.select;

/**
 * @author Administrator
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CrawlServiceImpl implements CrawlService {


    private final CrawlSourceMapper crawlSourceMapper;

    private final BookService bookService;


    private final CacheService cacheService;


    @Override
    public void addCrawlSource(CrawlSource source) {
        Date currentDate = new Date();
        source.setCreateTime(currentDate);
        source.setUpdateTime(currentDate);
        crawlSourceMapper.insertSelective(source);

    }

    @Override
    public List<CrawlSource> listCrawlByPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        SelectStatementProvider render = select(id, sourceName, sourceStatus, createTime, updateTime)
                .from(crawlSource)
                .orderBy(updateTime)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return crawlSourceMapper.selectMany(render);
    }

    @SneakyThrows
    @Override
    public void openOrCloseCrawl(Integer sourceId, Byte sourceStatus) {

        //判断是开启还是关闭，如果是关闭，则修改数据库状态后获取该爬虫正在运行的线程集合并全部停止
        //如果是开启，先查询数据库中状态，判断该爬虫源是否还在运行，如果在运行，则忽略，
        // 如果没有则修改数据库状态，并启动线程爬取小说数据加入到runningCrawlThread中
        if (sourceStatus == (byte) 0) {
            //关闭,直接修改数据库状态，并直接修改数据库状态后获取该爬虫正在运行的线程集合全部停止
            SpringUtil.getBean(CrawlService.class).updateCrawlSourceStatus(sourceId, sourceStatus);
            Set<Long> runningCrawlThreadId = (Set<Long>) cacheService.getObject(CacheKey.RUNNING_CRAWL_THREAD_KEY_PREFIX + sourceId);
            if (runningCrawlThreadId != null) {
                for (Long ThreadId : runningCrawlThreadId) {
                    Thread thread = ThreadUtil.findThread(ThreadId);
                    if (thread != null && thread.isAlive()) {
                        thread.interrupt();
                    }
                }
            }


        } else {
            //开启
            //查询爬虫源状态和规则
            CrawlSource source = queryCrawlSource(sourceId);
            Byte realSourceStatus = source.getSourceStatus();

            if (realSourceStatus == (byte) 0) {
                //该爬虫源已经停止运行了,修改数据库状态，并启动线程爬取小说数据加入到runningCrawlThread中
                SpringUtil.getBean(CrawlService.class).updateCrawlSourceStatus(sourceId, sourceStatus);
                RuleBean ruleBean = new ObjectMapper().readValue(source.getCrawlRule(), RuleBean.class);

                Set<Long> threadIds = new HashSet<>();
                //按分类开始爬虫解析任务
                for (int i = 1; i < 8; i++) {
                    final int catId = i;
                    Thread thread = new Thread(() -> {

                        parseBookList(catId, ruleBean, sourceId);

                    });
                    thread.start();
                    //thread加入到监控缓存中
                    threadIds.add(thread.getId());

                }
                cacheService.setObject(CacheKey.RUNNING_CRAWL_THREAD_KEY_PREFIX + sourceId, threadIds);


            }


        }

    }

    @Override
    public CrawlSource queryCrawlSource(Integer sourceId) {
        SelectStatementProvider render = select(CrawlSourceDynamicSqlSupport.sourceStatus, CrawlSourceDynamicSqlSupport.crawlRule)
                .from(crawlSource)
                .where(id, isEqualTo(sourceId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return crawlSourceMapper.selectMany(render).get(0);
    }

    /**
     * 解析分类列表
     */
    @Override
    public void parseBookList(int catId, RuleBean ruleBean, Integer sourceId) {

        //当前页码1
        int page = 1;
        int totalPage = page;

        while (page <= totalPage) {

            try {

                if(StringUtils.isNotBlank(ruleBean.getCatIdRule().get("catId" + catId))) {
                    //拼接分类URL
                    String catBookListUrl = ruleBean.getBookListUrl()
                            .replace("{catId}", ruleBean.getCatIdRule().get("catId" + catId))
                            .replace("{page}", page + "");

                    String bookListHtml = getByHttpClient(catBookListUrl);
                    if (bookListHtml != null) {
                        Pattern bookIdPatten = Pattern.compile(ruleBean.getBookIdPatten());
                        Matcher bookIdMatcher = bookIdPatten.matcher(bookListHtml);
                        boolean isFindBookId = bookIdMatcher.find();
                        while (isFindBookId) {
                            try {
                                String bookId = bookIdMatcher.group(1);
                                Book book = CrawlParser.parseBook(ruleBean, bookId);
                                //这里只做新书入库，查询是否存在这本书
                                Book existBook = bookService.queryBookByBookNameAndAuthorName(book.getBookName(), book.getAuthorName());
                                //如果该小说不存在，则可以解析入库，但是标记该小说正在入库，30分钟之后才允许再次入库
                                if (existBook == null) {
                                    //没有该书，可以入库
                                    book.setCatId(catId);
                                    //根据分类ID查询分类
                                    book.setCatName(bookService.queryCatNameByCatId(catId));
                                    if (catId == 7) {
                                        //女频
                                        book.setWorkDirection((byte) 1);
                                    } else {
                                        //男频
                                        book.setWorkDirection((byte) 0);
                                    }
                                    book.setCrawlBookId(bookId);
                                    book.setCrawlSourceId(sourceId);
                                    book.setCrawlLastTime(new Date());
                                    book.setId(new IdWorker().nextId());
                                    // 解析章节目录
                                    Map<Integer, List> indexAndContentList = CrawlParser.parseBookIndexAndContent(bookId, book, ruleBean, new HashMap<>(0));
                                    // 保存书籍内容
                                    if (book.getLastIndexName().contains("大结局") || book.getLastIndexName().contains("全文完") || book.getLastIndexName().contains("番外")
                                            || book.getLastIndexName().contains("完本") || book.getLastIndexName().contains("完结") || book.getLastIndexName().contains("终章")) {
                                        bookService.saveBookAndIndexAndContent(book, (List<BookIndex>) indexAndContentList.get(CrawlParser.BOOK_INDEX_LIST_KEY), (List<BookContent>) indexAndContentList.get(CrawlParser.BOOK_CONTENT_LIST_KEY));
                                    }else {
                                        log.info("书籍采集成功，但是未完本：" + book.getBookName() + "暂不入库");
                                    }

                                } else {
                                    //只更新书籍的爬虫相关字段
                                    bookService.updateCrawlProperties(existBook.getId(), sourceId, bookId);
                                }
                            } catch (Exception e) {
                                log.error(e.getMessage(), e);
                            }


                            isFindBookId = bookIdMatcher.find();
                        }

                        Pattern totalPagePatten = Pattern.compile(ruleBean.getTotalPagePatten());
                        Matcher totalPageMatcher = totalPagePatten.matcher(bookListHtml);
                        boolean isFindTotalPage = totalPageMatcher.find();
                        if (isFindTotalPage) {

                            totalPage = Integer.parseInt(totalPageMatcher.group(1));
                        }
                    }
                }
            }catch (Exception e){
                log.error(e.getMessage(),e);
            }
            page += 1;
        }
    }

    @Override
    public void updateCrawlSourceStatus(Integer sourceId, Byte sourceStatus) {
        CrawlSource source = new CrawlSource();
        source.setId(sourceId);
        source.setSourceStatus(sourceStatus);
        crawlSourceMapper.updateByPrimaryKeySelective(source);
    }

    @Override
    public List<CrawlSource> queryCrawlSourceByStatus(Byte sourceStatus) {
        SelectStatementProvider render = select(CrawlSourceDynamicSqlSupport.id, CrawlSourceDynamicSqlSupport.sourceStatus, CrawlSourceDynamicSqlSupport.crawlRule)
                .from(crawlSource)
                .where(CrawlSourceDynamicSqlSupport.sourceStatus, isEqualTo(sourceStatus))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return crawlSourceMapper.selectMany(render);
    }

    @Override
    public void crawBookByUrlAndSource(String bookUrl, Integer sourceId, Integer catId){
        //查询爬虫源状态和规则
        CrawlSource source = queryCrawlSource(sourceId);
        RuleBean ruleBean = null;
        try {
            ruleBean = new ObjectMapper().readValue(source.getCrawlRule(), RuleBean.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Pattern bookIdPatten = Pattern.compile(ruleBean.getBookIdPatten());
        Matcher bookIdMatcher = bookIdPatten.matcher(bookUrl);
        boolean isFindBookId = bookIdMatcher.find();
        while (isFindBookId) {
            try {
                String bookId = bookIdMatcher.group(1);
                Book book = CrawlParser.parseBook(ruleBean, bookId);
                //这里只做新书入库，查询是否存在这本书
                Book existBook = bookService.queryBookByBookNameAndAuthorName(book.getBookName(), book.getAuthorName());
                //如果该小说不存在，则可以解析入库，但是标记该小说正在入库，30分钟之后才允许再次入库
                if (existBook == null) {
                    //没有该书，可以入库
                    book.setCatId(catId);
                    //根据分类ID查询分类
                    book.setCatName(bookService.queryCatNameByCatId(catId));
                    if (catId == 7) {
                        //女频
                        book.setWorkDirection((byte) 1);
                    } else {
                        //男频
                        book.setWorkDirection((byte) 0);
                    }
                    book.setCrawlBookId(bookId);
                    book.setCrawlSourceId(sourceId);
                    book.setCrawlLastTime(new Date());
                    book.setId(new IdWorker().nextId());
                    // 解析章节目录
                    Map<Integer, List> indexAndContentList = CrawlParser.parseBookIndexAndContent(bookId, book, ruleBean, new HashMap<>(0));
                    // 保存书籍内容
                    if (book.getLastIndexName().contains("大结局") || book.getLastIndexName().contains("全文完") || book.getLastIndexName().contains("番外")
                            || book.getLastIndexName().contains("完本") || book.getLastIndexName().contains("完结") || book.getLastIndexName().contains("终章")) {

                        bookService.saveBookAndIndexAndContent(book, (List<BookIndex>) indexAndContentList.get(CrawlParser.BOOK_INDEX_LIST_KEY), (List<BookContent>) indexAndContentList.get(CrawlParser.BOOK_CONTENT_LIST_KEY));
                    }

                } else {
                    //只更新书籍的爬虫相关字段
                    bookService.updateCrawlProperties(existBook.getId(), sourceId, bookId);
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            log.info("书籍：" + bookUrl + "采集完成");
        }
    }
}
