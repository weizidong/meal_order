define([ 'app', 'css!directive/dialog/dialog' ], function(app) {
	//dialog服务
	app.register.service('dialogService', function() {
		var opts = null;
		var me = this;
		//回调
		me.onChange = function(callback) {
			me.callback = callback;
		};
		//初始化
		me.dialog = function(_opts) {
			opts = _opts;
			me.callback(opts);
		};
		//alert
		me.alert = function(opts) {
			opts.type = 'alert';
			opts.cancel = false;
			opts.confirm = opts.confirm || function() {
			};
//			console.log(opts.confirm);
			me.dialog(opts);
		};
		
		//调用方式 **不需要用到的不设置即可
//		dialogService.alert({
//			msg:'....', 显示的消息
//			confirm:function(){ 确定按钮执行的操作
//				......
//			}
//		});
		
		//confirm
		me.confirm = function(opts) {
			opts.type = 'alert';
			me.dialog(opts);
		};
		
		//调用方式
//		dialogService.confirm({
//			msg:'....', 显示的消息
//			confirm:function(){ 确定按钮执行的操作
//				......
//			}
//		});
		//input
		me.input = function(opts) {
			opts.type = 'input';
			me.dialog(opts);
		};
		//调用方式
//		dialogService.input({
//			name:'....', 显示的名称
//			input:'....', 输入框内容
//			unit:'....', 单位
//			confirm:function(){ 确定按钮执行的操作
//				......
//			}
//		});
		//radio
		me.radio = function(opts) {
			opts.type = 'radio';
			me.dialog(opts);
		};
		//调用方式
//		dialogService.radio({
//			options:'....', 所有选项
//			checked:'....', 选中项
//			unit:'....', 单位
//			textField:'.....', 显示字段名
//			valueField:'....', 属性字段名
//			confirm:function(){ 确定按钮执行的操作
//				......
//			}
//		});
		//checkbox
		me.checkbox = function(opts) {
			opts.type = 'checkbox';
			me.dialog(opts);
		};
		//调用方式
//		dialogService.checkbox({
//			options:'....', 所有选项（选项中需有checked字段，true为选中，false为不选中）
//			unit:'....', 单位
//			textField:'.....', 显示字段名
//			valueField:'....', 属性字段名
//			confirm:function(){ 确定按钮执行的操作
//				......
//			}
//		});
		
		// picture
		me.picture = function(opts) {
			opts.type = 'picture';
			me.dialog(opts);
		};
		
		// image
//		me.image = function(opts){
//			opts.type = 'image';
//			me.dialog(opts);
//		}
		//调用方式
//		dialogService.image({
//			msgTop:'...',图片上边的显示信息
//			url:'...', 图片URL地址
//			msgBottom:'...',图片下边显示信息
//			show : false   是否显示按钮，false表示不显示，显示下边提示信息
//		});
	});
	
	//dialog指令
	app.register.directive('dialog', function dialog() {
		
		//默认值
		var defOpts = {
			textField : 'name',
			valueField : 'id',
			cancel : true
		};
		//dialog控制器
		function controller($scope, dialogService) {
			$scope.disabled = false;
			//回调
			dialogService.onChange(function(opts) {
				opts = _.extend({}, defOpts, opts);
				$scope.opts = opts;
				$scope.disabled = true;
			});
			
			//模态框关闭按钮
			$scope.close = function($event) {
				if ($event) {
					$event.preventDefault();
					$event.stopPropagation();
				}
				if ($scope.opts.close) {
					$scope.opts.close();
				}
				$scope.opts = null;
//				setTimeout(function() {
//					$scope.disabled = false;
//				}, 10000);
			};
			
			//模态框确定按钮
			$scope.confirm = function($event) {
				$event.preventDefault();
				if ($scope.opts.type == 'input' && !$scope.opts.input&&!$scope.opts.hasNull) {
					$scope.close();
					return;
				}
				$scope.opts.confirm();
				$scope.close();
			};
			//输入框验证
			$scope.validate = function() {
				var validate = $scope.opts.validate;
				var input = $scope.opts.input.trim();
				if (validate == 'number') {//验证数字
					var reg = /^\d{1,3}$/ ;
					if(!reg.test(input)){   
						$scope.opts.input = input.substring(0,3);
					}
					return;
				}
				if (validate == 'text') {//验证字符
					if (input.length > 20) {
						$scope.opts.input = input.substring(0,20);
					}
					return;
				}
				if (validate == 'name') {//验证姓名
					if (input.length > 10) {
						$scope.opts.input = input.substring(0,10);
					}
					return;
				}
				if (validate == 'mark') {//验证备注
					if (input.length > 30) {
						$scope.opts.input = input.substring(0,30);
					}
					return;
				}
				if (validate == 'insurcode') {//验证社保号
//					console.log(input);
//					input = input.replace(/[\s\W]/,'');
					var reg = /[\D]+/gi;
					if (reg.test(input)) {
						$scope.opts.input = input.replace(reg,'');
					}
					if (input.length > 10) {
						$scope.opts.input = input.substring(0,10);
					}
					return;
				}
				if (validate) {
					validate(input);
					return;
				}
			};
			//多选点击事件
			$scope.onCheck = function(index) {
				if ($scope.opts.onCheck) {
					$scope.opts.onCheck(index);
				}else {
					$scope.opts.options[index].checked = !$scope.opts.options[index].checked;
				}
			};
			//单选单击事件
			$scope.onChoose = function(index) {
				if ($scope.opts.onChoose) {
					$scope.opts.onChoose(index);
				}else {
					$scope.opts.checked = $scope.opts.options[index][$scope.opts.valueField];
				}
			};
		}
		return {
			restrict : 'E',
			replace : true,
			controller :['$scope', 'dialogService', controller],
			templateUrl : 'app/directive/dialog/dialog.html?v=' + ___appVersion
//			link : function(scope, element, attrs, controller) {
//				scope.$watch('disabled', function(newVal,oldVal) {
//					if (scope.disabled) {
//						$(document).find('input').attr('disabled','disabled');
//						$(element).find('input').removeAttr("disabled");
//					}else {
//						$(document).find('input').removeAttr("disabled");
//					}
//				});
//			}
		}
	});
});
