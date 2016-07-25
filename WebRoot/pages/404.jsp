<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>    
    <title>404</title>
	<link href="/bbs/public/css/common.css" type="text/css" rel="stylesheet"/>
	<style type="text/css">
		body,div{margin:0;padding:0;}
		.img {
			background: url('/bbs/public/images/xlb.jpg');
			width: 231px;
			height: 135px;
			top: 168px;
			margin-left: -280px;
		}
		p{
			position: absolute;
			left: 50%;
		}
		.p1 {
			top: 184px;
			color: #ed921b;
			font-size: 24px;
			margin-left: 48px;
		}
		.p2 {
			top: 222px;
			font-size: 20px;
			color: #808080;
			margin-left: 63px;
		}
		.p3 {
			top: 260px;
			font-weight: bold;
			font-size:24px;
			color:#7C999B;
			margin-left: 90px;
		}
	</style>
    <script src="/bbs/public/js/common/jquery.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/common/common.js" type="text/javascript" charset="utf-8"></script>
  </head>
  
  <body class="white" style="overflow: hidden;">
	<jsp:include page="header.jsp"></jsp:include>
	<div style="min-height:800px">
	    <p class="img"></p>
	    <p class="p1" id="text1">粗问题啦，页面找不到</p>
	    <p class="p2" id="text2">（页面错误：404）</p>
	    <p class="p3" id="text3"><a href="/bbs">返回首页</a></p>
	</div>
    
      <div id="bottom-content-wrapper">
		<jsp:include page="footer.jsp" flush="true"/>
	  </div>
  </body>
</html>
