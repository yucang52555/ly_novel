package com.lyqiaofu.business.service;

import com.lyqiaofu.business.domain.BookCommentDO;
import com.lyqiaofu.business.domain.BookContentDO;

import java.util.List;
import java.util.Map;

/**
 * 书籍内容
 * @author ktc
 * @create 2021/7/12
 * @since 1.0.0
 */
public interface BookContentService {
    /**
     * @Desc 删除书籍
     * @Author ktc
     * @Date  2020/7/10 14:21
     **/
    int remove(String indexId);
}
