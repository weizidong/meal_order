define([ 'app', 'services/common/ajaxService' ], function(app) {

	app.register.service('chatService', [ 'ajaxService', function(ajaxService) {

		var me = this;

		// 加载管理设备列表
		me.getHistory = function(opts) {
			ajaxService.postJson('/healthcenter/chat/history', opts);
		};
		
		// 标记为已读
		me.markReaded = function(msgId,opts){
			opts.loadingType = 'bar';
			opts.data = {
				msgId : msgId
			};
			ajaxService.postJson('/healthcenter/chat/mark_readed',opts);
		};
		
		// 删除消息
		me.deleteMsg = function(opts){
			ajaxService.postJson('/healthcenter/chat/delete_record',opts);
		}

		/**
		 * 将 chatbox 的msg对象 转换为 web socket 的msg对象
		 */
		me.toWsMsg = function(chatMsg) {
			var wsMsg = {
				fromUserRole : 2,
				fromUser : chatMsg.senderId,
				toUser : chatMsg.receiverId
			};

			if (chatMsg.content.type == 'text') {
				wsMsg.type = 0;
				wsMsg.content = chatMsg.content.text;
			} else if (chatMsg.content.type == 'img') {
				wsMsg.type = 1;
				wsMsg.localId = chatMsg.content.localId;
				wsMsg.serverId = chatMsg.content.serverId;
			} else if (chatMsg.content.type == 'voice') {
				wsMsg.type = 2;
				wsMsg.localId = chatMsg.content.localId;
				wsMsg.serverId = chatMsg.content.serverId;
				wsMsg.voiceTime = chatMsg.content.voiceTime;
			}
			return wsMsg;
		};

		/**
		 * 将web socket 的msg对象数组 转换为 chatbox 的msg对象数组
		 */
		me.toChatMsgList = function(wsMsgList) {
			var chatMsgList = [];
			for ( var i in wsMsgList) {
				var wsMsg = wsMsgList[i];
				chatMsgList.push(me.toChatMsg(wsMsg));
			}
			return chatMsgList;
		};

		/**
		 * 将web socket 的msg对象 转换为 chatbox 的msg对象
		 */
		me.toChatMsg = function(wsMsg) {
			var chatMsg = {
				id : wsMsg.id,
				time : wsMsg.showTime ? wsMsg.created : null,
				senderId : wsMsg.fromUser,
				receiverId : wsMsg.toUser
			};
			if (wsMsg.type == 0) {
				chatMsg.content = {
					text : wsMsg.content,
					type : 'text'
				};
			} else if (wsMsg.type == 1) {
				chatMsg.content = {
					type : 'img',
					imgurl : wsMsg.resourceUrl,
					localId : wsMsg.localId,
					serverId : wsMsg.serverId
				};
			} else if (wsMsg.type == 2) {
				chatMsg.content = {
					type : 'voice',
					localId : wsMsg.localId,
					serverId : wsMsg.serverId,
					voiceTime : wsMsg.voiceTime
				};
			}
			return chatMsg;
		};

	} ]);
});