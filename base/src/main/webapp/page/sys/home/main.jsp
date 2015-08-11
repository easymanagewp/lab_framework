<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/jstl/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/jstl/vtag.tld" prefix="vtag" %>  
<!DOCTYPE html>
<html>
<head>
<title>V-LIMS 2015</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="icon" href="favicon.ico" type="image/x-icon" /> 
<meta name="renderer" content="webkit">

<c:set var="stylePath">${basePath}static/vlims/</c:set>
<link href="${stylePath}css/global.css" rel="stylesheet" type="text/css"/>
<link href="${stylePath}css/common.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" data-basePath="${basePath}" src="${stylePath}js/config.js" ></script>
<script type="text/javascript" src="${stylePath}js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${stylePath}js/base.js"></script>
<script type="text/javascript" src="${stylePath}js/frame.js"></script>
<script type="text/javascript" src="${stylePath}js/html5.js"></script>
<script type="text/javascript" src="${basePath}static/requirejs/require.js"></script>
<script type="text/javascript" src="${basePath}static/component/main.min.js"></script>
<script chatset="utf-8" src="${stylePath}js/menu.js"></script>
</head>
<body class="mainbody">
<div class="header">
<span class="logo"><a href="###"><img src="${stylePath}images/logo.png" /></a></span>
<span class="toolbar_l">
  <a href="javascript:void(0)" target="MianFrame"id="shuojin"><img src="${stylePath}images/toolbar_icon_fullscreen.png" /></a>
  <a href="${basePath}/sys/favorite/setting.do" target="MianFrame"><img src="${stylePath}images/toolbar_icon_shezhi.png" /></a>
