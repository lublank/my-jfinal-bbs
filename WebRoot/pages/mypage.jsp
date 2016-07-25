<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>个人主页</title>
	<link href="/bbs/public/css/common.css" type="text/css" rel="stylesheet"/>
	<link href="/bbs/public/css/theams/table_new.css" type="text/css" rel="stylesheet"/>
	<link href="/bbs/public/css/mypage.css" type="text/css" rel="stylesheet"/>
    <script src="/bbs/public/js/common/jquery.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/common/common.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/common/makeList.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/common/tableEvent.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/mypage.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div id="wrap">
	<!-- nav -->
	<div id="scbar" class="cl">
	<form action="/bbs/index" method="post">
		<div class="scbar_wrap">
        	<input type="text" name="srchtxt" id="scbar_txt" autocomplete="off" class=" xg1" placeholder="搜索主题和用户">
            <input type="submit" name="searchsubmit" id="scbar_btn" value="" />
        </div>
	</form>
        
	</div>
		<div class="cr"> </div>
	<div class="board">
			<div id="ct" class="wp c1">
				<div id="expandbox" class="expandbox_mn">
					<!-- 签到 -->
					<div class="signinwrap_expand">
						<div class="signin_expand no_sign">
							<a><span class="icon_signin"></span><span class="btn_sign">  立即签到 </span></a>
						</div>
						<div class="tips_signin" style="display:none;"><span class="tipcont_signin">请先<a href="/bbs/login" target="_blank" >登录</a></span><em class="point_signtip"></em></div>
					</div>
					<!-- 个人信息 -->
					<div id="uname"><a class="uname">username</a><input type="hidden" id="uid"/></div>
					<div class="rules_expand">
						<a class="img_head">
            			<img src="/bbs/public/images/head_images/man.png" id="face" width="128" height="128">
            			</a>
					</div>
					<div class="top20">
		            	<div class="grid">
		                	<div class="number num1">0</div>
		                	<div class="explain">帖子</div>
		                </div>
		                <div class="grid">
		                	<div class="number num2">0</div>
		                    <div class="explain">关注</div>
		                </div>
		                <div class="grid">
		                	<div class="number num3">0</div>
		                    <div class="explain">粉丝</div>
		                </div>
		                <div class="cr"></div>
		            </div>
		            <div class="sr">
		            	<div class="score"><span>积分：</span><span class="jifen">0</span></div>
		            	<div class="rtime"><span>注册时间：</span><br/><span class="regtime">2000-00-10</span></div>
		            </div>
					<!-- 消息中心 -->
					<div class="rules_expand read">
					<div class="titlebar_expand"><h3 style="font-size:14px;">消息中心：</h3></div>
						<ul class="recread cl">
						<!-- <li class="thread comment_m selected"><a class="cg comment_my" href="/bbs/mypage?t=1" >我的主页</a></li>-->
							<li class="thread at_m"><a class="cg at_my" href="/bbs/mypage?t=2" >我的转帖</a></li>
						</ul>
					</div>
					<!-- 好友中心 -->
					<div class="rules_expand member">
					<div class="titlebar_expand"><h3 style="font-size:14px;">好友中心：</h3></div>
						<ul class="active cl">
							<li class="thread gz_m"><a class="cg gz_my" href="/bbs/mypage?t=3" >我的关注</a></li>
							<li class="thread fan_m"><a class="cg fan_my" href="/bbs/mypage?t=4" >我的粉丝</a></li>
						</ul>
					</div>
					<!-- 论坛 -->
					<div class="rules_expand member">
					<div class="titlebar_expand"><h3 style="font-size:14px;">论坛：</h3></div>
						<ul class="active cl">
							<li class="thread tz_myWrit"><a class="cg tz_myWrite" href="/bbs/mypage?t=5" >我发表的帖子</a></li>
							<li class="thread tz_myRepl"><a class="cg tz_myReply" href="/bbs/mypage?t=6" >我回复的帖子</a></li>
							<li class="thread tz_mySav"><a class="cg tz_mySave" href="/bbs/mypage?t=7" >我收藏的帖子</a></li>
						</ul>
					</div>
				</div>
				
				<div class="plate_mn">
					<div class="tl bm bmw">
					<div class="th">
						<table cellspacing="0" cellpadding="0">
						<tbody><tr>
						<th class="filbarwrap_thread" colspan="2">
						<div class="tf filterbar_thread cl">
						
						<a href="javascript:;" class="bluebtn normalbtn y" id="newspecial" title="发新帖" >发帖</a>
						</div>
						</th>
						
						</tr>
						</tbody></table>
					</div>
					<!-- 消息列表 -->
					<div class="bm_c">
					<!-- 我的动态-->
						<ul style="display:none;">
							<li>
								<div class="header">
									<a href="/bbs/userpage?id=1435849">
									<img alt="occo" src="/bbs/public/images/head_images/woman.png">
									</a>
								</div>
								<div class="content_gz">
									<div class="useId">
										<a href="/bbs/userpage?id=1435849">occo</a>
									</div>
									<div class="detail_gz">回复了我的帖子 <em> <a target="_blank" href="#">为了作业</a></em>
										<p>2014-05-19 21:51:21</p>
										<div class="clear"></div>
									</div>
								</div>
							</li>
						</ul>
					<!-- 我的粉丝
						<h3><em class="fans_num">0</em> 个粉丝</h3>-->
						<ul style="display:none;">
							<li>
								<div class="header">
									<a href="/bbs/userpage?id=1435849">
									<img alt="occo" src="/bbs/public/images/head_images/woman.png">
									</a>
								</div>
								<div class="fans_info">
									<div class="useId">
										<a href="/bbs/userpage?id=1435849">occo</a>
									</div>
									<div style="float:right;">
										<a href="javascript:;" class="bluebtn normalbtn" id="add" >加关注</a>
										<!-- <a href="javascript:;" class="graybtn normalbtn" id="no_add" >取消关注</a> -->
										<a href="javascript:;" class="bluebtn normalbtn" id="remove" >移除粉丝</a>
									</div>
								</div>
							</li>
						</ul>
						<!-- 我的关注
						<h3><em class="gz_num">0</em> 个关注</h3>-->
						<ul style="display:none;">
							<li>
								<div class="header">
									<a href="/bbs/userpage?id=1435849">
									<img alt="occo" src="/bbs/public/images/head_images/woman.png">
									</a>
								</div>
								<div class="fans_info">
									<div class="useId">
										<a href="/bbs/userpage?id=1435849">occo</a>
									</div>
									<div style="float:right;">
										<a href="javascript:;" class="bluebtn normalbtn" id="removegz" >取消关注</a>
									</div>
								</div>
							</li>
						</ul>
						
					</div>
					<div class="table-footer">
						<div class="table-info">
							共有<span class="count-record">0</span>条记录，共<span class="count-page">1</span>页，当前是第<span class="cur-page">1</span>页
						</div>
						<div class="list-footer" style="position: relative;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<div class="clr"></div>
	<!-- footer -->
	<div id="bottom-content-wrapper" style="top: 80px;position: relative;">
		<jsp:include page="footer.jsp" flush="true"/>
	</div>
</body>
</html>