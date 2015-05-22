package com.hbin.mealorder.service.meal;

import java.util.List;

import com.hbin.mealorder.model.dao.meal.MealDao;
import com.hbin.mealorder.model.entity.meal.Meal;

public class MealService {
	private MealDao mealDao = new MealDao();

	public List<Meal> getAll() {
		return mealDao.getAll();
	}
}
