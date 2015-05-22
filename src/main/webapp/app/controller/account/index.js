"use strict";

define([ 'app', 'services/api/account/accountApi', 'css!style/account/index' ], function(app) {

	app.register.controller('controller.account.index', [ '$scope', '$rootScope', 'accountApi', '$location', function($scope, $rootScope, accountApi, $location) {

		alert($location.search().openId);
	} ]);
});
