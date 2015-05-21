define([ 'app', 'services/common/ajaxService' ], function(app) {

	app.register.service('bpRecordService', [ 'ajaxService', function(ajaxService) {

		var bpHistory = this;
		
		//加载血压历史记录
		bpHistory.getBpHistory = function(memberId,beginMeasurementTs,recordCount,success,error) {
			ajaxService.postJson('/healthcenter/bp_record/get_member_history', {
				data:{
					memberId:memberId,
					beginMeasurementTs:beginMeasurementTs,
					recordCount:recordCount
				},
				success : success,
				error : error
			});
		};
		
		//删除历史记录
		bpHistory.deleteBpHistory = function (recordIds,success,error) {
			log("传入service的recordId："+recordIds);
			
			ajaxService.postJson('/healthcenter/bp_record/delete_by_ids', {
				data:{
					ids:recordIds
				},
				success : success,
				error : error
			});
		};
		
	}]);
});