package com.hbin.mealorder.service.order;

import java.util.List;

import org.apache.log4j.Logger;

import com.hbin.mealorder.model.dao.order.MealOrderDao;
import com.hbin.mealorder.model.dao.order.MealOrderItemDao;
import com.hbin.mealorder.model.entity.order.MealOrder;
import com.hbin.mealorder.model.entity.order.MealOrderItem;
import com.hbin.mealorder.model.entity.order.enums.MealOrderStatus;

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
		List<MealOrderItem> items = orderItemDao.getByOrderId(mealOrder.getId());
		logger.debug(items);
		mealOrder.setItems(items);
		logger.debug(mealOrder);
		return mealOrder;
	}
}
