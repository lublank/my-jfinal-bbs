package blank.bbs.Controller;

import javax.servlet.http.HttpSession;

import blank.bbs.common.Constant;
 
 
/**
 * 
 * 前台页面跳转
 *
 */
public class IndexController extends BaseController {
	
	
	/** 
	 * 登录
	 * @author Lu.bl
	 * @date2014-5-18
	 * @throws Excetion
	 */
		public void login() throws Exception {   
		String url = getRequest().getRequestURI();
		HttpSession session = getRequest().getSession();
		if (session != null && session.getAttribute(Constant.SESSION_USERNAME) != null) {
			if (!url.equals("/bbs/index")) { 
				render("index.jsp");
				return;
			}
		}
	  render("login.jsp");  
	}
		
		
		/**
		 *  跳转到首页
		 * @author Lu.bl
		 * @date2014-5-20
		 * @throws Excetion
		 */
		public void index() throws Exception{ 
			render("index.jsp"); 
		}
		
		
		/**
		 * 跳转到注册页面 
		 * @author Lu.bl
		 * @date2014-5-20
		 * @throws Excetion
		 */
		public void regist() throws Exception{
			render("login.jsp"); 
		}
		/**
		 * 跳转到发表帖子页
		 *@ author Lu.bl
		 *@ date2014-5-26
		 * @throws Exception
		 */
		public void write() throws Exception{
			render("write.jsp"); 
		}
		/**
		 * 跳转到帖子详细页
		 * @author Lu.bl
		 * @date2014-6-5
		 * @throws Exception
		 */
		public void commentdetail() throws Exception{
			render("commentdetail.jsp"); 
		}
		/**
		 * 跳转到个人主页
		 * @author Lu.bl
		 * @date2014-6-5
		 * @throws Exception
		 */
		public void mypage() throws Exception{
			render("mypage.jsp"); 
		}
		/**
		 * 跳转到个人中心
		 * @author Lu.bl
		 * @date2014-6-5
		 * @throws Exception
		 */
		public void personal() throws Exception{
			render("personal.jsp"); 
		}
		/**
		 * 跳转到其他用户主页
		 * @author Lu.bl
		 * @date2014-6-16
		 * @throws Exception
		 */
		public void userpage() throws Exception{
			render("userpage.jsp"); 
		}
		/**
		 * 帖子管理
		 * @author Lu.bl
		 * @date2014-6-22
		 * @throws Exception
		 */
		public void tiezi() throws Exception{
			render("tiezi.jsp"); 
		}
}
