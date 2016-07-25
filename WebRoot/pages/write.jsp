<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>发帖页面</title>
	<link href="/bbs/public/css/common.css" type="text/css" rel="stylesheet"/>
	<link href="/bbs/public/css/theams/bootstrap.css" type="text/css" rel="stylesheet"/>
	<link href="/bbs/public/css/write.css" type="text/css" rel="stylesheet"/>
	<link href="/bbs/public/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet"/>
	<link href="/bbs/public/js/kindeditor-4.1.10/plugins/code/prettify.css" type="text/css" rel="stylesheet"/>
	
    <script src="/bbs/public/js/common/jquery.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/common/common.js" type="text/javascript" charset="utf-8"></script>
    <script src="/bbs/public/js/kindeditor-4.1.10/kindeditor.js" type="text/javascript"></script>
    <script src="/bbs/public/js/kindeditor-4.1.10/lang/zh_CN.js" type="text/javascript"></script>
    <script src="/bbs/public/js/kindeditor-4.1.10/plugins/code/prettify.js" type="text/javascript"></script>
    <script src="/bbs/public/js/write.js" type="text/javascript" charset="utf-8"></script>
  
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="wrap">
	<div class="td-head">
        	<p class="td_title">
        	<span class="td_title_ico"></span>
            <a href="/bbs/index">首页</a><span>&gt;</span><em>发表文章</em>
        	</p>
        </div>
        <div class="td-center" id="article">
            <div class="pantent">
                <form id="post" name="post" method="post">
                    <div class="control-group">
                        <label class="control-label" for="title">标题</label>
                        <div class="controls">
                            <input type="text" autocomplete="off" class="input-xlarge" name="title" id="title"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="section">版块</label>
                        <div class="controls">
                        <select id="select" style="width: 200px;">
                            <option></option>
                            <option>技术</option>
                            <option>文学</option>
                            <option>生活</option>
                            <option>闲聊</option>
                        </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="Content">内容</label>
                        <div class="controls">
                            <textarea name="content" id="content" cols="100" rows="8" style="width:800px;min-height:400px"></textarea>
                        </div>
                    </div>
                <!--<div class="control-group">
                    <label class="control-label">@我的好友(<span style="color:red;font-size:10px;">最多能@三个好友</span>)</label>
                        <div class="controls">
                            <select id="friends" multiple="multiple" size="3">
                            <option>--我的好友--</option>
                            <option>我的好友1</option>
                            <option>我的好友2</option>
                            <option>我的好友3</option>
                            <option>我的好友4</option>
                            <option>我的好友5</option>
                            <option>我的好友6</option>
                            </select>
                        </div>
                    </div>-->
                    <div class="control-group">
                        <div class="controls">
                            <input type="submit" class="btn" id="sub_btn" value="提交"/>
                            <img src="/bbs/public/images/loading.gif" id="loding" style="display: none" />
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div id="abc" style="width:10px;"></div>
        <div class="td-foot"></div>
</div>

<div class="clr"></div>
	<!-- footer -->
	<div id="bottom-content-wrapper" style="top: 80px;position: relative;">
		<jsp:include page="footer.jsp" flush="true"/>
	</div>
</body>
</html>