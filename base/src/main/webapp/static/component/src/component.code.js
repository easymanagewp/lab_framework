define('code',['config'],function(config){
	return function($select){
		var strBusInfo =$select.data("businfo");
		var strCode = $select.data("code");
		var strDefault = $select.data("default");
		var loadData = function(back){
			$.getJSON(config.dataSource.code.replace("{{busInfo}}",strBusInfo).replace("{{code}}",strCode),{},function(resp){
				if(resp.status==0){
					var result = resp.result;
					$.each(result,function(){
						var $option = $("<option>").val(this).html(this);
						if(this==strDefault){
							$option.attr('selected','selected')
						}
						$option.appendTo($select);
					})
				}
			})
		}
		loadData();
	};
})