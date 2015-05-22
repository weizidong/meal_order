package com.hbin.mealorder.model.dao.order;

import com.hbin.mealorder.model.entity.order.MealOrder;
import com.hbin.mealorder.model.mapper.order.MealOrderMapper;
import com.lifesense.framework.mybatis.dao.BaseDAO;

public class MealOrderDao extends BaseDAO<MealOrder, String, MealOrderMapper> {

	/**
	 * 获取最新的一个订单
	 * 
	 * @return
	 */
	public MealOrder getLast() {
		return getMapper().getLast();
	}

}
