package com.hbin.mealorder.service.wx.token.dto;

import java.io.Serializable;

/**
 * 微信公众号访问令牌信息
 * @author juzhi
 *
 */
@SuppressWarnings("serial")
public class TokenDto implements Serializable {

	// 访问令牌
	private String accessToken;

	// 令牌有效时间，单位：秒
	private Long expiresIn;

	// 获取令牌时间
	private Long timestamp;
	
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
}
