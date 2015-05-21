package com.hbin.mealorder.model.entity.order.enums;

public enum MealOrderStatus {

	正在点餐(0),

	结束点餐(1);

	private int code;

	MealOrderStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
