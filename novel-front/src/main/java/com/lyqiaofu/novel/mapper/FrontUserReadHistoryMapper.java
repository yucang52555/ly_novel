package com.lyqiaofu.novel.mapper;

import com.lyqiaofu.novel.vo.BookReadHistoryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
public interface FrontUserReadHistoryMapper extends UserReadHistoryMapper {

    List<BookReadHistoryVO> listReadHistory(@Param("userId") Long userId);

}
