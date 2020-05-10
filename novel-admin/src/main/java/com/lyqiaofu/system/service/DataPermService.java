package com.lyqiaofu.system.service;

import com.lyqiaofu.common.domain.DictDO;
import com.lyqiaofu.common.domain.Tree;
import com.lyqiaofu.system.domain.DataPermDO;

import java.util.List;
import java.util.Map;

/**
 * 数据权限管理
 * 
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2019-11-25 11:40:03
 */
public interface DataPermService {
	
	DataPermDO get(Long id);
	
	List<DataPermDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DataPermDO dataPerm);
	
	int update(DataPermDO dataPerm);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<DataPermDO> listModuleName();

    Tree<DataPermDO> getTree();

    Tree<DataPermDO> getTree(Long roleId);
}
