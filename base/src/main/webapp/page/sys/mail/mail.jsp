<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<body>
	<div class="bar">
		邮件记录列表
		&nbsp;总记录数: ${pageResult.pageBean.totalRows} (共 ${pageResult.pageBean.totalPages} 页)
	</div>
	<%@ include file="../../include/status.jsp"%>
	<div class="body">
		<ul id="tab" class="tab">
			<li onclick="window.location.href='page.do?type=sms'">
				<input type="button" value="短信信息" hidefocus/> 
			</li>
			<li onclick="#">
				<input type="button" class="current" value="邮件信息" hidefocus /> 
			</li>
		</ul>
		<div class="s_blank"></div>
		<form id="listForm" action="page.do" method="post">
			<input type="hidden" value="${vo.type}" name="type" />
			<div class="searchBar">
				<div class="normalsearchbar" style="">
					<label>查找：</label>
					<select name="queryColumn">
						<option value="receiver">收件人</option>
						<option value="busInfo">模块</option>
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
					<th class="check" width="5%">
						<input type="checkbox" class="allCheck" />
					</th>
					<th width="5%"></th>
					<th>
						<a href="javascript:void(0)" class="sort" name="receiver" hidefocus>收件人</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="subject" hidefocus>主题</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="content" hidefocus>内容</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="status" hidefocus>状态</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="sendTime" hidefocus>发送时间</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="busInfo" hidefocus>模块</a>
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
						<td>
							<input type="checkbox" name="ids" value="${e.id}" />
						</td>
						<td>${s.count + (pageResult.pageBean.currentPage-1) * pageResult.pageBean.pageSize}</td>
						<td>${e.receiver}</td>
						<td>${e.subject}</td>
						<td>${e.content}</td>
						<td>${e.status}</td>
						<td>${e.sendTime}</td>
						<td>${e.busInfo}</td>
						<td>${e.sort}</td>
						<td>
							<a href="show.do?id=${e.id}" title="查看" class="caozuobtn"><i><img src="${stylePath}images/caozuo_icon_chakan.png"></i></a>
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