package meal_order;

import org.junit.After;
import org.junit.Test;

import com.hbin.mealorder.model.dao.meal.MealDao;
import com.hbin.mealorder.model.entity.meal.Meal;
import com.lifesense.framework.mybatis.util.MyBatisUtil;

public class AddMeal {

	private MealDao mealDao = new MealDao();
	
	@After
	public void close(){
		MyBatisUtil.close();
	}
	
	@Test
	public void addMeal(){
		mealDao.create(Meal.generate("火腿香蛋", 8.0));
	}
	
}
