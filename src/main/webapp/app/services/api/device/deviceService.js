define([ 'app', 'services/common/ajaxService' ], function(app) {

	app.register.service('deviceService', [ 'ajaxService', function(ajaxService) {

		var device = this;

		//加载管理设备列表
		device.getList = function(success, error) {
			ajaxService.postJson('/healthcenter/device/get_devices', {
				success : success,
				error : error
			});
		};

		//获取管理设备信息
		device.getDeviceMsg = function(deviceId,success, error) {
			ajaxService.postJson('/healthcenter/device/get_device_msg/' + deviceId, {
				success : success,
				error : error
			});
		};
		
		//获取设备使用者信息
		device.getDeviceUser = function(deviceId,success, error) {
			ajaxService.postJson('/healthcenter/device/get_device_user/' + deviceId, {
				success : success,
				error : error
			});
		};
		
		//删除设备（解绑）
		device.unbindDevice = function(deviceId,success, error) {
			ajaxService.postJson('/healthcenter/device/unbind_device/' + deviceId, {
				success : success,
				error : error
			});
		};
		
		//获取家庭成员列表
		device.getMembers = function(success, error) {
			ajaxService.postJson('/healthcenter/device/get_members', {
				success : success,
				error : error
			});
		};
		
		//设置设备使用者
		device.setDeviceUser = function(deviceId,userNo,memberId,success, error) {
			ajaxService.postJson('/healthcenter/device/set_device_user', {
				data : {
					deviceId : deviceId,
					userNo : userNo,
					memberId : memberId
				},
				success : success,
				error : error
			});
		};
		
		// 找回设备
		device.findBackDevice = function(deviceId,bpRecord,success, error) {
			ajaxService.postJson('/healthcenter/device/find_back_device', {
				data : {
					deviceId : deviceId,
					bpRecord : bpRecord
				},
				success : success,
				error : error
			});
		};
		
		// 测试用，设置语言血压计提醒时间
		device.setTimes = function(timeInfo, success, error){
			ajaxService.postJson('/healthcenter/device/setTimes', {
				data : timeInfo,
				success : success,
				error : error
			});
		}
		
		// 绑定设备
		device.bindDevice = function(qrcode, success, error){
			ajaxService.postJson('/healthcenter/device/bind_device', {
				data : {"qrcode":qrcode},
				success : success,
				error : error
			});
		};
		
		// 检查该设备是否还属于当前用户管理
		device.checkDeviceOwner = function(deviceId, success, error){
			ajaxService.postJson('/healthcenter/device/check_device_owner/'+deviceId, {
				success : success,
				error : error
			});
		};
		

	} ]);
});