"use strict";

define([ 'app','Map' ], function(app,Map) {

	app.service('mapService', [ function() {

		var map = new Map();

		this.put = function(key, value) {
			map.put(key,value);
		};

		this.get = function(key) {
			return map.get(key);
		};

		this.containsKey = function(key) {
			return map.containsKey(key);
		};

		this.remove = function(key) {
			delete map.remove(key);
		};

	} ]);

});
