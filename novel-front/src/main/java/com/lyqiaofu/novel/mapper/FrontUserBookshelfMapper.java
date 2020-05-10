package com.lyqiaofu.novel.mapper;

import com.lyqiaofu.novel.vo.BookShelfVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
public interface FrontUserBookshelfMapper extends UserBookshelfMapper {

    List<BookShelfVO> listBookShelf(@Param("userId") Long userId);

}
