<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>帖子详细页面</title>
	<link href="/bbs/public/css/common.css" type="text/css" rel="stylesheet"/>
	<link href="/bbs/public/css/theams/table_new.css" type="text/css" rel="stylesheet"/>
	<link href="/bbs/public/css/mypage.css" type="text/css" rel="stylesheet"/>
	<link href="/bbs/public/css/commentdetail.css" type="text/css" rel="stylesheet"/>
	<link href="/bbs/public/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet"/>
	<link href="/bbs/public/js/kindeditor-4.1.10/plugins/code/prettify.css" type="text/css" rel="stylesheet"/>
	
    <script src="/bbs/public/js/common/jquery.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/common/common.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/common/makeList.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/common/tableEvent.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/kindeditor-4.1.10/kindeditor.js" type="text/javascript"></script>
    <script src="/bbs/public/js/kindeditor-4.1.10/lang/zh_CN.js" type="text/javascript"></script>
    <script src="/bbs/public/js/kindeditor-4.1.10/plugins/code/prettify.js" type="text/javascript"></script>
    <script src="/bbs/public/js/commentdetail.js" type="text/javascript" charset="utf-8"></script>
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
		<div id="ct" class="wp c1">
			<div id="expandbox" class="expandbox_mn">
				<!-- 楼主个人信息 -->
				<div>楼主</div>
				<div id="uname"><a href="javacript:;" class="uname">username</a><input type="hidden" id="uid"/></div>
				<div class="rules_expand">
					<a>
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
	            	<span class="guanzhu norbtn bluebtn" uid="">+关注</span>
	            </div>
		    </div>
		    <!-- right -->
		    <div class="plate">
				<div class="tl bm bmw">
					<div class="th">
						<table cellspacing="0" cellpadding="0">
							<tbody>
							<tr>
								<th class="filbarwrap_thread" colspan="2">
								<div class="tf filterbar_thread cl">
								<a href="javascript:;" class="bluebtn normalbtn y" id="newspecial" title="发新帖" style="margin-left: 20px;">发帖</a>
								<a href="#01" class="bluebtn normalbtn y" title="回复" >回复</a>
								</div>
								</th>
							</tr>
							</tbody>
						</table>
					</div>
					<!-- 帖子内容 -->
					<div class="content_tz">
						<div class="top_tz">
							<span>发表于：</span><span class="ctime time"></span>
							<span class="collect norbtn bluebtn" tid="" style="float: right;">+收藏</span>
							<span class="transmit norbtn bluebtn" tid="" style="float: right;">转发</span>
						</div>
						<div class="detail_tz">
							<h1 class="title"></h1>
							<div class="tzcontent">
								
							</div>
						</div>
					</div>
				</div>
				
				<!-- 回复 -->
				<div class="replypost">
					<h3><em class="replynum">0</em> 条回复</h3>
					<div class="reply_content">
						
					</div>
				<div class="table-footer">
					<div class="table-info">
						共有<span class="count-record">0</span>条记录，共<span class="count-page">1</span>页，当前是第<span class="cur-page">1</span>页
					</div>
					<div class="list-footer" style="position: relative;"></div>
				</div>
				</div>
				<!-- 发表回复 -->
				<div class="post">
				<form id="post" name="reply" method="post">
					<h3 name="01" id="01">快速回复评论</h3>
					<textarea name="content" id="content" cols="100" rows="8" style="width:768px;height:200px"></textarea>
					<div style="margin:10px 0;" class="tz_re">
						<input type="submit" class="bluebtn normalbtn" id="reply" value="评论" />
						<span class="waring"></span>
					</div>
				</form>
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