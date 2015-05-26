define([ 'app', 'services/api/common/ajaxApi' ], function(app) {

	app.register.service('accountApi', [ 'ajaxApi', function(ajaxApi) {
		this.setRemarkName = function(opts) {
			ajaxApi.postJson('account/set_remark_name',opts);
		};
	} ]);
});