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
				编辑模板信息
			</c:when>
			<c:otherwise>
				添加岗位信息
				<c:set var="action" value="add.do" />
			</c:otherwise>
		</c:choose>
	</div>
	<div class="body">
		<form id="validateForm" action="${action}" method="post" enctype="multipart/form-data">
			<table class="inputTable">
				<tr>
					<th>业务模块</th>
					<td><input type="text" name="busInfo" class="formText" value="${vo.busInfo}"/></td>
					<th>序号</th>
					<td><input type="text" name="sort" class="formText" value="${vo.sort}"/></td>
				</tr>
				<tr>
					<th>模板名称</th>
					<td><input type="text" name="name" class="formText" value="${vo.name}"/></td>
					<th>模板编码</th>
					<td><input type="text" name="templateName" class="formText" value="${vo.templateName}"/></td>
				</tr>
				<tr>
					<th>类别</th>
					<td>
						<select name=type>
							<option value="import" <c:if test="${'import' eq vo.type}">selected="selected"</c:if>>import</option>
							<option value="export" <c:if test="${'export' eq vo.type}">selected="selected"</c:if>>export</option>
						</select>
					</td>
					<th>文件</th>
					<td>
						<input type="file" name="file" class="formText"/>
						<c:if test="${fn:length(vo.path)>0}">
						<a href="download.do?filePath=${vo.path}&trueName=${vo.name}.xls">查看</a>
						</c:if>
					</td>
				</tr>
				<tr>
					<th>更新版本</th>
					<td><input type="text" name="versionNo" class="formText" value="${vo.versionNo}"/></td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>说明</th>
					<td colspan="3">
						<textarea name="note" class="formTextarea">${vo.note}</textarea>
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