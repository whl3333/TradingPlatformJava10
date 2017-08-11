$(function() {
	var executions =[];
	var maps = [];
	
	$.ajax({
		type : "GET",
		url : "./execution/getAll",
		dataType : "json",
		async : false,
		success : function(result) {
			executions = result;
		}
	});
	
	$.ajax({
		type : "GET",
		url : "./execution/getSymbols",
		dataType : "json",
		async : false,
		success : function(result) {
			maps = result;
		}
	});
	

	var showexecutions= function() { 										
    	$(".executions").find('tbody').find("tr").remove();
    	var arr = maps;
        for (var i = 0; i < executions.length; i++) {
        	var a=0,b=0,c=0,d=0,e=0,f=0;
        	if (executions[i]!=undefined) {
        		a=executions[i].orderID;
        		for(var key in arr){
        			if(key==a){
        				b=arr[key];
        				break;
        			}
        		}
        		c=executions[i].quantity;
        		d=executions[i].price;
        		if(executions[i].result=="filling"){
        			e="COMPLETED";
        			f='label label-success';
        		}else{
        			e="FAILED";
        			f='label label-danger';
        		}
        	}
            var newrow = '<tr><td>' + a+ '</td><td>' + b +'</td><td>' + c+ '</td><td>' + d + '</td><td>'+'<span>' + e+ '</span></td></tr>';
            $(".executions").find('tbody').prepend(newrow);
            $(".executions").find('tbody').find('tr').eq(0).find('span').eq(0).addClass(f);
        }
    };
	showexecutions();
	
});