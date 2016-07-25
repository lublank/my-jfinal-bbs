/**
 * 个人主页js
 */
var t = getQueryString("t")==null ? "1" : getQueryString("t");
$(function(){
	getUserInfo();//显示个人信息
	//签到
	checkSign();
	$(".btn_sign").live("click",function(){
		$.post("/bbs/base/signin",function(data){
			if(data.unlogin){
				$(".tips_signin").css('display','block');
				var t=setTimeout("$('.tips_signin').css('display','none');",5000);
				return false;
			}
			if(data.check){
				alert("您已经签到了，明天再签吧^_^");
				return false;
			}
			if(data.success){
				$(".signin_expand").removeClass("no_sign");
				$(".signin_expand").addClass("al_sign");
				$(".btn_sign").removeClass("btn_sign");
				alert("签到成功！");
				return false;
			}else {
				alert("签到失败,请重试!");
				return false;
			}
			
		},'json');
	});
	//发帖
	$("#newspecial").click(function(){
		window.location.href = "/bbs/write";
	});
	
	//根据不同的t值切换不同内容
	if(t==2){//转帖
		$(".thread").removeClass("selected");
		$(".at_m").addClass("selected");
		atMe();
	}else if(t==3){//关注
		$(".thread").removeClass("selected");
		$(".gz_m").addClass("selected");
		myAttention();
	}else if(t==4){//粉丝
		$(".thread").removeClass("selected");
		$(".fan_m").addClass("selected");
		myFans();
	}else if(t==5){//发表
		$(".thread").removeClass("selected");
		$(".tz_myWrit").addClass("selected");
		myPost();
		window.document.title = "个人主页 | 我的发表";//设置页面的标题
	}else if(t==6){//回复
		$(".thread").removeClass("selected");
		$(".tz_myRepl").addClass("selected");
		myReply();
	}else if(t==7){//收藏
		$(".thread").removeClass("selected");
		$(".tz_mySav").addClass("selected");
		mySave();
	}else{//发表
		$(".thread").removeClass("selected");
		$(".tz_myWrit").addClass("selected");
		myPost();
		window.document.title = "个人主页 | 我的发表";//设置页面的标题
//		$(".thread").removeClass("selected");
//		$(".comment_m").addClass("selected");
//		getActive();
	}
	//点击加关注
	$(".addfans").live("click",function(e){
		var uid = $(e.target).attr("uid");
		$.post("/bbs/polo/Checkattention","id="+uid,function(data){
			if(data.unlogin){
				alert("您还没登录呢，登录后再关注吧^_^");
				return false;
			}
			if(!data.check){
				alert(data.error);
			}else{
				alert("关注成功");
			}
			//按钮变成已关注
			$(e.target).html("已关注");
			$(e.target).removeClass("addfans");
		},'json');
	});
	//点击移除粉丝
	$(".removefans").live("click",function(e){
		var uid = $(e.target).attr("uid");
		$.post("/bbs/personal/removefans","id="+uid,function(data){
			if(data.unlogin){
				alert("您还没登录呢，先去登录吧^_^");
				windowl.location.href = "/bbs/login";
				return false;
			}
			if(data.success){
				alert(data.error);
				window.location.reload();
			}else{
				alert(data.error);
				window.location.reload();
			}
		},'json');
	});
	//点击取消关注
	$(".removegz").live("click",function(e){
		var uid = $(e.target).attr("uid");
		$.post("/bbs/personal/cancelAttention","id="+uid,function(data){
			if(data.unlogin){
				alert("您还没登录呢，先去登录吧^_^");
				windowl.location.href = "/bbs/login";
				return false;
			}
			if(data.success){
				alert(data.error);
				window.location.reload();
			}else{
				alert(data.error);
				window.location.reload();
			}
		},'json');
	});
});
//判断是否已签到
function checkSign(){
	$.post("/bbs/polo/checkSign",function(data){
		if(data.check){
			$(".signin_expand").removeClass("no_sign");
			$(".signin_expand").addClass("al_sign");
			$(".btn_sign").removeClass("btn_sign");
		}
	},'json');
}
/*显示登录用户信息*/
function getUserInfo(){
	$.post("/bbs/polo/getUsermessage",function(data){
		if(data.unlogin){
			alert("亲，您还没登陆喔^_^");
			window.location.href = "/bbs/login";
			return false;
		}
		if(data){
			$("#uid").val(data.uid);//用户id
			$(".uname").html(data.username);//用户名字
//			$(".uname").live('click',function(){window.location.href="/bbs/userpage?id="+$("#uid").val();});//点击用户名跳转到他的个人中心
			$("#face").attr("src",data.photo);//用户头像
			$(".num1").html(data.tiecount);//帖子数
			$(".num2").html(data.attention);//关注数
			$(".num3").html(data.fans);//粉丝数
			$(".jifen").html(data.score);//积分
			$(".regtime").html(data.time);//注册时间
		}
	},'json');
}
/*我的主页——获取所有我的动态*/
//function getActive(){
//	$.post("/bbs/base/active", function(data){
//		if(data.list.length){
//			var pagehref = "/bbs/base/active";
//			var gridlist = create.creatActivelist(data);
//			
//			var footer = create.creategridfoot2(data.pageNumber,data.totalPage,data.pageSize,6);
//			
//			var htmlStr = '共有<span class="count-record">'
//				+ data.totalRow
//				+ '</span>条记录，共<span class="count-page">'
//				+ data.totalPage
//				+ '</span>页，当前是第<span class="cur-page">'
//				+ data.pageNumber
//				+ '</span>页';
//			$('.bm_c').html(gridlist);
//			$('.table-info').html(htmlStr);
//			$('.list-footer').html(footer);
//			//绑定分页事件
//			var value = 8;
//			tableEvent.addFootHref("0",value,"bm_c",pagehref,data);
//		}else{
//			var str = "<div class='item'><h3 style='text-align: center;'>暂时无记录</h3></div>";
//			var str1 = '共有<span class="count-record">0</span>条记录，共<span class="count-page">0</span>页，当前是第<span class="cur-page">0</span>页';
//			$('.bm_c').html(str);
//			$('.table-info').html(str1);
//			$('.list-footer').html("");
//		}
//		
//	},'json');	
//}
/*我的转帖*/
function atMe(){
	$.post("/bbs/base/notifyList", function(data){
		if(data.list.length){
			var thead = [ "num", "title", "nickname", "stime" ];
			var theadname = [ "序号", "原主题", "原作者", "转发时间"];
			var colswidth = [ "80px", "auto", "120px", "200px" ];
			
			var infopage =  {hrefcol : 1,	infohref : "/bbs/commentdetail",  argument : ["tid"]};
			var gridlist = create.gridlist(thead, theadname, colswidth, infopage, data);
			
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

			var pageall =  {thead : thead,	theadname : theadname, colswidth : colswidth,  infopage : infopage, pagehref : "/bbs/base/notifyList"};
			var value = 9;
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
/*我的关注*/
function myAttention(){
	$.post("/bbs/polo/Myattention", function(data){
		if(data.list.length){
			var pagehref = "/polo/Myattention";
			var gridlist = create.createGz(data);
			
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
			var value = 3;
			tableEvent.addFootHref("0",value,"bm_c",pagehref,data);
			$(".gz_num").html(data.totalRow);//关注数目
		}else{
			var str = "<div class='item'><h3 style='text-align: center;'>暂时无记录</h3></div>";
			var str1 = '共有<span class="count-record">0</span>条记录，共<span class="count-page">0</span>页，当前是第<span class="cur-page">0</span>页';
			$('.bm_c').html(str);
			$('.table-info').html(str1);
			$('.list-footer').html("");
		}
		
	},'json');	
}
/*我的粉丝*/
function myFans(){
	$.post("/bbs/polo/Myfans", function(data){
		if(data.list.length){
			var pagehref = "/polo/Myfans";
			var gridlist = create.createFans(data);
			
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
			var value = 4;
			tableEvent.addFootHref("0",value,"bm_c",pagehref,data);
			$(".fans_num").html(data.totalRow);//粉丝数目
		}else{
			var str = "<div class='item'><h3 style='text-align: center;'>暂时无记录</h3></div>";
			var str1 = '共有<span class="count-record">0</span>条记录，共<span class="count-page">0</span>页，当前是第<span class="cur-page">0</span>页';
			$('.bm_c').html(str);
			$('.table-info').html(str1);
			$('.list-footer').html("");
		}
		
	},'json');	
}

/*我发表的帖子*/
function myPost(){
	$.post("/bbs/tiezi/getMycard", function(data){
		if(data.list.length){
			var thead = [ "num", "title", "section", "count", "ctime" ];
			var theadname = [ "序号", "主题", "版块", "回复", "发表时间"];
			var colswidth = [ "80px", "auto", "120px", "100px", "200px" ];
			
			var infopage =  {hrefcol : 1,	infohref : "/bbs/commentdetail",  argument : ["tid"]};
			var gridlist = create.gridlist(thead, theadname, colswidth, infopage, data);
			
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

			var pageall =  {thead : thead,	theadname : theadname, colswidth : colswidth,  infopage : infopage, pagehref : "/bbs/tiezi/getMycard"};
			var value = 5;
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
/*我回复的帖子*/
function myReply(){
	$.post("/bbs/tiezi/getMyreverts", function(data){
	if(data.list.length){
		var thead = [ "num", "title", "section", "nickname", "rtime" ];
		var theadname = [ "序号", "主题", "版块", "作者", "回复时间"];
		var colswidth = [ "80px", "auto", "120px", "100px", "200px" ];
		
		var infopage =  {hrefcol : 1,	infohref : "/bbs/commentdetail",  argument : ["tid"]};
		var gridlist = create.gridlist(thead, theadname, colswidth, infopage, data);
		
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

		var pageall =  {thead : thead,	theadname : theadname, colswidth : colswidth,  infopage : infopage, pagehref : "/bbs/tiezi/getMyreverts"};
		var value = 6;
		tableEvent.addFootHref("0",value,"bm_c",pageall,data);
	}else{
		var str = "<div class='item'><h3 style='text-align: center;'>暂时无记录</h3></div>";
		var str1 = '共有<span class="count-record">0</span>记录，共<span class="count-page">0</span>页，当前是第<span class="cur-page">0</span>页';
		$('.bm_c').html(str);
		$('.table-info').html(str1);
		$('.list-footer').html("");
	}
		
	},'json');	
}
/*我收藏的帖子*/
function mySave(){
	$.post("/bbs/tiezi/getMycollect", function(data){
		if(data.list.length){
			var thead = [ "num", "title", "section", "nickname", "ctime" ];
			var theadname = [ "序号", "主题", "版块", "作者", "收藏时间"];
			var colswidth = [ "80px", "auto", "120px", "150px" ];
			var operation = [{	showtext : "取消收藏",
				href : "javascript",	argument : ["tid"]}];
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
			var pageall =  {thead: thead,	theadname: theadname, colswidth: colswidth, operation:operation, infopage: infopage, pagehref: "/bbs/tiezi/getMycollect"};
			var value = 7;
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
