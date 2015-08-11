<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<input type="text" name="queryValue" value="${pageResult.queryValue}" />
<a class="btn pull_left" onclick="submit()"><spring:message code="global.search"/></a>
&nbsp;&nbsp;
<label><spring:message code="global.viewSize4Page"/>：</label>
<select name="pageBean.pageSize" id="pageSize">
		<option value="15">15</option>
		<option value="50">50</option>
		<option value="100">100</option>
		<option value="999">999</option>
</select>
<script>
	$("#pageSize").val('${pageResult.pageBean.pageSize}');
	var queryColumn = '${pageResult.queryColumn}';
	if(queryColumn.length>0){
		$("select[name='queryColumn']") .val(queryColumn);
	}
</script>