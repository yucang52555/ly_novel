package com.lyqiaofu.business.dao;

import com.lyqiaofu.business.domain.BookCommentDO;
import com.lyqiaofu.business.domain.BookContentDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookContentDao {

	int remove(String indexId);

	int batchRemove(Long[] indexIds);
}
