package com.lyqiaofu.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lyqiaofu.common.domain.LogDO;
import com.lyqiaofu.common.domain.PageDO;
import com.lyqiaofu.common.utils.Query;
@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
