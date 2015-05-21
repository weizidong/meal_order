"use strict";

define([ 'app' ], function(app) {

	app.service('localStorageService', [ function() {

		this.put = function(key, value) {
			localStorage.setItem(key, JSON.stringify(value));
		};

		this.get = function(key) {
			JSON.parse(localStorage.getItem(key));
		};

	} ]);

});
