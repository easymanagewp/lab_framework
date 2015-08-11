<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../include/common.jsp"%>
</head>
<body>
	<div class="bar">
		操作日志&nbsp;总记录数: ${pageResult.pageBean.totalRows} (共 ${pageResult.pageBean.totalPages} 页)
	</div>
	<%@ include file="../../include/status.jsp"%>
	<div class="body">
		<form id="listForm" action="page.do" method="post">
			<div class="searchBar">
				<div class="normalsearchbar" style="">
					<label>查找：</label>
					<select name="queryColumn">
						<option value="user">操作人</option>
					</select>
					<jsp:include page="../../include/page_select.jsp" />
				</div>
			</div>	
			<div class="buttonbar">
	        	<a class="btn pull_left" href="#" onclick="alert('导出');">导出</a>
			</div>	
			
			<table id="listTable" class="listTable">
				<thead>
				<tr>
					<th width="5%"></th>
					<th>
						<a href="javascript:void(0)" class="sort" name="busInfo" hidefocus>业务模块</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="content" hidefocus>模块</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="function" hidefocus>功能</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="type" hidefocus>操作类型</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="subject" hidefocus>访问地址</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="user" hidefocus>操作人</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="ip" hidefocus>ip地址</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="time" hidefocus>操作时间</a>
					</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${pageResult.resultList}" var="e" varStatus="s">
					<tr>
						<td>${s.count + (pageResult.pageBean.currentPage-1) * pageResult.pageBean.pageSize}</td>
						<td>${e.busInfo}</td>
						<td>${e.content}</td>
						<td>${e.function}</td>
						<td>${e.type}</td>
						<td>${e.subject}</td>
						<td>${e.user}</td>
						<td>${e.ip}</td>
						<td>${e.time}</td>
					</tr>
					 </c:forEach>
				</table>
				</tbody>
			<%@ include file="../../include/page.jsp"%>
		</form>
	</div>
</body>
</html>