package com.hbin.mealorder.service.wx.oauth.dto;

import java.io.Serializable;

/**
 * 微信网页授权，获取access_token的返回对象
 * 
 * @author lujuzhi
 * 
 */
@SuppressWarnings("serial")
public class AccessToken implements Serializable {

	/**
	 * 网页授权接口访问token
	 */
	private String access_token;

	/**
	 * access_token接口调用凭证超时时间，单位（秒）
	 */
	private long expires_in;

	/**
	 * 用户刷新access_token
	 */
	private String refresh_token;

	/**
	 * 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
	 */
	private String openid;

	/**
	 * 用户授权的作用域，使用逗号（,）分隔
	 */
	private String scope;

	private Integer errcode;

	private String errmsg;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(long expires_in) {
		this.expires_in = expires_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	@Override
	public String toString() {
		return "AccessToken [access_token=" + access_token + ", expires_in=" + expires_in + ", refresh_token=" + refresh_token + ", openid=" + openid + ", scope=" + scope + ", errcode=" + errcode + ", errmsg=" + errmsg + "]";
	}

}
