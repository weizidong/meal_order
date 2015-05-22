function log(params) {
//	console.log(params);
}

function wxAlert(msg){
	if (typeof (msg) == 'object') {
		msg = JSON.stringify(msg);
	}
	alert(msg);
}