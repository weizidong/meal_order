package com.hbin.mealorder.model.entity.order;

import java.util.Date;

import com.lifesense.framework.mybatis.entity.id.UUIDEntity;
import com.lifesense.framework.mybatis.interceptor.generatesql.annotation.Id;
import com.lifesense.framework.mybatis.interceptor.generatesql.annotation.Table;
import com.lifesense.framework.mybatis.interceptor.generatesql.annotation.Transient;

@SuppressWarnings("serial")
@Table
public class MealOrderItem implements UUIDEntity {

	@Id
	private String id;

	private String mealOrderId;

	private String accountId;

	private String mealId;

	private Integer quantity;

	private Date created;
	
	@Transient
	private String mealName;

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

	public String getMealOrderId() {
		return mealOrderId;
	}

	public void setMealOrderId(String mealOrderId) {
		this.mealOrderId = mealOrderId;
	}

	@Override
	public String toString() {
		return "MealOrderItem [id=" + id + ", mealOrderId=" + mealOrderId + ", accountId=" + accountId + ", mealId=" + mealId + ", quantity=" + quantity + ", created=" + created + "]";
	}

	public String getMealName() {
		return mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

}
