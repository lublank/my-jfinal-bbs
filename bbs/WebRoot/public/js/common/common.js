$(document).ready(function () {
	/*check the browser version*/
	(function(){
		var appName = navigator.appName;
		var userAgent = navigator.userAgent;
		var version;
		if(appName == 'Microsoft Internet Explorer'){
			version = userAgent.substring(30, 33);
			versionfloat = parseFloat(version);
			if(versionfloat < 8 && versionfloat > 4){
				alert('您当前的浏览器内核为IE' + versionfloat + '，请升级为IE8或IE8以上。');
			}
		}
	})();
	/*导航栏*/
    $('li.mainlevel').mousemove(function(){
        $(this).find('ul').show();
    });
    $('li.mainlevel').mouseleave(function(){
        $(this).find('ul').hide();
    });
	
	$(".login").click(function (){
		window.location.href = "/bbs/login";
	});
	/*注销登录*/
	$("#logout").live("click",function(){
		if(confirm("确定要退出登录吗?"))
		{
			window.location.href="/bbs/login/logout";
		}
    });
	//返回页首
	//首先将#back-to-top隐藏
	$("#back-to-top").hide();
	//当滚动条的位置处于距顶部100像素以下时，跳转链接出现，否则消失
	$(function () {
		$(window).scroll(function(){
			if ($(window).scrollTop()>100){
			$("#back-to-top").fadeIn(500);
			}
			else
			{
			$("#back-to-top").fadeOut(500);
			}
		});
		//当点击跳转链接后，回到页面顶部位置
		$("#back-to-top").click(function(){
			$('body,html').animate({scrollTop:0},100);
			return false;
		});
	});
 });
/**
 * 获取url参数
 * @param name : url参数名
 * @returns url参数值 or null
 */
function getQueryString(name){
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r!=null) return unescape(r[2]); return null; 
}
