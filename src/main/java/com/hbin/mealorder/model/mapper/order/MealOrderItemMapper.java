package com.hbin.mealorder.model.mapper.order;

import java.util.List;

import com.hbin.mealorder.model.entity.order.MealOrderItem;
import com.lifesense.framework.mybatis.mapper.BaseMapper;

public interface MealOrderItemMapper extends BaseMapper<MealOrderItem, String> {

	/**
	 * 查询订单详情
	 * 
	 * @param orderId
	 * @return
	 */
	List<MealOrderItem> getByOrderId(String orderId);

}
