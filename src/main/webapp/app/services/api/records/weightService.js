define([ 'app', 'services/common/ajaxService' ], function(app) {

	app.register.service('weightService', [ 'ajaxService', function(ajaxService) {

		var we = this;
		
		//加载体重历史记录
		we.getWeightHistory = function(memberId,beginMeasurementTs,recordCount,success,error) {
			ajaxService.postJson('/healthcenter/weight_record/get_history', {
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
		we.deleteWeightHistory = function (recordIds,success,error) {
			log("传入service的recordId："+recordIds);
			
			ajaxService.postJson('/healthcenter/weight_record/delete_by_ids', {
				data:{
					ids:recordIds
				},
				success : success,
				error : error
			});
		};
		
	}]);
});