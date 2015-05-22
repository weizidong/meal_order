define([ 'app', 'services/api/common/ajaxApi' ], function(app) {

	app.register.service('orderApi', [ 'ajaxApi', function(ajaxApi) {

		this.getMealOrder = function(opts){
			ajaxApi.postJson('order/get_meal_order',opts);
		};
		
		this.getAccountOrdeItems = function(opts){
			ajaxApi.postJson('order/get_account_orde_items',opts);
		};
	} ]);
});