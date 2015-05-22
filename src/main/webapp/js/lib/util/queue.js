define(function(){

	var Queue = function() {
			this.staticQueue = [];
			return this;
		}
		Queue.prototype = {
				running : false,
				next : function() { //按队列顺序，依次执行队列事件
					var thisQueue = this.staticQueue.shift();
					if(thisQueue){
						this.running = true;
						thisQueue();//执行事件
					}else{
						this.running = false;
					}
					return this;
				},
				push : function(callback) {//将事件放入队尾
					this.staticQueue.push(callback);
					if (this.staticQueue.length == 1 && !this.running) {
						this.next();
					}
					return this;
				},
				removeAll : function(){
					this.staticQueue = [];
					this.running = false;
					return this;
				},
				wait : function(delay){
					var me = this;
					me.push(function(){
						setTimeout(function(){
							me.next();
						}, delay)
					});
					return this;
				}
		}

//		console.log('begin');
//	var q = new Queue();
//		q.wait(3000).push(function(){
//			console.log(1);
//			q.next();
//		}).wait(1000).push(function(){
//			console.log(1);
//			q.next();
//		});
		
	return Queue;
		
});