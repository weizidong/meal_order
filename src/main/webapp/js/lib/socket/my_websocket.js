(function() {
	

	var MyWebSocket = window.MyWebSocket = function(opts) {

		if (!window.WebSocket) {
			return null;
		}
		var me = this;

		me.opts = _.defaults(opts, me.def);

		me.init();
	};

	MyWebSocket.prototype = {
		ws : null,
		onOpenFns : [],
		onCloseFns : [],
		onMessageFns : [],
//		logined:false,// 登录是否成功
		fromUser:"",
		// 默认参数
		def : {
			url : '',
			onOpen : function(){},
			onClose : function(){},
			onMessage : function(){}
		}
	};
	
	/**
	 * 初始化MyWebSocket的事件函数
	 */
	MyWebSocket.prototype.init = function() {
		var me = this;

	};

	/**
	 * 链接ReconnectingWebSocket
	 */
	MyWebSocket.prototype.connect = function() {
		var me = this;
		var ws = me.ws = new ReconnectingWebSocket(me.opts.url);
		ws.debug = true;
		ws.reconnectInterval = 30000;

		/**
		 * 打开事件
		 */
		ws.onopen = me.opts.onOpen

		/**
		 * 收到推送回来的消息事件
		 */
		ws.onmessage = function(event){
			var msg = event.data;
			msg = JSON.parse(msg);
			me.opts.onMessage(msg);
		};

		/**
		 * 关闭事件
		 */
		ws.onclose = me.opts.onClose
	};

	/**
	 * 关闭socket
	 */
	MyWebSocket.prototype.close = function() {
		var me = this;
		me.ws.close();
	};

	/**
	 * 推送消息
	 */
	MyWebSocket.prototype.send = function(msg) {
		var me = this;

		if (typeof (msg) == 'object') {
			msg = JSON.stringify(msg);
		}
		try {
			me.ws.send(msg);
		} catch (err) {
			alert('web socket send error!');
		}
	};

})();
