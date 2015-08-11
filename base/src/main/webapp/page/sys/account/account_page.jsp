<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../include/common.jsp"%>
</head>
<body>
	<div class="bar">
		账户列表&nbsp;总记录数: ${pageResult.pageBean.totalRows} (共 ${pageResult.pageBean.totalPages} 页)
	</div>
	<%@ include file="../../include/status.jsp"%>
	<div class="body">
		<ul id="tab" class="tab">
			<li onclick="window.location.href='${basePath}sys/user/page.do'">
				<input type="button" value="人员信息" hidefocus/> 
			</li>
			<li onclick="#">
				<input type="button" class="current" value="账户信息" hidefocus /> 
			</li>
		</ul>
		<div class="s_blank"></div>
		<form id="listForm" action="page.do" method="post">
			<div class="listBar">
				<div class="searchBar">
				<div class="normalsearchbar" style="">
					<label>查找：</label>
					<select name="queryColumn">
						<option value="name">
							名称
						</option>
					</select>
					<jsp:include page="../../include/page_select.jsp" />
				</div>
				<div class="buttonbar">
		        	<a class="btn pull_left" href="#" onclick="alert('导出')">导出</a>
				</div>	
			</div>	
			<table id="listTable" class="listTable">
				<tr>
					<th class="check">
						<input type="checkbox" class="allCheck" />
					</th>
					<th width="5%"></th>
					<th>
						<a href="javascript:void(0)" class="sort" name="code" hidefocus>用户名称</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="loginName" hidefocus>登录名称</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="isUsed" hidefocus>是否可用</a>
					</th>
					<th width="5%">
						<a href="javascript:void(0)" class="sort" name="sort" hidefocus>序号</a>
					</th>
					<th width="10%">
						<span>操作</span>
					</th>
				</tr>
				<c:forEach items="${pageResult.resultList}" var="e" varStatus="s">
					<tr>
						<td>
							<input type="checkbox" name="ids" value="${e.id}" />
						</td>
						<td>${s.count + (pageResult.pageBean.currentPage-1) * pageResult.pageBean.pageSize}</td>
						<td>${e.userVo.name}</td>
						<td>${e.loginName}</td>
						<td>
							<c:if test="${'Y' eq e.isUsed}"><span class="trueIcon"/></c:if>
							<c:if test="${'N' eq e.isUsed}"><span class="falseIcon"/></c:if>
						</td>
						<td>${e.sort}</td>
						<td>
							<a href="edit.do?id=${e.id}&userVo.id=${e.userVo.id}"  title="编辑" class="caozuobtn"><i><img src="${stylePath}/images/caozuo_icon_bianji.png"></i></a>
							<a href="#" onclick="del('update2del.do?ids=${e.id}')" title="删除" class="caozuobtn"><i><img src="${stylePath}/images/caozuo_icon_shanchu.png"></i></a>
						</td>
					</tr>
					 </c:forEach>
				</table>
			<%@ include file="../../include/page.jsp"%>
		</form>
	</div>
</body>
</html>