<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../include/common.jsp"%>
</head>
<body>
	<div class="bar">
		人员列表&nbsp;总记录数: ${pageResult.pageBean.totalRows} (共 ${pageResult.pageBean.totalPages} 页)
	</div>
	<%@ include file="../../include/status.jsp"%>
	<div class="body">
			<ul id="tab" class="tab">
				<li onclick="#">
					<input type="button" class="current" value="人员信息" hidefocus /> 
				</li>
				<li onclick="window.location.href='${basePath}sys/account/page.do'">
					<input type="button" value="账户信息" hidefocus/> 
				</li>
			</ul>
			<div class="s_blank"></div>
			<form id="listForm" action="page.do" method="post">
			<div class="searchBar">
				<div class="normalsearchbar" style="">
					<label>查找：</label>
					<select name="queryColumn">
						<option value="no">
						编号
					</option>
					<option value="name">
						名称
					</option>
					</select>
					<jsp:include page="../../include/page_select.jsp" />
				</div>
			</div>		
			<div class="buttonbar">
				<a class="btn pull_left" href="#" onclick="alert('数据导入');">导入</a>
				<a class="btn pull_left" href="#" onclick="alert('数据导出');">导出</a>
	        	<a class="btn pull_right" href="edit.do"><i><img src="${stylePath}/images/btn_icon_xinzeng.png"></i>添加</a>
			</div>	
			<table id="listTable" class="listTable">
				<thead>
				<tr>
					<th width="5%"></th>
					<th>
						<a href="javascript:void(0)" class="sort" name="no" hidefocus>编号</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="name" hidefocus>用户名称</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="credentialsNo" hidefocus>证件编号</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="duties" hidefocus>岗位</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="duty" hidefocus>职务</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="mobile" hidefocus>手机</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="education" hidefocus>学历</a>
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
						<td>${e.no}</td>
						<td>${e.name}</td>
						<td>${e.credentialsNo}</td>
						<td>
							${e.dutiesVo.name}
						</td>
						<td>${e.duty}</td>
						<td>${e.mobile}</td>
						<td>${e.education}</td>
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