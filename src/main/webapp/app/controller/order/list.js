"use strict";

define([ 'app', 'services/api/meal/mealApi', 'services/api/order/orderApi', 'css!style/order/list','services/api/account/accountApi' ], function(app) {

	app.register.controller('controller.order.list', [ '$scope', '$rootScope', '$location', 'mealApi', 'orderApi','accountApi', function($scope, $rootScope, $location, mealApi, orderApi,accountApi) {
		console.log('controller.order.list');
	} ]);
});
