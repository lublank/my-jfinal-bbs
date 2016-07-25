package blank.bbs.route;

import blank.bbs.Controller.CheckController; 
import blank.bbs.Controller.Fileupload;
import blank.bbs.Controller.FirstController;
import blank.bbs.Controller.LoginController;
import blank.bbs.Controller.PersonController;
import blank.bbs.Controller.PoloController; 
import blank.bbs.Controller.TieController;

import com.jfinal.config.Routes;

public class Baseroute extends Routes {

	@Override
	/**
	 * 用来处理逻辑请求
	 */
	public void config() {  
		 //登录页面
		 add("/login",LoginController.class);
		 //检查
		 add("/check",CheckController.class); 
		 //首页的操作
		 add("/base",FirstController.class);
		 //公共的操作
		 add("/polo",PoloController.class);
		 //帖子相关
		 add("/tiezi",TieController.class);
		 //个人中心
		 add("/personal",PersonController.class);
		 //上传
		 add("/upload",Fileupload.class);
		
	}

}
