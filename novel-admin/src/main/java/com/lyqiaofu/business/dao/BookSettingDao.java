package com.lyqiaofu.business.dao;

import com.lyqiaofu.business.domain.BookSettingDO;
import com.lyqiaofu.test.domain.OrderDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface BookSettingDao {

	List<BookSettingDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
}
