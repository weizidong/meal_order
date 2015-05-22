"use strict";

define([ 'app', 'services/api/meal/mealApi', 'css!style/meal/list' ], function(app) {

	app.register.controller('controller.meal.list', [ '$scope', '$rootScope', 'mealApi', '$location', function($scope, $rootScope, mealApi, $location) {

		// alert($location.search().openId);
		mealApi.getAll({
			success : function(data) {
				$scope.mealList = data;
			}
		});

		$scope.selectMeal = function(meal) {
			if (confirm(meal.name + ' ' + meal.price + '元，确定？')) {

			}
		};
	} ]);
});
