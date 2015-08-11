/***
 *	BZB Admin JavaScript
 *
 *	http://bzb
 *
 *	Copyright © 2012 bzb All Rights Reserved.
 **/

bzb = {
	base: "",
	currencySign: "￥",
	currencyUnit: "元",
	priceScale: "2",
	priceRoundType: "roundHalfUp"
};

// 编辑器
if(typeof(KE) != "undefined") {
	KE.show({
		id: "editor",
		height: "400px",
		items: ['source', '|', 'fullscreen', 'undo', 'redo', 'print', 'cut', 'copy', 'paste','plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright','justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript','superscript', '|', 'selectall', '-','title', 'fontname', 'fontsize', '|', 'textcolor', 'bgcolor', 'bold','italic', 'underline', 'strikethrough', 'removeformat', '|', 'image', 'flash', 'media', 'advtable', 'hr', 'emoticons', 'link', 'unlink'],
		imageUploadJson: bzb.base + "/admin/file!ajaxUpload.action",
		fileManagerJson: bzb.base + "/admin/file!ajaxBrowser.action",
		allowFileManager: true,
		autoSetDataMode: true
	});
}

// 货币格式化
function currencyFormat(price) {
	price = setScale(price, bzb.priceScale, bzb.priceRoundType);
	return bzb.currencySign + price + bzb.currencyUnit;
}

$().ready( function() {

	/* ---------- List ---------- */
	
	var $listForm = $("#listForm");// 列表表单
	if ($listForm.size() > 0) {
		var $searchButton = $("#searchButton");// 查找按钮
		var $allCheck = $("#listTable input.allCheck");// 全选复选框
		var $listTableTr = $("#listTable tr:gt(0)");
		var $idsCheck = $("#listTable input[name='ids']");// ID复选框
		var $deleteButton = $("#deleteButton");// 删除按钮
		var $pageNumber = $("#pageNumber");// 当前页码
		var $pageSize = $("#pageSize");// 每页显示数
		var $sort = $("#listTable .sort");// 排序
		var $orderBy = $("#orderBy");// 排序字段
		var $order = $("#order");// 排序方式
		
		// 全选
		$allCheck.click( function() {
            if ( $(this).prop('checked') ) {
                $idsCheck.prop('checked',true);
                $deleteButton.attr("disabled", false);
                $listTableTr.addClass("checked");
            }
            else {
                $idsCheck.prop('checked',false);
                $deleteButton.attr("disabled", true);
                $listTableTr.removeClass("checked");
            }
		});
		
		// 无复选框被选中时,删除按钮不可用
		$idsCheck.click( function() {
			var $this = $(this);
			if ($this.attr("checked")) {
				$this.parent().parent().addClass("checked");
				$deleteButton.attr("disabled", false);
			} else {
				$this.parent().parent().removeClass("checked");
				var $idsChecked = $("#listTable input[name='ids']:checked");
				if ($idsChecked.size() > 0) {
					$deleteButton.attr("disabled", false);
				} else {
					$deleteButton.attr("disabled", true)
				}
			}
		});
		
		// 批量删除
		$deleteButton.click( function() {
			var url = $(this).attr("url");
			var $idsCheckedCheck = $("#listTable input[name='ids']:checked");
			$.dialog({type: "warn", content: "您确定要删除吗?", ok: "确 定", cancel: "取 消", modal: true, okCallback: batchDelete});
			function batchDelete() {
				$.ajax({
					url: url,
					data: $idsCheckedCheck.serialize(),
					type: "POST",
					dataType: "json",
					cache: false,
					success: function(data) {
						if (data.status == "success") {
							$idsCheckedCheck.parent().parent().remove();
						}
						$deleteButton.attr("disabled", true);
						$allCheck.attr("checked", false);
						$idsCheckedCheck.attr("checked", false);
						$.message({type: data.status, content: data.message});
					}
				});
			}
		});
	
		// 查找
		$searchButton.click( function() {
			$pageNumber.val("1");
			$listForm.submit();
		});
	
		// 每页显示数
		$pageSize.change( function() {
			$pageNumber.val("1");
			$listForm.submit();
		});
	
		// 排序
		$sort.click( function() {
			var $currentOrderBy = $(this).attr("name");
			if ($orderBy.val() == $currentOrderBy) {
				if ($order.val() == "") {
					$order.val("asc")
				} else if ($order.val() == "desc") {
					$order.val("asc");
				} else if ($order.val() == "asc") {
					$order.val("desc");
				}
			} else {
				$orderBy.val($currentOrderBy);
				$order.val("asc");
			}
			$pageNumber.val("1");
			$listForm.submit();
		});
	
		// 排序图标效果
		if ($orderBy.val() != "") {
			$sort = $("#listTable .sort[name='" + $orderBy.val() + "']");
			if ($order.val() == "asc") {
				$sort.removeClass("desc").addClass("asc");
			} else {
				$sort.removeClass("asc").addClass("desc");
			}
		}
		
		// 页码跳转
		$.gotoPage = function(id) {
			$pageNumber.val(id);
			$listForm.submit();
		}
	}
	
});

