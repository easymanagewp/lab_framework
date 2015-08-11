<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../include/common.jsp"%>
<script type="text/javascript" src="${basePath}static/ztree/tool-1.0.0.js"></script>
<script type="text/javascript" src="${basePath}static/ztree/tool-1.0.0.css"></script>
<script type="text/javascript" src="${basePath}static/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${basePath}static/ztree/jquery.ztree.excheck-3.5.js"></script>
<link rel="stylesheet" href="${basePath}static/ztree/zTreeStyle.css"type="text/css">
</head>
<script>
	function setFunctionIds() {
		var zTree = $.fn.zTree.getZTreeObj("zTree");
		nodes = zTree.getCheckedNodes(true);
		var funIds = '';
		for (var i = 0, l = nodes.length; i < l; i++) {
			funIds += nodes[i].treeNid;
			funIds += ',';
		}
		$("#functionIds").val(funIds);
	}
</script>
<body>
	<%@ include file="../../include/status.jsp"%>
	<div class="bar">
		<c:set var="action" value="#" />
		<c:choose>
			<c:when test="${isEdit}">
				<c:set var="action" value="update.do?id=${vo.id}" />	
				编辑角色信息
			</c:when>
			<c:otherwise>
				添加角色信息
				<c:set var="action" value="add.do" />
			</c:otherwise>
		</c:choose>
	</div>
	<div class="body">
		<form id="validateForm" onsubmit="setFunctionIds();" action="${action}" method="post">
			<table class="inputTable">
				<tr>
					<th>名称</th>
					<td><input type="text" name="name" class="formText" value="${vo.name}"/></td>
					<th>序号</th>
					<td><input type="text" name="sort" class="formText" value="${vo.sort}"/></td>
				</tr>
				<tr>
					<th>说明</th>
					<td colspan="3">
						<textarea name="describtion" class="formTextarea">${vo.describtion}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4"></td>
				</tr>
				<tr>
					<th>功能</th>
					<td colspan="3">
						<input name="functionIds" id="functionIds" type="hidden"/>
						<ul id="zTree" checkbox="true" turl="${basePath}/sys/role/funTree.do?roleId=${vo.id}"></ul>
					</td>
				</tr>
			</table>
			<div class="buttonArea">
				<a class="btn pull_left" onclick="submit()">确定</a>
				<a class="btn pull_left" onclick="back()">返回</a>
			</div>
		</form>
	</div>
</body>
</html>