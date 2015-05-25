package com.hbin.mealorder.model.mapper.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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


	/**
	 * 查询订单详情
	 * 
	 * @param orderId
	 * @return
	 */
	List<MealOrderItem> getAccountOrderItems(@Param("accountId")String accountId,@Param("orderId")String orderId);

}
