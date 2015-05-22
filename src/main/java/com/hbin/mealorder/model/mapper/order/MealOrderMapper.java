package com.hbin.mealorder.model.mapper.order;

import com.hbin.mealorder.model.entity.order.MealOrder;
import com.lifesense.framework.mybatis.mapper.BaseMapper;

public interface MealOrderMapper extends BaseMapper<MealOrder, String> {

	/**
	 * 获取最新的订单
	 * 
	 * @return
	 */
	MealOrder getLast();
}
