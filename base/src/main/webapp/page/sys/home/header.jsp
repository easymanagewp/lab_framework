<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../include/common.jsp"%>
<script type="text/javascript"></script>
</head>
<body class="header">
	<div class="body">
		<div class="bodyLeft">
			<div class="logo"></div>
		</div>
		<div class="bodyRight">
			<div class="link">
				<span class="welcome">
					<strong></strong>&nbsp;张三&nbsp;
				</span>
				<!-- <a href="" target="mainFrame">哈哈</a>|
            	<a href="" target="_blank">哈哈</a>|
                <a href="" target="_blank">哈哈</a>|
                <a href="" target="_blank">哈哈</a>|
                <a href="" target="_blank">哈哈</a> -->
			</div>
			<div id="menu" class="menu">
				<ul>
						<li class="menuItem">
							<a href="${basePath}sys/unit/edit.do?id=0" target="mainFrame" hidefocus>单位信息</a>
						</li>
						<li class="menuItem">
							<a href="${basePath}sys/org/frame.do" target="mainFrame" hidefocus>组织信息</a>
						</li>
						<li class="menuItem">
							<a href="${basePath}sys/function/frame.do" target="mainFrame" hidefocus>功能信息</a>
						</li>
						<li class="menuItem">
							<a href="${basePath}sys/role/page.do" target="mainFrame" hidefocus>角色信息</a>
						</li>
						<li class="menuItem">
							<a href="${basePath}sys/duty/page.do" target="mainFrame" hidefocus>岗位信息</a>
						</li>
						<li class="menuItem">
							<a href="${basePath}sys/user/page.do" target="mainFrame" hidefocus>人员/账户</a>
						</li>
						<li class="menuItem">
							<a href="${basePath}sys/mail/page.do?type=sms" target="mainFrame" hidefocus>短信/邮件</a>
	            		</li>
	            		<li class="menuItem">
							<a href="${basePath}sys/upload/page.do" target="mainFrame" hidefocus>上传记录</a>
	            		</li>
	            		<li class="menuItem">
							<a href="${basePath}sys/message/frame.do" target="mainFrame" hidefocus>站内信</a>
	            		</li>
	            		<li class="menuItem">
							<a href="${basePath}sys/code/page.do" target="mainFrame" hidefocus>公共代码</a>
	            		</li>
	            		<li class="menuItem">
							<a href="${basePath}sys/template/page.do" target="mainFrame" hidefocus>导入/出模板</a>
	            		</li>
	            		<li class="menuItem">
							<a href="${basePath}sys/log/page.do" target="mainFrame" hidefocus>日志</a>
	            		</li>
	            </ul>
	            <div class="info">
					<a class="profile" href="#" target="mainFrame">个人信息</a>
					<a class="logout" href="${basePath}logout.do" target="_top">退出</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>