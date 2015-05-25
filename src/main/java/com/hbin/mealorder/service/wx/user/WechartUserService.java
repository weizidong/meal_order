package com.hbin.mealorder.service.wx.user;

import java.io.IOException;
import java.text.MessageFormat;

import net.sf.json.JSONObject;

import com.hbin.mealorder.service.wx.WeixinAPI;
import com.hbin.mealorder.service.wx.token.TokenService;
import com.hbin.mealorder.service.wx.user.dto.WeixinUserInfo;
import com.hbin.util.HttpClientManager;
import com.lifesense.framework.common.jackson.JacksonUtils;
import com.lifesense.framework.common.log.LoggerAdapter;
import com.lifesense.framework.common.log.LoggerAdapterFacory;

public class WechartUserService {

	LoggerAdapter log = LoggerAdapterFacory.getLogger(WechartUserService.class);

	/**
	 * 获取微信用户信息
	 * 
	 * @param serviceNo
	 * @param openId
	 * @param accessToken
	 * @return
	 */
	public WeixinUserInfo getUserInfo(String openId, Integer count) {

		if (count == null) {
			count = 1;
		}

		String accessToken = TokenService.getAccessToken();

		String url = WeixinAPI.GET_USER_INFO_URL;

		String body = HttpClientManager.get(MessageFormat.format(url, accessToken, openId));

		StringBuilder sb = new StringBuilder();

		sb.append("===========get user info start ====================");
		sb.append("accessToken = " + accessToken);
		sb.append("openId = " + openId);
		sb.append("retult = " + body);
		sb.append("===========get user info end   ====================");

		log.debug(sb.toString());

		JSONObject json = JSONObject.fromObject(body);

		// errcode判断结果代码
		int resultCode = TokenService.checkErrcode(json);

		if (1 == resultCode) {
			// 更新token
			accessToken = TokenService.updateAccessToken();
		}

		if (0 != resultCode && count < 3) { // 获取失败
			log.debug("重试获取微信用户信息。");
			count++;
			return getUserInfo(openId, count);
		}

		WeixinUserInfo weixinUserInfo = null;
		try {
			weixinUserInfo = (WeixinUserInfo) JacksonUtils.toBean(body, WeixinUserInfo.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return weixinUserInfo;

	}
}
