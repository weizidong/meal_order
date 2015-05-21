define([], function() {

	var Map = function() {
		var map = {};

		var _list;

		this.put = function(key, value) {
			if (value === undefined) {
				value = null;
			}
			map[key] = value;
		};

		this.get = function(key) {
			return map[key];
		};

		this.containsKey = function(key) {
			return !(this.get(key) === undefined);
		};

		this.remove = function(key) {
			delete map[key];
		};

		/**
		 * 将一个数组put到map里面
		 * 
		 * 参数keyMapper获取key的函数
		 * 
		 * 例如： setList([{id:1},{id:2}],function(item){ return item.id; });
		 */
		this.setList = function(_list, keyMapper) {
			_list = list;
			for ( var i in _list) {
				var item = _list[i];
				this.put(keyMapper(item), item);
			}
		};
		
		/**
		 * 返回的是调用setList传入的list 
		 */
		this.getList = function(){
			return _list;
		};

	};

	return Map;

});