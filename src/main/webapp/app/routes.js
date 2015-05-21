"use strict";

define([], function() {

	var routes = [];

	function route(state, url, templateUrl) {
		templateUrl = templateUrl || url;
		routes.push({
			state : state,
			url : '^' +url,
			templateUrl : templateUrl
		});
	}

	// 成员列表
	// route({
	// state : 'memberList',
	// url : '/member/memberList'
	// });

	route('clientIndex', '/client/index');
	
	return routes;
});