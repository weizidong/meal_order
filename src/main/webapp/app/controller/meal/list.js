"use strict";

define([ 'app', 'services/api/meal/mealService', 'css!style/meal/list' ], function(app) {

	app.register.controller('controller.meal.list', [ '$scope', '$rootScope', 'mealService', '$location', function($scope, $rootScope, accountService, $location) {

		alert($location.search().openId);
	} ]);
});
