package com.hbin.mealorder.model.mapper.meal;

import java.util.List;

import com.hbin.mealorder.model.entity.meal.Meal;
import com.lifesense.framework.mybatis.mapper.BaseMapper;

public interface MealMapper extends BaseMapper<Meal, String> {

	/**
	 * 获取所有
	 * 
	 * @return
	 */
	List<Meal> getAll();

}
