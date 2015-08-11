<%--
	站内信 - 草稿箱
 --%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../include/common.jsp"%>
</head>
<body>
	<div class="bar">
		<spring:message code="sys.message.roughDraft" />&nbsp;
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
			<div class="buttonbar">
			</div>	
			<table id="listTable" class="listTable">
				<thead>
				<tr>
					<th class="check">
						<input type="checkbox" class="allCheck" />
					</th>
					<th width="5%"></th>
					<th>
						<a href="javascript:void(0)" class="sort" name="receiverNames" hidefocus>
							<spring:message code="sys.message.targetUser"/>
						</a>
					</th>
					<th>
						<a href="javascript:void(0)" class="sort" name="subject" hidefocus>
							<spring:message code="sys.message.subject"/>
						</a>
					</th>
					<th width="8%">
						<a href="javascript:void(0)" class="sort" name="type" hidefocus>
							<spring:message code="sys.message.type"/>
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
							<c:forEach items="${e.targetAccountVos}" var="targetUser" varStatus="s2">
								<c:if test="${s2.index<2}">
									【${targetUser.userVo.name}(${targetUser.loginName})】
								</c:if>
								<c:if test="${s2.index==2}">
									...
								</c:if>
							</c:forEach>							
						</td>
						<td>${e.subject}</td>
						<td>
							<c:if test="${e.type=='MSG'}">邮件/消息</c:if>
							<c:if test="${e.type=='ADV'}">公告</c:if>
						</td>
						<td>
							<a href="javascript:void(0)" onclick="del('update2del.do?ids=${e.id}')" title="<spring:message code="global.delete"/>"><spring:message code="global.delete"/></a>
							<a href="edit.do?id=${e.id}" title="<spring:message code="global.edit"/>"><spring:message code="global.edit"/></a>
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