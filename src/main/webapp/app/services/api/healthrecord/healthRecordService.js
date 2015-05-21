define([ 'app', 'services/common/ajaxService' ], function(app) {

	app.register.service('healthRecordService', [ 'ajaxService', function(ajaxService) {

		var me = this;
		//加载健康档案信息
		me.getHealthRecord = function(memberId,success, error) {
			ajaxService.postJson('/healthcenter/healthrecord/get_health_record/'+memberId, {
				success : success,
				error : error
			});
		};
		//加载选项
		me.chooseOptions = function(type,memberId,success, error) {
			ajaxService.postJson('/healthcenter/healthrecord/get_health_record_options', {
				data:{
					type:type,
					memberId:memberId
				},
				success : success,
				error : error
			});
		};
		//保存选项
		me.saveOptions = function(memberId,type,options,success, error) {
			ajaxService.postJson('/healthcenter/healthrecord/save_health_record_options', {
				data:{
					memberId:memberId,
					type:type,
					options:options
				},
				success : success,
				error : error
			});
		};
		//上传健康档案图片
		me.uploadHealthRecordPic = function(opts) {
			ajaxService.postJson('/healthcenter/healthrecord/upload_healthRecord_picture', opts);
		};
		//获取健康档案图片列表
		me.getHealthRecordPics = function(opts) {
			ajaxService.postJson('/healthcenter/healthrecord/get_healthRecord_piclist', opts);
		};
		//获取健康档案图片列表
		me.getHealthRecordPicList = function(memberId,pictureType,success, error) {
			ajaxService.postJson('/healthcenter/healthrecord/get_healthRecord_picture_list', {
				data:{
					memberId:memberId,
					pictureType:pictureType,
				},
				success : success,
				error : error
			});
		};
		//删除健康档案图片
		me.deleteHealthRecordPic = function(healthRecordPicture,success, error) {
			ajaxService.postJson('/healthcenter/healthrecord/delete_healthRecord_picture', {
				data:healthRecordPicture,
				success : success,
				error : error
			});
		};
		//批量删除健康档案图片
		me.deleteHealthRecordPicture = function(opts) {
			ajaxService.postJson('/healthcenter/healthrecord/delete_healthRecord_picture_list',opts);
		};
		//更新健康档案图片
		me.updateHealthRecordPic = function(healthRecordPicture,success, error) {
			ajaxService.postJson('/healthcenter/healthrecord/update_healthRecord_picture', {
				data:healthRecordPicture,
				success : success,
				error : error
			});
		};
		//批量更新健康档案图片
		me.updateHealthRecordPicture = function(opts) {
			ajaxService.postJson('/healthcenter/healthrecord/update_healthRecord_picture_list',opts);
		}
	} ]);
});