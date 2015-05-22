package com.hbin.mealorder.model.dao.meal;

import java.util.List;

import com.hbin.mealorder.model.entity.meal.Meal;
import com.hbin.mealorder.model.mapper.meal.MealMapper;
import com.lifesense.framework.mybatis.dao.BaseDAO;

public class MealDao extends BaseDAO<Meal, String, MealMapper> {

	public List<Meal> getAll() {
		return getMapper().getAll();
	}

}
