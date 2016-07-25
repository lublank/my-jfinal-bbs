package blank.bbs.route;

 
 
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpSession; 

import blank.bbs.common.Constant;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;
 
/**
 * 权限拦截器 
 *
 */

public class Baseinterceptor  implements Interceptor { 
	
	
	public void intercept(ActionInvocation ai) { 
		Controller c = ai.getController(); 
		HttpServletRequest request = c.getRequest(); 
		HttpSession session = request.getSession();
		String  url = request.getRequestURI();
		
		//组装导航条
		if(session!=null&&session.getAttribute(Constant.SESSION_USERNAME)!=null){ 
			int userid = Integer.valueOf(session.getAttribute(Constant.SESSION_USERID).toString()); 
			int role =  session.getAttribute(Constant.USER_ROLE)==null?-1:Integer.valueOf(session.getAttribute(Constant.USER_ROLE).toString());
			String nav = "";
			String mlogin = "";
			nav+="<li class='nav_more2'><a href='/bbs/mypage?t=3'><span>我的关注</span></a></li>"
               +"<li class='nav_more2'><a href='/bbs/mypage?t=5'><span>我的发表</span></a></li>"
               +"<li class='nav_more2'><a href='/bbs/personal'><span>个人中心</span></a></li>";
			if(role==1){
				nav+="<li class='nav_more2'><a href='/bbs/tiezi'><span>帖子管理</span></a></li>";  
			};  
			mlogin+="欢迎您，<a href='/bbs/personal?id="+userid+"'>"+session.getAttribute(Constant.SESSION_USERNAME)+"</span> | <a href='javascript:;' id='logout' title='退出登录'>注销</a>";
			c.setAttr("content", nav);
			c.setAttr("message", mlogin);
		}else{
			c.setAttr("message","<a href='/bbs/login?id=1' class='login' title='登录'>登录</a>|<a href='/bbs/login?id=2' class='login' title='注册'>注册</a>");  
		} 
		
		//登录
		if(session!=null && session.getAttribute(Constant.SESSION_USERID)!=null){
			if(url.equals("/bbs/")||url.equals("/bbs/index")||url.equals("/bbs/login/Loginajax")){  
				c.render("/pages/index.jsp") ;
				return;
			}
			if(Integer.valueOf(session.getAttribute(Constant.SESSION_USERID).toString())!=1&&url.equals("/bbs/tiezi/tieList")){
				c.render("/pages/index.jsp");
				return;
			}
			ai.invoke();//继续执行链接
		}
		//未登录不给浏览
		else if(url.equals("/bbs/base/signin")||url.equals("/bbs/tiezi/write")||url.equals("/bbs/tiezi/answertie")||url.equals("/bbs/polo/Checkattention")||url.equals("/bbs/polo/CheckCollect")||url.equals("/bbs/personal/userInfo")||url.equals("/bbs/personal/getUsermessage")||url.equals("/bbs/polo/getUsermessage")||url.equals("/bbs/polo/updateNotify")||url.equals("/bbs/tiezi/tieList")){  
			Map<String,Object> mm = new HashMap<String,Object>();
			mm.put("unlogin", true);
			c.renderJson(mm); 
			return ;
		} 
		else{
			ai.invoke();
		} 
	} 
}
