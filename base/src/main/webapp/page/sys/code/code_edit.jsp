<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../include/common.jsp"%>
</head>
<body>
	<%@ include file="../../include/status.jsp"%>
	<div class="bar">
		<c:set var="action" value="#" />
		<c:choose>
			<c:when test="${isEdit}">
				<c:set var="action" value="update.do?id=${vo.id}" />	
				编辑公共代码信息
			</c:when>
			<c:otherwise>
				添加公共代码信息
				<c:set var="action" value="add.do" />
			</c:otherwise>
		</c:choose>
	</div>
	<div class="body">
		<form id="validateForm" action="${action}" method="post">
			<table class="inputTable">
				<tr>
					<th>模块</th>
					<td><input type="text" name="busInfo" class="formText" value="${vo.busInfo}"/></td>
					<th>序号</th>
					<td><input type="text" name="sort" class="formText" value="${vo.sort}"/></td>
				</tr>
				<tr>
					<th>名称</th>
					<td><input type="text" name="name" class="formText" value="${vo.name}"/></td>
					<th>类别</th>
					<td><input type="text" name="code" class="formText" value="${vo.code}"/></td>
				</tr>
				<tr>
					<th>内容</th>
					<td colspan="3">
						<textarea name="content" class="formTextarea">${vo.content}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4"></td>
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