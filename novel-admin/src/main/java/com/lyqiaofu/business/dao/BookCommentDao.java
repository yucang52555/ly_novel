package com.lyqiaofu.business.dao;

import com.lyqiaofu.business.domain.BookCommentDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookCommentDao {

	List<BookCommentDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int remove(String bookId);
}
