package com.hbin.mealorder.model.entity.meal;

import com.lifesense.framework.mybatis.entity.id.UUIDEntity;
import com.lifesense.framework.mybatis.interceptor.generatesql.annotation.Id;
import com.lifesense.framework.mybatis.interceptor.generatesql.annotation.Table;

@SuppressWarnings("serial")
@Table
public class Meal implements UUIDEntity {

	@Id
	private String id;

	private String name;

	private Double price;

	private Boolean deleted;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public static Meal generate(String name, double price) {
		Meal meal = new Meal();

		meal.setName(name);
		meal.setPrice(price);
		meal.setDeleted(false);

		return meal;
	}

	@Override
	public String toString() {
		return "Meal [id=" + id + ", name=" + name + ", price=" + price + ", deleted=" + deleted + "]";
	}
}