/* 创建Form对象，初始化target和inputs，如果oOptions存在，那么将对表单进行初始化 */
var Form = function(sSelector,oOptions){
	this.target = $(sSelector);
	this.inputs = {};

	var _arrInputs = this.target.find(':input[name]');
	for(var iIndex = 0;iIndex<_arrInputs.length; iIndex++){
		var _oInput = $(this);
		var _sInputName = _oInput.attr('name');
		this.inputs[_sInputName] = _oInput;
	}
	
	if(oOptions){
		this.init(oOptions);
	}
}

/* 

初始化form表单 
{
	url : '',									// 表单提交地址
	data : 'http://xxx'/{aa:cc},				// 表单数据
	filter : {
		beforeLoad : function(oParams){},		// 加载数据之前执行
		afterLoad : function(oData){},			// 加载数据之后执行
		beforeSubmit : function(oParams){},		// 数据提交之前执行
		afterSubmit : function(err,oData){}		// 数据提交之后执行
	},
	validate : function(){						// 数据验证
		
	}
}
*/
Form.prototype.init = function(oOptions){
	var _target = this.target;

	/* 如果url和method不存在，则从表单属性获取 */
	if(!oOptions.url){oOptions.url = _target.attr('action');}
	if(!oOptions.method){oOptions.method = _target.attr('method');}

	var _defaultOptions = {
		'url' : null,
		'method' : 'post',
		'data' : null,
		'filter' : {
			'beforeLoad' : function(oParams){return oParams;},
			'afterLoad' : function(resp){return resp;},
			'beforeSubmit' : function(oParams){return oParams;},
			'afterSubmit' : function(err,resp){if(err){/* 提交失败*/}else{return resp;}}
		},
		'validate' : function(oParams){return true;}
	}
	oOptions = $.extend(true,{},_defaultOptions,oOptions);
	this.options = oOptions;

	// 如果初始化数据存在，则加载数据
	if(oOptions.data){
		this.data(oOptions.data);
	}

	// 绑定提交事件
	_target.unbind('submit');
	_target.bind('submit',function(){
		var params = this.data();
		var validated = oOptions.validate(params);
		if(validated){
			params = oOptions.filter.beforeSubmit(params);
			$.ajax({
				url : oOptions.url,
				type : oOptions.method,
				dataType : 'json',
				data : params,
				error : function(err){
					oOptions.filter.afterSubmit(err);
				},
				success : function(resp){
					oOptions.filter.afterSubmit(null,resp);
				}
			})
		}
		return false;
	});

	
}

/*
 加载表单数据
*/
Form.prototype.data = function(oData){

	// 参数存在，加载数据
	if(oData){
		if(typeof(oData)=='string'){
			// 远程加载数据
			var _params = {};
			_params = this.options.filter.beforeLoad(_params);
			$.getJSON(oData,_params,function(resp){
				resp = this.options.filter.afterLoad(resp);
				this.data(resp);
			});
		}else{
			// 获取对象类型数据
			this.target.find(":input").each(function(){
				var name = $(this).attr("name");
				if(null != name && '' != name){
					var nameArr = name.split(".");
					var value = oData;
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
	}

	// 参数不存在，获取数据
	if(!oData){
		var params = {};
		var _element = this.target;
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
	}
};

// 表单清空
Form.prototype.clear = function(){
	this.target.find(':input')  
	 .not(':button, :submit, :reset, :hidden')  
	 .val('')  
	 .removeAttr('checked')  
	 .removeAttr('selected');
}