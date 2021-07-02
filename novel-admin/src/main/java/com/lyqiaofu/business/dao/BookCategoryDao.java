package com.lyqiaofu.business.dao;

import com.lyqiaofu.business.domain.BookCategoryDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookCategoryDao {

	List<BookCategoryDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int remove(String categoryId);

    int save(BookCategoryDO bookCategory);
}
