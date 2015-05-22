package meal_order;

import org.junit.After;
import org.junit.Test;

import com.hbin.mealorder.model.dao.meal.MealDao;
import com.hbin.mealorder.model.entity.meal.Meal;
import com.lifesense.framework.mybatis.util.MyBatisUtil;

public class AddMeal {

	private MealDao mealDao = new MealDao();

	@After
	public void close() {
		MyBatisUtil.close();
	}

	@Test
	public void addMeal() {
		mealDao.create(Meal.generate("水煮鱼", 15.0));
		mealDao.create(Meal.generate("茶树菇炒腊肉", 15.0));
		mealDao.create(Meal.generate("西芹猪肚", 15.0));
		mealDao.create(Meal.generate("红油肚丝", 15.0));
		mealDao.create(Meal.generate("酸菜猪肚", 15.0));
		mealDao.create(Meal.generate("酸菜鱼", 15.0));
		mealDao.create(Meal.generate("泡椒凤爪", 15.0));
		mealDao.create(Meal.generate("老干妈炒牛肉", 15.0));
		mealDao.create(Meal.generate("野山椒牛肉丝", 15.0));
		mealDao.create(Meal.generate("水煮牛肉", 18.0));
		mealDao.create(Meal.generate("泡椒猪腰", 18.0));
		mealDao.create(Meal.generate("红油猪耳", 15.0));
		mealDao.create(Meal.generate("虾饺", 12.0));
		mealDao.create(Meal.generate("肉丝面", 10.0));
		// mealDao.create(Meal.generate("梅菜扣肉", 13.0));
		// mealDao.create(Meal.generate("鼓汁排骨", 13.0));
		// mealDao.create(Meal.generate("土豆鸭", 13.0));
		// mealDao.create(Meal.generate("香菇鸡", 13.0));
		// mealDao.create(Meal.generate("支竹肉片", 13.0));
		// mealDao.create(Meal.generate("青笋腊肉", 13.0));
		// mealDao.create(Meal.generate("支竹回锅肉", 13.0));
		// mealDao.create(Meal.generate("咸鱼茄子", 13.0));
	}

}
