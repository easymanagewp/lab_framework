<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="validateErrorContainer" class="validateErrorContainer">
	<div class="validateErrorTitle">下列信息填写有误,请重新填写</div>
	<ul></ul>
</div>
<c:if test="${!empty status}">
<div id="message" class="message">
	<div class="messageTitle">${status.status}</div>
</div>
</c:if>