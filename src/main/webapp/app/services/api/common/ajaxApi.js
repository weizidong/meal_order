"use strict";

define([ 'app' ], function(app) {

	var baseUrl = 'api/';
	
	app.register.service('ajaxService', [ '$http', 'cfpLoadingBar', 'blockUI','$location', function($http, cfpLoadingBar, blockUI,$location) {
		var urlParams = $location.search();
		var openId = urlParams.openId;
		var LoadingType = {
			bar : 'bar',
			block : 'block'
		};

		var defaultOpts = {
			url : null,
			data : null,
			success : null,
			ajaxSuccess : null,
			baseAjaxSuccess : null,
			error : null,
			baseError : null,
//			localTyp : 'params',// url,params,其他不缓存，或loadingType为block也不缓存
//			localSuccess : null,
			formatResponse : true,
			loadingType : LoadingType.block
		// bar:只使用顶部进度条，block:遮罩层加载中
		};

//		function generateLocalStorageKey(opts) {
//			if (opts.localTyp == 'url') {
//				return ___appVersion + localStorage.setItem(opts.url, opts.data);
//			} else if (opts.localTyp == 'params') {
//				return ___appVersion + opts.url + JSON.stringify(opts.data);
//			} else {
//				return null;
//			}
//		}

//		function setLlocalStorageItem(opts, value) {
//			log('setLlocalStorageItem data------');
//			log(value);
//			log('setLlocalStorageItem data------');
//			var key = generateLocalStorageKey(opts);
//			if (key && value) {
//				localStorage.setItem(key, JSON.stringify(value));
//			}
//		}

//		function getLlocalStorageItem(opts) {
//			var key = generateLocalStorageKey(opts);
//			if (key) {
//				var value = localStorage.getItem(key);
//				if (value) {
//					return JSON.parse(value);
//				}
//			}
//		}

		this.postJson = function(url, opts) {

			opts = _.extend({}, defaultOpts, opts);

			opts.url = baseUrl + url;
			// 如果ajaxSuccess为空就将success覆盖ajaxSuccess
			opts.ajaxSuccess = opts.ajaxSuccess || opts.success;
			// 如果localStorageSuccess为空就将success覆盖localStorageSuccess
//			opts.localSuccess = opts.localSuccess || opts.success;

			// 如果loadintType == block 不缓存
//			opts.localTyp = opts.loadingType == LoadingType.block ? null : opts.localTyp;

			// log(JSON.stringify(defaultOpts));

//			var localStorageData = getLlocalStorageItem(opts);

//			if (localStorageData) {
//				log('存在本地缓存');
//				log(localStorageData);
//				// localStorage
//				if (opts.localSuccess) {
//					log('调用localStorageSuccess');
//					opts.localSuccess(localStorageData);
//				}
//
//			} else {
////				log('设置loadingType = block');
////				opts.loadingType = LoadingType.block;
//			}

			// baseAjaxsuccess
			if (!opts.baseAjaxSuccess) {
				opts.baseAjaxSuccess = function(response, status, headers, config) {

					complete();
					// cfpLoadingBar.complete();
//					log('response----');
//					log(response);
//					log('response----');
					var data = null;
					if (opts.formatResponse) {
						if (response) {
							data = response.data
						}
					} else {
						data = response;
					}

					if (response && response.code == 200) {
						// ajaxSuccess
						if (opts.ajaxSuccess) {
//							log('ajaxSuccess:');
							opts.ajaxSuccess(data);
						}
						// localStore.setItem
//						setLlocalStorageItem(opts, data);
						return;
					}
					if (opts.error) {
						opts.error(response);
					}
				}
			}

			// error
			if (!opts.baseError) {
				opts.baseError = function(response) {
					// alert(msg);//TODO 统一处理

					complete();
					// cfpLoadingBar.complete();

//					log('baseError : ' + response);
					if (opts.error) {
						opts.error(response);
					}
				};
			}

			function complete() {

				if (opts.loadingType == LoadingType.block) {
					blockUI.stop();
				}
			}

			if (opts.loadingType == LoadingType.block) {
				blockUI.start();
			}

			$http.post(opts.url, opts.data, {
				ignoreLoadingBar : opts.loadingType != LoadingType.bar,
				headers: { 'openId': openId}
			}).success(opts.baseAjaxSuccess).error(opts.error);

		};
		
		this.set = function(){

//			return ___appVersion + opts.url + JSON.stringify(opts.data);
//					localStorage.setItem(key, JSON.stringify(value));
		};

	} ]);
});
