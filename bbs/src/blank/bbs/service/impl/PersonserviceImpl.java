package blank.bbs.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.tx.Tx;

import blank.bbs.model.Attention;
import blank.bbs.model.Collect;
import blank.bbs.model.Fans;
import blank.bbs.model.User;
import blank.bbs.service.Personservice;

@Service("personservice")
public class PersonserviceImpl implements Personservice {

	@Override
	public  Map<String,Object> userInfo(String id) throws Exception {
		Map<String,Object> mm = new HashMap<String,Object>();
		User user = new User();
		user = User.dao.findById(id);
		if(user!=null){
			mm.put("uid", user.get("id"));
			mm.put("username", user.get("username"));
			mm.put("nickname", user.get("nickname"));
			mm.put("gender", user.get("gender"));
			mm.put("birthday", user.get("birthday"));
			mm.put("stars", user.get("stars"));
			mm.put("email", user.get("email"));
			mm.put("phone", user.get("phone"));
			mm.put("head", user.get("head"));
			mm.put("description", user.get("description"));
			mm.put("regtime", user.get("regtime"));
			mm.put("favorite", user.get("favorite"));
			mm.put("address", user.get("address"));
		}
		return mm;
	}

	@Override
	public Map<String, Object> updateInfo(Map<String, Object> map)	throws Exception {
		Map<String,Object> mm = new HashMap<String,Object>();
		int uid= Integer.valueOf(map.get("id").toString());
		String nickname= map.get("nickname").toString();
		int gender= Integer.valueOf(map.get("gender").toString());
		String birthday= map.get("birthday").toString();
		String stars= map.get("stars").toString();
		String email= map.get("email").toString();
		String phone= map.get("phone").toString();
		String description= map.get("description").toString();
		String favorite= map.get("favorite").toString();
		String address= map.get("address").toString();
		try{
			User.dao.findById(uid).set("nickname", nickname).set("gender", gender)
			.set("birthday", birthday).set("stars", stars).set("email", email).set("phone", phone)
			.set("description", description).set("favorite", favorite).set("address", address).update();
			mm.put("success", true);
			mm.put("error", "");
			}catch(Exception e){
				e.printStackTrace();
				mm.put("success", false);
				mm.put("error", "更新失败");
			}
			return mm;
	}

	@Override
	public Map<String, Object> updatepwd(Map<String, Object> map) throws Exception {
		Map<String,Object> mm = new HashMap<String,Object>();
		int uid= Integer.valueOf(map.get("id").toString());
		String opwd= map.get("opwd").toString();
		String newPwd= map.get("newPwd").toString();
		User user = User.dao.findById(uid, "password");
		String password = user.getStr("password");
		if(password.equals(opwd)){
			try{
			User.dao.findById(uid).set("password", newPwd).update();
			mm.put("success", true);
			mm.put("error", "");
			}catch(Exception e){
				e.printStackTrace();
				mm.put("success", false);
				mm.put("error", "更新失败");
			}
		}else{
			mm.put("success", false);
			mm.put("error", "密码修改失败");
		}
			return mm;
	}

	@Override
	public Map<String, Object> updatehead(Map<String, Object> map) throws Exception {
		Map<String,Object> mm = new HashMap<String,Object>();
		int uid= Integer.valueOf(map.get("id").toString());
		String head= map.get("head").toString();
	
		try{
		User.dao.findById(uid).set("head", head).update();
			mm.put("success", true);
			mm.put("error", "");
		}catch(Exception e){
			e.printStackTrace();
			mm.put("success", false);
			mm.put("error", "更新头像失败");
		}
	
		return mm;
	}
	
	/**
	 * 移除粉丝
	 */
	@Before(Tx.class)
	public   Map<String,Object> removefans(Map<String,Object>map) throws Exception{
		Map<String,Object> mm = new HashMap<String,Object>();
		int id= Integer.valueOf(map.get("id").toString());
		int uid= Integer.valueOf(map.get("uid").toString());  
		String sql = "select * from fans where uid ='"+uid+"' and uuid = '"+id+"'";
		try{
			Fans fans = Fans.dao.findFirst(sql);
			if(fans!=null){
				fans.delete(); 
				mm.put("success", true);
				mm.put("error", "您已移除了该粉丝");
			} else{ 
				mm.put("success", false);
				mm.put("error", "此人没关注你");
			}
		}catch(Exception e){
			e.printStackTrace();
			mm.put("success", false);
			mm.put("error", "网页错误，请联系管理员");
		}
	
		return mm;
	}
	
	/**
	 * 取消关注
	 */
	public  Map<String,Object> cancelAttention(Map<String,Object>map) throws Exception{
		Map<String,Object> mm = new HashMap<String,Object>();
		int id= Integer.valueOf(map.get("id").toString());
		int uid= Integer.valueOf(map.get("uid").toString());  
		String sql = "select * from attention where uid ='"+uid+"' and uuid = '"+id+"'";
		try{
			Attention fans = Attention.dao.findFirst(sql);
			if(fans!=null){
				fans.delete(); 
				mm.put("success", true);
				mm.put("error", "您已取消关注");
			} else{ 
				mm.put("success", false);
				mm.put("error", "你没关注此人");
			}
		}catch(Exception e){
			e.printStackTrace();
			mm.put("success", false);
			mm.put("error", "网页错误，请联系管理员");
		}
	
		return mm;
	 }
	
	/**
	 * 取消收藏
	 */
	public  Map<String,Object> cancelCollect(Map<String,Object>map) throws Exception{
		Map<String,Object> mm = new HashMap<String,Object>();
		int id= Integer.valueOf(map.get("id").toString());
		int uid= Integer.valueOf(map.get("uid").toString());  
		String sql = "select * from collect where uid ='"+uid+"' and tid = '"+id+"'";
		try{
			Collect fans = Collect.dao.findFirst(sql);
			if(fans!=null){
				fans.delete(); 
				mm.put("success", true);
				mm.put("error", "您已取消了收藏");
			} else{ 
				mm.put("success", false);
				mm.put("error", "你还没收藏了此文章");
			}
		}catch(Exception e){
			e.printStackTrace();
			mm.put("success", false);
			mm.put("error", "网页错误，请联系管理员");
		}
	
		return mm;
	}
}
