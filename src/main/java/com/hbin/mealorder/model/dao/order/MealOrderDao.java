package com.hbin.mealorder.model.dao.order;

import com.hbin.mealorder.model.entity.order.MealOrder;
import com.hbin.mealorder.model.mapper.order.MealOrderMapper;
import com.lifesense.framework.mybatis.dao.BaseDAO;

public class MealOrderDao extends BaseDAO<MealOrder, String, MealOrderMapper> {
	/**
	 * 查询正在点餐的订单
	 * 
	 * @param code
	 * @return
	 */
	public MealOrder getByStatus(Integer status) {
		return getMapper().getByStatus(status).get(0);
	}

}
