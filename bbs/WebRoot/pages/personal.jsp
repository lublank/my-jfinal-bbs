<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>个人中心</title>
	<link href="/bbs/public/css/common.css" type="text/css" rel="stylesheet"/>
	<link href="/bbs/public/css/personal.css" type="text/css" rel="stylesheet"/>
	
    <script src="/bbs/public/js/common/jquery.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/common/common.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/common/jUploader-1.01.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/personal.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div id="wrap">
		<!-- header -->
		<div id="header">
		    <div id="logo">
		        <a href="/bbs/index" rel="论坛logo"></a>
		    </div>
		    <div style="float:right; margin:5px 0;overflow:hidden;" id="b"></div>
		</div>
		<!-- nv -->
		<div id="nv">
			<ul>
				 <li class="same a mn_1"><a href="javascript:;" >基本资料</a></li>
		         <li class="separator"> </li>
		         <li class="same mn_2"><a href="javascript:;" >修改密码</a></li>
		         <li class="separator"> </li>
		         <li class="same mn_3"><a href="javascript:;" >修改头像</a></li>
		         <li class="separator"> </li>
		     <!--<li class="same mn_4"><a href="javascript:;" >留言板</a></li>
		         <li class="separator"> </li>-->
			</ul>
		</div>
		<!-- body -->
		<div class="pmain">
			<!-- 基本资料 -->
			<div class="same_block information">
				<table>
				<tbody>
					<tr><td class="ri">用户名：</td><td class="le"><span id="uname"></span></td></tr>
					<tr><td class="ri">昵称：</td><td class="le"><input type="text" id="unickname" /></td></tr>
					<tr><td class="ri">性别：</td><td class="le"><input type="radio" value="男" name="gender" id="gender1"/><label for="gender1">男</label>
												<input type="radio" value="女" name="gender" id="gender2" style="margin-left:10px"/><label for="gender2">女</label></td></tr>
					<tr><td class="ri">生日：</td><td class="le"><input type="text" id="birth" placeholder="2000-01-01"/></td></tr>
					<tr><td class="ri">邮箱：</td><td class="le"><input type="text" id="email" /></td></tr>
					<tr><td class="ri">电话：</td><td class="le"><input type="text" id="phone" /></td></tr>
					<tr><td class="ri">星座：</td><td class="le"><input type="text" placeholder="您的星座" id="star"/></td></tr>
					<tr><td class="ri">个人描述：</td><td class="le"><textarea rows="5" id="description"></textarea></td></tr>
					<tr><td class="ri">兴趣爱好：</td><td class="le"><textarea rows="5" id="favorite"></textarea></td></tr>
					<tr><td class="ri">居住地：</td><td class="le"><textarea rows="5" id="address"></textarea></td></tr>
					<tr><td class="ri">注册时间：</td><td class="le"><span id="rtime">2014-06-05</span></td></tr>
					
				</tbody>
				</table>
					<div style="margin:20px 0px 10px 378px;"><a href="javascript:;" class="bluebtn" id="sure_btn" >确认</a></div>
			</div>
		
			<!-- 修改密码 -->
			<div class="same_block changePwd block">
				<table>
				<tbody>
					<tr><td class="ri">原密码：</td><td class="le"><input type="password" id="oldPwd"/></td></tr>
					<tr><td class="ri">新密码：</td><td class="le"><input type="password" id="newPwd"/></td></tr>
					<tr><td class="ri">确认密码：</td><td class="le"><input type="password" id="rePwd"/></td></tr>
				</tbody>
				</table>
					<div style="margin:20px 0px 10px 396px;"><a href="javascript:;" class="bluebtn" id="sure_btn1" >确认</a></div>
			</div>
			<!-- 修改头像 -->
			<div class="same_block uphead block">
				<div class="select_head">
					<h3>选择一个系统头像</h3>
					<a class="sa head1" key="1"><img src="public/images/head_images/barehead.png" width="128px" height="128px"></a>
					<a class="sa head2" key="2"><img src="public/images/head_images/man.png" width="128px" height="128px"></a>
					<a class="sa head3" key="3"><img src="public/images/head_images/woman.png" width="128px" height="128px"></a>
				</div>
				<div style="margin:20px 0px 10px 396px;clear:both;"><a href="javascript:;" class="bluebtn" id="sure_btn2" style="margin:20px;">确认</a></div>
			<div class="upload_head">
				<div id="photostyle"> 
				<h3>上传您自定义的头像</h3>
				<h5 style="font-weight:normal;color:#999;">仅支持JPG、GIF、PNG图片文件，且文件小于5M</h5>
					<a class="show_head"><img id="body_photo" class="photo" style="width:128px;height:128px" src="public/images/head_images/barehead.png"></a>
					<div style="margin:20px 0px 20px 35px;clear:both;"><a href="javascript:;" class="bluebtn" id="sure_btn3">上传头像</a><img src="/bbs/public/images/loading.gif" id="loding" style="display: none" /></div>
			    </div>
			</div>
  				
  			</div>
		</div>
	</div>
	<div class="clr"></div>
	<!-- footer -->
	<div id="bottom-content-wrapper">
		<jsp:include page="footer.jsp" flush="true"/>
	</div>
</body>
</html>