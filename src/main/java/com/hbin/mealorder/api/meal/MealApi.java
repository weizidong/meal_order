package com.hbin.mealorder.api.meal;

import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hbin.mealorder.model.entity.meal.Meal;
import com.hbin.mealorder.service.meal.MealService;

@Path("meal")
@Singleton
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MealApi {
	private MealService mealService = new MealService();
	
	@Path("get_all")
	public List<Meal> getAll(){
		return mealService.getAll();
	}
}
