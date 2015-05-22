"use strict";

define([ 'routes'], function( routes) {
	var app = angular.module("mainModule", [ 'ui.router', 'ngSanitize', 'ngAnimate', 'ngTouch', 'chieffancypants.loadingBar', 'blockUI', 'angular-iscroll' ]);

	// http请求配置
	app.config([ '$httpProvider', function($httpProvider) {
		$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
		$httpProvider.defaults.withCredentials = true;
	} ]);

	// 顶部加载进度条
	app.config([ 'cfpLoadingBarProvider', function(cfpLoadingBarProvider) {

		// 小圆转动
		cfpLoadingBarProvider.includeSpinner = false;

		// 进度条
		cfpLoadingBarProvider.includeBar = true;

		// 延迟显示ms
		cfpLoadingBarProvider.latencyThreshold = 300;
	} ]);

	// 加载中遮罩层
	app.config(['blockUIConfig',function(blockUIConfig) {

		// 显示文本
		blockUIConfig.message = "加载中...";
		// 延迟显示
		blockUIConfig.delay = 300;
		// 关闭自动
		blockUIConfig.autoBlock = false;

	}]);

	// 路由设置
	app.config(['$stateProvider',function($stateProvider) {

		// key:state,value:route
		var stateRouteMap = {};

		// 注册所有route
		function registerRoutes(routes) {
			// 迭代routes，以route.state当key，route当value
			for ( var i in routes) {
				var route = routes[i];
				stateRouteMap[route.state] = route;
			}

			for ( var i in routes) {
				var route = routes[i];
				registerRoute(route);
			}
		}

		// 注册一个route
		function registerRoute(route) {
//			log('-----------------------');
//			log(route);

			var templateUrl = generateTemplateUrl(route.templateUrl);
//			log(templateUrl);
			var resolve = generateResolve(route.state);

			$stateProvider.state(route.state, {
				url : route.url,
				templateUrl : templateUrl,
				resolve : resolve
			});
		}

		function generateFullUrl(baseUrl, url) {

			if (url.indexOf('?') > 0) {
				url = url.substring(0, url.indexOf('?'));
			}
			return baseUrl + url;
		}

		// 根据url生成html url
		function generateTemplateUrl(url) {

			var templateUrl = generateFullUrl('app/views', url);
			templateUrl = templateUrl + '.html?v=' + ___appVersion;

			return templateUrl;
		}

		// 根据 route 生成 route参数的resolve，用户加载controller
		function generateResolve(state) {

			var load = generateResolveLoadFn(state);

			return {

				load : [ '$q', '$rootScope', '$location','blockUI','cfpLoadingBar', load ]
			};
		}

		function generateResolveLoadFn(state) {

			var loadControllers = [];

			generateLoadControllerByState(loadControllers, state);

//			log('loadControllers = ' + loadControllers);

			return function($q, $rootScope, $location,blockUI,cfpLoadingBar) {
				blockUI.start();
				cfpLoadingBar.start();

				var deferred = $q.defer();

				// require(controllers, function() {
				require(loadControllers, function(loadedController) {

					blockUI.stop();
					cfpLoadingBar.complete();
					$rootScope.$apply(function() {
						deferred.resolve();

					});
				});

				return deferred.promise;
			}
		}

		function generateLoadControllerByState(loadControllers, state) {
			var route = stateRouteMap[state];

			var controllerUrl = generateFullUrl('controller', route.templateUrl);

			loadControllers.push(controllerUrl);

			if (state.indexOf('.') > 0) {
				state = state.substring(0, state.lastIndexOf('.'));
				generateLoadControllerByState(loadControllers, state);
			}
		}

		registerRoutes(routes);

	}]);

	app.config([ '$locationProvider', function($locationProvider) {

//		log($locationProvider);

		// use the HTML5 History API
//		$locationProvider.html5Mode(true);
	} ]);
	
	app.config([ '$urlRouterProvider', function($urlRouterProvider) {

		// Prevent $urlRouter from automatically intercepting URL changes;
		// this allows you to configure custom behavior in between
		// location changes and route synchronization:
		$urlRouterProvider.deferIntercept();

	} ]);

	return app;
});
