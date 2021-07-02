package com.lyqiaofu.business.service;

import com.lyqiaofu.business.domain.BookCategoryDO;
import com.lyqiaofu.business.domain.BookDO;
import com.lyqiaofu.business.domain.BookIndexDO;

import java.util.List;
import java.util.Map;

/**
 * 书籍类别
 * @author ktc
 * @create 2020/6/20
 * @since 1.0.0
 */
public interface BookCategoryService {
    /**
     * 查询书籍类别
     * @param map
     * @return
     */
    List<BookCategoryDO> list(Map<String, Object> map);

    /**
     * 统计数量
     * @param map
     * @return
     */
    int count(Map<String, Object> map);

    /**
     * @Desc 删除书籍类别
     * @Author ktc
     * @Date  2020/7/10 14:21
     **/
    int remove(String categoryId);

    /**
     * @Desc 保存书籍类别
     * @param bookCategory
     * @return
     */
    int save(BookCategoryDO bookCategory);
}
