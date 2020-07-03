package com.lyqiaofu.business.service;

import com.lyqiaofu.business.domain.BookSettingDO;
import com.lyqiaofu.common.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 书籍展示设置
 * @author ktc
 * @create 2020/6/18
 * @since 1.0.0
 */
public interface BookSettingService {
    /**
     * 查询列表
     * @param params
     * @return
     */
    List<BookSettingDO> list(Map<String, Object> params);

    /**
     * 获取数量
     * @param query
     * @return
     */
    int count(Query query);

    /**
     * @Desc 获取书籍展示设置
     * @Author ktc
     * @Date  2020/7/1 10:17
     **/
    BookSettingDO get(Long id);

    /**
     * @Desc 删除书籍展示设置记录
     * @Author ktc
     * @Date  2020/7/1 10:18
     **/
    int remove(Long id);
}
