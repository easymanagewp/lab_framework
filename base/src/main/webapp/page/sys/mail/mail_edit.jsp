<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../include/common.jsp"%>
</head>
<body>
	<%@ include file="../../include/status.jsp"%>
	<div class="bar">
		<c:set var="action" value="add.do?type=${vo.type}" />	
		添加
		<c:if test="${'sms' eq vo.type}">
		短信
		</c:if>
		<c:if test="${'mail' eq vo.type}">
		邮件
		</c:if>
		信息
	</div>
	<div class="body">
		<form id="validateForm" action="${action}" method="post">
			<c:if test="${'mail' eq vo.type}">
			<table class="inputTable">
				<tr>
					<th>收件人</th>
					<td><input type="text" name="receiver" class="formText" value="${vo.receiver}"/></td>
				</tr>
				<tr>
					<th>主题</th>
					<td><input type="text" name="subject" class="formText" value="${vo.subject}"/></td>
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
					<td><input type="text" name="receiver" class="formText" value="${vo.receiver}"/></td>
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
				<input type="submit" class="formButton" value="确  定" hidefocus />&nbsp;&nbsp;
				<input type="button" class="formButton"
					onclick="window.history.back(); return false;" value="返  回"
					hidefocus />
			</div>
		</form>
	</div>
</body>
</html>