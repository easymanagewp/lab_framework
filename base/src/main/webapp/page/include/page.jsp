<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<c:if test="${0==pageResult.pageBean.totalRows}">
<div class="noRecord">没有找到任何记录!!</div>
</c:if>
<c:if test="${0<pageResult.pageBean.totalRows}">
<script type="text/javascript" src="${basePath}static/common/js/jquery.pager.js"></script>
<script type="text/javascript">
$().ready( function() {
	var $pager = $("#pager");
	$pager.pager({
		pagenumber: ${pageResult.pageBean.currentPage},
		pagecount: ${pageResult.pageBean.totalPages},
		buttonClickCallback: $.gotoPage
	});
})
</script>
<div class="pagerBar">
	<div class="pager">
		<span id="pager"></span>
	</div>
</div>
<input type="hidden" name="pageBean.currentPage" id="pageNumber" value="${pageResult.pageBean.currentPage}" />
<input type="hidden" name="order" id="order" value="${pageResult.order}" />
<input type="hidden" name="orderBy" id="orderBy" value="${pageResult.orderBy}" />
</c:if>
