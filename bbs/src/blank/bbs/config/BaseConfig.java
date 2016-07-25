package blank.bbs.config; 
import blank.bbs.handle.Basehandler; 
import blank.bbs.model.Attention;
import blank.bbs.model.Card;
import blank.bbs.model.Collect;
import blank.bbs.model.Fans;
import blank.bbs.model.Notify;
import blank.bbs.model.Reverts;
import blank.bbs.model.Score;
import blank.bbs.model.Sign;
import blank.bbs.model.User;
import blank.bbs.route.Baseinterceptor;
import blank.bbs.route.Baseroute; 
import blank.bbs.route.Indexroute; 
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin; 
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.spring.SpringPlugin;
import com.jfinal.render.ViewType;
import blank.bbs.route.VcodeController;

public class BaseConfig extends JFinalConfig {

	/**
	 * 常量配置
	 */
	public void configConstant(Constants me) { 
		loadPropertyFile("database_config.txt");  //加载数据库连接信息
		me.setDevMode(getPropertyToBoolean("devMode"));
		me.setViewType(ViewType.JSP); 
		me.setUrlParaSeparator("&"); //设置参数的间隔符为& 
	}

	/**
	 * 路由信息
	 */
	public void configRoute(Routes me) {  
		//公共--生成验证码
		me.add("/vcode",VcodeController.class); 
		//前端路由--页面跳转
		me.add(new Indexroute());
		//后端路由--调用后台
		me.add(new Baseroute()); 
	}

	/**
	 * 数据库连接信息
	 */
	public void configPlugin(Plugins me) { 
		
		//添加spring
		me.add(new SpringPlugin()); 
		
		//数据库连接
		C3p0Plugin cp = new C3p0Plugin(getProperty("jdbcUrl"),getProperty("user"),getProperty("password").trim()); 
		me.add(cp);
		ActiveRecordPlugin arp = new ActiveRecordPlugin("dengmysql",cp);
		me.add(arp); 
		arp.addMapping("user",  User.class);
		arp.addMapping("score", Score.class);
		arp.addMapping("card", Card.class);
		arp.addMapping("reverts", Reverts.class);
		arp.addMapping("sign", Sign.class);
		arp.addMapping("attention", Attention.class);
		arp.addMapping("fans", Fans.class);
		arp.addMapping("collect", Collect.class);
		arp.addMapping("notify", Notify.class); 
	}

	/**
	 * 全局拦截器
	 */
	public void configInterceptor(Interceptors me) {  
		me.add(new Baseinterceptor()); 
	}

	/**
	 * 处理器
	 */
	public void configHandler(Handlers me) { 
		me.add(new Basehandler());  
	}

}
