define('tree',function(){
	return function($element){
		$element.addClass('ztree');
		
		var fnBack = window[$element.data('back')],
			fnDataFilter = window[$element.data('filter')],
			fnOnCheck = window[$element.data('oncheck')],
			sCheck = $element.data('check'),
			result = [],
			url = $element.data('datasource');
			setting = {
				view : {showIcon:false},
				data : {
					simpleData : {
						pIdKey : "parentId",
						enable : true
					}
				},
				callback : {
					
				}
			};
			
		if(sCheck){
			setting.check = {enable:true};
		}
		if(fnOnCheck){
			setting.callback.onCheck = fnOnCheck;
		}
		if(fnBack){
			setting.callback.onClick = function(event,treeId,treeNode){
				fnBack({
					id : treeNode.id,
					name : treeNode.name
				});
			};
		}
		
		$.getJSON(url,{},function(resp){
			if(resp.status==0){
				var result = resp.result;
				if(fnDataFilter){
					for(var iIndex=0;iIndex<result.length;iIndex++){
						result[iIndex] = fnDataFilter(result[iIndex]);
					}
				}
				$.fn.zTree.init($element, setting,result).expandAll(true);
			}
		})
	}
});