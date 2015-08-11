<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../include/common.jsp"%>
<script type="text/javascript" src="${basePath}static/ztree/tool-1.0.0.js"></script>
<script type="text/javascript" src="${basePath}static/ztree/tool-1.0.0.css"></script>
<script type="text/javascript" src="${basePath}static/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${basePath}static/ztree/jquery.ztree.excheck-3.5.js"></script>
<link rel="stylesheet" href="${basePath}static/ztree/zTreeStyle.css" type="text/css">
</head>
<body class="input">
	<div class="bar">
		功能信息
	</div>
	<div class="body">
		<div class="buttonbar">
			<a onclick="window.location.reload();" class="btn pull_left"><spring:message code="global.refresh"/> </a>
		</div>	
		<ul class='v-com-tree' data-datasource="${basePath}/sys/function/all.do" data-filter="filter" data-back="showPage"></ul>
		<script type="text/javascript">
			function filter(node){
				node.target = "main";
				node.url = "/sys/function/page.do?pid="+node.id;
				return node;
			}
		</script>
	</div>
</body>
</html>