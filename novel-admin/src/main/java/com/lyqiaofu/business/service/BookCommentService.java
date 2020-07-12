package com.lyqiaofu.business.service;

import com.lyqiaofu.business.domain.BookCategoryDO;
import com.lyqiaofu.business.domain.BookCommentDO;

import java.util.List;
import java.util.Map;

/**
 * 书籍评论
 * @author ktc
 * @create 2020/6/20
 * @since 1.0.0
 */
public interface BookCommentService {
    /**
     * 查询书籍章节列表
     * @param map
     * @return
     */
    List<BookCommentDO> list(Map<String, Object> map);

    /**
     * 统计数量
     * @param map
     * @return
     */
    int count(Map<String, Object> map);

    /**
     * @Desc 删除书籍
     * @Author ktc
     * @Date  2020/7/10 14:21
     **/
    int remove(String bookId);
}
