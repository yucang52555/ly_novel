package com.lyqiaofu.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lyqiaofu.system.dao.RoleDataPermDao;
import com.lyqiaofu.system.domain.RoleDataPermDO;
import com.lyqiaofu.system.service.RoleDataPermService;



@Service
public class RoleDataPermServiceImpl implements RoleDataPermService {
	@Autowired
	private RoleDataPermDao roleDataPermDao;
	
	@Override
	public RoleDataPermDO get(Long id){
		return roleDataPermDao.get(id);
	}
	
	@Override
	public List<RoleDataPermDO> list(Map<String, Object> map){
		return roleDataPermDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return roleDataPermDao.count(map);
	}
	
	@Override
	public int save(RoleDataPermDO roleDataPerm){
		return roleDataPermDao.save(roleDataPerm);
	}
	
	@Override
	public int update(RoleDataPermDO roleDataPerm){
		return roleDataPermDao.update(roleDataPerm);
	}
	
	@Override
	public int remove(Long id){
		return roleDataPermDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return roleDataPermDao.batchRemove(ids);
	}
	
}
