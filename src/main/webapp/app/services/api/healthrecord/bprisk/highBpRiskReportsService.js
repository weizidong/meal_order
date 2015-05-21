define([ 'app', 'services/common/ajaxService' ], function(app) {

	app.register.service('highBpRiskReportsService', [ 'ajaxService', function(ajaxService) {

		var me = this;
		//查询风险报告列表
		me.getList = function(memberId,success,error){
			ajaxService.postJson('/healthcenter/high_bp_risk_reports/reports_list/'+memberId,{
				success:success,
				error:error
			});
		};
		//根据id查询报告
		me.getReport = function(highBpRiskReportsId,success,error){
			ajaxService.postJson('/healthcenter/high_bp_risk_reports/reports_get/'+highBpRiskReportsId,{
				success:success,
				error:error
			});
		};
		//保存一张报告
//		me.saveReport = function(highBpRiskReports,success,error){
//			ajaxService.postJson('/healthcenter/healthrecord/reports_save',{
//				data:highBpRiskReports,
//				success:success,
//				error:error
//			});
//		};
		//保存风险评估选项中间表记录
		me.saveChoosed = function(highBpRiskChoosed,success,error){
			ajaxService.postJson('/healthcenter/high_bp_risk_reports/choosed_save',{
				data:highBpRiskChoosed,
				success:success,
				error:error
			});
		};
		
		//保存风险评估选项中间表记录
		me.deleteChoosed = function(highBpRiskChoosed,success,error){
			ajaxService.postJson('/healthcenter/high_bp_risk_reports/choosed_delete',{
				data:highBpRiskChoosed,
				success:success,
				error:error
			});
		};
		
		//根据reportId查找所属的高血压风险评估选项
		me.getChoosedList = function(reportId,success,error){
			ajaxService.postJson('/healthcenter/healthrecord/options_list/'+reportId,{
				success:success,
				error:error
			});
		};
		
	} ]);

});