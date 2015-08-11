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
				编辑组织信息
			</c:when>
			<c:otherwise>
				添加组织信息
				<c:set var="action" value="add.do" />
			</c:otherwise>
		</c:choose>
	</div>
	<div class="body">
		<form id="validateForm" action="${action}" method="post">
			<table class="inputTable">
				<tr>
					<th>上级组织</th>
					<td colspan="3">
						<select name="parentVo.id">
							<c:forEach items="${orgList}" var="e" varStatus="s">
								<option value="${e.id}" <c:if test="${e.id eq vo.pid}">selected="selected"</c:if>>
									<c:forEach var="i" begin="0" end="${e.level}"> 
										&nbsp;&nbsp;&nbsp;&nbsp;
									</c:forEach>
									${e.name}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>名称</th>
					<td><input type="text" name="name" class="formText" value="${vo.name}"/></td>
					<th>编码</th>
					<td><input type="text" name="code"  class="formText" value="${vo.code}"/></td>
				</tr>
				<tr>
					<th>序号</th>
					<td><input type="text" name="sort"  class="formText" value="${vo.sort}"/></td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>说明</th>
					<td colspan="3">
						<textarea name="describtion" class="formTextarea">${vo.describtion}</textarea>
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