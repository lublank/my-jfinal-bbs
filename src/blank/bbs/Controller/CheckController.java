package blank.bbs.Controller;
 

import com.jfinal.aop.Before;
import com.jfinal.plugin.spring.Inject; 
import com.jfinal.plugin.spring.IocInterceptor;

import blank.bbs.service.Checkservice;

@Before(IocInterceptor.class)
public class CheckController extends BaseController {
	
	@Inject.BY_NAME 
	private Checkservice checkservice ;
	
	/**
	 * 检查注册名是否存在
	 * 
	 */
	public String checkLoginname() throws Exception{
		String loginname = getPara("usename")==null?"":getPara("usename");
		if(!"".equals(loginname)){ 
			 boolean result =checkservice.checkLoginname(loginname);  
			 renderJson(result); 
		}else{
			renderNull();
		} 
		return null;
	}
} 