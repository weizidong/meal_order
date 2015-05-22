package com.hbin.mealorder.service.order;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.hbin.mealorder.model.dao.order.MealOrderDao;
import com.hbin.mealorder.model.dao.order.MealOrderItemDao;
import com.hbin.mealorder.model.entity.order.MealOrder;
import com.hbin.mealorder.model.entity.order.MealOrderItem;
import com.hbin.mealorder.model.entity.order.enums.MealOrderStatus;
import com.lifesense.framework.rest.exception.WebException;
import com.lifesense.framework.rest.response.ResponseCode;

public class OrderService {

	private static final Logger logger = Logger.getLogger(OrderService.class);

	private MealOrderDao orderDao = new MealOrderDao();

	private MealOrderItemDao orderItemDao = new MealOrderItemDao();

	/**
	 * 获取订单
	 * 
	 * @return
	 */
	public MealOrder getMealOrder() {
		MealOrder mealOrder = orderDao.getByStatus(MealOrderStatus.正在点餐.getCode());
		logger.debug(mealOrder);
		if (mealOrder == null) {
			return MealOrder.generate();
		}
		List<MealOrderItem> items = orderItemDao.getByOrderId(mealOrder.getId());
		logger.debug(items);
		mealOrder.setItems(items);
		return mealOrder;
	}

	/**
	 * 创建订单
	 * 
	 * @return
	 */
	public MealOrder creatMealOrder() {
		MealOrder order = MealOrder.generate();
		orderDao.create(order);
		logger.debug(order);
		return order;
	}

	/**
	 * 结束点餐
	 * 
	 * @return
	 */
	public MealOrder completeMeal() {
		MealOrder order = orderDao.getByStatus(MealOrderStatus.正在点餐.getCode());
		if (order == null) {
			throw new WebException(ResponseCode.资源不存在);
		}
		logger.debug(order);
		order.setStatus(MealOrderStatus.结束点餐.getCode());
		orderDao.update(order);
		List<MealOrderItem> items = orderItemDao.getByOrderId(order.getId());
		logger.debug(items);
		order.setItems(items);
		return order;
	}

	/**
	 * 点餐
	 * 
	 * @param orderItem
	 * @return
	 */
	public MealOrderItem orderMealItem(MealOrderItem orderItem) {
		if (orderItem.getAccountId() == null || orderItem.getMealId() == null || orderItem.getMealOrderId() == null) {
			throw new WebException(ResponseCode.不允许为空);
		}
		orderItem.setCreated(new Date());
		orderItemDao.create(orderItem);
		return orderItem;
	}

	/**
	 * 取消点餐
	 * 
	 * @param orderItem
	 */
	public void deleteOrderMeal(MealOrderItem orderItem) {
		if (orderItem.getId() == null) {
			throw new WebException(ResponseCode.不允许为空);
		}
		orderItemDao.delete(orderItem.getId());
	}

	/**
	 * 更新点餐
	 * 
	 * @param orderItem
	 * @return
	 */
	public void updateOrderMeal(MealOrderItem orderItem) {
		if (orderItem.getId() == null || orderItem.getAccountId() == null || orderItem.getMealOrderId() == null) {
			throw new WebException(ResponseCode.不允许为空);
		}
		orderItemDao.update(orderItem);
	}
	

	/**
	 * 获取订单年明细
	 * 
	 * @param accountId
	 * @param orderId
	 *            可选，指定订单id
	 * @return
	 */
	public List<MealOrderItem> getAccountOrderItems(String accountId, String orderId) {
		return orderItemDao.query(accountId, orderId);
		
	}
	
}