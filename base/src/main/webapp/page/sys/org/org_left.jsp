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
<body>
	<div class="bar">
		组织信息
	</div>
	<div class="body">
		<div class="buttonbar">
			<a class="btn pull_left" onclick="window.location.reload();">刷新</a>
		</div>	
		<ul id="zTree" turl="orgTree.do" curl="${basePath}sys/org/page.do?pid" ></ul>
	</div>
</body>
</html>