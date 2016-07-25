/**
 * 其他用户主页
 */
var id = getQueryString("id")==null ? "" : getQueryString("id");
//var t = getQueryString("t")==null ? "1" : getQueryString("t");
$(function(){
	//发帖
	$("#newspecial").click(function(){
		window.location.href = "/bbs/write";
	});
	//根据id设置对应的链接
	if(id!=""){
		getUserInfo();//该用户信息
		$(".tz_myWrite").attr("href","/bbs/userpage?id="+id);
		hisPost();//ta的发表
	}else{
		alert("不存在该用户");
		window.location.href = "/bbs/index";
	}
	
//	//根据不同的id值切换不同内容
//	if(t==4){//发表
//		$(".thread").removeClass("selected");
//		$(".tz_myWrit").addClass("selected");
//		myPost();
//	}
});
	/*显示用户信息*/
	function getUserInfo(){
		$.post("/bbs/personal/getUsermessage","id="+id,function(data){
			if(data.unlogin){
				alert("亲，您还没登陆喔^_^");
				window.location.href = "/bbs/login";
				return false;
			}
			if(data){
				$("#uid").val(data.uid);//用户id
				$(".uname").html(data.username);//用户名字
				$(".uname").live('click',function(){window.location.href="/bbs/userpage?id="+$("#uid").val();});//点击用户名跳转到他的个人中心
				$("#face").attr("src",data.photo);//用户头像
				$(".num1").html(data.tiecount);//帖子数
				$(".num2").html(data.attention);//关注数
				$(".num3").html(data.fans);//粉丝数
				$(".jifen").html(data.score);//积分
				$(".regtime").html(data.time);//注册时间
			}
		},'json');
	}
	
	/*TA发表的帖子*/
	function hisPost(){
		$.post("/bbs/personal/getHiscard", "id="+id,function(data){
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

				var pageall =  {thead : thead,	theadname : theadname, colswidth : colswidth,  infopage : infopage, pagehref : "/bbs/personal/getHiscard"};
				var value = 5;
				tableEvent.addFootHref(id,value,"bm_c",pageall,data);
			}else{
				var str = "<div class='item'><h3 style='text-align: center;'>暂时无记录</h3></div>";
				var str1 = '共有<span class="count-record">0</span>条记录，共<span class="count-page">0</span>页，当前是第<span class="cur-page">0</span>页';
				$('.bm_c').html(str);
				$('.table-info').html(str1);
				$('.list-footer').html("");
			}
				
		},'json');	
	}