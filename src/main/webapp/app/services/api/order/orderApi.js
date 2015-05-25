define([ 'app', 'services/api/common/ajaxApi' ], function(app) {

	app.register.service('orderApi', [ 'ajaxApi', function(ajaxApi) {

		this.getMealOrder = function(opts){
			ajaxApi.postJson('order/get_meal_order',opts);
		};
		
		this.getAccountOrdeItems = function(opts){
			ajaxApi.postJson('order/get_account_orde_items',opts);
		};
		
		// 点餐
		this.orderMeal = function(opts){
			ajaxApi.postJson('order/order_meal',opts);
		};
		
		// 删除
		this.deleteOrderMeal = function(opts){
			ajaxApi.postJson('order/delete_order_meal',opts);
		};
	} ]);
});