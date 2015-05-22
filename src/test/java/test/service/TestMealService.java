package test.service;

import org.junit.After;
import org.junit.Test;

import com.hbin.mealorder.service.meal.MealService;
import com.lifesense.framework.mybatis.util.MyBatisUtil;

public class TestMealService {

	private MealService mealService = new MealService();
	
	@After
	public void close(){
		MyBatisUtil.close();
	}
	
	@Test
	public void testGetAll(){
		System.out.println(mealService.getAll());
	}
}
