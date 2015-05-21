package com.hbin.service.wx.oauth;

/**
 * 微信公众平台api接口常量
 * 
 * @author lujuzhi
 * 
 */
public class WeixinAPI {

	/**
	 * 获取access token的URL，http请求方式: GET
	 */
	public static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";

	/**
	 * 发送客服消息URL
	 */
	public static final String CUSTOMER_SERVICE_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token={0}";

	/**
	 * 第三方发送消息给设备主人的微信端，并最终送达设备的URL
	 */
	public static final String TRANSMSG_URL = "https://api.weixin.qq.com/device/transmsg?access_token={0}";

	/**
	 * 获取设备绑定openid的URL
	 */
	public static final String GET_DEVICE_OPENID_URL = "http://api.weixin.qq.com/device/get_openid?access_token={0}&device_type={1}&device_id={2}";

	/**
	 * 上传媒体文件
	 */
	public static final String UPLOAD_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token={0}&type={1}";
	
	/**
	 * 通过code换取网页授权access_token的URL
	 */
	public static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={0}&secret={1}&code={2}&grant_type=authorization_code";
	
	/**
	 * 刷新access_token，由于access_token拥有较短的有效期，当access_token超时后，
	 * 可以使用refresh_token进行刷新，refresh_token拥有较长的有效期（7天、30天、60天、90天），
	 * 当refresh_token失效的后，需要用户重新授权。 
	 */
	public static final String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid={0}&grant_type=refresh_token&refresh_token={1}";
	
	/**
	 * 获取用户信息(需scope为snsapi_userinfo)
	 */
	public static final String GET_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token={0}&openid={1}&lang=zh_CN";
	
	/**
	 * 创建二维码ticket
	 */
	public static final String CREATE_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token={0}";
	
	/**
	 * 批量创建二维码
	 */
	public static final String CREATE_QRCODE_URL = "https://api.weixin.qq.com/device/create_qrcode?access_token={0}";
	
	/**
	 * 设备授权url
	 */
	public static final String AUTHORIZE_DEVICE_URL = "https://api.weixin.qq.com/device/authorize_device?access_token={0}";
	
	/**
	 * 设备状态查询
	 */
	public static final String GET_DEVICE_STAT= "httpS://api.weixin.qq.com/device/get_stat?access_token={0}&device_id={1}";
	
	/**
	 * 网页授权获取用户信息
	 */
	public static final String AUTHORIZE_USERINFO_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={0}&redirect_uri={1}&response_type=code&scope=snsapi_userinfo&state={2}#wechat_redirect";
	
	/**
	 * 发送模板消息URL
	 */
	public static final String TEMPLATE_SERVICE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token={0}";
	
	/**
	 * 获取微信用户信息
	 */
	public static final String GET_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token={0}&openid={1}&lang=zh_CN";
	
	// 获取生成临时带参数二维码的ticket
	public static final String WX_GET_QRCODE_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token={0}";
	
	// 获取生成临时带参数二维码
	public static final String WX_GET_QRCODE_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket={0}";
	
	// 获得jsapi_ticket
	public static final String GET_JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={0}&type=1";

	// （微信）下载多媒体
	public static final String GET_DOWNLOAD_URL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token={0}&media_id={1}";

	public static final String GET_UPLOAD_URL = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token={0}&type={1}";
	
	/**-----------微信设备接口-----------start**/
	
	// 强制绑定用户和设备
	public static final String COMPEL_BIND_DEVICE_URL = "https://api.weixin.qq.com/device/compel_bind?access_token={0}";
	
	// 强制解绑用户和设备
	public static final String COMPEL_UNBIND_DEVICE_URL = "https://api.weixin.qq.com/device/compel_unbind?access_token={0}";
	
	// 获取绑定的设备id
	public static final String GET_BINDED_DEVCIE = "https://api.weixin.qq.com/device/get_bind_device?access_token={0}&openid={1}";
	
	/**-----------微信设备接口-----------end**/
	
}