</span>
<ul class="top_menu">
</ul>
<ul class="toolbar_r" style="display:block;">
  <li><a href="javascript:void(0)" onclick="window.location.href='${basePath}logout.do'"><img src="${stylePath}images/toolbar_icon_layout.png" /></a></li>
  
  <li><a href="###" target="MianFrame"class="usershezhi"><i><img src="${stylePath}images/touxiang_small.png"/></i><vtag:viewUserName/><img src="${stylePath}images/toolbar_icon_xiajiantou.png" /></a>
      <ul class="dropdown_menu xl_usercz">
         <li class="usercz"><a href="###"><i class="userczicon"><img src="${stylePath}images/dw_icon_userinfo.png" /></i><span class="usercz_title">个人资料</span></a></li>
         <li class="usercz"><a href="###"><i class="userczicon"><img src="${stylePath}images/dw_icon_usershezhi.png" /></i><span class="usercz_title">账户设置</span></a></li>
         <li class="usercz"><a href="javascript:void(0)" onclick="window.location.href='${basePath}logout.do'"><i class="userczicon"><img src="${stylePath}images/dw_icon_loginout.png" /></i><span class="usercz_title">注销</span></a></li>
      </ul>
  </li>
  
  <li><a href="###"><img src="${stylePath}images/toolbar_icon_email.png" /></i><i class="dbnum"><span>9</span></i></a>
        <ul class="dropdown_menu xl_email">
         <li class="pointer"><div class="dm_h_arrow"></div></li>
         <li class="item firstitem"><a href="###"><span class="dw_icon_email"></span><span class="dw_content"><h2 class="dw_content_header">我是邮件标题</h2><p class="dw_content_text">邮件内容吧啦吧啦吧啦邮件内容吧啦吧啦吧啦邮件内容吧啦吧啦吧啦邮件内容吧啦吧啦吧吧啦吧啦邮件内容吧啦吧啦吧啦啦</p></span><span class="dw_time"><img src="${stylePath}images/dw_icon_time.png" />1分钟前</span></a></li>
         <li class="item"><a href="###"><span class="dw_icon_email"></span><span class="dw_content"><h2 class="dw_content_header">我是邮件标题</h2><p class="dw_content_text">邮件内容吧啦吧啦吧啦邮件内容吧啦吧啦吧啦邮件内容吧啦吧啦吧啦邮件内容吧啦吧啦吧啦吧啦吧啦邮件内容吧啦吧啦吧啦</p></span><span class="dw_time"><img src="${stylePath}images/dw_icon_time.png" />10分钟前</span></a></li>
         <li class="item"><a href="###"><span class="dw_icon_email"></span><span class="dw_content"><h2 class="dw_content_header">我是邮件标题</h2><p class="dw_content_text">邮件内容吧啦吧啦吧啦邮件内容吧啦吧啦吧啦邮件内容吧啦吧啦吧啦邮件内容吧啦吧啦吧啦吧啦吧啦邮件内容吧啦吧啦吧啦</p></span><span class="dw_time"><img src="${stylePath}images/dw_icon_time.png" />1小时前</span></a></li>
         <li class="dm_footer"><a href="###">查看所有邮件</a></li>
      </ul>
  </li>
  
  <li><a href="###"><img src="${stylePath}images/toolbar_icon_daiban.png" /></i><i class="dbnum"><span>99</span></i></a>
      <ul class="dropdown_menu xl_dbsx">
         <li class="pointer"><div class="dm_h_arrow"></div></li>
         <li class="dm_header"><span>您有15条新的待办事项消息</span></li>
         <li class="dbsx"><a href="###"><i class="dbsxicon"><img src="${stylePath}images/dw_icon_dbsx.png" /></i><span class="dbsx_title">我是邮件标题</span><span class="dbsx_time"><img src="${stylePath}images/dw_icon_time.png" />1小时前</span></a></li>
         <li class="dbsx"><a href="###"><i class="dbsxicon"><img src="${stylePath}images/dw_icon_dbsx.png" /></i><span class="dbsx_title">我是邮件标题</span><span class="dbsx_time"><img src="${stylePath}images/dw_icon_time.png" />1小时前</span></a></li>
         <li class="dbsx"><a href="###"><i class="dbsxicon"><img src="${stylePath}images/dw_icon_dbsx.png" /></i><span class="dbsx_title">我是邮件标题</span><span class="dbsx_time"><img src="${stylePath}images/dw_icon_time.png" />1小时前</span></a></li>
         <li class="dbsx"><a href="###"><i class="dbsxicon"><img src="${stylePath}images/dw_icon_dbsx.png" /></i><span class="dbsx_title">我是邮件标题</span><span class="dbsx_time"><img src="${stylePath}images/dw_icon_time.png" />1小时前</span></a></li>
         <li class="dbsx"><a href="###"><i class="dbsxicon"><img src="${stylePath}images/dw_icon_dbsx.png" /></i><span class="dbsx_title">我是邮件标题</span><span class="dbsx_time"><img src="${stylePath}images/dw_icon_time.png" />1小时前</span></a></li>
         <li class="dm_footer"><a href="###">查看所有待办事项</a></li>
      </ul>
  </li>
</ul>
</div>
<div class="menubox">
<div class="userinfo">
  <div class="user_touxiang">
    <span class="tou_mask"></span>
    <img src="${stylePath}images/touxiang_large.png" class="user_tx"/>
  </div>
  <ul class="userinfolist">
    <li><h2>欢迎您的到来</h2></li>
    <li><h3> <vtag:viewUserName/> </h3></li>
    <li><span><vtag:viewRoleName/></span></li>
  </ul>
</div>
<ul class="menu_tree">
  <li><a href="###"><i class="munulisticon"><img src="${stylePath}images/menu_icon_pc.png" /></i><span class="menuliname">待办事项(展开)</span><i class="dbnum"><span>99</span></i></a>
      <ul class="submenu">
         <li><a href="###">待办事项一<i class="dbnum"><span>55</span></i></a></li>
         <li><a href="###">待办事项二<i class="dbnum"><span>55</span></i></a></li>
      </ul>
  </li>
</ul>
</div>
<div class="mianframe">
<iframe src="" name="MianFrame" scrolling="no" id="MianFrame" width="100%" height="99.9%" marginheight=0 frameborder=0 vspace=0 hspace=0 style="background-color=transparent" allowtransparency="true" > </iframe>
</div>
</body>

</html>