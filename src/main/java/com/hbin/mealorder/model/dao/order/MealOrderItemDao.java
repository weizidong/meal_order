package com.hbin.mealorder.model.dao.order;

import java.util.List;

import com.hbin.mealorder.model.entity.order.MealOrderItem;
import com.hbin.mealorder.model.mapper.order.MealOrderItemMapper;
import com.lifesense.framework.mybatis.dao.BaseDAO;

public class MealOrderItemDao extends BaseDAO<MealOrderItem, String, MealOrderItemMapper> {

	/**
	 * 查询订单详情
	 * 
	 * @param id
	 * @return
	 */
	public List<MealOrderItem> getByOrderId(String orderId) {
		return getMapper().getByOrderId(orderId);
	}
}
