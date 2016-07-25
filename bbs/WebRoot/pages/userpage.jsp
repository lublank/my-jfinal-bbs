<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>TA的主页</title>
	<link href="/bbs/public/css/common.css" type="text/css" rel="stylesheet"/>
	<link href="/bbs/public/css/theams/table_new.css" type="text/css" rel="stylesheet"/>
	<link href="/bbs/public/css/mypage.css" type="text/css" rel="stylesheet"/>
    <script src="/bbs/public/js/common/jquery.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/common/common.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/common/makeList.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/common/tableEvent.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/userpage.js" type="text/javascript" charset="utf-8"></script>
    <style type="text/css">
    	.thread{margin:10px 0;}
    </style>
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
					<!-- 中心 -->
					<div class="rules_expand read">
					<div class="titlebar_expand"><h3 style="font-size:14px;">TA的中心：</h3></div>
						<ul class="recread cl">
							<li class="thread tz_myWrit selected"><a class="cg tz_myWrite" href="/bbs/userpage?id=4" >TA的发表</a></li>
						<!-- 	<li class="thread comment_m "><a class="cg comment_my" href="/bbs/userpage?t=1" >TA的主页</a></li>
							<li class="thread gz_m"><a class="cg gz_my" href="/bbs/userpage?id=2" >TA的关注</a></li>
							<li class="thread fan_m"><a class="cg fan_my" href="/bbs/userpage?id=3" >TA的粉丝</a></li>
							<li class="thread tz_myRepl"><a class="cg tz_myReply" href="/bbs/userpage?id=5" >TA的回复</a></li>-->
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
						
					</div>
					<div class="table-footer">
						<div class="table-info">
							共有<span class="count-record">0</span>条记录，共<span class="count-page">1</span>页，当前是第<span class="cur-page">1</span>页
						</div>
						<div class="list-footer" style="position: relative;top: -38px;"></div>
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