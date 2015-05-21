package com.hbin.mealorder.service.wx;

import java.io.IOException;
import java.util.Map;

import com.lifesense.framework.common.properties.PropertiesUtil;

public class WechartServiceNoService {

	private static String serviceNo;

	private static String appid;

	private static String secret;

	/**
	 * 初始化微信服务号配置
	 */
	static {
		try {
			Map<String, String> propMap = PropertiesUtil.readPropertiesForMap("/env-config/wechart_service_no.properties");

			serviceNo = propMap.get("service_no");
			appid = propMap.get("appid");
			secret = propMap.get("secret");
		} catch (IOException e) {
			throw new RuntimeException("初始化微信服务号配置发生异常。", e);
		}
	}

	public static String getServiceNo() {
		return serviceNo;
	}

	public static String getAppid() {
		return appid;
	}

	public static String getSecret() {
		return secret;
	}

}
