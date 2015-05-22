package test.service;

import org.junit.After;
import org.junit.Test;

import com.hbin.mealorder.service.order.OrderService;
import com.lifesense.framework.mybatis.util.MyBatisUtil;

public class TestOrderService {

	private OrderService orderService = new OrderService();
	
	@After
	public void close(){
		MyBatisUtil.close();
	}
	
	@Test
	public void test(){
		System.out.println(orderService.getAccountOrderItems("sdf", "sdf"));
	}
}
