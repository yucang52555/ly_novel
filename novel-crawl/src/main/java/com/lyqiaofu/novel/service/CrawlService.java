package com.lyqiaofu.novel.service;

import com.lyqiaofu.novel.core.crawl.RuleBean;
import com.lyqiaofu.novel.entity.CrawlSource;

import java.io.IOException;
import java.util.List;

/**
 * @author Administrator
 */
public interface CrawlService {

    /**
     * 新增爬虫源
     * @param source 爬虫源提交的数据对象
     * */
    void addCrawlSource(CrawlSource source);


    /**
     * 爬虫源分页列表
     * @param page 当前页码
     * @param pageSize 分页大小
     *@return 爬虫源集合
     * */
    List<CrawlSource> listCrawlByPage(int page, int pageSize);

    /**
     * 开启或停止爬虫
     * @param sourceId 爬虫源ID
     * @param sourceStatus 状态，0关闭，1开启
     * */
    void openOrCloseCrawl(Integer sourceId, Byte sourceStatus);

    /**
     * 更新爬虫状态
     * @param sourceId 爬虫源ID
     * @param sourceStatus 状态，0关闭，1开启
     * */
    void updateCrawlSourceStatus(Integer sourceId, Byte sourceStatus);

    /**
     * 根据爬虫状态查询爬虫源集合
     * @param sourceStatus 状态，0关闭，1开启
     * @return 返回爬虫源集合
     * */
    List<CrawlSource> queryCrawlSourceByStatus(Byte sourceStatus);

    /**
     * 根据分类ID和规则解析分类列表
     * @param catId 分类ID
     * @param ruleBean 规则对象
     * @param sourceId
     */
    void parseBookList(int catId, RuleBean ruleBean, Integer sourceId);


    /**
     * 查询爬虫源
     * @param sourceId 源ID
     * @return 源信息
     * */
    CrawlSource queryCrawlSource(Integer sourceId);

    /**
     * 采集单本书籍
     * @param bookUrl
     * @param sourceId
     */
    public void crawBookByUrlAndSource(String bookUrl, Integer sourceId, Integer catId);
}
