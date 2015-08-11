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
				编辑人员信息
			</c:when>
			<c:otherwise>
				添加人员信息
				<c:set var="action" value="add.do" />
			</c:otherwise>
		</c:choose>
	</div>
	<div class="body">
		<ul id="tab" class="tab">
			<li onclick="#">
				<input type="button" class="current" value="基本信息" hidefocus /> 
			</li>
			<c:if test="${isEdit}">
				<c:forEach items="${accountList}" var="e" varStatus="s">
				<li onclick="window.location.href='${basePath}sys/account/edit.do?id=${e.id}&userVo.id=${vo.id}'">
					<input type="button" value="${e.loginName}" hidefocus /> 
				</li>
				</c:forEach>
				<li onclick="window.location.href='${basePath}sys/account/edit.do?userVo.id=${vo.id}'">
					<input type="button" value="新账户" hidefocus /> 
				</li>
			</c:if>
		</ul>
		 <div class="s_blank"></div>
		<form id="validateForm" action="${action}" method="post">
			<table class="inputTable">
				<tr>
					<th>姓名</th>
					<td><input type="text" name="name" class="formText" value="${vo.name}"/></td>
					<th>编号</th>
					<td><input type="text" name="no" class="formText" value="${vo.no}"/></td>
				</tr>
				<tr>
					<th>岗位</th>
					<td colspan="3">
						<c:forEach items="${dutyList}" var="e" varStatus="s">
							<input type="radio" name="dutiesVo.id" value="${e.id}" <c:if test="${vo.dutiesVo.id==e.id}">checked="checked"</c:if>/>${e.name}
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th>职务</th>
					<td><input type="text" name="duty"  class="formText" value="${vo.duty}"/></td>
					<th>职称</th>
					<td><input type="text" name="techTitle"  class="formText" value="${vo.techTitle}"/></td>
				</tr>
				<tr>
					<th>证件类型</th>
					<td><input type="text" name="credentialsType"  class="formText" value="${vo.credentialsType}"/></td>
					<th>证件编号</th>
					<td><input type="text" name="credentialsNo"  class="formText" value="${vo.credentialsNo}"/></td>
				</tr>
				<tr>
					<th>手机</th>
					<td><input type="text" name="mobile"  class="formText" value="${vo.mobile}"/></td>
					<th>电话</th>
					<td><input type="text" name="telephone"  class="formText" value="${vo.telephone}"/></td>
				</tr>
				<tr>
					<th>民族</th>
					<td><input type="text" name="nation"  class="formText" value="${vo.nation}"/></td>
					<th>到岗日期</th>
					<td><input type="text" name="workDate"  class="formText" value="${vo.workDate}"/></td>
				</tr>
				<tr>
					<th>学历</th>
					<td><input type="text" name="education"  class="formText" value="${vo.education}"/></td>
					<th>专业</th>
					<td><input type="text" name="profession" class="formText" value="${vo.profession}"/></td>
				</tr>
				<tr>
					<th>性别</th>
					<td><input type="text" name="sex"  class="formText" value="${vo.sex}"/></td>
					<th>生日</th>
					<td><input type="text" name="birthday"  class="formText" value="${vo.birthday}"/></td>
				</tr>
				<tr>
					<th>邮箱</th>
					<td><input type="text" name="email"  class="formText" value="${vo.email}"/></td>
					<th>住址</th>
					<td><input type="text" name="address"  class="formText" value="${vo.address}"/></td>
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