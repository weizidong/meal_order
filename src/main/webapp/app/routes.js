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

	route('accountIndex', '/member/list','/account/index');
	

	route('mealList', '/meal/list');
	
	return routes;
});