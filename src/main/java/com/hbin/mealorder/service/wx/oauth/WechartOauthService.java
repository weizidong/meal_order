package com.hbin.mealorder.service.wx.oauth;

import java.io.IOException;
import java.text.MessageFormat;

import com.hbin.mealorder.service.wx.WeixinAPI;
import com.hbin.mealorder.service.wx.oauth.dto.AccessToken;
import com.hbin.util.HttpClientManager;
import com.lifesense.framework.common.jackson.JacksonUtils;
import com.lifesense.framework.common.log.LoggerAdapter;
import com.lifesense.framework.common.log.LoggerAdapterFacory;
import com.lifesense.framework.common.util.StringUtil;

public class WechartOauthService {

	private static LoggerAdapter log = LoggerAdapterFacory.getLogger(WechartOauthService.class);

	/**
	 * 获取重定向微信的授权url
	 * 
	 * @param appid
	 * @param redirectUri
	 * @param state
	 * @return
	 */
	public static String getAuthorizeUrl(String appid, String redirectUri, String state) {

		String stateUrl = StringUtil.isEmpty(state) ? "" : "&state=" + state;

		String getCodeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=" + redirectUri + "&response_type=code&scope=snsapi_base" + stateUrl + "#wechat_redirect";

		return getCodeUrl;
	}

	/**
	 * 通过授权获取的code获取accessToken
	 * 
	 * @param appid
	 * @param secret
	 * @param code
	 * @return
	 */
	public static AccessToken getAccessTokenByCode(String appid, String secret, String code) {
		String body = HttpClientManager.get(MessageFormat.format(WeixinAPI.GET_ACCESS_TOKEN_URL, appid, secret, code));

		StringBuilder sb = new StringBuilder();

		sb.append("===========get authorize access token  start ====================");
		sb.append("appid = " + appid);
		sb.append("secret = " + secret);
		sb.append("code = " + code);
		sb.append("retult = " + body);
		sb.append("===========get authorize access token  end   ====================");

		log.debug(sb.toString());

		AccessToken accessToken = null;
		try {
			accessToken = JacksonUtils.toBean(body, AccessToken.class);
		} catch (IOException e) {
			throw new RuntimeException("通过code获取accessToken，返回结果将json转换为" + AccessToken.class + "发生异常。", e);
		}

		if (accessToken == null) {
			throw new RuntimeException("通过code获取accessToken，返回结果为null");
		}

		// 调用接口返回错误
		if (accessToken.getErrcode() != null) {
			throw new RuntimeException("通过code获取accessToken，返回结果为错误" + accessToken);
		}

		log.debug(accessToken.toString());

		// 网页授权的作用域为snsapi_base，则本步骤中获取到网页授权access_token的同时，也获取到了openid，snsapi_base式的网页授权流程即到此为止
		return accessToken;

	}
}
