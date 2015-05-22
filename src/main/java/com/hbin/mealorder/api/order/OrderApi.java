package com.hbin.mealorder.api.order;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hbin.mealorder.model.entity.order.MealOrder;
import com.hbin.mealorder.model.entity.order.MealOrderItem;
import com.hbin.mealorder.service.order.OrderService;

@Path("order")
@Singleton
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderApi {

	private OrderService orderService = new OrderService();

	/**
	 * 获取订单
	 * 
	 * @return
	 */
	@Path("get_meal_order")
	public MealOrder getMealOrder() {
		return orderService.getMealOrder();
	}

	/**
	 * 创建订单
	 * 
	 * @return
	 */
	@Path("creat_meal_order")
	public MealOrder creatMealOrder() {
		return orderService.creatMealOrder();
	}

	/**
	 * 结束点餐
	 * 
	 * @return
	 */
	@Path("complete_meal_order")
	public MealOrder completeMeal() {
		return orderService.completeMeal();
	}

	public MealOrderItem orderMeal() {
		return null;
	}
}
