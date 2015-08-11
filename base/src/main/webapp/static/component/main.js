requirejs.config({
	baseUrl : '/static/component/'
});

define('http',function(){
	
	var	Ajax = function(url){
		this._url = url;
		this._params = {};
		this._success = function(){};
		this._error = function(){};
		this._method = 'GET';
		this._async = true;
	};
	
	Ajax.prototype.params = function(key,value){
		if(arguments.length == 1 && typeof(key) == 'object'){
			this._params = $.extend(this._params,key);
		}else if(typeof(key) == 'string' && typeof(value) == 'string'){
			this.params({
				key : value
			});
		}
		return this;
	}
	
	Ajax.prototype.async = function(async){
		this._async = !!async;
	}
	
	Ajax.prototype.success = function(successCall){
		this._success = successCall;
		return this;
	}
	
	Ajax.prototype.error = function(errorCall){
		this._error = errorCall;
		return this;
	}
	
	Ajax.prototype.go = function(){
		var ajax = this;
		$.ajax(ajax._url,{
			type : ajax._method,
			data : ajax._params,
			dataType : 'json',
			async : ajax._async,
			error : ajax._error,
			success : ajax._success
		})
		return this;
	}
	
	Ajax.prototype.method = function(method){
		this._method = method;
		return this;
	}
	
	
	var Get = function(url){this._url = url;};
	Get.prototype = new Ajax().method('GET');
	
	var Post = function(url){this._url = url;}
	Post.prototype = new Ajax().method('POST');
	
	var Put = function(url){this._url = url;}
	Put.prototype = new Ajax().method('POST').params('_method','put');
	
	var Delete = function(url){this._url = url;}
	Delete.prototype = new Ajax().method('POST').params('_method','delete');
	
	var Patch = function(url){this._url = url;};
	Patch.prototype = new Ajax().method('PATCH').params('_method','patch');
	
	var Http = {
			Get : function(url){return new Get(url);},
			Post : function(url){return new Post(url);},
			Put : function(url){return new Put(url);},
			Delete : function(url){return new Delete(url);},
			Patch : function(url){return new Patch(url);}
	};
	
	return Http;
});

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
});

define('org.tree',['config','tree'],function(config,tree){
	return function($element){
		$element.data('datasource',config.dataSource.org);
		tree($element);
	}
});

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
});

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
});


define('config',{
	"clsNames" : {
		"code" : ".v-com-code",
		"account" : ".v-com-account",
		"account-view" : ".v-com-account-view",
		"org" : ".v-com-org",
		'org-view' : '.v-com-org-view',
		'org-tree':'.v-com-org-tree',
		'tree' : '.v-com-tree',
		'duty-view' : '.v-com-duty-view'
	},
	"dataSource" : {
		'code' : basePath + '/sys/code/{{busInfo}}/{{code}}.do',
		'account-user' : basePath + '/sys/user.do?simple=true',
		'account-account':basePath + '/sys/account.do?simple=true',
		'account-view' : basePath + '/sys/account/{{id}}.do?simple=true',
		'org' : basePath + '/sys/org.do?simple=true',
		'org-view' : basePath + '/sys/org/{{id}}.do?simple=true',
		'duty-view' : basePath + '/sys/duty/{{id}}.do'
	}
});

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
});


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
});


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
});

$(function(){
	require(['config'],function(config){
		var arrCodes = $(config.clsNames.code);
		if(arrCodes.length>0){
			require(['code'],function(code){
				arrCodes.each(function(){
					var $select = $(this);
					code($select);
				});
			});
		}
		
		var arrAccounts = $(config.clsNames.account);
		if(arrAccounts.length>0){
			require(['account'],function(account){
				arrAccounts.each(function(){
					var $select = $(this);
					account($select);
				});
			});
		}
		
		var arrOrg = $(config.clsNames.org);
		if(arrOrg.length>0){
			require(['org'],function(org){
				arrOrg.each(function(){
					var $element = $(this);
					org($element);
				});
			});
		}
		
		var arrAccountView = $(config.clsNames['account-view']);
		if(arrAccountView.length>0){
			require(['account.view'],function(accountView){
				arrAccountView.each(function(){
					accountView($(this));
				});
			});
		}
		
		var arrOrgView = $(config.clsNames['org-view']);
		if(arrOrgView.length>0){
			require(['org.view'],function(orgView){
				arrOrgView.each(function(){
					orgView($(this));
				});
			});
		}
		
		var arrOrgTree = $(config.clsNames['org-tree']);
		if(arrOrgTree.length>0){
			require(['org.tree'],function(orgTree){
				arrOrgTree.each(function(){
					orgTree($(this));
				});
			});
		}
		var arrTree = $(config.clsNames['tree']);
		if(arrTree.length>0){
			require(['tree'],function(tree){
				arrTree.each(function(){
					tree($(this));
				});
			});
		}
		
		var arrDutyView = $(config.clsNames['duty-view']);
		if(arrDutyView.length>0){
			require(['duty.view'],function(dutyView){
				arrDutyView.each(function(){
					dutyView($(this));
				});
			});
		}
		
		var arrForm = $('form');
		if(arrForm.length>0){
			require(['../js/jquery/jquery.form'],function(formValidate){
				formValidate();
			});
		}
	});
	
});
