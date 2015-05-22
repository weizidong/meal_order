"use strict";

//alert(new Date().getTime() - _startTs);
define([ 'angularAMD', 'app', 'css!app.css', 'directive/scrollerbox/scrollerbox'], function(angularAMD, app) {

	app.controller("app.controller", [ '$rootScope', '$scope', '$urlRouter', 'cfpLoadingBar', 'blockUI', function($rootScope, $scope, $urlRouter, cfpLoadingBar, blockUI) {

//		alert(new Date().getTime() - _startTs);
		
		$rootScope.$on('$locationChangeSuccess', function(e) {
//			log('$locationChangeSuccess');
//			setTimeout(function(){
				blockUI.stop();
				cfpLoadingBar.complete();
//			}, 300);

		});
		$rootScope.$on('$locationChangeStart', function(e) {
//			console.log('$locationChangeStart');
			cfpLoadingBar.start();
			blockUI.start();

		});

		$urlRouter.listen();
		
	} ]);

//	log('angularAMD.bootstrap');
	angularAMD.bootstrap(app);
//	 angular.bootstrap(document, ['mainModule']);

});