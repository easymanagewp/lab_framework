<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../include/common.jsp"%>
</head>
<body>
	<div class="bar">
	模板列表&nbsp;总记录数: ${pageResult.pageBean.totalRows} (共 ${pageResult.pageBean.totalPages} 页)
	</div>
	<%@ include file="../../include/status.jsp"%>
	<div class="body">
		<form id="listForm" action="page.do" method="post">
			<div class="searchBar">
				<div class="normalsearchbar" style="">
					<label>查找：</label>
					<select name="queryColumn">
						<option value="type">
						类别
					</option>
					<option value="templateName">
						模板编码
					</option>
					<option value="busInfo">
						业务模块
					</option>
					</select>
					<jsp:include page="../../include/page_select.jsp" />
				</div>
			</div>	
			<div class="buttonbar">
				<a class="btn pull_left" href="#" onclick="window.location.href='import.do'">导入<a>
				<a class="btn pull_left" href="#" onclick="window.location.href='export.do?source=sys-template-daorudaochu.xls&target=导入导出模板信息列表.xls'" >导出</a>
	        	<a class="btn pull_right" href="edit.do"><i><img src="${stylePath}/images/btn_icon_xinzeng.png"></i>添加</a>
			</div>	
			<table id="listTable" class="listTable">
				<tr>
					<th width="5%"></th>
					<th>
						<a href="javascript:void(0)" class="sort" name="type" hidefocus>类别</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="busInfo" hidefocus>业务模块</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="name" hidefocus>模板名称</a>
					</th>
					<th >
						<a href="javascript:void(0)" class="sort" name="templateName" hidefocus>模板编码</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="versionNo" hidefocus>更新版本</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="createTimeStr" hidefocus>操作时间</a>
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
						<td>${s.count + (pageResult.pageBean.currentPage-1) * pageResult.pageBean.pageSize}</td>
						<td>${e.type}</td>
						<td>${e.busInfo}</td>
						<td>${e.name}</td>
						<td>${e.templateName}</td>
						<td>${e.versionNo}</td>
						<td>${e.createTimeStr}</td>
						<td>${e.sort}</td>
						<td>
							<a href="edit.do?id=${e.id}"  title="编辑" class="caozuobtn"><i><img src="${stylePath}/images/caozuo_icon_bianji.png"></i></a>
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