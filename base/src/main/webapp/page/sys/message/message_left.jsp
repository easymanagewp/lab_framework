<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<%@ include file="../../include/common.jsp"%>
</head>
<body>
	<div class="bar">
		<spring:message code="sys.message.title" />
	</div>
	<%@ include file="../../include/status.jsp"%>
	<div class="body" style="width:150px;">
		<div class="buttonbar" >
        	<a class="btn pull_right" href="edit.do" target="rightMsg">&nbsp;&nbsp;&nbsp;<spring:message code="sys.message.xiexin"/>&nbsp;&nbsp;&nbsp;</a>
        	<a class="btn pull_right" href="inbox_page.do" target="rightMsg">&nbsp;&nbsp;&nbsp;<spring:message code="sys.message.shouxin"/>&nbsp;&nbsp;&nbsp;</a>
		</div>	
		<table id="listTable" class="listTable">
			<tr>
				<td>
					<a href="gonggao_page.do" target="rightMsg">
						<spring:message code="sys.message.gonggao" />
					</a>
				</td>
			</tr>
			<tr>
				<td>
					<a href="inbox_page.do" target="rightMsg">
						<spring:message code="sys.message.inbox" />
					</a>
				</td>
			</tr>
			<tr>
				<td>
					<a href="outbox_page.do" target="rightMsg">
						<spring:message code="sys.message.outbox" />
					</a>
				</td>
			</tr>
			<tr>
				<td>
					<a href="rough_draft_page.do" target="rightMsg">
						<spring:message code="sys.message.roughDraft" />
					</a>
				</td>
			</tr>
			<tr>
				<td>
					<a href="hui_shou_zhan_page.do" target="rightMsg">
						<spring:message code="sys.message.huiShouZhan" />
					</a>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>