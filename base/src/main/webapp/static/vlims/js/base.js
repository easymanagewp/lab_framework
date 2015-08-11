/***
 *	bzb JavaScript
 *
 *	http://www.bzb.cn
 *
 *	Copyright © 2015 bzb.cn All Rights Reserved. 
 **/

// 解决IE6不缓存背景图片问题
if(!window.XMLHttpRequest) {
	document.execCommand("BackgroundImageCache", false, true);
}

// 添加收藏夹
function addFavorite(url, title) {
	if (document.all) {
		window.external.addFavorite(url, title);
	} else if (window.sidebar) {
		window.sidebar.addPanel(title, url, "");
	}
}

// html字符串转义
function htmlEscape(htmlString) {
	htmlString = htmlString.replace(/&/g, '&amp;');
	htmlString = htmlString.replace(/</g, '&lt;');
	htmlString = htmlString.replace(/>/g, '&gt;');
	htmlString = htmlString.replace(/'/g, '&acute;');
	htmlString = htmlString.replace(/"/g, '&quot;');
	htmlString = htmlString.replace(/\|/g, '&brvbar;');
	return htmlString;
}

// 设置Cookie
function setCookie(name, value) {
	var expires = (arguments.length > 2) ? arguments[2] : null;
	document.cookie = name + "=" + encodeURIComponent(value) + ((expires == null) ? "" : ("; expires=" + expires.toGMTString())) + ";path=" + bzb.base;
}

// 获取Cookie
function getCookie(name) {
	var value = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
	if (value != null) {
		return decodeURIComponent(value[2]);
    } else {
		return null;
	}
}

// 获取url参数
function getURLParam( paras ) { 
    var url = location.href;
    var paraString = url.substring(url.indexOf("?")+1,url.length).split("&"); 
    var paraObj = {} 
    for (i=0; j=paraString[i]; i++){ 
    	paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=")+1,j.length); 
    } 
    var returnValue = paraObj[paras.toLowerCase()];
    if(typeof(returnValue)=="undefined"){ 
    	return ""; 
    }else{ 
    	return returnValue; 
    } 
}

// 删除cookie
function removeCookie(name) {
	var expires = new Date();
	expires.setTime(expires.getTime() - 1000 * 60);
	setCookie(name, "", expires);
}

// 浮点数加法运算
function floatAdd(arg1, arg2) {
	var r1, r2, m;
	try{
		r1 = arg1.toString().split(".")[1].length;
	} catch(e) {
		r1 = 0;
	}
	try {
		r2 = arg2.toString().split(".")[1].length;
	} catch(e) {
		r2 = 0;
	}
	m = Math.pow(10, Math.max(r1, r2));
	return (arg1 * m + arg2 * m) / m;
}

// 浮点数减法运算
function floatSub(arg1, arg2) {
	var r1, r2, m, n;
	try {
		r1 = arg1.toString().split(".")[1].length;
	} catch(e) {
		r1 = 0
	}
	try {
		r2 = arg2.toString().split(".")[1].length;
	} catch(e) {
		r2 = 0
	}
	m = Math.pow(10, Math.max(r1, r2));
	n = (r1 >= r2) ? r1 : r2;
	return ((arg1 * m - arg2 * m) / m).toFixed(n);
}

// 浮点数乘法运算
function floatMul(arg1, arg2) {
	var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
	try {
		m += s1.split(".")[1].length;
	} catch(e) {}
	try {
		m += s2.split(".")[1].length;
	} catch(e) {}
	return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
}

// 浮点数除法运算
function floatDiv(arg1, arg2) {
	var t1 = 0, t2 = 0, r1, r2;    
	try {
		t1 = arg1.toString().split(".")[1].length;
	} catch(e) {}
	try {
		t2 = arg2.toString().split(".")[1].length;
	} catch(e) {}
	with(Math) {
		r1 = Number(arg1.toString().replace(".", ""));
		r2 = Number(arg2.toString().replace(".", ""));
		return (r1 / r2) * pow(10, t2 - t1);
	}
}

// 设置数值精度
function setScale(value, scale, roundingMode) {
	if (roundingMode.toLowerCase() == "roundhalfup") {
		return (Math.round(value * Math.pow(10, scale)) / Math.pow(10, scale)).toFixed(scale);
	} else if (roundingMode.toLowerCase() == "roundup") {
		return (Math.ceil(value * Math.pow(10, scale)) / Math.pow(10, scale)).toFixed(scale);
	} else {
		return (Math.floor(value * Math.pow(10, scale)) / Math.pow(10, scale)).toFixed(scale);
	}
}


$.fn.getParams = function(){
	var params = {};
	var _element = $(this);
	_element.find(":input").each(function(){
		if(null != $(this).attr("name") && '' != $(this).attr("name")){
			if($(this).attr("type")=="radio" || $(this).attr("type")=="checkbox"){
				if(this.checked){
					if(params[$(this).attr("name")]){
						params[$(this).attr("name")] += "," +$.trim($(this).val());
					}else{
						params[$(this).attr("name")] = $.trim($(this).val());
					}
				}
			}else if($(this).val()!=null && $(this).val()!=""){
				params[$(this).attr("name")] = $.trim($(this).val());
			}
		}
	});
	return params;
};
$.fn.loadData = function(row){
	$(this).find(":input").each(function(){
		var name = $(this).attr("name");
		if(null != name && '' != name){
			var nameArr = name.split(".");
			var value = row;
			for(var iIndex = 0;iIndex<nameArr.length;iIndex++){
				 value = value[nameArr[iIndex]];
				 if(value==null){
					 break;
				 }
			}
			if(value!=null){
				if($(this).attr("type")=="radio" || $(this).attr("type")=="checkbox"){
					if($(this).val()==value){
						$(this).attr("checked","checked");
						if(this.onclick){
							this.onclick();
						}
					}
				}else{
					$(this).val(value);
				}
			}
		}
	});
};

$.fn.initForm = function(oParams){
	if(getURLParam('id')){
		// 加载数据
	}
	$(this).loadData(oParams.data);
	if(getURLParam('id')){
		// 提交更新
	}
};

Array.prototype.contains = function(oObj){
	for(iIndex = 0;iIndex<this.length;iIndex++){
		if(this[iIndex] == oObj){
			return true;
		}
	}
	return false;
}
