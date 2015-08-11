<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../include/common.jsp"%>
</head>
<body>
	<div class="bar">
		角色列表&nbsp;总记录数: ${pageResult.pageBean.totalRows} (共 ${pageResult.pageBean.totalPages} 页)
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
						<a href="javascript:void(0)" class="sort" name="busInfo" hidefocus>模块</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="trueName" hidefocus>名称</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="type" hidefocus>类型</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="size" hidefocus>大小</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="createTime" hidefocus>操作时间</a>
					</th>
					<th width="10%">
						<span>操作</span>
					</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${pageResult.resultList}" var="e" varStatus="s">
					<tr>
						<td>${s.count + (pageResult.pageBean.currentPage-1) * pageResult.pageBean.pageSize}</td>
						<td>${e.busInfo}</td>
						<td>${e.trueName}</td>
						<td>${e.type}</td>
						<td>${e.size}</td>
						<td>${e.createTimeStr}</td>
						<td>
							<a href="download.do?filePath=${e.path}&trueName=${e.trueName}.xls">详情</a>
						</td>
					</tr>
					 </c:forEach>
				</tbody>
				</table>
			<%@ include file="../../include/page.jsp"%>
		</form>
	</div>
</body>
</html>