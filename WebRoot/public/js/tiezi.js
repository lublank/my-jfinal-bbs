/**
 * 帖子管理
 */
$(function(){
	//加载帖子列表
	var txt ="";
	getPage(txt);
	//点击搜索按钮
	$("#scbar_btn").click(function(){
		var txt = $("#scbar_txt").val();
		getPage(txt);
	});
	//按回车搜索
	$("#scbar_txt").keydown(function(e){
		var txt = $("#scbar_txt").val();
		if(txt !="" && e.keyCode == 13){
			getPage(txt);
		}
	});
	

	
});
//组装列表
function getPage(txt){
	var searchCondtion = "key=" + txt;
	$.post("/bbs/tiezi/tieList",searchCondtion,function(data){
		if(data.unlogin){
			alert("亲，您还没登陆喔^_^");
			window.location.href = "/bbs/login";
			return false;
		}
		if(data.list.length){
			var thead = [ "num", "title", "section", "nickname", "rtime" ];
			var theadname = [ "序号", "主题", "版块", "作者", "最新回复时间"];
			var colswidth = [ "80px", "auto", "120px", "150px" ];
			var operation = [{	showtext : "隐藏",
				href : "",	argument : ["tid"], style :["style"]}];
			var infopage =  {hrefcol : 1,	infohref : "/bbs/commentdetail",  argument : ["tid"]};
			var gridlist = create.gridlist2(thead, theadname, colswidth, operation, infopage, data);
			
			var footer = create.creategridfoot2(data.pageNumber,data.totalPage,data.pageSize,6);
			
			var htmlStr = '共有<span class="count-record">'
				+ data.totalRow
				+ '</span>条记录，共<span class="count-page">'
				+ data.totalPage
				+ '</span>页，当前是第<span class="cur-page">'
				+ data.pageNumber
				+ '</span>页';
			$('.bm_c').html(gridlist);
			$('.table-info').html(htmlStr);
			$('.list-footer').html(footer);
			//绑定分页事件
			var pageall =  {thead: thead,	theadname: theadname, colswidth: colswidth, operation:operation, infopage: infopage, pagehref: "/bbs/tiezi/tieList"};
			var value = 8;
			tableEvent.addFootHref("0",value,"bm_c",pageall,data);
		}else{
			var str = "<div class='item'><h3 style='text-align: center;'>暂时无记录</h3></div>";
			var str1 = '共有<span class="count-record">0</span>条记录，共<span class="count-page">0</span>页，当前是第<span class="cur-page">0</span>页';
			$('.bm_c').html(str);
			$('.table-info').html(str1);
			$('.list-footer').html("");
		}
			
	},'json');						
}