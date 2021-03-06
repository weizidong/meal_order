package com.hbin.mealorder.api.order;

import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hbin.mealorder.api.order.dto.GetOrderItemParam;
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
	@POST
	@Path("get_meal_order")
	public MealOrder getMealOrder() {
		return orderService.getMealOrder();
	}

	/**
	 * 创建订单
	 * 
	 * @return
	 */
	@POST
	@Path("creat_meal_order")
	public MealOrder creatMealOrder() {
		return orderService.creatMealOrder();
	}

	/**
	 * 结束点餐
	 * 
	 * @return
	 */
	@POST
	@Path("complete_meal_order")
	public MealOrder completeMeal() {
		return orderService.completeMeal();
	}

	/**
	 * 点餐
	 * 
	 * @return
	 */
	@POST
	@Path("order_meal")
	public MealOrderItem orderMealItem(MealOrderItem orderItem) {
		return orderService.orderMealItem(orderItem);
	}

	/**
	 * 取消点餐
	 * 
	 * @param orderItem
	 * @param request
	 */
	@POST
	@Path("delete_order_meal")
	public void deleteOrderMeal(MealOrderItem orderItem) {
		orderService.deleteOrderMeal(orderItem);
	}

	/**
	 * 更新点餐
	 * 
	 * @return
	 */
	@POST
	@Path("update_order_meal")
	public void updateOrderMeal(MealOrderItem orderItem) {
		orderService.updateOrderMeal(orderItem);
	}

	@POST
	@Path("get_account_orde_items")
	public List<MealOrderItem> getAccountOrderItems(GetOrderItemParam param) {
		return orderService.getAccountOrderItems(param.getAccountId(), param.getOrderId());
	}
}
