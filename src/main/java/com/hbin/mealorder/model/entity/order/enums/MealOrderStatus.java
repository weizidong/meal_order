package com.hbin.mealorder.model.entity.order.enums;

public enum MealOrderStatus {

	正在点餐(0),

	结束点餐(1);

	private Integer code;

	MealOrderStatus(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

}
