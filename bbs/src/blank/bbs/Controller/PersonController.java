package blank.bbs.Controller;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.plugin.spring.Inject;
import com.jfinal.plugin.spring.IocInterceptor;

import blank.bbs.common.Constant;
import blank.bbs.service.Personservice;
import blank.bbs.service.Tieservice;

@Before(IocInterceptor.class)
public class PersonController extends BaseController {

	@Inject.BY_NAME 
	Personservice personservice;
	@Inject.BY_NAME 
	Tieservice tieservice;
	/**
	 *  获取登录用户信息
	 * @return
	 * @throws Exception
	 */
	public void userInfo() throws Exception{
		String  userid = getSession().getAttribute(Constant.SESSION_USERID)==null?"":getSession().getAttribute(Constant.SESSION_USERID).toString();
		renderJson(personservice.userInfo(userid));
	}
	/**
	 *  获取用户的个人信息
	 * @return
	 * @throws Excetion
	 */
	 public void getUsermessage() throws Exception{
		 String id = getPara("id")==null?"":getPara("id").toString();
		
		 Map<String,Object>mm = new HashMap<String,Object>();
			if(!"".equals(id)){   
				 mm=tieservice.getMessage(id);
			} 
			renderJson(mm);
	 }
	 /**
	 *  获取TA的发表
	 * @return
	 * @throws Excetion
	 */
	public void getHiscard() throws Exception{
		String id = getPara("id")==null?"":getPara("id").toString();
		String curpage = getPara("curpage")==null?"1":getPara("curpage").toString();
		String pagesize=getPara("pagesize")==null?"15":getPara("pagesize").toString();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userid", id);
		map.put("curpage", curpage);
		map.put("pagesize", pagesize);
		renderJson(tieservice.getmycard(map));
	}
	/**
	 * 更新个人资料
	 * @return
	 * @throws Exception
	 */
	public void updateInfo() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String nickname = getPara("nickname")==null?"":getPara("nickname").toString();
		String gender = getPara("gender")==null?"":getPara("gender").toString();
		String birthday = getPara("birthday")==null?"":getPara("birthday").toString();
		String stars = getPara("stars")==null?"":getPara("stars").toString();
		String email = getPara("email")==null?"":getPara("email").toString();
		String phone = getPara("phone")==null?"":getPara("phone").toString();
		String description = getPara("birthday")==null?"":getPara("description").toString();
		String favorite = getPara("favorite")==null?"":getPara("favorite").toString();
		String address = getPara("address")==null?"":getPara("address").toString();
		String uid = getSession().getAttribute(Constant.SESSION_USERID).toString();
		map.put("id", uid);
		map.put("nickname",nickname);
		map.put("gender", gender);
		map.put("birthday", birthday);
		map.put("stars", stars);
		map.put("email", email);
		map.put("phone", phone);
		map.put("description", description);
		map.put("favorite", favorite);
		map.put("address", address);
		Map<String,Object> mm=personservice.updateInfo(map);
		renderJson(mm);
	}
	/**
	 * 更改密码
	 * @return
	 * @throws Exception
	 */
	public void updatepwd() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String opwd = getPara("oldPwd")==null?"":getPara("oldPwd").toString();
		String newPwd = getPara("newPwd")==null?"":getPara("newPwd").toString();
		String uid = getSession().getAttribute(Constant.SESSION_USERID).toString();
		map.put("id", uid);
		map.put("opwd", opwd);
		map.put("newPwd", newPwd);
		Map<String,Object> mm=personservice.updatepwd(map);
		renderJson(mm);
	}
	/**
	 * 修改系统头像
	 * @return
	 * @throws Exception
	 */
	public void updatehead() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String head = getPara("head")==null?"":getPara("head").toString();
		String uid = getSession().getAttribute(Constant.SESSION_USERID).toString();
		map.put("id", uid);
		map.put("head", head);
		Map<String,Object> mm=personservice.updatehead(map);
		renderJson(mm);
	}
	/**
	 * 移除粉丝
	 * @return
	 * @throws Excetion
	 */
	public void removefans() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String  id = getPara("id");
		String uid = getSession().getAttribute(Constant.SESSION_USERID).toString();
		map.put("id", id);
		map.put("uid", uid);
		Map<String,Object> mm=personservice.removefans(map);
		renderJson(mm); 
	}
	/**
	 * 取消关注
	 * @return
	 * @throws Excetion
	 */
	public void cancelAttention() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String  id = getPara("id");
		String uid = getSession().getAttribute(Constant.SESSION_USERID).toString();
		map.put("id", id);
		map.put("uid", uid);
		Map<String,Object> mm=personservice.cancelAttention(map);
		renderJson(mm); 
	}
	
	/**
	 * 取消收藏
	 * @return
	 * @throws Excetion
	 */
	public void cancelCollect() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String  id = getPara("id");
		String uid = getSession().getAttribute(Constant.SESSION_USERID).toString();
		map.put("id", id);
		map.put("uid", uid);
		Map<String,Object> mm=personservice.cancelCollect(map);
		renderJson(mm); 
	}
}
