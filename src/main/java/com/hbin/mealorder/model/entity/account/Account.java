package com.hbin.mealorder.model.entity.account;

import com.lifesense.framework.mybatis.entity.id.UUIDEntity;
import com.lifesense.framework.mybatis.interceptor.generatesql.annotation.Id;
import com.lifesense.framework.mybatis.interceptor.generatesql.annotation.Table;

@SuppressWarnings("serial")
@Table
public class Account implements UUIDEntity {

	@Id
	private String id;

	private String openId;

	private String nickname;

	private String remarkName;

	private String headimg;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRemarkName() {
		return remarkName;
	}

	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
}
