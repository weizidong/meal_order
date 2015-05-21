define([ 'app', 'Map', 'services/common/ajaxService' ], function(app, Map) {

	app.register.service('memberService', [ 'ajaxService', '$rootScope','$location', function(ajaxService, $rootScope,$location) {

		var me = this;

		var memberLastRecordMap = new Map();

		var memberList;

		var memberMap = new Map();

		// 加载成员列表信息
		me.getMemberList = function(opts) {

			if (!(memberList === undefined) && !opts.refresh) {
				opts.success(memberList);
				return;
			}

			var oSuccess = opts.success;
			opts.success = function(data) {
				memberList = data;
				_.each(memberList, function(member) {
					memberMap.put(member.id, member);
				});
				oSuccess(data);
			};
			ajaxService.postJson('/healthcenter/member/get_member_list', opts);
		};

		// 获取成员最新一条血压记录
		me.getLastRecord = function(memberId, opts) {

			if (memberLastRecordMap.containsKey(memberId) && !opts.refresh) {
				opts.success(memberLastRecordMap.get(memberId));
				return;
			}
			var oSuccess = opts.success;
			opts.success = function(data) {
				memberLastRecordMap.put(memberId, data);
				oSuccess(data);
			};

			ajaxService.postJson('/healthcenter/member/get_last_record/' + memberId, opts);
		};

		// 加载成员基本信息
		me.getMember = function(memberId, opts) {
			if (memberMap.containsKey(memberId) && !opts.refresh) {
				opts.success(memberMap.get(memberId));
				return;
			}
			var oSuccess = opts.success;
			opts.success = function(data) {
				memberMap.put(memberId, data);
				oSuccess(data);
			};
			ajaxService.postJson('/healthcenter/member/get_member/' + memberId, opts);
		};

		 // 加载共享成员人数
		me.getShareMemberCount = function(memberId, success, error) {
			ajaxService.postJson('/healthcenter/member/get_share_member_count/' + memberId, {
				success : success,
				error : error
			});
		};

		// 加载分享成员列表信息
		me.getShareMemberList = function(memberId, success, error) {
			ajaxService.postJson('/healthcenter/member/get_share_member/' + memberId, {
				success : success,
				error : error
			});
		};
		// 删除共享成员
		me.deleteShareMember = function(accountId, memberId, success, error) {
			ajaxService.postJson('/healthcenter/member/delete_share_member', {
				data : {
					accountId : accountId,
					memberId : memberId
				},
				success : success,
				error : error,
				loadingType : 'block'
			});
		};
		// 添加成员
		me.addMember = function(member, opts) {
			opts.data = member;
			var oSuccess = opts.success;
			opts.success = function(member) {
				if (memberList) {
					memberList.push(member);
				}
				memberMap.put(member.id, member);
				oSuccess(member);
			};
			ajaxService.postJson('/healthcenter/member/add_member', opts);
		};
		// 删除成员
		me.deleteMember = function(memberId, opts) {
			var oSuccess = opts.success;
			opts.success = function(data) {
				memberMap.remove(memberId);
				if (memberList) {
					_.each(memberList, function(member, i, list) {
						if (!member) {
							return;
						}
						if (member.id == memberId) {
							list.splice(i, 1);
						}
					});
				}
				oSuccess(data);
			};

			ajaxService.postJson('/healthcenter/member/delete_member/' + memberId, opts);
		};
		// 更新成员信息
		me.updateMember = function(member, opts) {
			opts.data = member;
			var oSuccess = opts.success;
			opts.success = function(returnMember) {
//				console.log(returnMember);
				memberMap.put(returnMember.id, returnMember);
				if (memberList) {
					_.each(memberList, function(member, i) {
						if (member.id == returnMember.id) {
							memberList.splice(i, 1, returnMember);
						}
					});
				}
				oSuccess(member);
			};
			ajaxService.postJson('/healthcenter/member/update_member', opts);
		};
		// 上传成员头像图片
		me.uploadHeadImg = function(memberId, serverId, opts) {
			opts.data = {
				memberId : memberId,
				serverId : serverId
			};
			// $scope.member.headimgurl = data;

			var oSuccess = opts.success;
			opts.success = function(data) {
				if (memberId) {
					var oldMember = memberMap.get(memberId);
					oldMember.headimgurl = data;
					if (memberList) {
						_.each(memberList, function(member, i) {
							if (member.id == oldMember.id) {
								member.headimgurl = data;
							}
						});
					}
				}
				oSuccess(data);
			};

			ajaxService.postJson('/healthcenter/member/upload_head_img', opts);
		};
		// 验证成员信息
		me.validateMember = function(member) {
			if (member.name == null || member.name.trim().length < 1 || member.name.trim().length > 10) {
				return "成员姓名必须为1~10个字符！";
			}
			return true;
		};

		// 生成临时二维码
		me.generateQrcode = function(memberId, success, error) {
			ajaxService.postJson('/healthcenter/member/generate_qrcode', {
				data : {
					memberId : memberId
				},
				success : success,
				error : error
			});
		};

		// 获取二维码
		me.getQrcode = function(cacheKey, success, error) {
			ajaxService.postJson('/healthcenter/member/get_qrcode', {
				data : {
					cacheKey : cacheKey
				},
				success : success,
				error : error
			});
		};

		// 测量记录认领
		me.matchingUser = function(matchingUser, success, error) {
			ajaxService.postJson('/healthcenter/member/matching_user', {
				data : matchingUser,
				success : success,
				error : error,
				loadingType : 'block'
			});
		};
		
		// 判定该测量记录是否已被认领
		me.hasMatchingUser = function(recordId, success, error) {
//			console.log(recordId);
			ajaxService.postJson('/healthcenter/member/has_matching_user/'+recordId, {
				success : success,
				error : error,
				loadingType : 'block'
			});
		};

		// ---------------- 成员未读消息-------------------------

		var memberUnreadMap = new Map();
		
		// 设置未读消息状态
		me.setUnread = function(memberId, hasUnread) {
			memberUnreadMap.put(memberId, hasUnread);

			$rootScope.$broadcast('memberUnreadChange', {
				memberId : memberId,
				hasUnread : hasUnread
			});
		};

		// 获取未读消息状态
		// me.hasUnread = function(memberId, fn) {
		// if(memberUnreadMap.containsKey(memberId)){
		// fn(unread);
		// }else{
		// me.getUnread(memberId, function(data) {
		// unread = (data > 0);
		// me.setUnread(memberId, unread);
		// fn(unread);
		// });
		// }
		// };

		// me.onChangeUnread = function(){
		// };

		// AJAX获取未读消息状态
		me.hasUnread = function(memberId, opts) {

			if (memberUnreadMap.containsKey(memberId) && !opts.refresh) {
				opts.success(memberUnreadMap.get(memberId));
				return;
			}
			var oSuccess = opts.success;
			opts.success = function(data) {
				var unread = data > 0;
				memberUnreadMap.put(memberId, unread);
				oSuccess(unread);
			};

			ajaxService.postJson('/healthcenter/member/getUnread/' + memberId, opts);
		};
		
		$rootScope.$on('onWsMessage',function(e,msg){
			
			// 只处理 发送消息
			if (msg.cmd != 'send_chatmsg') {
				return;
			}

			var wsMsg;
			// 如果是回复，是其他端发送的消息（不是从这发出的消息）
			if (msg.ack) {
				var data = msg.data;
				if (data.result != "success") {
					return;
				}
				wsMsg = data.msg;
			} else {
				// 服务器主动推过来的消息
				wsMsg = msg.data;
			}

			var fromUserRole = wsMsg.fromUserRole;
			
			//只处理发过来的消息
			if(fromUserRole != 1){
				return;
			}
			
			var memberId = wsMsg.toUser;
			
			// 当前页面在对话中时不处理
			if ($location.path() == '/chat/index') {
				var urlParams = $location.search();
				// 是否添加
				if(urlParams.member_id == memberId){
					return;
				}
			}

			// 获取url参数
			
			
			me.setUnread(memberId,true);

		});

	} ]);
});