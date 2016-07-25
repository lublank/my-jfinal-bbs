<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>登录</title>
	<link href="/bbs/public/css/common.css" type="text/css" rel="stylesheet"/>
	<link href="/bbs/public/css/theams/bootstrap.css" type="text/css" rel="stylesheet"/>
    <link href="/bbs/public/css/login.css" type="text/css" rel="stylesheet"/>
    <script src="/bbs/public/js/common/jquery.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/common/common.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/common/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/common/jquery.validate.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/login.js" type="text/javascript"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div style="margin:0 auto; width:1000px; min-height:800px;">
	<!-- 登录表单 -->
	<div id="Login">
        <div class="login-wrapper">
        <a href="javascript:;" class="btn_close"></a>
        <div class="well-white">
            
            <form id="frmlogin" method="post">
                <fieldset class="fied">

                    <legend class="form-signin-heading">登 陆</legend>
                    <div class="control-group">
                    <label class="control-label" for="username">用户名：</label>
                    <input type="text" autocomplete="off" placeholder="请输入用户名…"  title="请输入用户名" name="username" id="username" tabindex="1" />
                    <a href="#" class="icon-info-sign" data-toggle="tooltip" data-placement="right" title="用户名由字母和数字组成"></a>
                    </div>
                   <div class="control-group">
                    <label class="control-label" for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
                    <input type="password" placeholder="请输入密码…" title="请输入密码" id="password" name="password" tabindex="2" />
                    <a href="#" class="icon-info-sign" data-toggle="tooltip" data-placement="right" title="密码由a-z、A-Z、0-9、_组成"></a>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="verify1">验证码：</label>
                            <input type="text" autocomplete="off" title="请输入验证码" class="input-large" name="verify1" id="verify1"  tabindex="3"/>
							<a class="imge" title="点击换一张">
								<img name="image" class="img" src="/bbs/vcode" onclick="src='/bbs/vcode'"/>
							</a>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <input type="checkbox" id="remember" name="remember" tabindex="4" style="margin: 10px 0;" />
                            <span class="help-inline" style="margin-left: 0px; font-size:14px; color:black;">记住用户密码？</span>
                        </div>
                    </div>
                    <div class="control-group" style="position: relative; padding-bottom: 10px;">
                        <div>
                            <input type="submit" class="btn" tabindex="5" id="btn_login" value="登录"/>
                            <img src="/bbs/public/images/loading.gif" id="loding" style="display: none" />
                        </div>
                        <div style="margin-left: 140px; position: absolute; top: 5px;">
                            <span>没有账号?</span> | <a href="javascript:" id="link-register">立即注册</a>
                        </div>
                    </div>
                </fieldset>
            </form>
            </div>
            </div>
      </div>
      <!-- 注册表单 -->
      <div id="Register">
      	<div class="login-wrapper">
        <a href="javascript:;" class="btn_close"></a>
        <div class="well-white">
        <form id="frmregister" method="post">
                <fieldset class="fied">
                    <legend class="form-signin-heading">帐号注册</legend>
                    <div class="control-group">
                        <label class="control-label" for="user">用户名：</label>
                        <input type="text" autocomplete="off" class="input-xlarge" name="user" id="user" value="" tabindex="1"/>
                        <a href="#" class="icon-info-sign" data-toggle="tooltip" data-placement="right" title="用户名由字母和数字组成"></a>
                        
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="pwd">密码：</label>
                            <input type="password" class="input-xlarge" name="pwd" id="pwd" tabindex="2"/>
                            <a href="#" class="icon-info-sign" data-toggle="tooltip" data-placement="right" title="密码由a-z、A-Z、0-9、_组成"></a>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="pwda">确认密码：</label>
                            <input type="password" class="input-xlarge" name="pwda" id="pwda" tabindex="3" />
                            <a href="#" class="icon-info-sign" data-toggle="tooltip" data-placement="right" title="确认密码要相同"></a>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="email">电子邮件：</label>
                            <input type="text" autocomplete="off" class="input-xlarge" name="email" id="email" tabindex="4" />
                            <a href="#" class="icon-info-sign" data-toggle="tooltip" data-placement="right" title="电子邮件填写您的常用邮箱"></a>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="verifycode">验证码：</label>
                            <input type="text" autocomplete="off" class="input-large" name="verify2" id="verify2" tabindex="5" />
                            <a class="imge" title="点击换一张">
								<img name="image" class="img" src="/bbs/vcode" onclick="src='/bbs/vcode'"/>
							</a>
                    </div>
                    <div class="control-group">
                        <div class="controls" style="position: relative; padding-bottom: 10px;">
                            <input id="Button1" type="submit" class="btn" value="注册"/>
                            <img src="/bbs/public/images/loading.gif" id="Img1" style="display: none" />
                        </div>
                        <div style="margin-left: 200px; position:relative; bottom: 40px;">
                             <a href="javascript:" id="link-back">返回</a>
                        </div>
                    </div>
                </fieldset>
        </form>
        </div>
        </div>
    </div>
      </div>
      <div id="bottom-content-wrapper">
		<jsp:include page="footer.jsp" flush="true"/>
	  </div>
</body>
</html>