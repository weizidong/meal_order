"use strict";

define([ 'app', 'services/api/meal/mealApi', 'services/api/order/orderApi', 'css!style/meal/list' ], function(app) {

	app.register.controller('controller.meal.list', [ '$scope', '$rootScope', '$location', 'mealApi', 'orderApi', function($scope, $rootScope, $location, mealApi, orderApi) {

		var urlParams = $location.search();
		var accountId = urlParams.accountId;
		var mealOrderId = urlParams.mealOrderId;

		// alert($location.search().openId);
		mealApi.getAll({
			success : function(data) {
				$scope.mealList = data;
			}
		});

		$scope.selectMeal = function(meal) {
			if (confirm(meal.name + ' ' + meal.price + '元，确定？')) {
				orderApi.orderMeal({
					data : {
						mealId : meal.id,
						accountId : accountId,
						mealOrderId : mealOrderId,
						quantity : 1
					},
					success : function() {

					}
				});
			}
		};
	} ]);
});
