<%@ taglib uri="/WEB-INF/tld/jstl/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/jstl/fn.tld" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tld/jstl/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/jstl/vtag.tld" prefix="vtag" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="Author" content="  " />
<meta name="Copyright" content=" " />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="icon" href="favicon.ico" type="image/x-icon" /> 
<meta name="renderer" content="webkit">
<c:set var="stylePath">${basePath}static/vlims/</c:set>
<link href="${stylePath}css/global.css" rel="stylesheet" type="text/css"/>
<link href="${stylePath}css/common.css" rel="stylesheet" type="text/css" />
<link href="${stylePath}css/base.css" rel="stylesheet" type="text/css" />
<link href="${stylePath}css/admin.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${basePath}static/common/js/jquery.js"></script>
<script type="text/javascript" data-basePath="${basePath}" src="${basePath}static/vlims/js/config.js" ></script>
<script type="text/javascript" src="${stylePath}js/common.js"></script>
<script type="text/javascript" src="${stylePath}js/artTemplate/template.js"></script>
<script type="text/javascript" src="${stylePath}/js/base.js"></script>
<script type="text/javascript" src="${stylePath}/js/admin.js"></script>
<script type="text/javascript" src="${stylePath}js/frame.js"></script>
<script type="text/javascript" src="${stylePath}js/html5.js"></script>

<script type="text/javascript" src="${basePath}static/common/js/jquery.validate.js"></script>
<script type="text/javascript" src="${basePath}static/common/js/jquery.validate.methods.js"></script>
<script type="text/javascript" src="${basePath}static/common/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="${basePath}/static/lhgdialog/skins/default.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/static/ztree/zTreeStyle.css" />
<script type="text/javascript" src="${basePath}/static/lhgdialog/lhgdialog.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}/static/ztree/jquery.ztree.all-3.5.min.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${basePath}/static/js/valiform/css/style.css" />
<script tupe="text/javascript" src="${basePath}/static/js/valiform/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="${basePath}static/requirejs/require.js"></script>
<script type="text/javascript" src="${basePath}static/component/main.min.js"></script>

<script type="text/javascript">
	var referer = '${referer}';
	if(referer){
		window.history.back = function(){
			window.location.href = referer;	
		}
	}
</script>