define([ 'app', 'services/common/ajaxService','services/api/healthrecord/dateFormatService' ], function(app) {

	app.register.service('chartService', [ 'ajaxService','dateFormatService', function(ajaxService,dateFormatService) {

		var me = this;
		//加载测量数据（血压，运动，体重）图表概览数据
		me.getWholeChart = function(memberId,type,success,error){
			//发送ajax异步请求数据
			ajaxService.postJson('/healthcenter/chart/get_whole_chart',{
				data:{
					memberId:memberId,
					type:type
				},
				success:success,
				error:error
			});		
		};
		
		
		//加载血压测量图表数据
		me.getBPChartData = function(memberId,type,startTime,success,error){
			//发送ajax异步请求数据
			ajaxService.postJson('/healthcenter/chart/get_bp_chart_data',{
				data:{
					memberId:memberId,
					type:type,
					startTime:startTime
				},
				success:success,
				error:error
			});	
		};
		
		//加载运动测量图表数据
		me.getStepChartData = function(memberId,type,startTime,success,error){
			//发送ajax请求
			ajaxService.postJson('/healthcenter/chart/get_step_chart_data',{
				data:{
					memberId:memberId,
					type:type,
					startTime:startTime
				},
				success:success,
				error:error
			});
		};
		
		//加载体重测量图表数据
		me.getWeightChartData = function(memberId,type,startTime,success,error){
			//发送ajax请求
			ajaxService.postJson('/healthcenter/chart/get_weight_chart_data',{
				data:{
					memberId:memberId,
					type:type,
					startTime:startTime
				},
				success:success,
				error:error
			});	
		};
		
		//将数据进行解析并补全缺省数据(天)
		me.dataAnalysis = function(data,num,mold,longs){
			data = data || [];
			var list = [];
			
			for(var j=0;j<num;j++){
				//获取传入的时间的前num天的年月日
				var time = dateFormatService.getNowOldDate(num-j-1,longs);
				var flag = true;
				for(var i=0;i<data.length;i++){
					if(time==getDates(data[i].measurementDate,10)){
						data[i].measurementDate=time+' 00:00';
						list.push(data[i]);
						flag = false;
						break;
						}	
					}
				if(flag){
					if(mold=='bprecord'){
					var record = {
						diastolicPressure: 0,
						heartRate: 0,
						measurementDate:time+" 00:00",
						systolicPressure: 0
					   }
					}else if(mold=='step'){
						var record = {
								steps:0,
								measurementDate:time+" 00:00",
							   }
					}else if(mold=='weight'){
						var record = {
								weight:0,
								measurementDate:time+" 00:00",
							   }
					}
						list.push(record);
					}
				}
			
			return list;
		};
		//将数据进行解析并补全缺省数据(月)
		me.dataAnalysisByYear = function(data,num,mold,longs){
			data = data || [];
			var list = [];
//			console.log(longs);
			for(var j=0;j<num;j++){
				//获取当前时间前num月的年月日
				var time = dateFormatService.getNowOldMoth(num-j,longs);
				var flag = true;
				for(var i=0;i<data.length;i++){
					
					if(time==getDates(data[i].measurementDate,7)){
						data[i].measurementDate=time+' 00:00';
						list.push(data[i]);
						flag = false;
						break;
						}	
					}
				if(flag){
					
					if(mold=='bprecord'){
						var record = {
							diastolicPressure: 0,
							heartRate: 0,
							measurementDate:time+" 00:00",
							systolicPressure: 0
						   }
						}else if(mold=='step'){
							var record = {
									steps:0,
									measurementDate:time+" 00:00",
								   }
						}else if(mold=='weight'){
							var record = {
									weight:0,
									measurementDate:time+" 00:00",
								   }
						}
						list.push(record);
					}
				}
			return list;
		};
		//截取数据中的年月日
		function getDates(data,n){
			return data.substring(0,n);
		}

	}]);

});