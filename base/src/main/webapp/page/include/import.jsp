<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="bar">数据导入</div>
<div class="body">
	<form id="validateForm" action="import.do" method="post" enctype="multipart/form-data">
		<input name="param" type="hidden" value="<%=request.getParameter("param")%>">
		<table class="inputTable">
			<tr>
				<th>模板文件</th>
				<td><a href="#" onclick="location.href='downtemp.do?templateName=<%=request.getParameter("templateName") %>&trueName=<%=request.getParameter("trueName")%>'"><%=request.getParameter("trueName")%></a></td>
			</tr>
			<tr>
				<th>导入模式</th>
				<td>
					<select name="type">
						<option value="1">追加现有数据</option>
						<option value="2">先清除已有数据再重新导入</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>导入文件</th>
				<td><input type="file" name="file" accept="application/vnd.ms-excel" /></td>
			</tr>
			<tr>
				<th>说明</th>
				<td>请先下载模板文件，按要求填入数据后再进行导入，序号列必须有数据，负责视为无效数据</td>
			</tr>
		</table>
		<div class="buttonArea">
			<a class="btn pull_left" onclick="submit()">确  定</a>
			<a class="btn pull_left" onclick="window.history.back(); ">返回</a>
		</div>
	</form>
</div>