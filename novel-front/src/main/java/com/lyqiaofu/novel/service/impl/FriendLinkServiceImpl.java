package com.lyqiaofu.novel.service.impl;

import com.lyqiaofu.novel.core.utils.BeanUtil;
import com.lyqiaofu.novel.service.FriendLinkService;
import com.lyqiaofu.novel.core.cache.CacheKey;
import com.lyqiaofu.novel.core.cache.CacheService;
import com.lyqiaofu.novel.entity.FriendLink;
import com.lyqiaofu.novel.mapper.FriendLinkMapper;
import lombok.RequiredArgsConstructor;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lyqiaofu.novel.mapper.FriendLinkDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.select.SelectDSL.select;

/**
 * @author 11797
 */
@Service
@RequiredArgsConstructor
public class FriendLinkServiceImpl implements FriendLinkService {

    private final FriendLinkMapper friendLinkMapper;

    private final CacheService cacheService;


    @Override
    public List<FriendLink> listIndexLink() {
        List<FriendLink> result = (List<FriendLink>) cacheService.getObject(CacheKey.INDEX_LINK_KEY);
        if(result == null || result.size() == 0) {
            SelectStatementProvider selectStatement = select(linkName,linkUrl)
                    .from(friendLink)
                    .where(isOpen,isEqualTo((byte)1))
                    .orderBy(sort)
                    .build()
                    .render(RenderingStrategies.MYBATIS3);
            result =  friendLinkMapper.selectMany(selectStatement);
            cacheService.setObject(CacheKey.INDEX_LINK_KEY,result);
        }
        return result;
    }
}
