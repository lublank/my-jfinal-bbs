package blank.bbs.route;

import blank.bbs.Controller.IndexController;

import com.jfinal.config.Routes;

public class Indexroute extends Routes {

	@Override
	public void config() {  
		//默认页面，用来返回页面地址
		 add("/",IndexController.class,"/pages");
	}

}
