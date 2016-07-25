/**
 *首页js
 */
var key = "";
$(function(){
	//加载帖子列表
	var txt ="";
	getPage(txt);
	key = $("#skey").val();
	if(key!=""&&key!=null){
		getPage(key);
	}
	//加载推荐阅读、加载活跃会员
	getActive();
	getActiveperson();
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
		return false;
	});
	//全部
	$(".show_all").click(function(){
		$(".show_suggest").removeClass("show_elect");
		$(".show_hot").removeClass("show_elect");
		$(".show_all").addClass("show_elect");
		var txt = "";
		getPage(txt);
	});
	//推荐
	$(".show_suggest").click(function(){
		$(".show_all").removeClass("show_elect");
		$(".show_hot").removeClass("show_elect");
		$(".show_suggest").addClass("show_elect");
		var txt = "1";
		getPage(txt);
	});
	//热门
	$(".show_hot").click(function(){
		$(".show_all").removeClass("show_elect");
		$(".show_suggest").removeClass("show_elect");
		$(".show_hot").addClass("show_elect");
		var txt = "2";
		getPage(txt);
	});
	//发帖
	$("#newspecial").click(function(){
		window.location.href = "/bbs/write";
	});
	//点击关注
	$(".guanzhu").live('click',function(e){
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
//组装列表
function getPage(txt){
	var searchCondtion = "key=" + txt;
	$.post("/bbs/base/tieList",searchCondtion,function(data){
		if(data.list.length){
			var pagehref = "/bbs/base/tieList";
			var gridlist = create.createlist(data);
			
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
			var value = 1;
			tableEvent.addFootHref("0",value,"bm_c",pagehref,data);//第一个传值"0"无意义的
		}else{
			var str = "<div class='item'><h3 style='text-align: center;'>暂时无记录，请重新搜索</h3></div>";
			var str1 = '共有<span class="count-record">0</span>本书，共<span class="count-page">0</span>页，当前是第<span class="cur-page">0</span>页';
			$('.bm_c').html(str);
			$('.table-info').html(str1);
			$('.list-footer').html("");
		}
		
	},'json');						
}
//推荐阅读
function getActive(){
	$.post("/bbs/tiezi/getActive",function(data){
		if(data.result.length){
			var readlist = "<ul class='recread cl'>";
			for(var i=0; i<data.result.length; i++){
			    readlist +="<li class='hread'>"
						+"<a class='cg' href='/bbs/commentdetail?tid="+data.result[i].tid+"' title='"+data.result[i].title+"' target='_blank' >"+data.result[i].title+"</a>"
						+"</li>";
					
			};
			readlist +="</ul>";
			$(".read").html(readlist);
		}
		
	},'json');
}

//活跃会员
function getActiveperson(){
	$.post("/bbs/tiezi/getActiveperson",function(data){
		if(data.result.length){
			var menlist = "";
			for(var i=0; i<data.result.length; i++){
			    menlist +="<ul class='active cl'>"
						+"<li class='hread'>"
						+"<a href='/bbs/userpage?id="+data.result[i].uid+"' class='avatar1' >"
						+"<img src='"+data.result[i].head+"' />"
						+"</a>"
						+"<a href='/bbs/userpage?id="+data.result[i].uid+"'>"+data.result[i].nickname+"</a>"
						+"<span class='guanzhu norbtn bluebtn' uid='"+data.result[i].uid+"'>+关注</span>"
						+"</li>"
						+"</ul>";
			};
			$(".member").html(menlist);
		}
		
	},'json');
}