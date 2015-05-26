"use strict";

define([ 'app', 'services/api/meal/mealApi', 'services/api/order/orderApi', 'css!style/order/list','services/api/account/accountApi','directive/dialog/dialog' ], function(app) {

	app.register.controller('controller.order.list', [ '$scope', '$rootScope', '$location', 'mealApi', 'orderApi','accountApi','dialogService', function($scope, $rootScope, $location, mealApi, orderApi,accountApi,dialogService) {
		orderApi.getMealOrder({
			success:function(data){
				console.log(data);
				$scope.order = data;
			}
		});
		$scope.remark = function(account) {
			dialogService.input({
				hasNull:true,
				validate : 'text',
				input:account.remarkName,
				confirm:function(){
					account.remarkName = $scope.opts.input;
					accountApi.setRemarkName({
						data:{
							accountId:account.id,
							remarkName:account.remarkName
						}
					});
				}
			});
		};
		
	} ]);
});
