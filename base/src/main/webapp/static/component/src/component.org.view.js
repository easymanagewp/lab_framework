define('org.view',['config'],function(config){
	return function($element){
		var sId = $element.data('id');
		
		var viewName = function(sViewName){
			$element.text(sViewName);
		}
		
		$.getJSON(config.dataSource['org-view'].replace('{{id}}',sId),{},function(resp){
			if(resp.status==0){
				var result = resp.result;
				viewName(result.name);
			}
		})
	}
})