<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../include/common.jsp"%>
<link rel="stylesheet" type="text/css" href="/static/ztree/zTreeStyle.css" />
<script type="text/javascript" src="/static/ztree/jquery.ztree.all-3.5.min.js" charset="utf-8"></script>
<style>
	.selectedFunction{
		cursor: pointer;
		text-align:center;
	}
	
	.showNodeInfo{
		background-color: #ffe6b0;
		border: 1px solid #ffb951;
	}
	
	
</style>
<script>
var showNodeInfo = function(node){
	$('.selectedFunction').removeClass('showNodeInfo');
	$(node).addClass('showNodeInfo');
	$('#viewTable :input').each(function(){
		if($(this).attr('name')=='functionName'){
			$(this).val($(node).data('node').name);
		}else if($(this).attr('name')=='sort'){
			$(this).val($(node).data('node').sort)
		}else{
			$(this).val($(node).data('node').alias || '')
		}
	})
	$('#viewTable').data('li',$(node));
	
	$('#viewTable :input[name="alias"]').on('blur',function(){
		$('#viewTable').data('li').data('node').alias=$(this).val();
	})
	$('#viewTable :input[name="sort"]').on('blur',function(){
		$('#viewTable').data('li').data('node').sort = $(this).val();
	})
},
ajaxSubmit = function(){
	require(['http'],function(http){
		var params = {favorites:[]};
		var selectedFunction = $('.selectedFunction')
		for(var iIndex=0;iIndex<selectedFunction.length;iIndex++){
			params['favorites['+iIndex+'].functionVo.id'] = $(selectedFunction[iIndex]).data('node').id;
			params['favorites['+iIndex+'].alias'] = $(selectedFunction[iIndex]).data('node').alias;
			params['favorites['+iIndex+'].sort'] = $(selectedFunction[iIndex]).data('node').sort;
		}
		http.Post(basePath + '/sys/favorite.do').params(params).success(function(resp){
			alert(resp.message);
		}).go();
	})
},
favorites,
functionFilter = function(node){
	if(node.url==null || node.url == '' || node.url == "/"){
		node.nocheck = true;
	}
	for(var iIndex=0;iIndex<favorites.length;iIndex++){
		if(favorites[iIndex].functionVo.id==node.id){
			node.checked=true;
			appendSelectNode({
				name : favorites[iIndex].functionVo.name,
				alias : favorites[iIndex].alias,
				id : favorites[iIndex].functionVo.id,
				sort : favorites[iIndex].sort
			});
		}
	}
	node.url = "";
	return node;
},
selectFunction = function(e,treeId,selectFunction){
	if(selectFunction.checked){
		appendSelectNode(selectFunction);
	}else{
		$('#'+selectFunction.id).remove();
	}
},
appendSelectNode = function(node){
	console.info(node);
	$('#selectedFunction').append(
		$('<li id="'+node.id+'" onclick="showNodeInfo(this)" class="selectedFunction" style="width:90px;display:inline-block;">').data('node',{
			name : node.name,
			alias : node.alias || node.name,
			id : node.id,
			sort : node.sort
		}).html(node.name)
	)
};


$(function(){
	require(['tree','http'],function(tree,http){
		http.Get(basePath + '/sys/favorite.do').success(function(resp){
			if(resp.status==0){
				favorites = resp.result;
				tree($('#functionTree'));
			}
		}).go();
	})
})
</script>
</head>
<body>
	<%@ include file="../../include/status.jsp"%>
	<div class="bar">
		<c:set var="action" value="#" />
		快捷菜单收藏
	</div>
	<div class="body">
		<form id="validateForm" action="${action}" method="post">
			<table class="inputTable">
				<tr>
					<th style="width:44%;text-align:center;">可收藏菜单</th>
					<th style="text-align:center;">已收藏菜单</th>
				</tr>
				<tr>
					<td>
						<ul id="functionTree" data-oncheck="selectFunction" data-check="check" data-datasource="/sys/function.do" data-filter="functionFilter"></ul>
					</td>
					<td valign="top">
						<ul style="height:100%;" id="selectedFunction"></ul>
					</td>
				</tr>
			</table>
			<br>
			<table id="viewTable" class="inputTable">
				<th>功能：</th>
				<td><input type="text" disabled="disabled" name="functionName"/></td>
				<th>别名：</th>
				<td><input type="text" name="alias"/></td>
				<th>序号：</th>
				<td><input type="text" name="sort"/></td>
			</table>
			<div class="buttonArea">
				<a class="btn pull_left" onclick="ajaxSubmit()">确定</a>
				<a class="btn pull_left" onclick="back()">返回</a>
			</div>
		</form>
	</div>
</body>
</html>