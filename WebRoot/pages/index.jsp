<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>BBS首页</title>
	<link href="/bbs/public/css/common.css" type="text/css" rel="stylesheet"/>
	<link href="/bbs/public/css/theams/table_new.css" type="text/css" rel="stylesheet"/>
	<link href="/bbs/public/css/index.css" type="text/css" rel="stylesheet"/>
	
    <script src="/bbs/public/js/common/jquery.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/common/common.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/common/makeList.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/common/tableEvent.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/index.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		String key = request.getParameter("srchtxt");
	%>
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
				 <li class="a" id="mn_Nfb8a"><a href="#" >学术讨论</a></li>
		         <li class="separator"> </li>
		         <li id="mn_Nfd8b"><a href="#" >谈天说地</a></li>
		         <li class="separator"> </li>
		         <li id="mn_N03ac"><a href="#" >灌水专区</a></li>
		         <li class="separator"> </li>
				 <li id="morePlate"><a href="#">更多</a></li>
			</ul>
		</div>
		<div id="scbar" class="cl">
	        <div class="scbar_wrap">
	        	<input type="hidden" id="skey" value="<%=key%>"/>
	        	<input type="text" name="srchtxt" id="scbar_txt" autocomplete="off" class=" xg1" placeholder="搜索主题和用户">
	            <button type="submit" name="searchsubmit" id="scbar_btn" value="true"></button>
	        </div>
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
					<!-- 版块信息 -->
					<div class="titlebar_expand"><h3>版块信息</h3></div>
					<div class="rules_expand">
						<div>
							<div class="ptn xg2">
							<span>★我们怀揣着梦想而来，是如此相近，&nbsp; &nbsp;&nbsp; &nbsp;却又各不相同。在这千百万人中我们&nbsp; &nbsp;&nbsp; &nbsp;聚到一起</span><br>
							<span>★在这里，无关新手与大神，只要你说&nbsp; &nbsp;&nbsp; &nbsp;得有理有据，我们都欢迎你踊跃发出&nbsp; &nbsp;&nbsp; &nbsp;你的声音</span><br>
							<span>★在这里，我们鼓励原创精品贴，期待&nbsp; &nbsp;&nbsp; &nbsp;在大学生社区上留下你浓墨重彩的一&nbsp; &nbsp;&nbsp; &nbsp;页。</span><br>
							<span>★在这里，有最全最新的大学资讯，有&nbsp; &nbsp;&nbsp; &nbsp;一群最懂生活的朋友。</span><br>
							<span>★在这里，只属于深大，其他资讯请发&nbsp; &nbsp;&nbsp; &nbsp;到相应版块。</span><br>
						    </div>
						</div>
					</div>
					<!-- 推荐阅读 -->
					<div class="titlebar_expand"><h3>推荐阅读</h3></div>
					<div class="rules_expand read">
						
					</div>
					<!-- 活跃会员 -->
					<div class="titlebar_expand"><h3>活跃会员</h3></div>
					<div class="rules_expand member">
						
					</div>
				</div>
				
				<div class="plate_mn">
					<div class="tl bm bmw">
					<div class="th">
						<table cellspacing="0" cellpadding="0">
						<tbody><tr>
						<th class="filbarwrap_thread" colspan="2">
						<div class="tf filterbar_thread cl">
						<a href="javascript:;" class="show_elect show_all" >全部</a>
                        <span class="pipe2"> </span>
                        <a href="javascript:;" class="show_suggest" >推荐</a>
                        <span class="pipe2"> </span>
                        <a href="javascript:;" class="show_hot">热门</a>
                        <span style=" margin-left:24px;">&nbsp;</span>
                                             
						<a href="javascript:;" class="bluebtn normalbtn y" id="newspecial" title="发新帖" >发帖</a>
						</div>
						</th>
						
						</tbody></table>
					</div>
					<!-- 帖子列表 -->
					<div class="bm_c">
						
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
	<p id="back-to-top" style="display: block;"><a href="#top"><span></span>回到顶部</a></p>
	<div class="clr"></div>
	<!-- footer -->
	<div id="bottom-content-wrapper">
		<jsp:include page="footer.jsp" flush="true"/>
	</div>
</body>
</html>