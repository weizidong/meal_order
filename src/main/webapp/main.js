require.config({

	baseUrl : "app/",

	// 配置类库路径别名
	paths : {
		// 'angular' : '../js/lib/angular-1.3.10/angular.min',
		'angularAMD' : '../js/lib/angular-plugins/angularAMD',
		'ngload' : '../js/lib/angular-plugins/ngload',
		'css' : '../js/lib/require/css.min',
		"jquery": "../js/lib/jquery/jquery-2.1.3.min",
		'jweixin' : 'http://res.wx.qq.com/open/js/jweixin-1.0.0',
		'Queue' : '../js/lib/util/queue',
		'Map' : '../js/lib/util/map',
		'mobiscroll.datetime' :'../js/lib/jquery/mobiscroll/js/mobiscroll.datetime',
		'mobiscroll.select' :'../js/lib/jquery/mobiscroll/js/mobiscroll.select',
		'mobiscroll.core' :'../js/lib/jquery/mobiscroll/js/mobiscroll.core',
		'mobiscroll.android-ics' :'../js/lib/jquery/mobiscroll/js/mobiscroll.android-ics',
		'mobiscroll.i18n.zh' :'../js/lib/jquery/mobiscroll/js/i18n/mobiscroll.i18n.zh',
		'mobiscroll.android-icsCss' :'../js/lib/jquery/mobiscroll/css/mobiscroll.android-ics',
		'mobiscroll.animationCss' :'../js/lib/jquery/mobiscroll/css/mobiscroll.animation',
		'mobiscroll.coreCss' :'../js/lib/jquery/mobiscroll/css/mobiscroll.core',
		'bootstrap.min' : '../js/lib/bootstrap-3.3.4-dist/js/bootstrap.min'
//			,
//		'iscroll' : '../js/lib/iscroll/iscroll-infinite'
	// 'angular-route' : 'js/lib/angular-1.3.10/angular-route.min',
	// 'angular-sanitize' : 'js/lib/angular-1.3.10/angular-sanitize.min',
	// 'blockUI' :
	// 'js/lib/angular-plugins/angular-block-ui/angular-block-ui.min'
	},
	// 在放弃加载一个脚本之前等待的秒数。设为0禁用等待超时。默认为7秒
	// nodeIdCompat : 0,
	// 配置不支持AMD的js文件
	shim : {
		// 'angularAMD' : [ 'angular' ],
		'ngload' : [ 'angularAMD' ],
		'services/chart/chartData':[ 'jquery' ],
		'services/chart/canvas':[ 'services/chart/chartData' ],
		//日期选择
		'mobiscroll.core' :['jquery'],
		'mobiscroll.i18n.zh' :['mobiscroll.core'],
		'mobiscroll.android-ics' :['mobiscroll.core'],
		'mobiscroll.datetime':['mobiscroll.i18n.zh','mobiscroll.android-ics'],
		'mobiscroll.select':['mobiscroll.i18n.zh','mobiscroll.android-ics'],
		'bootstrap.min':['jquery']
//	,	
//		'iscroll' : {
//			exports : 'IScroll'
//		}
	},
	urlArgs : "v=" + ___appVersion,
	// 启动应用程序
	deps : [ 'app.controller' ]
});
