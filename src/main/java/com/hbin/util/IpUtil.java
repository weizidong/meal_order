package com.hbin.util;

import javax.servlet.http.HttpServletRequest;

import com.lifesense.framework.common.util.StringUtil;

/**
 * IP操作工具类
 * 
 * @author lujuzhi
 * 
 */
public class IpUtil {

	/**
	 * 获取客户端IP地址，支持代理服务器
	 * 
	 * @param request
	 * @return
	 */
	public static final String getIpAddress(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");

		String localIP = "127.0.0.1";

		if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		// if (null == ip) {
		// return null;
		// }
		//
		// String regex =
		// "^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])(\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])){3}$";
		//
		// // IP格式验证（针对开发时本地调用）
		// if (!Pattern.matches(regex, ip)) {
		// return null;
		// }

		return ip;

	}

	/**
	 * 获取服务器主机hostname
	 * 
	 * @param request
	 * @return
	 */
	public static final String getServerHostname(HttpServletRequest request) {

		StringBuffer url = request.getRequestURL();
		String contextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();

		// System.err.println("contextUrl=" + contextUrl);

		return contextUrl;
	}

	/**
	 * 获取服务器主机hostname（url后不带“/”）
	 * 
	 * @param request
	 * @return
	 */
	public static final String getServerHostnameNoEnd(HttpServletRequest request) {

		StringBuffer url = request.getRequestURL();
		String contextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();

		return contextUrl;
	}

	/**
	 * 获取服务器主机hostname（url后不带“/”）
	 * 
	 * @param request
	 * @return
	 */
	public static final String getServerHostnameRoot(HttpServletRequest request) {

		StringBuffer url = request.getRequestURL();
		String servletPath = request.getServletPath();
		String path = "";
		if (!StringUtil.isEmpty(servletPath)) {
			path = request.getServletPath();
		}
		path = path + request.getPathInfo();
		String contextUrl = url.delete(url.length() - path.length(), url.length()).toString();

		return contextUrl;
	}

}
