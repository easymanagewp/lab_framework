<%--
	站内信 - 公告
 --%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../include/common.jsp"%>
</head>
<body>
	<div class="bar">
		<spring:message code="sys.message.gonggao" />
		&nbsp;
		<spring:message code="global.pageResult" arguments="${pageResult.pageBean.totalRows},${pageResult.pageBean.totalPages}" argumentSeparator=","/>
	</div>
	<%@ include file="../../include/status.jsp"%>
	<div class="body">
		<form id="listForm" action="page.do" method="post">
			<input type="hidden" name="position" value="${vo.position}">
			<div class="searchBar">
				<div class="normalsearchbar">
					<label><spring:message code="global.search.label"/>：</label>
					<select name="queryColumn">
						<option value="subject">
							<spring:message code="sys.message.subject"/>
						</option>
						<option value="sender.name">
							<spring:message code="sys.message.sendUser"/>
						</option>
					</select>
					<jsp:include page="../../include/page_select.jsp" />
				</div>
			</div>	
		
			<table id="listTable" class="listTable">
				<thead>
				<tr>
					<th class="check">
						<input type="checkbox" class="allCheck" />
					</th>
					<th width="5%"></th>
					<th width="10%">
						<a href="javascript:void(0)" class="sort" name="readFlag" hidefocus>
							<spring:message code="sys.message.readFlag"/>
						</a>
					</th>
					<th width="20%">
						<a href="javascript:void(0)" class="sort" name="sender" hidefocus>
							<spring:message code="sys.message.sendUser"/>
						</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="subject" hidefocus>
							<spring:message code="sys.message.subject"/>
						</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="sendTime" hidefocus>
							<spring:message code="sys.message.receiveTime"/>
						</a>
					</th>
					<th width="10%">
						<span>
							<spring:message code="global.operation"/>
						</span>
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
						<td>
							<c:if test="${'N' eq e.readFlag}">未读</c:if>
							<c:if test="${'Y' eq e.readFlag}">已读</c:if>		
						</td>
						<td>${e.messageVo.senderVo.userVo.name}(${e.messageVo.senderVo.loginName})</td>
						<td>
							<c:if test="${'N' eq e.readFlag}"><strong><a href="update2read.do?id=${e.id}">${e.messageVo.subject}</a></strong></c:if>
							<c:if test="${'Y' eq e.readFlag}"><a href="show.do?id=${e.id}">${e.messageVo.subject}</a></c:if>
						</td>
						<td>${e.messageVo.sendTime}</td>
						<td>
							<a href="javascript:void(0)" onclick="del('update2del.do?ids=${e.id}')" title="<spring:message code="global.delete"/>"><spring:message code="global.delete"/></a>
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