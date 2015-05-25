package com.hbin.mealorder.service.wx.token;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.hbin.mealorder.service.wx.WechartServiceNoService;
import com.hbin.mealorder.service.wx.WeixinAPI;
import com.hbin.mealorder.service.wx.token.dto.TokenDto;
import com.hbin.util.CachedClient;
import com.hbin.util.HttpClientManager;
import com.hbin.util.StringUtil;

/**
 * 微信服务号token service 从缓存中获取微信的tocken
 * 
 * @author lujuzhi
 * 
 */
public class TokenService {

	private static Logger log = LogManager.getLogger(TokenService.class);

	private static final String ACCESS_TOKEN = "access_token";

	private static final String EXPIRES_IN = "expires_in";

	/**
	 * 判断errcode结果代码
	 * 
	 * @param errcode
	 * @return int 0-成功，1-accessToken超时或失效后重发3次，2-未知错误
	 */
	public static int checkErrcode(JSONObject json) {

		if (json.containsKey("base_resp")) {
			json = json.getJSONObject("base_resp");
		}

		// errcode判断结果代码

		// 兼容其他返回值

		int errcode = 0;

		if (json.containsKey("errcode")) {
			errcode = json.getInt("errcode");
		} else if (json.containsKey("ret")) {
			errcode = json.getInt("ret");
		}

		if (0 == errcode || 43004 == errcode) { // 已发送成功，说明43004表示需要接收者关注（即用户已取消关注）
			return 0;
		} else if (42001 == errcode || 40001 == errcode || 40002 == errcode || 40014 == errcode) { // accessToken超时或失效后重发3次
			return 1;
		} else { // 未知错误
			return 2;
		}
	}

	/**
	 * 更新access_token
	 */
	public static String updateAccessToken() {

		try {

			Map<String, Object> tockenMap = getAccessTokenByWeixin(1);

			if (tockenMap == null) {
				return null;
			}

			String cacheKey = getCachKey();

			String accessToken = StringUtil.toString(tockenMap.get(ACCESS_TOKEN));

			Long expiresIn = StringUtil.toLong(tockenMap.get(EXPIRES_IN));

			TokenDto tokenDto = new TokenDto();

			tokenDto.setAccessToken(accessToken);
			tokenDto.setExpiresIn(expiresIn);
			tokenDto.setTimestamp(System.currentTimeMillis());

			CachedClient.put(cacheKey, tokenDto);

			return accessToken;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 根据服务号获取访问token
	 * 
	 * @return access_token 访问令牌
	 */
	public static String getAccessToken() {

		String cacheKey = getCachKey();

		Object tokenObject = CachedClient.get(cacheKey);

		if (tokenObject == null) {
			return updateAccessToken();
		}

		TokenDto tokenDto = (TokenDto) tokenObject;

		Long timestamp = tokenDto.getTimestamp();

		Long diff = (System.currentTimeMillis() - timestamp) / 1000;

		if (diff > tokenDto.getExpiresIn() - 2000) {
			return updateAccessToken();
		}

		String accessToken = tokenDto.getAccessToken();

		return accessToken;
	}

	/**
	 * 向微信获取（4-queryTimes）次access_token
	 * 
	 * @param queryTimes
	 * @return
	 */
	private static Map<String, Object> getAccessTokenByWeixin(Integer queryTimes) {

		// 如果查询3次都失败就不查询了
		if (queryTimes > 3) {
			return null;
		}

		String appid = WechartServiceNoService.getAppid();

		String appSecret = WechartServiceNoService.getSecret();

		// 调用接口获取access_token
		String body = HttpClientManager.get(MessageFormat.format(WeixinAPI.TOKEN_URL, appid, appSecret));

		log.debug("refreshAccessToken body = " + body);

		if (StringUtil.isBlank(body)) {
			queryTimes++;
			return getAccessTokenByWeixin(queryTimes);
		}

		JSONObject json = JSONObject.fromObject(body);

		if (json.containsKey("errcode")) { // 获取失败
			queryTimes++;
			return getAccessTokenByWeixin(queryTimes);
		}

		String accessToken = json.getString(ACCESS_TOKEN);
		Long expiresIn = json.getLong(EXPIRES_IN);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put(ACCESS_TOKEN, accessToken);
		resultMap.put(EXPIRES_IN, expiresIn);

		return resultMap;
	}

	/**
	 * 
	 * @return
	 */
	private static String getCachKey() {
		return WechartServiceNoService.getServiceNo() + "_" + ACCESS_TOKEN;
	}

}
