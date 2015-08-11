<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../include/common.jsp"%>
</head>
<body>
	<%@ include file="../../include/status.jsp"%>
	<div class="bar">
		信息详情
	</div>
	<div class="body">
		<form id="validateForm" action="${action}" method="post">
			<c:if test="${'mail' eq vo.type}">
			<table class="inputTable">
				<tr>
					<th>收件人</th>
					<td>${vo.receiver}</td>
				</tr>
				<tr>
					<th>主题</th>
					<td>${vo.subject}</td>
				</tr>
				<tr>
					<th>内容</th>
					<td>
						<textarea name="content" class="formTextarea">${vo.content}</textarea>
					</td>
				</tr>
			</table>
			</c:if>
			<c:if test="${'sms' eq vo.type}">
			<table class="inputTable">
				<tr>
					<th>收件人</th>
					<td>${vo.receiver}</td>
				</tr>
				<tr>
					<th>内容</th>
					<td>
						<textarea name="content" class="formTextarea">${vo.content}</textarea>
					</td>
				</tr>
			</table>
			</c:if>
			<div class="buttonArea">
				<a class="btn pull_left" onclick="back()">返回</a>
			</div>
		</form>
	</div>
</body>
</html>