package com.lyqiaofu.novel.service.impl;

import com.github.pagehelper.PageHelper;
import com.lyqiaofu.novel.core.utils.BeanUtil;
import com.lyqiaofu.novel.service.NewsService;
import com.lyqiaofu.novel.core.cache.CacheKey;
import com.lyqiaofu.novel.core.cache.CacheService;
import com.lyqiaofu.novel.entity.News;
import com.lyqiaofu.novel.mapper.NewsMapper;
import com.lyqiaofu.novel.vo.NewsVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lyqiaofu.novel.mapper.NewsDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.select.SelectDSL.select;

/**
 * @author 11797
 */
@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsMapper newsMapper;

    private final CacheService cacheService;


    @Override
    public List<News> listIndexNews() {
        List<News> result = (List<News>) cacheService.getObject(CacheKey.INDEX_NEWS_KEY);
        if(result == null || result.size() == 0) {
            SelectStatementProvider selectStatement = select(id, catName, catId, title)
                    .from(news)
                    .orderBy(createTime.descending())
                    .limit(2)
                    .build()
                    .render(RenderingStrategies.MYBATIS3);
            result = newsMapper.selectMany(selectStatement);
            cacheService.setObject(CacheKey.INDEX_NEWS_KEY,result);
        }
        return result;
    }

    @Override
    public News queryNewsInfo(Long newsId) {
        SelectStatementProvider selectStatement = select(news.allColumns())
                .from(news)
                .where(id,isEqualTo(newsId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return newsMapper.selectMany(selectStatement).get(0);

    }

    @Override
    public List<NewsVO> listByPage(int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        SelectStatementProvider selectStatement = select(id, catName, catId, title,createTime)
                .from(news)
                .orderBy(createTime.descending())
                .build()
                .render(RenderingStrategies.MYBATIS3);

        return BeanUtil.copyList(newsMapper.selectMany(selectStatement),NewsVO.class);
    }
}
