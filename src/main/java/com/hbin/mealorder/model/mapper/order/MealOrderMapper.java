package com.hbin.mealorder.model.mapper.order;

import java.util.List;

import com.hbin.mealorder.model.entity.order.MealOrder;
import com.lifesense.framework.mybatis.mapper.BaseMapper;

public interface MealOrderMapper extends BaseMapper<MealOrder, String> {
	/**
	 * 查询正在点餐的订单
	 * 
	 * @param status
	 * @return
	 */
	List<MealOrder> getByStatus(Integer status);
}
