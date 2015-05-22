"use strict";

define([ 'app', 'services/api/account/accountApi', 'services/api/order/orderApi', 'css!style/account/index' ], function(app) {

	app.register.controller('controller.account.index', [ '$scope', '$rootScope', '$location', 'accountApi', 'orderApi', function($scope, $rootScope, $location, accountApi, orderApi) {

		var accountId = $location.search().accountId;

		// 是否已经开始点餐
		orderApi.getMealOrder({
			success : function(mealOrder) {
				$scope.mealOrder = mealOrder;
				
				if(!mealOrder){
					$scope.accountOrders = [];
					return;
				}

				// 获取帐号的订单
				getAccountOrdeItems();
			}
		});

		// 获取帐号的订单
		function getAccountOrdeItems() {
			orderApi.getAccountOrdeItems({
				data : {
					accountId : accountId,
					orderId : $scope.mealOrder?$scope.mealOrder.id:''
				},
				success : function(accountOrders) {
					$scope.accountOrders = accountOrders;
				}
			});
		}

		// 点餐
		$scope.orderMeal = function() {
			if(!$scope.mealOrder || $scope.mealOrder.status != 0){
				alert("亲，还没有开始点餐。")
				return;
			}
			$location.path('/meal/list').search({
				accountId : accountId,
				mealOrderId : $scope.mealOrder.id
			});
		};
	} ]);
});
