package blank.bbs.service.impl; 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 

import org.joda.time.DateTime;
import org.springframework.stereotype.Service; 

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.tx.Tx;

import blank.bbs.common.Constant;
import blank.bbs.model.Sign;
import blank.bbs.model.User; 
import blank.bbs.service.Loginservice;

@Service("loginservice")
public class LoginserviceImpl implements Loginservice{
	
	
	/**
	 * 登录验证
	 */
	public Map<String,Object> loginajax(Map<String,Object>map) throws Exception{
		Map<String,Object> mp = new HashMap<String,Object>();
		String  name = map.get("name").toString();
		String password = map.get("password").toString(); 
		String  sql  = " select * from user where username = '"+name+"' and password ='"+password+"'";  
		User user = User.dao.getbysql(sql);   
		Map<String,Object> ma = new HashMap<String,Object>(); 
		if(user!=null){
			ma.put(Constant.SESSION_ACCOUNT, user.get("username"));
			ma.put(Constant.SESSION_USERNAME, user.get("nickname")); 
			ma.put(Constant.SESSION_USERID, user.get("id"));
			String hql =" select stime from sign t where t.uid = "+user.get("id");
			Sign sign =Sign.dao.findFirst(hql);  
			if(sign!=null){
				SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
				String stime = sign.get("stime")==null?"test":sign.get("stime").toString(); 
				DateTime datime = new DateTime();
				formatDate.parse(stime);
				datime.toString("yyyy-MM-dd");
				if(datime.equals(datime)){
					ma.put(Constant.SIGN_IN, false);
				}else{
					ma.put(Constant.SIGN_IN, true);
				} 
			}else{
				ma.put(Constant.SIGN_IN, true);
			} 
			ma.put(Constant.USER_ROLE, user.get("role"));//设置角色属性
			mp.put("url", "/bbs/index");
			mp.put("success", true);
			mp.put("error", "");
		}else{
			mp.put("success", false);
			mp.put("error", "登录失败，请重试");
		} 
		List<Object> list  = new ArrayList<Object>();
		list.add(mp); 
		mp.put("smap", ma); 
		return mp;
	}
	
	/**
	 * 注册
	 */
	@Before(Tx.class)
	public  Map<String,Object> Loginregister(Map<String,Object>map) throws Exception{
		Map<String,Object> mm = new HashMap<String,Object>(); 
		String username = map.get("username").toString();
		String password = map.get("password").toString();
		String email = map.get("email").toString();
		DateTime date = new DateTime();
		String datetime = date.toString("yyyy-MM-dd hh:mm:ss");
		try{
			new User().set("username", username)
			.set("password", password)
			.set("email", email)
			.set("nickname", username)
			.set("regtime", datetime)
			.save(); 
			mm.put("success", true);
			mm.put("error", "");
		}catch(Exception e){
			mm.put("success", false);
			mm.put("error", "注册失败，请联系管理员");
			e.printStackTrace();
		}
		return mm;
	}
	
	
}
