<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>帖子管理</title>
	<link href="/bbs/public/css/common.css" type="text/css" rel="stylesheet"/>
	<link href="/bbs/public/css/theams/table_new.css" type="text/css" rel="stylesheet"/>
	<link href="/bbs/public/css/mypage.css" type="text/css" rel="stylesheet"/>
    <script src="/bbs/public/js/common/jquery.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/common/common.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/common/makeList.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/common/tableEvent.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/tiezi.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div id="wrap">
	<!-- nav -->
	<div id="scbar" class="cl">
        <div class="scbar_wrap">
        	<input type="text" name="srchtxt" id="scbar_txt" autocomplete="off" class=" xg1" placeholder="搜索主题和用户">
            <button type="submit" name="searchsubmit" id="scbar_btn" value="true"></button>
        </div>
	</div>
		<div class="cr"> </div>
	<div class="plate" style="width:1022px;background:#FFF;">
		<div class="tl bm bmw">
			
			<!-- 列表 -->
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
	<p id="back-to-top" style="display: block;"><a href="#top"><span></span>回到顶部</a></p>
	<div class="clr"></div>
	<!-- footer -->
	<div id="bottom-content-wrapper" style="top: 80px;position: relative;">
		<jsp:include page="footer.jsp" flush="true"/>
	</div>
</body>
</html>