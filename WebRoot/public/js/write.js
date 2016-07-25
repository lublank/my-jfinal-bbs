/**
 *发表帖子js
 */
$(function(){
	$("#sub_btn").on('click', function (e) {
		var title = $("#title").val();
		var select = $("#select").val();
		var content = document.post.content.value;
		if(title==""){
			alert("请输入标题！");
			$("#title").focus();
			return false;
		}else if(select==""){
			alert("请选择版块！");
			$("#select").focus();
			return false;
		}else if(content==""){
			alert("请输入帖子内容！");
			$("#content").focus();
			return false;
		}else{
			//请求后台
			$.post("/bbs/tiezi/write",{"title":title,"section":select,"content":content},function(data){
				if(data.unlogin){
					alert("您还没登录或登录超时，请先登录");
					window.location.href = "/bbs/login";
					return false;
				}
				if(data.success){
					alert("发布成功");
					window.location.href = "/bbs/mypage?t=5";
				}else{
					alert("发布出错了T_T,请重试！");
				}
			},'json');
		}
		return false;
	});
});

//配置发帖子文本框
KindEditor.ready(function(K) {
	var editor = K.create('textarea[name="content"]', {
		cssPath : '/bbs/public/js/kindeditor-4.1.10/plugins/code/prettify.css',
		allowFileManager : false,
		resizeType : 1,
		allowImageUpload: true,
        uploadJson: '/Upload/UploadFile',
		afterCreate : function() {
			this.sync();
		},
		afterChange : function() {
			this.sync();
		},
		afterBlur:function(){
			this.sync();  
		},
		items : ['bold', 'italic', 'underline', 'strikethrough', 'removeformat','|','insertorderedlist', 'insertunorderedlist', 
				 'forecolor', 'hilitecolor', 'fontname', 'fontsize', 'undo', 'redo',  '|', 'justifyleft', 'justifycenter', 'justifyright',
				 'justifyfull', 'link', 'unlink', 'emoticons', '|', 'source'],
		
	});
});
