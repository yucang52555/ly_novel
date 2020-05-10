package com.lyqiaofu.novel.mapper;

import com.lyqiaofu.novel.entity.BookIndex;
import org.apache.ibatis.annotations.Param;

/**
 * @author Administrator
 */
public interface CrawlBookIndexMapper extends BookIndexMapper {


    /**
     * 查询最后的章节
     * */
    BookIndex queryLastIndex(@Param("bookId") Long bookId);
}
