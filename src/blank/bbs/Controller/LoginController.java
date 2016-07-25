package blank.bbs.Controller; 
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map; 
import javax.servlet.http.HttpSession;   
import com.jfinal.aop.Before; 
import com.jfinal.log.Logger; 
import com.jfinal.plugin.spring.IocInterceptor; 
import com.jfinal.plugin.spring.Inject;  
import blank.bbs.service.Loginservice; 

@Before(IocInterceptor.class)
public class LoginController extends BaseController { 
	
	public static final Logger log = Logger.getLogger(LoginController.class);
 
	@Inject.BY_NAME 
	private Loginservice loginservice ;

	/**
	 * 用户登录 
	 * @return
	 * @throws Excetion
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" }) 

	
	public void Loginajax() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String name = getPara("user.name");
		String password = getPara("user.password");
		String code = getPara("code");
		map.put("name", name);
		map.put("password", password);
		try { 
			//验证码是否争取
			if (validateCode(code)) {  
				Map<String, Object> mm = loginservice.loginajax(map);
				if (Boolean.parseBoolean(mm.get("success").toString())) {
					Map<String, Object> sessionresult = (Map<String, Object>) mm
							.get("smap");
					Iterator result = sessionresult.entrySet().iterator();
					while (result.hasNext()) { 
						String array[] = result.next().toString().split("=");  
						setSessionAttr(array[0],array[1]);
					} 
				} 
				renderJson(mm); 
			}else{
				Map<String,Object> result = new HashMap<String,Object>();
				result.put("success", false);
				result.put("error","验证码错误，请重新输入");
				renderJson(result); //验证码
				
			}
		} catch (Exception e) {
			e.getStackTrace(); 
			log.info("登录出错,请检查相关信息");
			Map<String,Object> logmessage = new HashMap<String,Object>(); 
			logmessage.put("success", false);
			logmessage.put("error", "登录信息有误");
			renderJson(logmessage);
		}

	} 
	/**
	 * 判断验证码 是否正确
	 * 
	 * @return
	 * @throws Excetion
	 */
	public boolean validateCode(String validateCode) throws Exception {
		HttpSession httpsession = getRequest().getSession();
		String makeValidateCode = (String) httpsession
				.getAttribute("validationCode"); 
		if (validateCode != null && makeValidateCode != null) {
			return makeValidateCode.equalsIgnoreCase(validateCode);
		} else {
			return false;
		}
	} 
	/**
	 * 退出登陆  
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception{ 
		HttpSession ctx =getSession();
		//清除session
		if(ctx!=null){  
			ctx.invalidate();
		}   
		redirect("/login") ; 
		return null;
	} 
	
	/**
	 *  注册
	 * @return
	 * @throws Excetion
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void Loginregister() throws Exception{
		String username = getPara("username")==null?"":getPara("username").toString();
		String password = getPara("password")==null?"":getPara("password").toString();
		String email = getPara("email")==null?"":getPara("email").toString();
		String code = getPara("verifyCode");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username", username);
		map.put("password", password);
		map.put("email", email);
		//验证码是否争取
		if (validateCode(code)) {  
			Map<String,Object> mmp = loginservice.Loginregister(map);
			if(Boolean.valueOf(mmp.get("success").toString())){
				map.put("name", username);
				map.put("password", password);
				Map<String, Object> mm = loginservice.loginajax(map);
				if (Boolean.parseBoolean(mm.get("success").toString())) {
					Map<String, Object> sessionresult = (Map<String, Object>) mm
							.get("smap");
					Iterator result = sessionresult.entrySet().iterator();
					while (result.hasNext()) { 
						String array[] = result.next().toString().split("=");  
						setSessionAttr(array[0],array[1]);
					}   
				} 
				mm.put("url", "/bbs/personal");
				renderJson(mm);
			}else{
				renderJson(mmp);
			}
		} else {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", false);
			result.put("error", "验证码错误，请重新输入");
			renderJson(result);

		}

	}
	
}
