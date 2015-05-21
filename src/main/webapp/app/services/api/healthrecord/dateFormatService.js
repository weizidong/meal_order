define([ 'app'], function(app) {

	app.register.service('dateFormatService',function() {
		var me = this;
	
		
		 Date.prototype.format=function(fmt) {        
	            var o = {        
	            "M+" : this.getMonth()+1, //月份        
	            "d+" : this.getDate(), //日        
	            "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时        
	            "H+" : this.getHours(), //小时        
	            "m+" : this.getMinutes(), //分        
	            "s+" : this.getSeconds(), //秒        
	            "q+" : Math.floor((this.getMonth()+3)/3), //季度        
	            "S" : this.getMilliseconds() //毫秒        
	            };        
	            var week = {        
	            "0" : "\u65e5",        
	            "1" : "\u4e00",        
	            "2" : "\u4e8c",        
	            "3" : "\u4e09",        
	            "4" : "\u56db",        


	            "5" : "\u4e94",        
	            "6" : "\u516d"       
	            };        
	            if(/(y+)/.test(fmt)){        
	                fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));        
	            }        
	            if(/(E+)/.test(fmt)){        
	                fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);        
	            }        
	            for(var k in o){        
	                if(new RegExp("("+ k +")").test(fmt)){        
	                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));        
	                }        
	            }        
	            return fmt;        
	        }
		 function theWeek(longs) {
			    var totalDays = 0;
			    now = new Date(longs);
			    years = now.getYear()
			    if (years < 1000)
			        years += 1900
			    var days = new Array(12);
			    days[0] = 31;
			    days[2] = 31;
			    days[3] = 30;
			    days[4] = 31;
			    days[5] = 30;
			    days[6] = 31;
			    days[7] = 31;
			    days[8] = 30;
			    days[9] = 31;
			    days[10] = 30;
			    days[11] = 31;
			     
			    //判断是否为闰年，针对2月的天数进行计算
			    if (Math.round(now.getYear() / 4) == now.getYear() / 4) {
			        days[1] = 29
			    } else {
			        days[1] = 28
			    }
			 
			    if (now.getMonth() == 0) {
			        totalDays = totalDays + now.getDate();
			    } else {
			        var curMonth = now.getMonth();
			        for (var count = 1; count <= curMonth; count++) {
			            totalDays = totalDays + days[count - 1];
			        }
			        totalDays = totalDays + now.getDate();
			    }
			    //得到第几周
			    var week = Math.round(totalDays / 7);
			    return week;
			}
		 
			//将long类型转换为时间将年月日提取出来
			me.getDateTime=function(dateTime,format){
				var n = new Date();
				n.setTime(dateTime);
				var d = n.format(format);
				//获取年月日
				return d;
			};
			
			//获取当前时间的前n天的年月日
			me.getNowOldDate =function(n,longs){
				var longTime = longs-24*60*60*1000*n;
				var n = new Date(longTime);			
				return n.format('yyyy-MM-dd');		
			};
			me.getNowOldMoth = function(n,longs){
				var times = new Date(longs);
				var year = times.getFullYear();
			    var month = times.getMonth()+2-n;
			    if(month<=0){
			    	year--;
			    	month=month+12;
			    }
			    return year+'-'+cover(month);
			};
			
			//获取传入的时间是周几
			me.getStateWeek = function(longs){
				var date  = new Date(longs);
				var num = date.getDay();
				var dateLong = date.getTime()+(6-num)*24*60*60*1000;
				if(dateLong>new Date().getTime()){
					return num+1;
				}
				return 7;
			};
			//获取今天本月的第几天
			me.getStateMonthDay  = function(longs){
				var date = new Date(longs);
				var num = date.getDate();
				date.setHours(23, 59, 59, 59);
				
				return num;
			};
			//获取当前的月份
			me.getStateMonth = function(longs){
				var date = new Date(longs);
				var num = date.getMonth();
				return num+1;
			};
			
			//获取7天前的时间,n为1或者-1
			me.getUpWeek = function(longs,n){
				var date = new Date(longs);
				var num = date.getDay();
				date.setHours(23,59, 59, 999);
				if(n==1){
					return date.getTime()-(num+1)*24*60*60*1000;
				}else{
					var endLongs = date.getTime()+(num+1)*24*60*60*1000;
					if(endLongs>new Date().getTime()){
						return new Date().getTime();
					}else{
						return endLongs;
					}
				}
			};
			
			//判断传入的时间
			//获取一周开始的日期和结束的日期
			me.getStateDayWeek = function(longs){
				var date = new Date(longs);
				var num = date.getDay();
				var stateLong = new Date(date.getTime()-num*24*60*60*1000);
				var endLong = new Date(date.getTime()+(6-num)*24*60*60*1000);
				var stateMonth = stateLong.getMonth()+1;
				var stateDay = stateLong.getDate();
				var endMonth = endLong.getMonth()+1;
				var endDay = endLong.getDate();
				return stateMonth+'月'+stateDay+'日~'+endMonth+'月'+endDay+'日';
			};
			//当传入的数字小于10补零
			function cover(num){
				if(num<10){
					return '0'+num;
				}
				return num;
			}
			//获取月份
			me.getStateMonths = function(longs){
				var date = new Date(longs);
				var year = date.getFullYear();
				var num = date.getMonth();
				return year+'年'+cover(num+1)+'月';
			};
			
			//获取与当前时间星期差
			me.getDifferenceWeek = function(longs){	
				//console.log("当前为第"+theWeek(new Date().getTime()));
				//console.log("上周为"+theWeek(longs));
				return theWeek(new Date().getTime())-theWeek(longs);
			};
			//获取传入的时间的上个月的时间
			me.getDifferenceMonth = function(longs,n){
				var date = new Date(longs);
				date.setHours(23, 59, 59, 999);
				var num = date.getDate();
				if(n==1){
					return 	date.getTime()-num*24*60*60*1000;
				}else{
					var maxDay = getLastDay(date.getFullYear(),date.getMonth()+1);
					var maxLastDay = getLastDay(date.getFullYear(),date.getMonth()+2);
					var sumTime = date.getTime()+(maxDay-num+maxLastDay)*24*60*60*1000;
					if(sumTime>new Date().getTime()){
						return new Date().getTime();
					}else{
						return sumTime;
					}
				}
				
				
			};
			//获取上一年的12月31号23点59分59秒
			me.getDifferenceYear = function(longs,n){
				var date =  new Date(longs);
				var year = date.getFullYear();
				if(n==1){
				year=year-1;
				return new Date(year+'/12/31 23:59:59').getTime();
				}else{
					year=year+1;
					if(new Date(year+'/12/31 23:59:59').getTime()>new Date().getTime()){
						return new Date().getTime();
					}else{
						return new Date(year+'/12/31 23:59:59').getTime();
					}	
				}
			};
			
			//返回年份
			me.getStateYear = function(longs){
				var date =  new Date(longs);
				var year = date.getFullYear();
				return year;
			};
			//获取月份的最大天数
			function getLastDay(year,month){  
			 var new_year = year;  //取当前的年份  
			 var new_month = month++;//取下一个月的第一天，方便计算（最后一天不固定）  
			 if(month>12)      //如果当前大于12月，则年份转到下一年  
			 {  
			 new_month -=12;    //月份减  
			 new_year++;      //年份增  
			 }  
			 var new_date = new Date(new_year,new_month,1);        //取当年当月中的第一天  
			 return (new Date(new_date.getTime()-1000*60*60*24)).getDate();//获取当月最后一天日期  
			}
			
			//将字符串装换成longs
			me.timeFormat = function(str){
				if(!str){
					return '';
				}
				str = str.replace(/-/g, '/');
				var date = new Date(str);
				return date.getTime();
			};
			
			//获取传入的时间的那周六的时间
			me.getSaturday = function(longs){
				var date = new Date(longs);
				date.setHours(23, 59, 59, 999);
				//获取是这周的第几天
				var num = date.getDay();
				//返回
				return date.getTime()+(6-num)*24*60*60*1000;	
			};
			
			//获取当月的最后一天的时间
			me.getLastMonthDay = function(longs){
				var date = new Date(longs);
				date.setHours(23, 59, 59, 999);
				var year = date.getFullYear();
				var month = date.getMonth();
				var num = getLastDay(year,month+1);
				var day = date.getDate();
				return date.getTime()+(num-day)*24*60*60*1000;
			};
			
			//获取传入的时间的这一年的最后一天时间
			me.getLastYearDay = function(longs){
				var date = new Date(longs);
				var year = date.getFullYear();
				var dates = new Date(year+"/12/31 23:59:59");
				return dates;
			};
			
			//获取一个月的最大天数
			me.getMaxDay = function(longs){
				var date = new Date(longs);
				date.setHours(23, 59, 59, 999);
				var year = date.getFullYear();
				var month = date.getMonth();
				var num = getLastDay(year,month+1);
				return num;
			};
			
	});

});