define('account',['config'],function(config){
	return function($element){
		var strBack = $element.data("back");	/* 获取回调函数 */
		var fnBack = window[strBack];
		var strDefault = $element.data('default');
		/*
		var resultNodes = [];	
		
		var arrDefault = [];
		var strDefault = "";
		var loadDefault = function(){
			strDefault = $element.data('default');
			if(strDefault!=null && strDefault!=''){
				arrDefault = strDefault.split(',');
				for(var iIndex=0;iIndex<arrDefault.length;iIndex++){
					$.getJSON("/sys/account/"+arrDefault[iIndex]+".do?simple=true",{},function(resp){
						if(resp.status==0){
							resultNodes.push({
								id : resp.result.id,
								name : resp.result.loginName
							});
						}
					})
				}
			}
		}
		
		var click = function(){
			loadDefault();
			var $dialog = $.dialog({
				title : "选择用户账户",
				content : "<ul id='v_com_base_account_tree' class='accountList ztree'></ul>",
				width : 200,
				max : false,
				min : false,
				height:300,
				ok : function(){
					var zTreeObj = $.fn.zTree.getZTreeObj("v_com_base_account_tree");
					funBack(resultNodes);
					var _default = [];
					for(var iIndex=0;iIndex<resultNodes.length;iIndex++){
						_default.push(resultNodes[iIndex].id);
					}
					$element.data("default",_default.join(','));
					resultNodes = [];
				}
			});
			$("#v_com_base_account_tree").parent().parent().attr("valign","top").css("text-align","left");
			var filter = function(treeId,parentNode,responseData){
				if(responseData.status == 0){
					var result = responseData.result;
					if(!parentNode){
						for(var iIndex =0;iIndex<result.length;iIndex++){
							result[iIndex].isParent = true;
							result[iIndex].nocheck = true;
						}
					}else{
						for(var iIndex =0;iIndex<result.length;iIndex++){
							result[iIndex].isParent = false;
							result[iIndex]['name'] = result[iIndex].loginName;
							if(arrDefault.contains(result[iIndex].id)){
								result[iIndex]['checked'] = true;
							}
						}
					}
					return result;
				}else{
					alert(responseData.msg);
				}
			}
			
			var setting = {
				check : {enable:true},
				view : {showIcon:false},
				async: {
					enable: true,
					type : "get",
					dataFilter : filter,
					url:config.dataSource['account-user']
				},
				callback : {
					"beforeAsync" : function(treeId,treeNode){
						if(treeNode){
							var userId = treeNode.id;
							$.fn.zTree.getZTreeObj(treeId).setting.async.url = config.dataSource['account-account']+"&userId="+userId;
						}else{
							$.fn.zTree.getZTreeObj(treeId).setting.async.url = config.dataSource['account-user'];
						}
						return true;
					},
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
			
			$.fn.zTree.init($(".accountList"), setting);
			
		}
		*/
		
		var click = function(){
			var $dialog = $.dialog({
				title : "选择用户账户",
				content : "url:"+basePath+"/sys/account/select.do",
				width : 900,
				max : false,
				min : false,
				height:450,
				ok : function(){
					var defaultAccount = this.iwin['arrDefaultAccount'];
					$element.data('default',defaultAccount.join(','));
					fnBack(this.iwin['resultAccount']);
				},
				init : function(){
					if($element.data('default')!=null && $element.data('default')!=''){
						this.iwin['defaultAccount'] = $element.data('default').split(',');
						this.iwin.fnLoadDefault();
					}
				}
			});
		}
		
		$element.on('click',click);
	};
})