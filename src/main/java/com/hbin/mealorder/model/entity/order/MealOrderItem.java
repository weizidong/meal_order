package com.hbin.mealorder.model.entity.order;

import java.util.Date;

import com.lifesense.framework.mybatis.entity.id.UUIDEntity;
import com.lifesense.framework.mybatis.interceptor.generatesql.annotation.Id;
import com.lifesense.framework.mybatis.interceptor.generatesql.annotation.Table;

@SuppressWarnings("serial")
@Table
public class MealOrderItem implements UUIDEntity {

	@Id
	private String id;

	private String accountId;

	private String mealId;

	private Integer quantity;

	private Date created;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getMealId() {
		return mealId;
	}

	public void setMealId(String mealId) {
		this.mealId = mealId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
