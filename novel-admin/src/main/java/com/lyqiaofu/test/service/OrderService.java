package com.lyqiaofu.test.service;

import com.lyqiaofu.test.domain.OrderDO;

import java.util.List;
import java.util.Map;

/**
 * 付呗-订单信息表
 * 
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2019-11-25 11:57:16
 */
public interface OrderService {
	
	OrderDO get(Long id);
	
	List<OrderDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrderDO order);
	
	int update(OrderDO order);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
