$(function(){
	var $menuTree = $('.menu_tree'),
	parseMenu = function(parentMenu,menus){
		var existed = new Array();
		var arrMenus = [];
		for(var iIndex=0;iIndex<menus.length;iIndex++){
			if(parentMenu && menus[iIndex].parentId==parentMenu.id){
				if(parentMenu.childer==null){
					parentMenu.childer=[];
				}
				parseMenu(menus[iIndex],menus);
				parentMenu.childer.push(menus[iIndex]);
				existed.push(menus[iIndex].id);
			}else if(!parentMenu && !existed.contains(menus[iIndex].parentId)){
				var isEx = true;
				for(var iIndex2=0;iIndex2<menus.length;iIndex2++){
					var menu2 = menus[iIndex2];
					if(menu2.id == menus[iIndex].parentId){
						isEx = false;
						break;
					}
				}
				if(isEx){
					parseMenu(menus[iIndex],menus);
					arrMenus.push(menus[iIndex]);
					existed.push(menus[iIndex].id);
				}
			}
			
		}
		if(arrMenus.length>0){
			createMenu($menuTree,arrMenus);
		}
	}
	createMenu = function(parentMenu,menus){
			for(var iIndex=0;iIndex<menus.length;iIndex++){
				var menu = menus[iIndex];
				var $li = $("<li>");
				var $a = $("<a>");
				if(menu.url!=null && menu.url!='' && menu.url!='/'){
					$a.attr('href',menu.url);
					$a.attr('target','MianFrame');
				}else{
					$a.attr('href','javascript:void(0)');
				}
				if(parentMenu == $menuTree){
					var $i = $("<i>");
					$i.addClass('munulisticon')
					$i.append(
							$('<img>').attr('src',basePath + '/static/vlims/images/menu_icon_pc.png')
					)
					$i.appendTo($a);
					$("<span>").addClass('menuliname').text(menu.name).appendTo($a);
					$('<i class="zs_icon"></i>').appendTo($a);
				}else{
					$a.text(menu.name);
				}
				$a.appendTo($li);
				$li.appendTo(parentMenu);
				if(menu.childer && menu.childer.length>0){
					var $ul = $("<ul>").addClass('submenu');
					createMenu($ul,menu.childer);
					$li.append($ul);
				}
			}
	}
	
	require(['http'],function(http){
		
		var loadTopMenu = function(){
			http.Get(basePath + '/sys/favorite.do').success(function(respData){
				if(respData.status==0){
					var resp = respData.result;
					var $topMenu = $('.top_menu');
					$.each(resp,function(){
						var menuItem = this;
						var $li = $("<li>");
						var $a = $("<a>").html('<i><img src="'+basePath+'/static/vlims/images/menu_icon_pc.png"></i>').append('<span>'+menuItem.alias+'</span>');
						if(menuItem.isUsed=="N"){
							$a.attr('href','javascript:void(0)');
							$a.on('click',function(){
								alert("已经被管理员取消访问权限，原因：【"+(menuItem.message || "未知")+"】,可在收藏列表内重新执行保存，以删除此菜单，或者不进行操作，继续保留此菜单的显示");
							})
						}else{
							$a.attr({
								'href':menuItem.functionVo.url,
								"target" : "MianFrame"
							})
						}
						$a.appendTo($li);
						$li.appendTo($topMenu);
					})
				}else{
					alert(respData.message);
				}
			}).error(function(){
				alert('无法加载快捷菜单，请检查网络是否连接或者联系管理员');
			}).go();
		};
		
		http.Get(basePath+'/sys/function.do').success(function(respData){
			if(respData.status==0){
				parseMenu(null,respData.result);
				menuTree();
				loadTopMenu();
			}else{
				alert(respData.message);
			}
		}).error(function(){
			alert('无法加载系统菜单，请检查网络是否连接或者联系管理员');
		}).go();
		
		
	})
})