define([ 'app' ], function(app) {
	// dialog指令
	app.directive('scrollerBox', function() {
		return {
			restrict : 'EA',
			replace : true,
			transclude: true,
			templateUrl : 'app/directive/scrollerbox/scrollerbox.html?v=' + ___appVersion
		}
	});
	
	app.service('scrollerboxService', [ function() {

		this.setScroller = function(scope, scrollName, callback) {
			setTimeout(function() {
				if (!scope || !scope[scrollName]) {
					setScroller(scope, scrollName, callback);
				}

				callback(scope[scrollName]);
			}, 0);
		}
	} ]);
});
