define([ 'app', 'services/common/ajaxService' ], function(app) {

	app.register.service('pedometerService', [ 'ajaxService', function(ajaxService) {

		var we = this;
		
		//加载步数历史记录
		we.getStepHistory = function(memberId,beginMeasurementTs,recordCount,success,error) {
			ajaxService.postJson('/healthcenter/step_record/get_history', {
				data:{
					memberId:memberId,
					beginMeasurementTs:beginMeasurementTs,
					recordCount:recordCount
				},
				success : success,
				error : error
			});
		};
		
		//删除步数历史记录
		we.deleteStepHistory = function (recordIds,success,error) {
			log("传入service的recordId："+recordIds);
			
			ajaxService.postJson('/healthcenter/step_record/delete_by_ids', {
				data:{
					ids:recordIds
						},
				success : success,
				error : error
			});
		};
		
	}]);
});