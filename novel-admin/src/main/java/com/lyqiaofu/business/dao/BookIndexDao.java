package com.lyqiaofu.business.dao;

import com.lyqiaofu.business.domain.BookDO;
import com.lyqiaofu.business.domain.BookIndexDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface BookIndexDao {

	List<BookIndexDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int remove(String indexId);

	int batchRemove(Long[] indexIds);
}
