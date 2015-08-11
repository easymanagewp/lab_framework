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
<style type="text/css">
	.selectdUser{list-style: none;}
	.selectdUser li{display:inline-block;margin-right:5px;background-color: #ffe6b0;border: 1px solid #ffb951;padding:0px 20px 0px 10px;text-align:center;position: relative;}
	.selectdUser li .del{position: absolute;right:0;top:0;background:#ffe6bb;border-left:1px solid #ffb951;}
</style>
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


	var userItemTemplate,
		selectdUser,
		selectUserBtn,
		validateForm,
		fnSelectUserback=function(nodes){
		$.each(nodes,function(){
			var existedUser = selectdUser.find(":input[name='userId'][value='"+this.id+"']");
			if(!(existedUser) || !(existedUser.length>0)){
				selectdUser.append(userItemTemplate(this));
			}
		})
	},
		fnDeleteUser = function(oEle){
		$(oEle).parent().remove();
		var userIds = selectdUser.find(":input[name='userId']");
		var tempDefaultData = [];
		for(var iIndex=0;iIndex<userIds.length;iIndex++){
			tempDefaultData.push($(userIds[iIndex]).val());
		}
		selectUserBtn.data('default',tempDefaultData.join(','));
	}
	$(function(){
		userItemTemplate = template.render($('#userItemTemplate').html());
		selectdUser = $(".selectdUser");
		selectUserBtn = $('#selectUserBtn');
		validateForm = $('#validateForm');
		validateForm.on('submit',function(){
			var arrSelectedUserIds = selectdUser.find(':input[name="userId"]'),
				curSelectedUserIdInput;
			for(var iIndex=0;iIndex<arrSelectedUserIds.length;iIndex++){
				curSelectedUserIdInput = $(arrSelectedUserIds[iIndex]);
				if($(this).attr('action').indexOf('send.do')!=-1){
					curSelectedUserIdInput.attr('name','targetUserVoList['+iIndex+'].accountVo.id');
				}else{
					curSelectedUserIdInput.attr('name',"targetAccountVos["+iIndex+"].id");
				}
			}
			return true;
		})
	})
</script>
</head>
<body>
	<%@ include file="../../include/status.jsp"%>
	<div class="bar">
		<c:set var="action" value="#" />
		<c:choose>
			<c:when test="${isEdit}">
				<c:set var="action" value="update2rd.do?id=${vo.id}" />	
				修改消息/公告
			</c:when>
			<c:otherwise>
				添加消息/公告
				<c:set var="action" value="save2rd.do" />
			</c:otherwise>
		</c:choose>
	</div>
	<div class="body">
		<script type="text/template" id="userItemTemplate">
			<li>
				{{userName}}({{loginName}})
				<input type="hidden" name="userId" value="{{id}}"/>
				<a class="del" onclick="fnDeleteUser(this)" href="javascript:void(0)">×</a>
			</li>
		</script>
		<form id="validateForm" action="${action}" method="post">
			<table class="inputTable">
			
				<%--收件人 --%>
				<tr>
					<th>
						<spring:message code="sys.message.targetUser"/>
					</th>
					<td>
						<ul class="selectdUser">
							<c:forEach items="${vo.targetAccountVos}" var="targetAccount">
								<li>
									${targetAccount.userVo.name}(${targetAccount.loginName})
									<input type="hidden" name="userId" value="${targetAccount.id}"/>
									<a class="del" onclick="fnDeleteUser(this)" href="javascript:void(0)">×</a>
								</li>
							</c:forEach>
						</ul>
						<a id="selectUserBtn" style="clear:left;" href="javascript:void(0)" class="v-com-account" data-back="fnSelectUserback"><spring:message code="global.select"/></a>
					</td>
				</tr>
				
				<%-- 主题 --%>
				<tr>
					<th>
						<spring:message code="sys.message.subject"/>
					</th>
					<td><input type="text" style="width: 350px" name="subject" class="formText" value="${vo.subject}"/></td>
				</tr>
				
				<%-- 类别 --%>
				<tr>
					<th>
						<spring:message code="sys.message.type"/>
					</th>
					<td>
						<select name="type">
							<option value="0">
								<spring:message code="sys.message.type.message"/>
							</option>
							<option value="1">
								<spring:message code="sys.message.type.notice"/>
							</option>
						</select>
					</td>
				</tr>
				
				<%-- 内容 --%>
				<tr>
					<th>
						<spring:message code="sys.message.content"/>
					</th>
					<td colspan="3">
						<textarea id="editor" name="content" class="editor">${vo.content}</textarea>
						<div class="blank"></div>
					</td>
				</tr>
			</table>
			<div class="buttonArea">
				<%-- 存草稿 --%>
				<a class="btn pull_left" onclick="submit()"><spring:message code="sys.message.cuncaogao"/></a>
				
				<%-- 发送 --%>
				<a class="btn pull_left" onclick="$('form').attr('action','send.do?id=${vo.id}');submit()"><spring:message code="global.send"/></a>
				
				<%-- 返回 --%>
				<a class="btn pull_left" onclick="back()"><spring:message code="global.back"/></a>
			</div>
		</form>
	</div>
</body>
</html>