package blank.bbs.service; 
import java.util.Map;
  
 
public interface Loginservice {
	
	/**
	 *  登录验证
	 * @return
	 * @throws Excetion
	 */
	 Map<String,Object> loginajax(Map<String,Object>map) throws Exception;
	
	/**
	 * 注册
	 * @return
	 * @throws Excetion
	 */
	 Map<String,Object> Loginregister(Map<String,Object>map) throws Exception;
}
