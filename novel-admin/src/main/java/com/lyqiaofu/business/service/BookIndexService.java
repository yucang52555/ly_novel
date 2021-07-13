package com.lyqiaofu.business.service;

import com.lyqiaofu.business.domain.BookIndexDO;

import java.util.List;
import java.util.Map;

/**
 * 章节
 * @author ktc
 * @create 2020/6/20
 * @since 1.0.0
 */
public interface BookIndexService {
    /**
     * 查询书籍章节列表
     * @param map
     * @return
     */
    List<BookIndexDO> list(Map<String, Object> map);

    /**
     * 统计数量
     * @param map
     * @return
     */
    int count(Map<String, Object> map);

    /**
     * @Desc 删除多余章节
     * @Author ktc
     * @Date  2020/7/10 14:21
     **/
    int remove(String indexId);

    /**
     * 批量删除
     * @param indexIds
     * @return
     */
    int batchremove(Long[] indexIds);
}
