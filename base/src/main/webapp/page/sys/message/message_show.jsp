<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../include/common.jsp"%>
<link rel="stylesheet" href="${basePath}/static/common/editor/themes/default/default.css" />
<link rel="stylesheet" href="${basePath}/static/common/editor/plugins/code/prettify.css" />
<script charset="utf-8" src="${basePath}/static/common/editor/kindeditor.js"></script>
<script charset="utf-8" src="${basePath}/static/common/editor/lang/zh_CN.js"></script>
<script charset="utf-8" src="${basePath}/static/common/editor/plugins/code/prettify.js"></script>
<script charset="utf-8" src="${basePath}/static/common/editor/plugins/multiimage/multiimage.js"></script>
<!--<script type="text/javascript" src="${basePath}/static/common/editor/kindeditor.js"></script>-->
<script type="text/javascript">
KindEditor.ready(function(K) {
        var options = { 
            cssPath : '${basePath}/static/common/editor/plugins/code/prettify.css',
			height: "200px",
			width:"700px",
			items: ['source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
					'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
					'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
					'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
					'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
					'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat'],
			//uploadJson : '${basePath}/admin/editor/upload.do',
			//fileManagerJson : '${basePath}/admin/editor/browser.action',
			allowFileManager : false,
			pasteType :1,
			//imageTabIndex:1, 
			//newlineTag:"br",
			//resizeType : 0, //文本框不可拖动 
			afterCreate : function() { //kindeditor创建后，将编辑器的内容设置到原来的textarea控件里 
			this.sync(); }, 
			afterChange: function(){ //编辑器内容发生变化后，将编辑器的内容设置到原来的textarea控件里 
			this.sync(); }, 
			afterBlur : function() { //编辑器聚焦后，将编辑器的内容设置到原来的textarea控件里 
			this.sync(); } 
		}; 
		var editor = K.create('#editor', options); 
		editor.sync();
		prettyPrint();
	});

</script>
</head>
<body>
	<%@ include file="../../include/status.jsp"%>
	<div class="bar">
		消息/公告查看
	</div>
	<div class="body">
		<form id="validateForm" action="${action}" method="post">
			<input id="position" type="hidden" name="position" value="1">
			<table class="inputTable">
				<tr>
					<th>发件人</th>
					<td>${vo.senderVo.name}</td>
					<th>日期</th>
					<td>${vo.sendTime}</td>
				</tr>
				<tr>
					<th>收件人</th>
					<td>${vo.receiverNames}</td>
					<th>类别</th>
					<td>${vo.type}</td>
				</tr>
				<tr>
					<th>主题</th>
					<td colspan="3">${vo.subject}</td>
				</tr>
				<tr>
					<th>内容</th>
					<td colspan="3">
						<textarea id="editor" name="content" class="editor">${vo.content}</textarea>
						<div class="blank"></div>
					</td>
				</tr>
			</table>
			<div class="buttonArea">
				<input type="button" class="formButton" onclick="location.href='edit.do'" value="回 复" hidefocus />&nbsp;&nbsp;
				<input type="button" class="formButton"
					onclick="window.history.back(); return false;" value="返  回"
					hidefocus />
			</div>
		</form>
	</div>
</body>
</html>