package test.service;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;

import com.hbin.mealorder.service.order.OrderService;
import com.lifesense.framework.mybatis.util.MyBatisUtil;

public class TestOrderService {
	private static final Logger logger = Logger.getLogger(TestOrderService.class);

	private OrderService service = new OrderService();

	@After
	public void close() {
		MyBatisUtil.close();
	}

	@Test
	public void testGetAll() {
		logger.debug(service.getMealOrder());
	}
}
