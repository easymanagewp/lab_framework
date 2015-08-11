<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../include/common.jsp"%>
</head>
<body>
	<div class="bar">
		收藏功能&nbsp;总记录数: ${pageResult.pageBean.totalRows} (共 ${pageResult.pageBean.totalPages} 页)
	</div>
	<%@ include file="../../include/status.jsp"%>
	<div class="body">
		<form id="listForm" action="page.do" method="post">
			 <div class="searchBar">
				<div class="normalsearchbar" style="">
					<label>查找：</label>
					<select name="queryColumn">
						<option value="roleFun.funtion.name">
							功能
						</option>
						<option value="account.user.name">
							用户
						</option>
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
						<a href="javascript:void(0)" class="sort" name="account.user.name" hidefocus>账户</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="account.loginName" hidefocus>用户</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="roleFun.funtion.name" hidefocus>功能</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="alias" hidefocus>别名</a>
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
						<td class="c">${s.count + (pageResult.pageBean.currentPage-1) * pageResult.pageBean.pageSize}</td>
						<td>${e.accountVo.userVo.name}</td>
						<td>${e.accountVo.loginName}</td>
						<td>${e.functionVo.name}</td>
						<td>${e.alias}</td>
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