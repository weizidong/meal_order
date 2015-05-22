package com.hbin.mealorder.servlet.filter;

import java.io.IOException;
import java.util.Map;

import com.lifesense.framework.common.properties.PropertiesUtil;

public class DebugConfig {

    private static boolean isDebug = false;

    private static String debugOpenId = "";

    /**
     * 初始化微信会话配置
     */
    static {
        try {
            Map<String, String> propMap = PropertiesUtil.readPropertiesForMap("env-config/debug_config.properties");

            isDebug = Boolean.valueOf(propMap.get("is_debug"));
            debugOpenId = propMap.get("debug_open_id");
        } catch (IOException e) {
            throw new RuntimeException("初始化微信会话配置。", e);
        }
    }

    /**
     * 是否debug模式，是的话不经过微信授权，直接生成wxSession
     * 
     * @return
     */
    public static boolean isDebug() {
        return isDebug;
    }

    /**
     * 获取debug模式的openId
     * 
     * @return
     */
    public static String getDebugOpenId() {
        return debugOpenId;
    }
}
