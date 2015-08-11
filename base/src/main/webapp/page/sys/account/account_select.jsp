<%@ page language='java' import='java.util.*' pageEncoding='UTF-8'%>
<!DOCTYPE html>
<html>
<head>
<%@ include file='../../include/common.jsp'%>
<link rel="stylesheet" type="text/css" href="/static/ztree/zTreeStyle.css" />
<script type="text/javascript" src="/static/ztree/jquery.ztree.all-3.5.min.js" charset="utf-8"></script>
<link href='${basePath}static/component/css/component.account.select.css' rel='stylesheet' type='text/css' />
<script type="text/javascript" data-main="/static/component/main.js" src="/static/requirejs/require.js" ></script>
</head>
<body id='accountSelect'>
	<div class='fl border' style='width:24.5%;height:98%;'>
		<div class='title'>过滤类型</div>
		<ul class='_tab'>
			<li class='filter' data-type='role'>角色</li>
			<li class='filter' data-type='org'>组织</li>
			<li class='filter' data-type='duty'>岗位</li>
		</ul>
	</div>
	<div id='filterTree' class='fl border' style='width:24.5%;height:98%;'>
		<div class='title' id="filterTitle">←选择过滤类型</div>
		<ul class='ztree' data-back='fnFilterTreeClick'></ul>
	</div>
	<div class='fl border' style='width:24.5%;height:98%;'>
		<div class='title'>用户</div>
		<ul id='userTree' class='v-com-tree' data-check='true' data-datasource='/sys/user.do?simple=true' data-filter='fnFilterAccount' data-oncheck='fnOnCheckAccount'></ul>
	</div>
	<div class='fl border' style='width:24.5%;height:98%;'>
		<div class='title'>已选</div>
		<ul id="viewUsers"></ul>
	</div>
	<script type='text/javascript'>
		var $accountSelect = $('#accountSelect'),
			$filterTree = $('#filterTree .ztree'),
			$userTree = $('#userTree'),
			$viewUsers = $('#viewUsers'),
			$filterTitle = $('#filterTitle'),
			arrDefaultAccount = [],
			zTreeObj,
			fnLoadRoles,
			fnLoadOrgs,
			fnLoadDutys,
			fnFilterTreeClick,
			fnFilterAccount,
			fnOnCheckAccount,
			fnRemoveCheked,
			fnLoadDefault,
			filterType,
			filterValue
			resultAccount = [];
		
		
		
		$accountSelect.on('loadFilterTree',function(event,$filterElement){
			if(filterType=='role'){
				$filterTitle.html('角色');
				fnLoadRoles($filterElement);
			}else if(filterType=='org'){
				$filterTitle.html('组织结构');
				fnLoadOrgs($filterElement);
			}else if(filterType=='duty'){
				$filterTitle.html('岗位');
				fnLoadDutys($filterElement);
			}
		});
		
		$accountSelect.on('loadAccountTree',function(event,node){
			require(['tree'],function(tree){
				var loadUserUrl = '/sys/user.do?simple=true';
				if(filterType=='org'){
					loadUserUrl += '&orgId='+filterValue;
				}else if(filterType=='role'){
					loadUserUrl += '&roleId='+filterValue;
				}else if(filterType=='duty'){
					loadUserUrl += '&dutyId='+filterValue;
				}
				
				$userTree.data('datasource',loadUserUrl);
				tree($userTree);
			});
		})
		
		$('.filter').on('click',function(){
			filterType = $(this).data('type');
			$accountSelect.trigger('loadFilterTree',[$(this)]);
		});
		
		/* ====================== 加载数据 ============================== */
		fnLoadRoles = function($filterElement){
			require(['tree'],function(tree){
				$filterTree.data('datasource','/sys/role.do?simple=true&tree=true');
				tree($filterTree);
			});
		};
		
		fnLoadOrgs = function($filterElement){
			require(['org.tree'],function(oOrgTree){
				oOrgTree($filterTree);
			});
		};
		
		fnLoadDutys = function($filterElement){
			require(['tree'],function(tree){
				$filterTree.data('datasource','/sys/duty.do?simple=true');
				tree($filterTree);
			});
		}
		/* ====================== 加载数据 ============================== */
		
		/* ====================== 过滤器数点击事件，触发账户加载事件 ============================== */
		fnFilterTreeClick = function(node){
			filterValue = node.id;
			$accountSelect.trigger('loadAccountTree',[node]);
		}
		
		
		fnFilterAccount = function(result){
			if(!result.parentId){
				result.nocheck = true;
			}else if(arrDefaultAccount.contains(result.id)){
				result.checked = true;
			}
			return result;
		}
		
		fnOnCheckAccount = function(event,treeId,node){
			if(node.checked){
				resultAccount.push({
					id : node.id,
					loginName : node.name,
					userName : node.getParentNode().name,
					userId : node.getParentNode().id
				});
				arrDefaultAccount.push(node.id);
			}else{
				var _resultAccount = [];
				for(var iIndex =0;iIndex<resultAccount.length;iIndex++){
					if(resultAccount[iIndex].id!=node.id){
						_resultAccount.push(resultAccount[iIndex]);
					}
				}
				resultAccount = _resultAccount;
				
				var _arrDefaultAccount = [];
				for(var iIndex=0;iIndex<arrDefaultAccount.length;iIndex++){
					if(node.id != arrDefaultAccount[iIndex]){
						_arrDefaultAccount.push(arrDefaultAccount[iIndex]);
					}
				}
				arrDefaultAccount = _arrDefaultAccount;
			}
			$accountSelect.trigger('reviewAccounts');
		}
		
		$accountSelect.on('reviewAccounts',function(){
			$viewUsers.html('');
			for(var iIndex =0;iIndex<resultAccount.length;iIndex++){
				$('<li>').html(resultAccount[iIndex].userName+'('+resultAccount[iIndex].loginName+')').appendTo($viewUsers)
				.append('<a href="javascript:void(0)" onclick="fnRemoveCheked(\''+resultAccount[iIndex].id+'\')">删除</a>');
			}
		})
		
		fnRemoveCheked = function(nodeId){
			var _resultAccount = [];
			for(var iIndex =0;iIndex<resultAccount.length;iIndex++){
				if(resultAccount[iIndex].id!=nodeId){
					_resultAccount.push(resultAccount[iIndex]);
				}
			}
			resultAccount = _resultAccount;
			
			var _arrDefaultAccount = [];
			for(var iIndex=0;iIndex<arrDefaultAccount.length;iIndex++){
				if(nodeId != arrDefaultAccount[iIndex]){
					_arrDefaultAccount.push(arrDefaultAccount[iIndex]);
				}
			}
			arrDefaultAccount = _arrDefaultAccount;
			
			if(!zTreeObj){
				zTreeObj = $.fn.zTree.getZTreeObj($userTree.attr('id'));
			}
			node = zTreeObj.getNodeByParam('id',nodeId);
			node.checked = false;
			zTreeObj.updateNode(node,false);
			$accountSelect.trigger('reviewAccounts');
		}
		
		fnLoadDefault = function(){
			arrDefaultAccount = (window.defaultAccount || []);
			if(arrDefaultAccount.length>0){
				for(var iIndex =0;iIndex<arrDefaultAccount.length;iIndex++){
					$.getJSON('/sys/account/'+arrDefaultAccount[iIndex]+".do?simple=true",{},function(resp){
						if(resp.status==0){
							resultAccount.push({
								id : resp.result.id,
								loginName : resp.result.loginName,
								userId : resp.result.userId,
								userName : resp.result.userName
							});
						}
						$accountSelect.trigger('reviewAccounts');
					})
				}
			}
		}
		
		
	</script>
</body>
</html>