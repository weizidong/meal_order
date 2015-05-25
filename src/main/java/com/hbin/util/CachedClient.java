package com.hbin.util;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class CachedClient {

	private static MemCachedClient mcc = null;

	private static CachedClient cachedClient = null;

	private CachedClient() {

		mcc = new MemCachedClient();

		// step1:创建一个Socked连接池实例
		SockIOPool pool = SockIOPool.getInstance();

		// 获取缓存服务器列表，当使用分布式缓存时，可以指定多个缓存服务器。
		String[] servers = CachedClientConf.CACHED_SERVERS.split(",");

		// 获取服务器权重
		String[] cachedWeights = CachedClientConf.CACHED_WEIGHTS.split(",");

		Integer[] weights = new Integer[cachedWeights.length];

		for (int i = 0; i < cachedWeights.length; i++) {
			weights[i] = Integer.parseInt(cachedWeights[i]);
		}

		// step2:向连接池设置服务器和权重
		pool.setServers(servers);
		pool.setWeights(weights);

		// set some TCP settings
		// disable nagle
		// set the read timeout to 3 secs
		// and don't set a connect timeout
		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(0);

		// initialize the connection pool
		pool.initialize();

	}

	/**
	 * 
	 * @MethodName: getInstance
	 * @Description: 初始化参数配置(单例模式)
	 * @return MemCachedUtil
	 */
	public synchronized static CachedClient getInstance() {
		if (cachedClient == null) {
			cachedClient = new CachedClient();
		}
		return cachedClient;
	}

	public static MemCachedClient getMemCachedClient() {
		getInstance();
		return mcc;
	}

	/**
	 * 获取缓存键值
	 * 
	 * @param key
	 *            键
	 */
	public static Object get(String key) {
		return getMemCachedClient().get(key);
	}

	/**
	 * 添加缓存键值
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public static void add(String key, Object value) {
		getMemCachedClient().add(key, value);
	}

	/**
	 * 添加缓存键值
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @param expire
	 *            过期时间（单位：秒）
	 */
	public static void add(String key, Object value, int expire) {
		getMemCachedClient().add(key, value, expire);
	}

	/**
	 * 添加缓存键值
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public static void put(String key, Object value) {
		getMemCachedClient().set(key, value);
	}

	/**
	 * 添加缓存键值
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @param expire
	 *            过期时间（单位：秒）
	 */
	public static void put(String key, Object value, int expire) {
		getMemCachedClient().set(key, value, expire);
	}

	/**
	 * 删除缓存键值
	 * 
	 * @param key
	 *            键
	 */
	public static void remove(String key) {
		getMemCachedClient().delete(key);
	}

	/**
	 * 判断是否缓存中存在key
	 * 
	 * @param key
	 *            键
	 * @return boolean true-存在，false-不存在
	 */
	public static boolean keyExists(String key) {
		return getMemCachedClient().keyExists(key);
	}

	public static void main(String[] args) {
		// put("ttttttttttt", "1111111", 10000);
		// remove("ttttttttttt");
		System.out.println(get("ttttttttttt"));
	}
}
