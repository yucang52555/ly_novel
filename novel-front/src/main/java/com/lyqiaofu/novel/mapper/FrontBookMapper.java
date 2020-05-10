package com.lyqiaofu.novel.mapper;

import com.lyqiaofu.novel.entity.Book;
import com.lyqiaofu.novel.search.BookSP;
import com.lyqiaofu.novel.vo.BookVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
public interface FrontBookMapper extends BookMapper {


    List<BookVO> searchByPage(BookSP params);

    void addVisitCount(@Param("bookId") Long bookId);

    List<Book> listRecBookByCatId(@Param("catId") Integer catId);

    void addCommentCount(@Param("bookId") Long bookId);

    List<Book> queryNetworkPicBooks(@Param("limit") Integer limit,@Param("offset") Integer offset);

    /**
     * 按评分随机查询小说集合
     * @param limit 查询条数
     * @return 小说集合
     * */
    List<Book> selectIdsByScoreAndRandom(@Param("limit") int limit);
}
