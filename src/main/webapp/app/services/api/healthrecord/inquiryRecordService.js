define([ 'app', 'services/common/ajaxService' ], function(app) {

	app.register.service('inquiryRecordService', [ 'ajaxService', function(ajaxService) {

		var me = this;

		//加载问诊记录列表
		me.getList = function(memberId,recordCount,inquiryDate,success,error){
			ajaxService.postJson('/healthcenter/healthrecord/inquiry_list',{
				data:{
					memberId:memberId,
					recordCount:recordCount,
					inquiryDate:inquiryDate
					},
				success:success,
				error:error

			});
		};
		// 获取问诊记录详细信息
		me.getInquiryRecord = function(inquiryRecordId,success, error) {
			ajaxService.postJson('/healthcenter/healthrecord/inquiry_record/' + inquiryRecordId, {
				success : success,
				error : error,

			});
		};

	} ]);

});