package com.lyqiaofu.business.service;

import com.lyqiaofu.business.domain.BookDO;
import com.lyqiaofu.common.domain.PageDO;
import com.lyqiaofu.common.utils.Query;
import com.lyqiaofu.test.domain.OrderDO;

import java.util.List;
import java.util.Map;

/**
 * 书籍
 * @author ktc
 * @create 2020/6/20
 * @since 1.0.0
 */
public interface BookService {
    /**
     * 分页查询书籍列表
     * @param map
     * @return
     */
    List<BookDO> list(Map<String, Object> map);

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

    /**
     * 根据id获取书籍信息
     * @param id
     * @return
     */
    BookDO get(Long id);

    /**
     * 更新书籍信息
     * @param book
     * @return
     */
    int update(BookDO book);

    /**
     * 新增书籍
     * @param book
     * @return
     */
    int save(BookDO book);

    /**
     * 批量删除书籍
     * @param bookIds
     * @return
     */
    int batchremove(Long[] bookIds);
}
