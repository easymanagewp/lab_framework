define('duty.view',['config'],function(config){
	return function($element){
		var sId = $element.data('id');
		
		var viewDutyName = function(viewName){
			$element.text(viewName);
		}
		
		$.getJSON(config.dataSource['duty-view'].replace('{{id}}',sId),{},function(resp){
			if(resp.status==0){
				var result = resp.result;
				viewDutyName(result);
			}
		});
	}
})