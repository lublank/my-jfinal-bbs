/**
 * 个人中心js
 */
$(function(){
	//切换样式
	$(".mn_1").click(function(){
		$(".same").removeClass("a");
		$(".mn_1").addClass("a");
		$(".same_block").addClass("block");
		$(".information").removeClass("block");
	});
	$(".mn_2").click(function(){
		$(".same").removeClass("a");
		$(".mn_2").addClass("a");
		$(".same_block").addClass("block");
		$(".changePwd").removeClass("block");
	});
	$(".mn_3").click(function(){
		$(".same").removeClass("a");
		$(".mn_3").addClass("a");
		$(".same_block").addClass("block");
		$(".uphead").removeClass("block");
	});
	
	//头像
	$(".head1").click(function(){
		$(".sa").removeClass("selected");
		$(".head1").addClass("selected");
	});
	$(".head2").click(function(){
		$(".sa").removeClass("selected");
		$(".head2").addClass("selected");
	});
	$(".head3").click(function(){
		$(".sa").removeClass("selected");
		$(".head3").addClass("selected");
	});
	
	//显示个人资料
	userInfo();
	//修改个人资料
	$("#sure_btn").click(function(){
		updateInfo();
	});
	//修改密码
	$("#sure_btn1").click(function(){
		updatePassword();
	});
	//更改头像
	$("#sure_btn2").click(function(){
		var key = $(".selected").attr("key");
		if(key==null||key==""){
			alert("请先选择任一个系统头像！");
			return false;
		}
		if(key==1){
			head_pic = "public/images/head_images/barehead.png";
		}else if(key==2){
			head_pic = "public/images/head_images/man.png";
		}else if(key==3){
			head_pic = "public/images/head_images/woman.png";
		};
		$.post("/bbs/personal/updatehead","head="+head_pic,function(data){
			if(data.success){
				alert("头像更新成功！");
				window.location.reload();
			}else{
				alert(data.error);
			}
		},'json');
	});
	//上传图片		
	// 全局配置jUploader的参数
	$.jUploader.setDefaults({
	    cancelable: true, // 可取消上传
	    allowedExtensions: ['jpg', 'png', 'gif', 'jpeg', 'bmp'], // 只允许上传图片
	    messages: {
	        upload: '浏览',
	        cancel: '取消',
	        emptyFile: "{file} 为空，请选择一个文件.",
	        invalidExtension: "{file} 后缀名不合法. 只有 {extensions} 是允许的.",
	        onLeave: "文件正在上传，如果你现在离开，上传将会被取消。"
	    },
	    showMessage: function (message) {
        	alert(message);
        },
	});
	//绑定'上传图片'按钮
	$('#sure_btn3').live('click',function(){

		 $.jUploader({
		    button : "sure_btn3", 	  // 设置按钮id
			  name : "file",			 //传给后台的图片名称
			action : '/bbs/upload/savephoto',//设置上传处理接口  
			// 开始上传事件
			onUpload: function (fileName) {
			    	$("#loding").show();
			    },
		    // 上传完成事件
			onComplete: function (data) {
				$("#loding").hide();
			 	if(data.tbody.success){
			 		filename = data.tbody.filename; //把文件名保存下来，当点击"保存或添加"时，传回给后台
			 		path = data.tbody.path;			//文件路径
			 		setTimeout("$('#imgUploadDialog .dialog-close').trigger('click');",100);
			 		//显示图片
					$('#body_photo').attr('src',data.tbody.path);
			 		//保存到数据库
			 		$.post("/bbs/personal/updatehead","head="+path,function(data){
						if(data.success){
							alert("头像更新成功！");
						}else{
							alert("头像更新失败！");
						}
					},'json');
			 	}
			 	else{
			 			alert("抱歉！未知错误，请重试。");
			 		}
				}
		  });
	});
	
});

//显示个人资料
function userInfo(){
	$.post("/bbs/personal/userInfo",function(data){
		if(data.unlogin){
			alert("亲，您还没登陆喔^_^");
			window.location.reload();
			return false;
		}
		if(data){
			$("#uname").html(data.username);
			$("#unickname").val(data.nickname);
			if(data.gender==1){
				$("#gender1").attr("checked",true);//性别：男
			}else if(data.gender==0){
				$("#gender2").attr("checked",true);//性别：女
			}
			$("#birth").val(data.birthday);
			$("#email").val(data.email);
			$("#phone").val(data.phone);
			$("#star").val(data.stars);
			$("#description").val(data.description);
			$("#favorite").val(data.favorite);
			$("#address").val(data.address);
			$("#rtime").html(data.regtime);
			$("#body_photo").attr("src",data.head);
		}
	},'json');
}
//修改个人资料
function updateInfo(){
	var nickname = $("#unickname").val();
	var gender = $("input[name='gender']:checked").val();
	if(gender=="女"){
		gender=0;
	}else if(gender=="男"){
		gender=1;
	}
	var email = $("#email").val();
	var phone = $("#phone").val();
	var birth = $("#birth").val();
	var stars = $("#star").val();
	var description = $("#description").val();
	var favorite = $("#favorite").val();
	var address = $("#address").val();
	
	var data = "nickname="+nickname+"&gender="+gender+"&email="+email+"&phone="+phone+"&birthday="+birth+"&stars="+stars+"&description="+description+"&favorite="+favorite+"&address="+address;
	$.post("/bbs/personal/updateInfo",data,function(data){
		if(data.success){
			alert("资料更新成功！");
			window.location.reload();
		}else{
			alert(data.error);
		}
	},'json');
}
//修改密码
function updatePassword(){
	var oldPwd = $("#oldPwd").val();
	var newPwd = $("#newPwd").val();
	var rePwd = $("#rePwd").val();
	if(newPwd!=rePwd){
		alert("两次输入密码不正确");
		return false;
	}
	
	var data = "oldPwd="+oldPwd+"&newPwd="+newPwd+"&rePwd="+rePwd;
	$.post("/bbs/personal/updatepwd",data,function(data){
		if(data.success){
			alert("密码修改成功！");
			window.location.reload();
		}else{
			alert(data.error);
		}
	},'json');
}
