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
			<c:when test="${isNewAccount}">
				添加账户信息
				<c:set var="action" value="add.do" />
			</c:when>
			<c:otherwise>
				编辑账户信息
				<c:set var="action" value="update.do?id=${vo.id}" />	
			</c:otherwise>
		</c:choose>
	</div>
	<div class="body">
		<ul id="tab" class="tab">
			<li onclick="window.location.href='${basePath}sys/user/edit.do?id=${vo.userVo.id}'">
				<input type="button" value="基本信息" hidefocus /> 
			</li>
			<c:if test="${isEdit}">
				<c:forEach items="${accountList}" var="e" varStatus="s">
				<li onclick="window.location.href='${basePath}sys/account/edit.do?id=${e.id}&userVo.id=${e.userVo.id}'">
					<input type="button" value="${e.loginName}" <c:if test="${e.id eq vo.id}">class="current"</c:if> hidefocus /> 
				</li>
				</c:forEach>
				<li onclick="window.location.href='${basePath}sys/account/edit.do?userVo.id=${vo.userVo.id}'">
					<input type="button" value="新账户" <c:if test="${isNewAccount}">class="current"</c:if> hidefocus /> 
				</li>
			</c:if>
		</ul>
		 <div class="s_blank"></div>
		<form id="validateForm" action="${action}" method="post">
			<table class="inputTable">
				<tr>
					<th>组织</th>
					<td>
						<select name="orgVo.id">
							<option value="">--</option>
							<c:forEach items="${orgList}" var="e" varStatus="s">
								<option value="${e.id}" <c:if test="${e.id eq vo.orgVo.id}">selected="selected"</c:if>>
									<c:forEach var="i" begin="0" end="${e.level}"> 
										&nbsp;&nbsp;&nbsp;&nbsp;
									</c:forEach>
									${e.name}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>用户名称</th>
					<td>${vo.userVo.name}
						<input type="hidden" name="userVo.id"  class="formText" value="${vo.userVo.id}"/>
					</td>
				</tr>
				<tr>
					<th>登录名称</th>
					<td><input type="text" name="loginName" class="formText" value="${vo.loginName}"/></td>
					<th>登录密码</th>
					<td><input type="password" name="password"  class="formText" value="${vo.password}"/></td>
				</tr>
				<tr>
					<th>签章</th>
					<td><input type="file" name="file" accept="image/gif" class="formText" value="${vo.signature}"/><a href="#">预览</a></td>
					<th>序号</th>
					<td><input type="text" name="sort" class="formText" value="${vo.sort}"/></td>
				</tr>
				<tr>
					<td colspan="4"></td>
				</tr>
				<tr>
					<th>角色</th>
					<td colspan="3">
						<c:forEach items="${accountRoleList}" var="e" varStatus="s">
							<c:set var="roleIds">${roleIds},${e.roleVo.id}</c:set>
						</c:forEach>
						<c:forEach items="${roleList}" var="e" varStatus="s">
							<input name="roleIds" value="${e.id}" type="checkbox"
								<c:if test="${fn:indexOf(roleIds,e.id)>-1}">checked="checked"</c:if> />${e.name}
						</c:forEach>
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