package com.hbin.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 缓存客户端配置文件操作类
 * 
 * @author lujuzhi
 * 
 */
public class CachedClientConf {

	private static Properties prop = null;

	private static CachedClientConf cachedClientConf = null;

	private static final Logger log = LoggerFactory.getLogger(CachedClientConf.class);

	/**
	 * 缓存服务器IP、端口列表
	 */
	public static String CACHED_SERVERS = get("cached_servers");
	
	/**
	 * 缓存服务器权重
	 */
	public static String CACHED_WEIGHTS = get("cached_weights");
	
	/**
	 * 
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description: 构造方法,加载配置文件
	 * </p>
	 */
	private CachedClientConf() {
		prop = new Properties();
		try {
			InputStream in = CachedClientConf.class.getResourceAsStream("cached_client.properties");
			in = CachedClientConf.class.getResourceAsStream("/env-config/cached_client.properties");
			prop.load(in);
		} catch (IOException e) {
			log.error("加载配置信息失败 :{" + e.toString() + "}");
		}
	}

	/**
	 * 初始化参数配置(单例模式)
	 * 
	 * @return
	 */
	public synchronized static CachedClientConf getInstance() {
		if (cachedClientConf == null) {
			cachedClientConf = new CachedClientConf();
		}
		return cachedClientConf;
	}

	/**
	 * 根据key获取配置参数值
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		getInstance();
		return prop.getProperty(key);
	}

	public static void main(String[] args) {
		System.out.println(CACHED_SERVERS);
	}
}
