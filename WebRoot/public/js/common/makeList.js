/**
 * 生成列表js 
 * @author:Blank
 * @creaTime:2014-05-24
 */
(function(window, undefined){
	var document = window.document,
	$ = window.jQuery;
	var create = {
		/**
		 * table列表形式
		 * 带单元格超链接，带分页
		 */
		gridlist : function(thead, theadname, colswidth, infopage,data){
			var tbody = data.list;
			var numcol = thead.length, numrow = tbody.length, gridstr = "<div id='grid'><table style='width:100%;'><tbody><tr class='thead'>", i, j;
			gridstr += "<th style='width:" + colswidth[0] + ";'>" + theadname[0] + "</th>";
			//产生表头
			for (i = 1; i < numcol; i++) {
				gridstr += "<th style='width:" + colswidth[i] + ";'>"
						+ theadname[i] + "</th>";
			}
			if(numrow>0){
				//产生每一行
				for (i = 0; i < numrow; i++) {
					gridstr += "<tr><td>" + tbody[i][thead[0]] + "</td>";
					for (j = 1; j < numcol; j++) {
						if (j == infopage.hrefcol) {
							gridstr += "<td><a style='color:#01B2FE;' href='" + infopage.infohref + "?"
									+ infopage.argument + "="
									+ tbody[i]["tid"] + "' target='_blank'>"
									+ tbody[i][thead[j]] + "</a></td>";
						} else {
							gridstr += "<td>" + tbody[i][thead[j]] + "</td>";
						}
					}
					gridstr += "</td></tr>";
				}
			}else{
				gridstr += "<tr><td colspan='"+numcol+"' style='text-align:center;padding-top:8px;padding-bottom:8px;'>该列表暂时还没有记录"
				+ "</td></tr>";
		}
		gridstr += "</tbody></table></div>";
		return gridstr;
		},
		
		/**
		 * 管理员专用
		 * table列表形式
		 * 带单元格超链接，带分页
		 */
		gridlist2 : function(thead, theadname, colswidth, operation, infopage,tbody){
			var numcol = thead.length, numrow = tbody.list.length, numoperation = operation.length, gridstr = "<div id='grid'><table style='width:100%;'><tbody><tr class='thead'>", i, j;
			gridstr += "<th style='width:" + colswidth[0]
					+ ";'>" + theadname[0] + "</th>";
			//产生表头
			for (i = 1; i < numcol; i++) {
				gridstr += "<th style='width:" + colswidth[i] + ";'>"
						+ theadname[i] + "</th>";
			}
			gridstr += "<th style='width: 100px;'>操作</th>";

			gridstr += "</tr>";

			if(numrow>0){
			for (i = 0; i < numrow; i++) {
				gridstr += "<tr><td style='text-align:center;'>"
						+ (i+1)
						+ "</td>";
				for (j = 1; j < numcol; j++) {
					if (j == infopage.hrefcol) {
						if(infopage.argument instanceof Array){
							gridstr += "<td><a href='" + infopage.infohref + "?"
							        + infopage.argument[0] + "="
									+ tbody.list[i][infopage.argument[0]] + "' style='color:#01B2FE;' "
									+ (infopage.target ? "target='" + infopage.target + "'" : "")
									+ ">"
									+ tbody.list[i][thead[j]] + "</a></td>";
						}else{
							gridstr += "<td><a href='" + infopage.infohref + "?"
									+ infopage.argument + "="
									+ tbody.list[i][infopage.argument] + "' style='color:#01B2FE;' "
									+ (infopage.target ? "target='" + infopage.target + "'" : "")
									+ ">"
									+ tbody.list[i][thead[j]] + "</a></td>";
						}
					} else {
						gridstr += "<td style='text-align:center;'>" + tbody.list[i][thead[j]] + "</td>";
					}
				}
				gridstr += "<td style='text-align:center;'>";
				for (var k = 0; k < numoperation; k++) {
					if(operation[k].argument instanceof Array){
						if(operation[k].href=="javascript"){//判断收藏还是隐藏
							gridstr += "<a  style='color:#01B2FE;' href='/bbs/deleteSave?"
							+ operation[k].argument[0] + "="
							+ tbody.list[i][operation[k].argument[0]] +"'>"
							+ operation[k].showtext + "</a><span> </span>";
						}else{
							if(tbody.list[i][operation[k].style[0]]){//判断该帖子是否已隐藏
								gridstr += "<a  style='color:#01B2FE;' href='/bbs/tiezi/unhidden?"
								+ operation[k].argument[0] + "="
								+ tbody.list[i][operation[k].argument[0]] +"'>取消隐藏"
								+ "</a><span> </span>";
							}else{
								gridstr += "<a  style='color:#01B2FE;' href='/bbs/tiezi/hidden?"
								+ operation[k].argument[0] + "="
								+ tbody.list[i][operation[k].argument[0]] +"'>隐藏"
								+ "</a><span> </span>";
							}
							
						}
					}else{
						gridstr += "<a href='" + operation[k].href + "?"
						+ operation[k].argument + "="
						+ tbody.list[i][operation[k].argument] + "'>"
						+ operation[k].showtext + "</a><span> </span>";
					}																		
				}
				gridstr += "</td></tr>";
			}//产生每一行
			}else{
				var colNum = numcol + 1;
				gridstr += "<tr><td colspan='"+colNum+"' style='text-align:center;padding-top:8px;padding-bottom:8px;'>该列表暂时还没有记录"
					+ "</td></tr>";
			}

			gridstr += "</tbody></table></div>";
			return gridstr;
		},
		/**
		 * 产生分页1 
		 */
		creategridfoot : function(pageArgument) {
			var footstr = '<div id="tablefoot"><a id="jumphref">go</a><span>跳转到第<input type="text" id="jump" style="width: 18px;height: 11px"/>页</span>'
					+ '<a href="javascript:;" id="endpage">尾页</a><a href="javascript:;" id="pagedown">下一页</a>'
					+ '<a href="javascript:;" id="pageup">上一页</a><a href="javascript:;" id="firstpage">首页</a>'
					+ '<span>共'
					+ pageArgument.totalPage
					+ '页</span><span>第'
					+ pageArgument.pageSize
					+ '页</span>'
					+ '<div class="clear"></div></div>';
			return footstr;
		},
		
		/**
		 * 分页2，带可输每页显示条数
		 * @param pageArgument
		 * @returns HTML
		 * @pagecount:总页数
		 * @pagenum:每页显示数
		 * @curpage:当前页
		 */
		creategridfoot2 : function(curpage,pagecount,pagenum,size){
			if (size === undefined) size = 10; 
			var pageForeach = (pagecount > size)?size : pagecount;
			//构建翻页数组
			var arr = new Array(pageForeach);
			//第一页与最后一页
			arr[0] = 1;
			arr[pageForeach-1] = pagecount;
			//leftFloat为当前页所在位置与第一页间距
			var leftFloat = Math.floor((pageForeach-2)/2),
				rightFloat = (pageForeach-2) - leftFloat - 1;
			if(curpage - leftFloat > 1 && curpage + rightFloat < pagecount){
				var startPage = curpage - leftFloat;
				for(var i=1;i<pageForeach-1;i++){
					arr[i] = startPage;
					startPage++;
				}
			}
			else if (curpage - leftFloat <= 1) {
				for(var i=1;i<pageForeach-1;i++){
					arr[i] = i + 1;
				}
			}
			else if (curpage + rightFloat >= pagecount) {
				var startPage = pagecount - pageForeach + 2;
				for(var i=1;i<pageForeach-1;i++){
					arr[i] = startPage;
					startPage++;
				}
			}
			var htmlStr = '<div id="page"><div class="right-page">';
			//上一页
			if(curpage == 1)  htmlStr += '<a href="javascript:void(0);" class="disable-page up-page com-page">上一页</a>';
			else htmlStr += '<a href="javascript:void(0);" class="up-page com-page">上一页</a>';
			var arrText = [];
			for (var i = 0;i<arr.length;i++){
				if(i == 0 && arr[1] != undefined && arr[1] > 2){
					arrText[0] = arr[i]+"...";
				}
				else if (i == arr.length - 1 && arr.length - 2 > 0 && arr[i-1] != undefined && arr[i-1] + 1 < arr[i]) {
					arrText[i] = "..."+arr[i];
				}
				else{
					arrText[i] = arr[i];
				}
			}
			for ( var int = 0; int < arr.length; int++) {
				if (curpage == arr[int]) {
					htmlStr += '<a href="javascript:void(0);" class="num-page disable-page com-page" data-page="'+arr[int]+'">'+arrText[int]+'</a>';
				}
				else{
					htmlStr += '<a href="javascript:void(0);" class="num-page com-page" data-page="'+arr[int]+'">'+arrText[int]+'</a>';
				}
			}
			//下一页
			if(curpage == pagecount)  htmlStr += '<a href="javascript:void(0);" class="disable-page down-page com-page">下一页</a>';
			else htmlStr += '<a href="javascript:void(0);" class="down-page com-page">下一页</a>';
			htmlStr += '<label class="com-page"><input type="text" class="px page-pagenum" size="2" '
				     +'value="'+pagenum+'" '
				     +'title="输入每页显示的条数，按回车快速跳转"/>'
				     + '<span title="当前每页显示 '
					 + pagenum
					 +'条"> 条/页</span></label>'
				     +'<label class="com-page"><input type="text" class="px go-page-input" size="2" title="输入页码，按回车快速跳转"/>'
					 + '<span title="共 '
					 + pagecount
					 +' 页"> / '
					 +pagecount
					 +' 页</span></label>'
					 +'<a class="go-page-a com-page" href="javascript:void(0);">GO</a></div></div><div style="clear:both;"></div>';
			return htmlStr;
		},
		
		/**
		 * 产生表格
		 */
		creategrid : function(thead, theadname, colswidth, infopage,
				tbody) {
			var gridstr = this.gridlist(thead, theadname, colswidth,
					infopage, tbody.list);
			gridstr += this.creategridfoot(tbody.pageNumber, tbody.pagecount,
					tbody.totalPage,tbody.pageSize);
			return gridstr;
		},
		
		/**
		 * 产生帖子的list列表
		 */
		
		createlist : function(tbody){
			var listbody ="<table summary='forum_22' cellspacing='0' cellpadding='0'>"
						 +"<tbody>"
						 +"<tr style='height: 50px;background-color: rgba(221, 245, 255, 0.5);''>"
						 +"<th style='width:50px;'>头像</th>"
			  			 +"<th style='width:350px;'>主题</th>"
						 +"<th style='width:70px;'>作者</th>"
						 +"<th style='width:60px;'>回复</th>"
						 +"<th style='width:120px;'>最新回复时间</th>"
						 +"</tr>";
			var rownum = tbody.list.length;
			if(rownum>0){
				for (var i=0; i < rownum; i++) {
					listbody += "<tr class='listhover_forum'>"
							 +"<td class='userhead'>"
							 +"<a class='avatar' >"
							 +"<img src='"+tbody.list[i].photo+"' />"
							 +"</a>"
							 +"</td>"
							 +"<td>"
							 +"<div class='maincont_list'>"
							 +"<a style='color:#32a5e7;' href='commentdetail?tid="+tbody.list[i].tid+"' title='"+tbody.list[i].title+"' class='under'>"+tbody.list[i].title+"</a>"
							 +"</div>"
							 +"</td>"
							 +"<td class='latestreply_list'><span><a href='/bbs/userpage?id="+tbody.list[i].uid+"' class='author_list' >"+tbody.list[i].nickname+"</a></span></td>"
							 +"<td class='latestreply_list'><span>"+tbody.list[i].count+"</span></td>"
							 +"<td class='latestreply_list'>"
							 +"<span class='date_list'>"+tbody.list[i].rtime+"</span>"
							 +"</td>"
							 +"</tr>";
					
				}
				
			}else{
			listbody += "<tr class='listhover_forum'>"
					 +"<td class='userhead' colspan='5'>"
					 +"<h3 >暂时还没记录，请重新搜索"
					 +"</h3>"
					 +"</td>";
			}
			listbody +="</tbody></table>";
			return listbody;
		},
		
		/**
		 * 组装“我的动态”列表
		 * @param tbody
		 */
		creatActivelist : function(tbody){
			var listbody ="<ul>";
								
			var rownum = tbody.list.length;
			if(rownum>0){
				for (var i=0; i < rownum; i++) {
					listbody += "<li>"
							 +"<div class='header'>"
							 +"<a href='/bbs/personal?id="+tbody.list[i].id+"'>"
							 +"<img alt='"+tbody.list[i].name+"' src='"+tbody.list[i].photo+"'></a></div>"
							 +"<div class='content_gz'>"
							 +"<div class='useId'>"
							 +"<a href='/bbs/personal?id="+tbody.list[i].id+"'>"+tbody.list[i].name+"</a>"
							 +"</div>"
							 +"<div class='detail_gz'>回复了我的帖子 <em><a target='_blank' href='/bbs/commentdetail?id="+tbody.list[i].cardid+"'>"+tbody.list[i].title+"</a></em>"
							 +"<p>"+tbody.list[i].time+"</p>"
							 +"<div class='clear'></div>"
							 +"</div></div></li>";
				};
				
			}else{
			listbody += "<li>"
					 +"<h3 >暂时还没记录</h3>"
					 +"</li>";
			}
			listbody +="</ul>";
			return listbody;
		},
		
		/**
		 * 组装回复帖子列表
		 * @param tbody
		 * @returns
		 */
		createReply : function(tbody){
			var listbody ="";
			
			var rownum = tbody.list.length;
			if(rownum>0){
				for (var i=0; i < rownum; i++) {
					listbody += "<div class='item'>"
							 +"<div class='authi'>"
							 +"<a class='name_uinfo' href='/bbs/userpage?id="+tbody.list[i].id+"' target='_blank' title='"+tbody.list[i].nickname+"'>"+tbody.list[i].nickname+"</a>"
							 +"<span>发表于：<span title='"+tbody.list[i].rtime+"' class='ctime'>"+tbody.list[i].rtime+"</span></span></div>"
							 +"<div class='recontent'>"+tbody.list[i].reply+"</div></div>";
				};
				
			}else{
			listbody += "<div class='item'><h3 >暂时还没评论记录</h3></div>";
			}
		
			return listbody;
		},
		/**
		 * 我的粉丝
		 * @param tbody
		 * @returns {String}
		 */
		createFans : function(tbody){
			
			var listbody = "<h3><em class='fans_num'>0</em> 个粉丝</h3><ul>";
			var rownum = tbody.list.length;
			if(rownum>0){
				for (var i=0; i < rownum; i++) {
					listbody += "<li>"
							 +"<div class='header'>"
							 +"<a href='/bbs/userpage?id="+tbody.list[i].uuid+"'>"
							 +"<img alt='"+tbody.list[i].nickname+"' src='"+tbody.list[i].photo+"'></a></div>"
							 +"<div class='fans_info'>"
							 +"<div class='useId'>"
							 +"<a href='/bbs/userpage?id="+tbody.list[i].uuid+"'>"+tbody.list[i].nickname+"</a>"
							 +"</div>"
							 +"<div style='float:right;'>"
							 +"<a href='javascript:;' class='bluebtn normalbtn addfans' uid='"+tbody.list[i].uuid+"'>加关注</a>"
							 +"<a href='javascript:;' class='bluebtn normalbtn removefans' uid='"+tbody.list[i].uuid+"'>移除粉丝</a>"
							 +"</div></div></li>";
				};
				
			}else{
			listbody += "<li>"
					 +"<h3 >暂时还没记录</h3>"
					 +"</li>";
			}
			listbody +="</ul>";
			return listbody;
		},
		/**
		 * 我的关注
		 * @param tbody
		 * @returns {String}
		 */
		createGz: function(tbody){
			
			var listbody = "<h3><em class='gz_num'>0</em> 个关注</h3><ul>";
			var rownum = tbody.list.length;
			if(rownum>0){
				for (var i=0; i < rownum; i++) {
					listbody += "<li>"
							 +"<div class='header'>"
							 +"<a href='/bbs/userpage?id="+tbody.list[i].uuid+"'>"
							 +"<img alt='"+tbody.list[i].nickname+"' src='"+tbody.list[i].photo+"'></a></div>"
							 +"<div class='fans_info'>"
							 +"<div class='useId'>"
							 +"<a href='/bbs/userpage?id="+tbody.list[i].uuid+"'>"+tbody.list[i].nickname+"</a>"
							 +"</div>"
							 +"<div style='float:right;'>"
							 +"<a href='javascript:;' class='bluebtn normalbtn removegz' uid='"+tbody.list[i].uuid+"'>取消关注</a>"
							 +"</div></div></li>";
				};
				
			}else{
			listbody += "<li>"
					 +"<h3 >暂时还没记录</h3>"
					 +"</li>";
			}
			listbody +="</ul>";
			return listbody;
		},
		
		
		
		setTableSortField : function(url, para, callback){
			$.ajax({
				type : "post",
				url : url,
				async : false,
				data : para,
				dataType : "json",
				success : function(data2){
					var footer = create.creategridfoot2(data2.pageNumber,data2.totalPage,data2.pageSize,6);
					var htmlStr = '共有<span class="count-record">'
						+ data2.totalRow
						+ '</span>条记录，共<span class="count-page">'
						+ data2.totalPage
						+ '</span>页，当前是第<span class="cur-page">'
						+ data2.pageNumber
						+ '</span>页';
					$('.table-info').html(htmlStr);
					$('.list-footer').html(footer);
					callback(data2);
				}
			});
		},
		
		bindTheadClick : function(url, config, callback){
			var _this = this;
			_this.setTableSortField(url, config, callback);
		},
	};
	
	window.create = create;
})(window);