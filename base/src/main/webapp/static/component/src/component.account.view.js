define('account.view',['config'],function(config){
	return function($element){
		var sId = $element.data('id');
		
		var viewAccountName = function(viewName){
			$element.text(viewName);
		}
		
		$.getJSON(config.dataSource['account-view'].replace('{{id}}',sId),{},function(resp){
			if(resp.status==0){
				var result = resp.result;
				viewAccountName(result.loginName);
			}
		});
	}
})