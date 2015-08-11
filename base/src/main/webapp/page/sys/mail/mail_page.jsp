<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../include/common.jsp"%>
<c:if test="${'sms' eq vo.type}">
	<%@ include file="sms.jsp"%>
</c:if>
<c:if test="${'mail' eq vo.type}">
	<%@ include file="mail.jsp"%>
</c:if>