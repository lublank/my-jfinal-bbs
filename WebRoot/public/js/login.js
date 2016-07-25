/**
 * 登录javascript
 */
var id = getQueryString("id")==null ? "1" : getQueryString("id");
$(function () {
	if(id==2){
		$("#Login").hide();
        $("#Register").show();
        $(".img").attr('src','/bbs/vcode');
	}else{
		$("#Login").show();
        $("#Register").hide();
        $(".img").attr('src','/bbs/vcode');
	}

    $('.icon-info-sign').tooltip();
    //获取cookie保存的用户名
    var name = document.getElementById('username');
	var pwd = document.getElementById('password');
	if (getCookie("username")&&getCookie("password")) {
        name.value=getCookie("username");
        pwd.value=getCookie("password");
    };
    /**登录**/
    $("#frmlogin").validate({
        errorClass: "help-inline",
        rules: {
            username: {
                required: true,
                minlength: 5
            },
            password: {
                required: true,
                minlength: 6
            },
            verify1: {
                required: true,
                minlength: 4,
                maxlength: 4
            }
        },

        messages: {
            username: {              // 需要进行验证的输入框name
                required: "用户名为空",  // 验证条件：必填
                minlength: "用户名最短长度为5位"
            },
            password: {
                required: "密码为空",
                minlength: "密码最短长度为6位"
            },
            verify1: {
                required: "验证码为空",
                minlength: "验证码为四位的",
                maxlength: "验证码为四位的"
            }
        },
        highlight: function (element) {
            $(element).closest('.control-group').removeClass('success').addClass('error');
        },
        success: function (element) {
            element
            .closest('.control-group').removeClass('error').addClass('success');
        }

    });

    $('#btn_login').on('click', function (e) {
        e.preventDefault();//阻止默认事件
        if ($("#frmlogin").valid()) {
        	var username = $("#username").val();
        	var password = $("#password").val();
        	var verify1 = $("#verify1").val();
        	/**记住用户名**/
        	var remember = document.getElementById('remember');
            if (remember.checked) {
                setCookie("username",username);
                setCookie("password",password);
            }
            else {
                delCookie("username");
                delCookie("password");
            };
            /**post请求后台,检验登录是否成功**/
        	$.post("/bbs/login/Loginajax",{"user.name":username,"user.password":password,"code":verify1},function(data){
				if(data.success){
					window.location.href = data.url;
				}
				else{
					alert(data.error);
				}
			},"json");
        };
    });
    /**注册**/
    function getname(){
    	var uname = $("#user").val();
    	return uname;
    }
    $("#frmregister").validate({
        errorClass: "help-inline",
        rules: {
            user: {
                required: true,
                minlength: 5,
			    remote:{
			    	/**ajax请求后台验证是否该注册用户名已经存在**/
					  type: "POST",
					  dataType: "json",
					  url: "/bbs/check/checkLoginname",
					  data: {
						  usename: function(){return $("#user").val();}
					  }
			    }
            },
            pwd: {
                required: true,
                minlength: 6,
//                userpwd: true
            },
            pwda: {
                required: true,
                minlength: 6,
                equalTo: "#pwd"
            },
            email: {
                required: true,
                email: true
            },
            verify2: {
                required: true,
                minlength:4,
                maxlength: 4
            }
        },
        messages: {
            user: {
                required: "用户名为空",
                minlength: "用户名最短长度为5位",
                remote: "用户名已存在"
            },
            pwd: {
                required: "密码为空",
                minlength: "密码最短长度为6位"
            },
            pwda: {
                required: "密码为空",
                minlength: "密码最短长度为6位",
                equalTo: "两次输入密码不一致"
            },
            email: {
                required: "邮件地址为空",
                email: "邮件地址格式错误",
            },
            verify2: {
                required: "验证码为空",
                minlength: "验证码为四位的",
                maxlength: "验证码为四位的"
            }
        },
        highlight: function (element) {
            $(element).closest('.control-group').removeClass('success').addClass('error');
        },
        success: function (element) {
            element
            .closest('.control-group').removeClass('error').addClass('success');
        }
        
    });
    $('#Button1').on('click', function (e) {
        e.preventDefault();
                var user = $("#user").val();
            	var pwd = $("#pwd").val();
            	var email = $("#email").val();
            	var verify2 = $("#verify2").val();
        if ($("#frmregister").valid()) {
                $("#loding").show();
                /**注册表单提交到后台**/
            	$.post("/bbs/login/Loginregister",{"username":user,"password":pwd,"email":email,"verifyCode":verify2},function(data){
					if(data.success){
						$("#loding").hide();
						alert("恭喜，帐号注册成功");
						window.location.href = data.url;
					}else{
						alert(data.error);
					}
				},"json");
            
                    
        }
        return false;
    });
  //焦点
    $("#username").focus();
	//切换
	$("#link-register").click(function () {
        $("#Login").hide();
        $("#Register").show();
        $(".img").attr('src','/bbs/vcode');
    });
    $("#link-back").click(function () {
        $("#Login").show();
        $("#Register").hide();
        $(".img").attr('src','/bbs/vcode');
    });
    
	//关闭按钮
	$(".btn_close").click(function(){
		$("#Login").hide("normal");
		$("#Register").hide("fast");
		window.location.href = "/bbs/index";
	});
});
/**cookie设置**/
//读取cookies 
	function getCookie(name) 
	{ 
	    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	 
	    if(arr=document.cookie.match(reg))
	 
	        return unescape(arr[2]); 
	    else 
	        return null; 
	} 
	//写cookies
	function setCookie(name,value) 
	{ 
	    var Days = 1; 
	    var exp = new Date(); 
	    exp.setTime(exp.getTime() + Days*24*60*60*1000); 
	    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString(); 
	} 
	//删除cookies 
	function delCookie(name) 
	{ 
	    var exp = new Date(); 
	    exp.setTime(exp.getTime() - 1); 
	    var cval=getCookie(name); 
	    if(cval!=null) 
	        document.cookie= name + "="+cval+";expires="+exp.toGMTString(); 
	} 