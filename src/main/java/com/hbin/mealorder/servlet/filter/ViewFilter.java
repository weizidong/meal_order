package com.hbin.mealorder.servlet.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hbin.mealorder.service.wx.WechartServiceNoService;
import com.hbin.mealorder.service.wx.oauth.WechartOauthService;
import com.hbin.mealorder.service.wx.oauth.dto.AccessToken;
import com.hbin.util.IpUtil;
import com.lifesense.framework.common.log.LoggerAdapter;
import com.lifesense.framework.common.log.LoggerAdapterFacory;
import com.lifesense.framework.common.util.StringUtil;

/**
 * Servlet Filter implementation class WxSessionFilter
 */
public class ViewFilter implements Filter {

	private static LoggerAdapter log = LoggerAdapterFacory.getLogger(ViewFilter.class);

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		try {
			log.debug("请求地址：" + getRequestUrl(httpRequest));

			String redirect = getRequestUrl(httpRequest);

			long startTs = System.currentTimeMillis();

			if (redirect.endsWith("/view/")) {

				log.debug("/view/跳转=" + redirect);

				request.getRequestDispatcher("/index.html").forward(request, response);

				log.debug("/view/跳转成功。" + (System.currentTimeMillis() - startTs));

				return;
			}

			String code = request.getParameter("code");

			// debug 模式
			if (DebugConfig.isDebug()) {

				redirect(httpRequest, httpResponse, DebugConfig.getDebugOpenId());
				return;
			}

			if (!StringUtil.isEmpty(code)) {// 授权成功，回调,

				log.debug("微信授权成功");

				AccessToken accessToken = WechartOauthService.getAccessTokenByCode(WechartServiceNoService.getAppid(), WechartServiceNoService.getSecret(), code);

				// 如果openId未创建，调用微信api获取昵称、头像 并创建帐号

				log.debug("授权成功-获取微信用户信息成功。" + (System.currentTimeMillis() - startTs));

				startTs = System.currentTimeMillis();

				log.debug("微信授权成功-保存会话信息");

				log.debug("微信授权成功-保存会话信息成功。" + (System.currentTimeMillis() - startTs));

				startTs = System.currentTimeMillis();

				log.debug("微信授权成功-redirect");

				// MyBatisUtil.commitSession();

				// wxJSApi不支持h5，将 /view/member/list 重定向为 /view/#/member/list
				redirect(httpRequest, httpResponse, accessToken.getOpenid());

				log.debug("微信授权成功-redirect成功。" + (System.currentTimeMillis() - startTs));

				return;

			}

			// 获取用户信息

			log.debug("获取用户信息【缓存】");

			startTs = System.currentTimeMillis();

			log.debug("获取用户信息【缓存】成功。" + (System.currentTimeMillis() - startTs));

			// 如果缓存key值为空,获取缓存UserInfo为空,重定向到 微信 授权

			log.debug("重定向到微信授权");

			// 授权
			authorize(httpRequest, httpResponse);
			return;

		} catch (Exception e) {
			e.printStackTrace();
			failure(httpRequest, httpResponse);
		} finally {
			// MyBatisUtil.closeSession();
		}
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

	@Override
	public void destroy() {

	}

	private void redirect(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String openId) throws IOException, ServletException {

		String redirect = getRequestUrl(httpRequest);
		redirect = redirect.replaceFirst("/view", "/view/#");
		if (httpRequest.getQueryString() != null) {
			redirect += "&";
		} else {
			redirect += "?";
		}
		redirect += "openId=" + openId;
		log.debug("redirect=" + redirect);
		// TODO:这里会耗时？？
		httpResponse.sendRedirect(redirect);
	}

	/**
	 * 获取请求全路径
	 * 
	 * @param request
	 * @return
	 */
	private String getRequestUrl(HttpServletRequest request) {
		String hostname = IpUtil.getServerHostname(request);
		String requestUrl = hostname + request.getRequestURI().substring(1);
		String queryString = request.getQueryString();
		if (!StringUtil.isEmpty(queryString)) {
			requestUrl += "?" + queryString;
		}
		return requestUrl;
	}

	/**
	 * 授权
	 * 
	 * @param appid
	 * @param serviceNo
	 * @param httpRequest
	 * @param httpResponse
	 * @throws IOException
	 */
	private void authorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
		String redirectUri = URLEncoder.encode(getRequestUrl(httpRequest), "utf-8");

		log.debug("回调地址:" + redirectUri);

		String getCodeUrl = WechartOauthService.getAuthorizeUrl(WechartServiceNoService.getAppid(), redirectUri, "state");

		httpResponse.sendRedirect(getCodeUrl);
	}

	private void failure(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException, ServletException {

		String requestUrl = getRequestUrl(httpRequest);

		log.debug("请求地址：" + requestUrl);

		httpRequest.getRequestDispatcher("/failure.html").forward(httpRequest, httpResponse);

	}

}
