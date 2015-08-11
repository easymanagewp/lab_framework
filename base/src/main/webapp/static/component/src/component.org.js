define('org',['config'],function(config){
	return function($element){
		var strBack = $element.data("back");	/* 获取回调函数 */
		var funBack = window[strBack];
		
		var resultNodes = [];	/* 储存所有选择的节点 */
		
		/*
		 * 初始化加载默认节点数据
		 */
		var arrDefault = [];
		var strDefault = "";
		var loadDefault = function(){
			strDefault = $element.data('default');
			if(strDefault!=null && strDefault!=''){
				arrDefault = strDefault.split(',');
				for(var iIndex=0;iIndex<arrDefault.length;iIndex++){
					$.getJSON("/sys/org/"+arrDefault[iIndex]+".do?simple=true",{},function(resp){
						if(resp.status==0){
							resultNodes.push({
								id : resp.result.id,
								name : resp.result.name
							});
						}
					})
				}
			}
		}
		
		/*
		 * 元素点击事件，弹出账户选择栏
		 */
		var click = function(){
			loadDefault();
			var $dialog = $.dialog({
				title : "请选择组织",
				content : "<ul id='v_com_base_org_tree' class='orgTree ztree'></ul>",
				width : 200,
				max : false,
				min : false,
				height:300,
				ok : function(){
					var zTreeObj = $.fn.zTree.getZTreeObj("v_com_base_org_tree");
					funBack(resultNodes);
					var _default = [];
					for(var iIndex=0;iIndex<resultNodes.length;iIndex++){
						_default.push(resultNodes[iIndex].id);
					}
					$element.data("default",_default.join(','));
					resultNodes = [];
				}
			});
			$("#v_com_base_org_tree").parent().parent().attr("valign","top").css("text-align","left");
			
			var setting = {
				check : {enable:true},
				view : {showIcon:false},
				data : {
					simpleData : {
						pIdKey : "parentId",
						enable : true
					}
				},
				callback : {
					"onCheck" : function(event,treeId,treeNode){
						if(treeNode.checked){
							resultNodes.push({
								id : treeNode.id,
								name : treeNode.name
							})
						}else{
							var _resultNodes = [];
							for(var iIndex=0;iIndex<resultNodes.length;iIndex++){
								if(resultNodes[iIndex].id!=treeNode.id){
									_resultNodes.push(resultNodes[iIndex]);
								}
							}
							resultNodes = _resultNodes;
						}
					}
				}
			};
			
			$.getJSON( config.dataSource.org,{},function(resp){
				if(resp.status==0){
					var result = resp.result;
					for(var iIndex =0;iIndex<result.length;iIndex++){
						if(arrDefault.contains(result[iIndex].id)){
							result[iIndex].checked = true;
						}
					}
					$.fn.zTree.init($("#v_com_base_org_tree"), setting,result);
				}
			})
		}
		
		$element.on('click',click);
	}
})