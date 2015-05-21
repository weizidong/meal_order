package com.hbin.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.lifesense.framework.common.util.StringUtil;

/**
 * HttpClient连接池访问URL资源工具类
 * 
 * @author lujuzhi
 * 
 */
@SuppressWarnings("deprecation")
public class HttpClientManager {

	private static Logger log = Logger.getLogger(HttpClientManager.class);

	private static HttpParams httpParams = null;

	private static PoolingClientConnectionManager connectionManager = null;

	static {

		if (null == connectionManager) {

			System.out.println("创建 HttpClient connectionManager");

			// 设置组件参数, HTTP协议的版本,1.1/1.0/0.9
			httpParams = new BasicHttpParams();
			HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setUserAgent(httpParams, "HttpComponents/1.1");
			HttpProtocolParams.setUseExpectContinue(httpParams, true);

			// 设置连接超时时间
			int REQUEST_TIMEOUT = 300 * 1000; // 设置请求超时5分钟
			int SO_TIMEOUT = 300 * 1000; // 设置等待数据超时时间5分钟
			httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, REQUEST_TIMEOUT);
			httpParams.setParameter(CoreConnectionPNames.SO_TIMEOUT, SO_TIMEOUT);

			// 设置访问协议
			SchemeRegistry schreg = new SchemeRegistry();
			schreg.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
			schreg.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));

			// 多连接的线程安全的管理器
			connectionManager = new PoolingClientConnectionManager(schreg);
			connectionManager.setDefaultMaxPerRoute(20); // 每个主机的最大并行链接数
			connectionManager.setMaxTotal(100); // 客户端总并行链接最大数
		}

	}

	/**
	 * 
	 * @return
	 */
	public static DefaultHttpClient getHttpClient() {
		return new DefaultHttpClient(connectionManager, httpParams);
	}

	/**
	 * 
	 * @MethodName: get
	 * @Description: Get请求,不带参数
	 * @param url
	 *            资源访问路径
	 * @return String 响应内容
	 */
	public static String get(String url) {

		// Get请求
		HttpGet httpGet = new HttpGet(url);

		String body = null;

		try {

			// 设置参数
			httpGet.setURI(new URI(httpGet.getURI().toString()));

			// 发送请求
			HttpResponse httpResponse = getHttpClient().execute(httpGet);

			// 打印响应状态
			System.out.println(httpResponse.getStatusLine());

			// 获取返回数据
			HttpEntity entity = httpResponse.getEntity();

			body = EntityUtils.toString(entity, Consts.UTF_8);
			// System.out.println("返回JSON body：" + body);
			if (entity != null) {
				EntityUtils.consume(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			httpGet.abort();
			httpGet.releaseConnection();
		}
		return body;
	}

	public static String get(String url, Map<String, String> queryParams) {
		if (queryParams != null && queryParams.keySet().size() > 0) {

			String temp = "";

			if (url.indexOf("?") > 0) {
				temp = "&";
			} else {
				temp = "?";
			}

			for (String paramName : queryParams.keySet()) {
				url += temp + paramName + "=" + queryParams.get(paramName);
				temp = "&";
			}
		}

		System.out.println("=============url=" + url);
		String result = get(url);
		return result;
	}

	/**
	 * 
	 * @MethodName: post
	 * @Description: Post请求,不带参数
	 * @param url
	 *            资源访问路径
	 * @return String 响应内容
	 */
	public static String post(String url, String contentType) {

		// Post请求
		HttpPost httpPost = new HttpPost(url);

		String body = null;
		try {

			httpPost.setHeader("Content-Type", contentType);

			// 发送请求
			HttpResponse httpResponse = getHttpClient().execute(httpPost);

			// 打印响应状态
			System.out.println(httpResponse.getStatusLine());

			// 获取返回数据
			HttpEntity entity = httpResponse.getEntity();

			body = EntityUtils.toString(entity, Consts.UTF_8);
			// System.out.println("返回JSON body：" + body);
			if (entity != null) {
				EntityUtils.consume(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			httpPost.abort();
			httpPost.releaseConnection();
		}
		return body;
	}

	/**
	 * 
	 * @MethodName: post
	 * @Description: Post请求,带参数
	 * @param url
	 *            资源访问路径
	 * @param nvps
	 *            参数集合
	 * @return String 响应内容
	 */
	public static String post(String url, List<NameValuePair> nvps) {

		log.info("**************post url = " + url);

		// Post请求
		HttpPost httpPost = new HttpPost(url);

		String body = null;

		try {

			// 设置参数
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

			// 发送请求
			HttpResponse httpResponse = getHttpClient().execute(httpPost);

			// 打印响应状态
			System.out.println(httpResponse.getStatusLine());

			// 获取返回数据
			HttpEntity entity = httpResponse.getEntity();

			body = EntityUtils.toString(entity, Consts.UTF_8);
			// System.out.println("返回JSON body：" + body);
			if (entity != null) {
				EntityUtils.consume(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			httpPost.abort();
			httpPost.releaseConnection();
		}
		return body;
	}

	public static String post(String url, Map<String, String> paramsMap) {

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		log.info("**********************************");
		for (final String paramName : paramsMap.keySet()) {

			final String paramValue = paramsMap.get(paramName);

			log.info(paramName + ":" + paramValue);

			NameValuePair param = new NameValuePair() {

				@Override
				public String getValue() {
					return paramValue;
				}

				@Override
				public String getName() {
					return paramName;
				}
			};

			nvps.add(param);
		}
		log.info("**********************************");

		return post(url, nvps);
	}

	/**
	 * 
	 * @MethodName: post
	 * @Description: Post请求,带参数
	 * @param url
	 *            资源访问路径
	 * @param params
	 *            参数
	 * @return String 响应内容
	 */
	public static String postJson(String url, String params) {

		// Post请求
		HttpPost httpPost = new HttpPost(url);

		String body = null;

		try {

			StringEntity se = new StringEntity(params, Consts.UTF_8);

			se.setContentType("application/json");

			// 设置参数
			httpPost.setEntity(se);

			// 发送请求
			HttpResponse httpResponse = getHttpClient().execute(httpPost);

			// 打印响应状态
			System.out.println(httpResponse.getStatusLine());

			// 获取返回数据
			HttpEntity entity = httpResponse.getEntity();

			body = EntityUtils.toString(entity, Consts.UTF_8);
			// System.out.println("返回JSON body：" + body);
			if (entity != null) {
				EntityUtils.consume(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			httpPost.abort();
			httpPost.releaseConnection();
		}
		return body;
	}

	public static String postMultipartFormData(String url, Map<String, ContentBody> contentBodyMap) {

		// Post请求
		HttpPost httpPost = new HttpPost(url);

		httpPost.setHeader("Connection", "keep-alive");

		String body = null;

		try {

			MultipartEntity multiEntity = new MultipartEntity();

			for (String name : contentBodyMap.keySet()) {
				multiEntity.addPart(name, contentBodyMap.get(name));
			}

			// 设置参数
			httpPost.setEntity(multiEntity);

			// 发送请求
			HttpResponse httpResponse = getHttpClient().execute(httpPost);

			// 打印响应状态
			System.out.println(httpResponse.getStatusLine());

			// 获取返回数据
			HttpEntity entity = httpResponse.getEntity();

			body = EntityUtils.toString(entity, Consts.UTF_8);
			// System.out.println("返回JSON body：" + body);
			if (entity != null) {
				EntityUtils.consume(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			httpPost.abort();
			httpPost.releaseConnection();
		}
		return body;
	}

	/**
	 * 
	 * @MethodName: post Multipart/form-data
	 * @Description: Post请求
	 * @param url
	 *            资源访问路径
	 * @param filePath
	 *            文件路径
	 * @return String 响应内容
	 */
	public static String postMultipartFormData(String url, String name, String filePath) {

		// Post请求
		HttpPost httpPost = new HttpPost(url);

		httpPost.setHeader("Connection", "keep-alive");

		String body = null;

		try {

			MultipartEntity multiEntity = new MultipartEntity();

			multiEntity.addPart(name, new FileBody(new File(filePath)));

			// 设置参数
			httpPost.setEntity(multiEntity);

			// 发送请求
			HttpResponse httpResponse = getHttpClient().execute(httpPost);

			// 打印响应状态
			System.out.println(httpResponse.getStatusLine());

			// 获取返回数据
			HttpEntity entity = httpResponse.getEntity();

			body = EntityUtils.toString(entity, Consts.UTF_8);
			// System.out.println("返回JSON body：" + body);
			if (entity != null) {
				EntityUtils.consume(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			httpPost.abort();
			httpPost.releaseConnection();
		}
		return body;
	}

	/**
	 * 
	 * @param url
	 * @param fileName
	 * @param fileUrl
	 * @param paramsMap
	 * @return
	 */
	public static String postMultipartFormData(String url, String name, String filePath, Map<String, String> paramsMap) {

		System.out.println("postMultipartFormData url = " + url);

		// Post请求
		HttpPost httpPost = new HttpPost(url);

		httpPost.setHeader("Connection", "keep-alive");

		httpPost.addHeader("Charsert", "UTF-8");

		String body = null;

		try {

			MultipartEntity multiEntity = new MultipartEntity();

			if (!StringUtil.isEmpty(filePath)) {
				multiEntity.addPart(name, new FileBody(new File(filePath)));
			}

			System.out.println("******************************");
			for (final String paramName : paramsMap.keySet()) {
				String paramValue = paramsMap.get(paramName);
				System.out.println(paramName + ":" + paramValue);
				multiEntity.addPart(paramName, new StringBody(paramValue, Charset.forName("UTF-8")));
			}
			System.out.println("******************************");

			// 设置参数
			httpPost.setEntity(multiEntity);

			// 发送请求
			HttpResponse httpResponse = getHttpClient().execute(httpPost);

			// 打印响应状态
			System.out.println(httpResponse.getStatusLine());

			// 获取返回数据
			HttpEntity entity = httpResponse.getEntity();

			body = EntityUtils.toString(entity, Consts.UTF_8);
			// System.out.println("返回JSON body：" + body);
			if (entity != null) {
				EntityUtils.consume(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			httpPost.abort();
			httpPost.releaseConnection();
		}
		return body;
	}

	/**
	 * 
	 * @MethodName: post
	 * @Description: Post请求,带参数,带传入post请求对象
	 * @param httpPost
	 *            post请求对象
	 * @param nvps
	 *            参数集合
	 * @return String 响应内容
	 */
	public static String post(HttpPost httpPost, List<NameValuePair> nvps) {

		String body = null;

		try {

			// 设置参数
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

			// 发送请求
			HttpResponse httpResponse = getHttpClient().execute(httpPost);

			// 打印响应状态
			System.out.println(httpResponse.getStatusLine());

			// 获取返回数据
			HttpEntity entity = httpResponse.getEntity();

			body = EntityUtils.toString(entity, Consts.UTF_8);
			// System.out.println("返回JSON body：" + body);
			if (entity != null) {
				EntityUtils.consume(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			httpPost.abort();
			httpPost.releaseConnection();
		}
		return body;
	}

	public static String downloadFile(String fileUrl) {

		// Get请求
		HttpGet httpGet = new HttpGet(fileUrl);

		String filePath = "";

		try {

			// 发送请求
			HttpResponse httpResponse = getHttpClient().execute(httpGet);

			// 打印响应状态
			System.out.println(httpResponse.getStatusLine());

			if (200 == httpResponse.getStatusLine().getStatusCode()) {

				// 获取返回数据
				HttpEntity entity = httpResponse.getEntity();

				filePath = System.getProperty("jetty.home") + "/webapps/userfiles/weixin/";

				Header header = entity.getContentType();

				String fileName = "";

				if ("image/jpeg".equals(header.getValue())) {
					fileName += Long.toString(System.currentTimeMillis()) + "_" + Long.toString(Math.abs(System.nanoTime()));
					filePath += fileName + ".jpg";
				} else {

				}

				File file = new File(filePath);

				FileOutputStream outputStream = new FileOutputStream(file);

				InputStream inputStream = entity.getContent();

				byte buff[] = new byte[4096];
				int counts = 0;
				while ((counts = inputStream.read(buff)) != -1) {
					outputStream.write(buff, 0, counts);

				}
				outputStream.flush();
				outputStream.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			httpGet.abort();
			httpGet.releaseConnection();
		}
		return filePath;
	}

	public static void main(String[] args) {
		// String body = postMultipartFormData(
		// "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=_PLXRnqQUOUuohxy3fQgoPHUb2fIlKe4BhC9EJ1wWcZ3qLlQDm3y8LVrLPBKlHwhOClgCB8MbH17-Wvf8s40GFKK1Ov1nUJDNFPgfSV1Kn4VQyrlSNJwOwvDgEcnqAI5c_05PVETDMO4RmgIV6UlqA&type=image",
		// "E:/QQp.jpg");
		// System.out.println(body);

		downloadFile("http://mmbiz.qpic.cn/mmbiz/WBK8CRia8S32EKJvr9KTwkIr8Mic7RsjwBBIapMubPDuf1e7ugNb23vQiak5wYq3DLyatbAfTBOd473kr9usia43ww/0");
	}
}
