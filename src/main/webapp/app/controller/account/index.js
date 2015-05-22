"use strict";

define([ 'app', 'services/api/account/accountApi', 'services/api/order/orderApi', 'css!style/account/index' ], function(app) {

	app.register.controller('controller.account.index', [ '$scope', '$rootScope', '$location', 'accountApi', 'orderApi', function($scope, $rootScope, $location, accountApi, orderApi) {

		var accountId = $location.search().accountId;

		$scope.orderMeal = function() {
			$location.path('/meal/list').search({
				accountId : accountId
			});
		};
	} ]);
});
