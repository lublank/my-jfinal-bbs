/**
 * 帖子详情、回复
 */
var id = getQueryString("tid")==null ? "" : getQueryString("tid");
$(function(){
	if(id!=""){
		getUserInfo();
		getTiezi();
		getList();

		$(".collect").attr("tid",id);//收藏帖子的id
		$(".transmit").attr("tid",id);//收藏帖子的id
	}else{
		alert("页面出错了，不存在贴子");
		window.location.href = "/bbs/index";
	}
		//发表评论
		$("#reply").on('click', function (e) {
			var content = document.reply.content.value;
			if(content==""){
				alert("请输入内容");
				return false;
			}else{
				$.post("/bbs/tiezi/answertie","id="+id+"&reply="+content,function(data){
					//check ta是否已登录
					if(data.unlogin){
						var str = "<span class='error_msg'>用户尚未登录，点击<a href='/bbs/login' target='_blank' style='color:#08c;'>这里</a>登录</span>";
						$(".waring").replaceWith(str);
						return false;
					}
					if(data.success){
						alert("评论成功！");
						window.location.href = "/bbs/commentdetail?tid="+id;
					}else{
						alert(data.error);
					}
				},'json');
				
			}

			return false;//阻止submit的默认行为（相当于e.preventDeafult();）
		});
		//发帖
		$("#newspecial").click(function(){
			window.location.href = "/bbs/write";
		});
		//点击关注
		$(".guanzhu").live('click',function(e){
			var uid = $("#uid").val();
			if(uid=="")
			{
				alert("加关注失败");
			}else{
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
				$(".guanzhu").html("已关注");
			},'json');
			}
			
		});
		//点击收藏
		$(".collect").live('click',function(e){
			var tid = $(".collect").attr("tid");
			if(tid=="")
			{
				alert("收藏失败");
			}else{
				$.post("/bbs/polo/CheckCollect","id="+tid,function(data){
				if(data.unlogin){
					alert("您还没登录呢，登录后再收藏吧^_^");
					return false;
				}
				if(!data.check){
					alert(data.error);
				}else{
					alert("收藏成功");
				}
				//按钮变成已关注
				$(".collect").html("已收藏");
			},'json');
			}
			
		});
		//点击转发
		$(".transmit").live('click',function(e){
			var tid = $(".transmit").attr("tid");
			if(tid=="")
			{
				alert("转发失败");
			}else{
				$.post("/bbs/polo/updateNotify","id="+tid,function(data){
				if(data.unlogin){
					alert("您还没登录呢，登录后再转发吧^_^");
					return false;
				}
				if(!data.check){
					alert(data.error);
				}else{
					alert("转发成功");
				}
				//按钮变成已关注
				$(".transmit").html("已转发");
			},'json');
			}
			
		});
});
//显示发帖用户信息
function getUserInfo(){
	$.post("/bbs/tiezi/getmessage","id="+id,function(data){
		if(data){
			$("#uid").val(data.uid);//用户id
			$(".uname").html(data.username);//用户名字
//			$(".uname").live('click',function(){window.location.href="/bbs/userpage?id="+$("#uid").val();});
			$(".uname").attr("href","/bbs/userpage?id="+$("#uid").val());//点击用户名跳转到他的个人中心
			$("#face").attr("src",data.photo);//用户头像
			$(".num1").html(data.tiecount);//帖子数
			$(".num2").html(data.attention);//关注数
			$(".num3").html(data.fans);//粉丝数
			$(".jifen").html(data.score);//积分
			$(".regtime").html(data.time);//注册时间
//			$(".guanzhu").attr("uid",uid);//加关注
		}
	},'json');
}
//帖子信息
function getTiezi(){
	$.post("/bbs/tiezi/getCardmessage","id="+id,function(data){
		if(data.success){
			$(".time").html(data.ctime);//发帖时间
			$(".title").html(data.title);//帖子标题
			$(".tzcontent").html(data.content);//帖子内容
			window.document.title = "帖子详情 | "+data.title;//设置页面的标题
		}else{
			alert(data.error);
			window.location.href = "/bbs/index";
		}
	},'json');
}
//组装评论列表
function getList(){
	$.post("/bbs/tiezi/getreturn","id="+id,function(data){
		if(data.list.length){
			$(".replynum").html(data.totalRow);//评论数
			
			var pagehref = "/bbs/tiezi/getreturn";
			var gridlist = create.createReply(data);
			
			var footer = create.creategridfoot2(data.pageNumber,data.totalPage,data.pageSize,6);
			
			var htmlStr = '共有<span class="count-record">'
				+ data.totalRow
				+ '</span>条记录，共<span class="count-page">'
				+ data.totalPage
				+ '</span>页，当前是第<span class="cur-page">'
				+ data.pageNumber
				+ '</span>页';
			$('.reply_content').html(gridlist);
			$('.table-info').html(htmlStr);
			$('.list-footer').html(footer);
			//绑定分页事件
			var value = 2;
			tableEvent.addFootHref(id,value,"reply_content",pagehref,data);
		}else{
			var str = "<div class='item'><h3 style='text-align: center;'>暂时无评论记录</h3></div>";
			var str1 = '共有<span class="count-record">0</span>本书，共<span class="count-page">0</span>页，当前是第<span class="cur-page">0</span>页';
			$('.reply_content').html(str);
			$('.table-info').html(str1);
			$('.list-footer').html("");
		}
		
	},'json');						
}

//回复框
KindEditor.ready(function(K) {
	var editor = K.create("#content", {
		cssPath : '/bbs/public/js/kindeditor-4.1.10/plugins/code/prettify.css',
		uploadJson : 'upload_json.jsp',
		fileManagerJson : 'file_manager_json.jsp',
		resizeType : 0,
		allowFileManager : true,
		afterCreate : function() {
			this.sync();
		},
		afterChange : function() {
			this.sync(); 
		},
		afterBlur:function(){
			this.sync();  
		},
		items : ['bold', 'italic', 'underline', 'removeformat','|',	 'forecolor', 'hilitecolor', 'fontname', 'fontsize', '|', 'justifyleft', 'justifycenter', 'justifyright',
				 'justifyfull', 'emoticons', ],
		
	});
});
