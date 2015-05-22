define([ 'app', 'services/api/common/ajaxService' ], function(app) {

	app.register.service('mealService', [ 'ajaxService', function(ajaxService) {

		/**
		 * 获取所有
		 */
		this.getAll = function(){
			ajaxService.postJson(opts);
		}
	} ]);
});