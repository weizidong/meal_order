package com.hbin.mealorder.api.order;

import java.util.List;

import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
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

	/**
	 * 点餐
	 * 
	 * @return
	 */
	@Path("order_meal")
	public MealOrderItem orderMealItem(MealOrderItem orderItem, @Context HttpServletRequest request) {
		return null;
	}

	/**
	 * 取消点餐
	 * 
	 * @param orderItem
	 * @param request
	 */
	@Path("cancel_order_meal")
	public void cancelOrderMeal(MealOrderItem orderItem, @Context HttpServletRequest request) {

	}

	/**
	 * 更换点餐
	 * 
	 * @return
	 */
	@Path("update_order_meal")
	public MealOrderItem updateOrderMeal(MealOrderItem orderItem, @Context HttpServletRequest request) {
		return null;
	}

	@Path("get_account_ordes")
	public List<MealOrderItem> getAccountOrders(GetOrderItemParam param) {
		return orderService.getAccountOrderItems(param.getAccountId(), param.getOrderId());
	}
}
