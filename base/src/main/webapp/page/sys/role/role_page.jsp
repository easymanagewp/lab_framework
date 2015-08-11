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
						<option value="name">名称</option>
					</select>
					<jsp:include page="../../include/page_select.jsp" />
				</div>
				<div class="buttonbar">
		        	<a class="btn pull_right" href="edit.do"><i><img src="${stylePath}/images/btn_icon_xinzeng.png"></i>添加</a>
				</div>	
			</div>
			<table id="listTable" class="listTable">
				<thead>
				<tr>
					<th width="5%"></th>
					<th>
						<a href="javascript:void(0)" class="sort" name="name" hidefocus>名称</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="describtion" hidefocus>说明</a>
					</th>
					<th width="5%">
						<a href="javascript:void(0)" class="sort" name="sort" hidefocus>序号</a>
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
						<td>${e.name}</td>
						<td>${e.describtion}</td>
						<td>${e.sort}</td>
						<td>
							<a href="edit.do?id=${e.id}"  title="编辑" class="caozuobtn"><i><img src="${stylePath}/images/caozuo_icon_bianji.png"></i></a>
							<a href="#" onclick="del('update2del.do?ids=${e.id}')" title="删除" class="caozuobtn"><i><img src="${stylePath}/images/caozuo_icon_shanchu.png"></i></a>
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