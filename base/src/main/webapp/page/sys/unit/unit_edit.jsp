<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../include/common.jsp"%>
</head>
<body>
	<div class="bar">
		<c:set var="action" value="#" />
		<c:choose>
			<c:when test="${isEdit}">
				<c:set var="action" value="update.do?id=${vo.id}" />	
				编辑单位信息
			</c:when>
		</c:choose>
	</div>
	<div class="body">
		<form id="validateForm" action="${action}" method="post" enctype=”multipart/form-data”>
			<table class="inputTable">
				<tr>
					<th>单位名称</th>
					<td>
						<input type="text" name="name" class="formText" title="单位名称"  value="${vo.name}"/>
					</td>
					<th>网站</th>
					<td><input type="text" name="website"  class="formText" value="${vo.website}"/></td>
				</tr>
				<tr>
					<th>联系人</th>
					<td><input type="text" name="linkMan"  class="formText" value="${vo.linkMan}"/></td>
					<th>联系电话</th>
					<td><input type="text" name="telephone"  class="formText" value="${vo.telephone}"/></td>
				</tr>
				<tr>
					<th>邮编</th>
					<td><input type="text" name="post"  class="formText" value="${vo.post}"/></td>
					<th>传真</th>
					<td><input type="text" name="fax"  class="formText" value="${vo.fax}"/></td>
				</tr>
				<tr>
					<th>联系地址</th>
					<td><input type="text" name="address"  class="formText" value="${vo.address}"/></td>
					<th>Logo</th>
					<td><input type="file" name="file" accept="image/gif" class="formText" value="${vo.logo}"/><a href="/${vo.logo}" target="_blank">预览</a></td>
				</tr>
			</table>
			<div class="buttonArea">
				 <a class="btn pull_left" onclick="$('form').submit();">确定</a>
			</div>
		</form>
	</div>
</body>
</html>