(function(window, undefined){
/**
 * 为分页绑定事件
 */
	var tableEvent = {
			addFootHref : function(id,value,divid,pageall,data){
				var pageArgument = data;
				//绑定上一页事件
				$(".up-page").click(function(){
					if($(this).hasClass("disable-page")) return false;
					else {
						var tableSortConfig = {"id" : id, "curpage" : pageArgument.pageNumber - 1,"pagesize" : pageArgument.pageSize
											   };
						var href = value>4 ? pageall.pagehref : pageall ;
						create.bindTheadClick(href,tableSortConfig,function(data){	
							var gridstr = "";
							if(value==1){//主页
								gridstr += create.createlist(data);
							}else if(value==2){//回帖
								gridstr += create.createReply(data);
							}else if(value==3){//关注
								gridstr += create.createGz(data);
							}else if(value==4){//粉丝
								gridstr += create.createFans(data);
							}else if(value==5||value==6||value==9){//我的发表、我的回复、转帖
								gridstr += create.gridlist(pageall.thead, pageall.theadname, pageall.colswidth, pageall.infopage,data);
							}else if(value==7||value==8){//我的收藏、管理帖子
								gridstr += create.gridlist2(pageall.thead, pageall.theadname, pageall.colswidth, pageall.operation, pageall.infopage,data);
							}
							$('.'+divid).html(gridstr);
							
							//再次绑定分页事件
							tableEvent.addFootHref(id,value,divid,pageall, data);
						});
					}
				});
				//绑定下一页
				$(".down-page").click(function(){
					if($(this).hasClass("disable-page")) return false;
					else {
						var tableSortConfig = {"id" : id, "curpage" : pageArgument.pageNumber + 1,"pagesize" : pageArgument.pageSize
											   };
						var href = value>4 ? pageall.pagehref : pageall ;
						create.bindTheadClick(href,tableSortConfig,function(data){	
							var gridstr = "";
							if(value==1){//主页
								gridstr += create.createlist(data);
							}else if(value==2){//回帖
								gridstr += create.createReply(data);
							}else if(value==3){//关注
								gridstr += create.createGz(data);
							}else if(value==4){//粉丝
								gridstr += create.createFans(data);
							}else if(value==5||value==6||value==9){//我的发表、我的回复、转帖
								gridstr += create.gridlist(pageall.thead, pageall.theadname, pageall.colswidth, pageall.infopage,data);
							}else if(value==7||value==8){//我的收藏、管理帖子
								gridstr += create.gridlist2(pageall.thead, pageall.theadname, pageall.colswidth, pageall.operation, pageall.infopage,data);
							}
							$('.'+divid).html(gridstr);
							
							//再次绑定分页事件
							tableEvent.addFootHref(id,value,divid,pageall, data);
						});
					}
				});
				//绑定选页
				$(".num-page").click(function(){
					if($(this).hasClass("disable-page")) return false;
					else {
						var gopage = parseInt($(this).attr("data-page"),10);

						var tableSortConfig = {"id" : id, "curpage" : gopage,"pagesize" : pageArgument.pageSize
											   };
						var href = value>4 ? pageall.pagehref : pageall ;
						create.bindTheadClick(href,tableSortConfig,function(data){	
							var gridstr = "";
							if(value==1){//主页
								gridstr += create.createlist(data);
							}else if(value==2){//回帖
								gridstr += create.createReply(data);
							}else if(value==3){//关注
								gridstr += create.createGz(data);
							}else if(value==4){//粉丝
								gridstr += create.createFans(data);
							}else if(value==5||value==6||value==9){//我的发表、我的回复、转帖
								gridstr += create.gridlist(pageall.thead, pageall.theadname, pageall.colswidth, pageall.infopage,data);
							}else if(value==7||value==8){//我的收藏、管理帖子
								gridstr += create.gridlist2(pageall.thead, pageall.theadname, pageall.colswidth, pageall.operation, pageall.infopage,data);
							}
							$('.'+divid).html(gridstr);
							//再次绑定分页事件
							tableEvent.addFootHref(id,value,divid,pageall, data);
						});
					}
				});
				//绑定输入页
				$(".go-page-input").keydown(function(event){
					if(event.keyCode == 13){	
						var gopage = $(this).val(),
							maxPage = parseInt($(".num-page:last").attr("data-page"),10);
						if(!/^\d+$/.test(gopage) || parseInt(gopage,10) < 1 || parseInt(gopage,10) > maxPage){
							$(this).val("");
							return ;
						}
						else{
							var tableSortConfig = {"id" : id, "curpage" : gopage,"pagesize" : pageArgument.pageSize
												   };
							var href = value>4 ? pageall.pagehref : pageall ;
							create.bindTheadClick(href,tableSortConfig,function(data){	
								var gridstr = "";
								if(value==1){//主页
									gridstr += create.createlist(data);
								}else if(value==2){//回帖
									gridstr += create.createReply(data);
								}else if(value==3){//关注
									gridstr += create.createGz(data);
								}else if(value==4){//粉丝
									gridstr += create.createFans(data);
								}else if(value==5||value==6||value==9){//我的发表、我的回复、转帖
									gridstr += create.gridlist(pageall.thead, pageall.theadname, pageall.colswidth, pageall.infopage,data);
								}else if(value==7||value==8){//我的收藏、管理帖子
									gridstr += create.gridlist2(pageall.thead, pageall.theadname, pageall.colswidth, pageall.operation, pageall.infopage,data);
								}
								$('.'+divid).html(gridstr);
								
								//再次绑定分页事件
								tableEvent.addFootHref(id,value,divid,pageall, data);
							});
							//阻止冒泡
							event.stopPropagation();
						}
					}
				});
				//绑定GO按钮
				$(".go-page-a").click(function(){
					var gopage = parseInt($(".go-page-input").val(), 10),
						maxPage = parseInt($(".num-page:last").attr("data-page"),10),
						minCount = (parseInt(pageArgument.totalPage, 10) - 1) * parseInt(pageArgument.pageSize) + 1,
						pageSize = parseInt($(".page-pagenum").val(), 10);
					if(!/^\d+$/.test(gopage) || gopage < 1 || gopage > maxPage) {
						gopage = 1;
					}
					if(!/^\d+$/.test(pageSize) || pageSize < 1){
						$(".page-pagenum").val(pageArgument.pageSize);
						pageSize = pageArgument.pageSize;
					}
					//判断越界翻页，并阻止
					if((gopage - 1) * pageSize >= minCount){
						gopage = 1;
					}
					var tableSortConfig = {"id" : id, "curpage" : gopage,"pagesize" : pageSize};
					var href = value>4 ? pageall.pagehref : pageall ;
					create.bindTheadClick(href,tableSortConfig,function(data){	
						var gridstr = "";
						if(value==1){//主页
							gridstr += create.createlist(data);
						}else if(value==2){//回帖
							gridstr += create.createReply(data);
						}else if(value==3){//关注
							gridstr += create.createGz(data);
						}else if(value==4){//粉丝
							gridstr += create.createFans(data);
						}else if(value==5||value==6||value==9){//我的发表、我的回复、转帖
							gridstr += create.gridlist(pageall.thead, pageall.theadname, pageall.colswidth, pageall.infopage,data);
						}else if(value==7||value==8){//我的收藏、管理帖子
							gridstr += create.gridlist2(pageall.thead, pageall.theadname, pageall.colswidth, pageall.operation, pageall.infopage,data);
						}
						$('.'+divid).html(gridstr);
						
						//再次绑定分页事件
						tableEvent.addFootHref(id,value,divid,pageall, data);
					});
				});
				//绑定每页显示条数
				$(".page-pagenum").keydown(function(event){
					if(event.keyCode == 13){
						var pageSize = parseInt($(this).val(), 10);
						if(!/^\d+$/.test(pageSize) || pageSize <=0 ){
							$(this).val(pageArgument.pageSize);
							return ;
						}
						var tableSortConfig = {"id" : id, "curpage" : 1,"pagesize" : pageSize};
						var href = value>4 ? pageall.pagehref : pageall ;
						create.bindTheadClick(href,tableSortConfig,function(data){	
							var gridstr = "";
							if(value==1){//主页
								gridstr += create.createlist(data);
							}else if(value==2){//回帖
								gridstr += create.createReply(data);
							}else if(value==3){//关注
								gridstr += create.createGz(data);
							}else if(value==4){//粉丝
								gridstr += create.createFans(data);
							}else if(value==5||value==6||value==9){//我的发表、我的回复、转帖
								gridstr += create.gridlist(pageall.thead, pageall.theadname, pageall.colswidth, pageall.infopage,data);
							}else if(value==7||value==8){//我的收藏、管理帖子
								gridstr += create.gridlist2(pageall.thead, pageall.theadname, pageall.colswidth, pageall.operation, pageall.infopage,data);
							}
							$('.'+divid).html(gridstr);
							
							//再次绑定分页事件
							tableEvent.addFootHref(id,value,divid,pageall, data);
						});
						//阻止冒泡
						event.stopPropagation();
					}
				});
			},
	};
	window.tableEvent = tableEvent;
})(window);