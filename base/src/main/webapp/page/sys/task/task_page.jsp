<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../include/common.jsp"%>
</head>
<body>
	<div class="bar">
		任务列表&nbsp;总记录数: ${pageResult.pageBean.totalRows} (共 ${pageResult.pageBean.totalPages} 页)
	</div>
	<%@ include file="../../include/status.jsp"%>
	<div class="body">
		<form id="listForm" action="page.do" method="post">
			<div class="searchBar">
				<div class="normalsearchbar" style="">
					<label>查找：</label>
					<select name="queryColumn">
						<option value="tittle">标题</option>
					</select>
					<jsp:include page="../../include/page_select.jsp" />
				</div>
				<div class="buttonbar">
		        	<a class="btn pull_left" href="#" onclick="alert('导出')">导出</a>
				</div>	
			</div>
			<table id="listTable" class="listTable">
				<thead>
				<tr>
					<th width="5%"></th>
					<th>
						<a href="javascript:void(0)" class="sort" name="sender" hidefocus>发件人</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="reciver" hidefocus>收件人</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="tittle" hidefocus>标题</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="content" hidefocus>内容</a>
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
						<td>${e.sender}</td>
						<td>${e.reciver}</td>
						<td>${e.tittle}</td>
						<td>${e.content}</td>
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