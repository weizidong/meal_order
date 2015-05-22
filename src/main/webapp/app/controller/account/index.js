"use strict";

define([ 'app', 'services/api/account/accountService', 'css!style/account/index' ], function(app) {

	app.register.controller('controller.account.index', [ '$scope', '$rootScope', 'accountService', '$location', function($scope, $rootScope, accountService, $location) {

		alert($location.search().openId);
	} ]);
});
