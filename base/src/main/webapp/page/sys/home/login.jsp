<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/jstl/c.tld" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="Author" content="  " />
<meta name="Copyright" content=" " />
<title>用户登录</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="icon" href="favicon.ico" type="image/x-icon" /> 
<link href="${basePath}/static/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${basePath}/static/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${basePath}static/common/js/jquery.js"></script>
<script type="text/javascript" data-basePath="${basePath}" src="${basePath}static/vlims/js/config.js" ></script>
<script type="text/javascript"></script>
</head>
<body class="login">
	<div class="blank"></div>
	<div class="blank"></div>
	<div class="blank"></div>
	<div class="body">
		<form id="loginForm" action="login.do" method="post">
            <table class="loginTable">
            	<tr>
            		<td rowspan="4">
            			<img src="${basePath}static/admin/images/login_logo.png" alt="管理中心" />
            		</td>
            		<td colspan="3">
            			<c:if test="${!empty status}">
							<div id="message" class="message">
								<div class="messageTitle">${status.status}:${status.message}</div>
							</div>
						</c:if>
					</td>
            	</tr>
            	<tr>
                    <th>
                    	用户名:
                    </th>
					<td>
                    	<input type="text" id="username" name="username" class="formText" value="admin"/>
                    </td>
                </tr>
                <tr>
					<th>
						密&nbsp;&nbsp;&nbsp;码:
					</th>
                    <td>
                    	<input name="password" type="password" id="password" class="formText" value="admin" />
                    </td>
                </tr>
                <tr>
                	<th>
                		验证码:
                	</th>
                    <td>
                    	<input type="text" id="captcha" name="captcha" class="formText captcha" />
                   		<img id="captchaImage" class="captchaImage" width="80" height="35" src="patchca/img.do" alt="点击换一张" title="点击换一张" onclick="this.src='patchca/img.do?a='+new Date()"/>
                    </td>
                </tr>
                <tr>
                	<td></td>
                	<th></th>
                    <td>
                    	<label>
                    		<input type="checkbox" id="isRememberUsername" />&nbsp;记住用户名
                    	</label>
                    	
                    </td>
                </tr>
                <tr>
                	<td>
                		&nbsp;
                	</td>
                	<th>
                		&nbsp;
                	</th>
                    <td>
                        <input type="button" class="homeButton" value="" onclick="window.open('/')" hidefocus />
                        <input type="submit" class="submitButton" value="登 录" hidefocus />
                    </td>
                </tr>
            </table>
            <div class="powered"></div>
				<div class="link">
					<a href="#">技术支持</a> |
					<a href="#">西安瑞铂软件科技有限公司</a> |
					<a href="#">联系我们</a> |
					<a href="#">驱动下载 </a> 
				</div>
        </form>
	</div>
</body>
</html>