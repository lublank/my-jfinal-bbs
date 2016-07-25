package blank.bbs.Controller;
 

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import com.jfinal.aop.Before;
import com.jfinal.plugin.spring.Inject;
import com.jfinal.plugin.spring.IocInterceptor;

import blank.bbs.common.Constant; 
import blank.bbs.model.Sign; 
import blank.bbs.service.Tieservice;
@Before(IocInterceptor.class)
public class PoloController extends BaseController {
	
	
	@Inject.BY_NAME 
	Tieservice tieservice;
	/**
	 * 检查是否签到 
	 * @return
	 * @throws Excetion
	 */
	public void checkSign() throws Exception{  
		DateTime datime = new DateTime();  
		String hql =" select * from sign t where t.uid = "+getSession().getAttribute(Constant.SESSION_USERID) +" and t.stime = '"+datime.toString("yyyy-MM-dd")+"'";
		Sign sign =Sign.dao.findFirst(hql); 
		if(sign!=null){
			 renderJson("check",true);
		 }else{
			 renderJson("check",false);
			} 
		}
		/**
		 *  获取用户的个人信息
		 * @return
		 * @throws Excetion
		 */
	 public void getUsermessage() throws Exception{
		 String id  = getSession().getAttribute(Constant.SESSION_USERID)==null?"": getSession().getAttribute(Constant.SESSION_USERID).toString(); 
			Map<String,Object>mm = new HashMap<String,Object>();
			if(!"".equals(id)){   
				 mm=tieservice.getMessage(id);
			} 
			renderJson(mm);
	 }
	 
	 /**
	  *  检查是否关注
	  * @return
	  * @throws Excetion
	  */
	 public void Checkattention() throws Exception{
		 Map<String,Object> mm = new HashMap<String,Object>();
		 String  id  = getPara("id")==null?"":getPara("id").toString();
		 String uid = getSession().getAttribute(Constant.SESSION_USERID)==null?"":getSession().getAttribute(Constant.SESSION_USERID).toString();
		 if(!"".equals(id)){
			 if(uid.equals(id)){
				 mm.put("success", true);
				 mm.put("error", "其实这个人是你自己");
				 mm.put("check", false);
			 }else{ 
				 mm=tieservice.Checkattention(id,uid);
			 }
		 }
		 renderJson(mm);
	 }
	 
	 /**
	  * 我的关注列表
	  * @return
	  * @throws Excetion
	  */
	 public void Myattention() throws Exception{
		 Map<String,Object> mm = new HashMap<String,Object>();
		 String id = getSession().getAttribute(Constant.SESSION_USERID)==null?"":getSession().getAttribute(Constant.SESSION_USERID).toString();
		 String curpage = getPara("curpage")==null?"1":getPara("curpage").toString();
		 String pagesize=getPara("pagesize")==null?"15":getPara("pagesize").toString();
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("id", id);
		 map.put("curpage", curpage);
		 map.put("pagesize", pagesize);
		 if(!"".equals(id)){
			 mm = tieservice.Myattention(map);
		 } 
		 renderJson(mm);
	 }
	
	 
	 /**
	  *  我的粉丝列表
	  * @return
	  * @throws Excetion
	  */
	 public void Myfans() throws Exception{
		 Map<String,Object> mm = new HashMap<String,Object>();
		 String id = getSession().getAttribute(Constant.SESSION_USERID)==null?"":getSession().getAttribute(Constant.SESSION_USERID).toString();
		 String curpage = getPara("curpage")==null?"1":getPara("curpage").toString();
		 String pagesize=getPara("pagesize")==null?"15":getPara("pagesize").toString();
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("id", id);
		 map.put("curpage", curpage);
		 map.put("pagesize", pagesize);
		 if(!"".equals(id)){
			 mm = tieservice.Myfans(map);
		 } 
		 renderJson(mm); 
	 }
	 
	 /**
	  *  检查是否收藏
	  * @return
	  * @throws Excetion
	  */
	 public void CheckCollect() throws Exception{
		 Map<String,Object> mm = new HashMap<String,Object>();
		 String  id  = getPara("id")==null?"":getPara("id").toString();
		 String uid = getSession().getAttribute(Constant.SESSION_USERID)==null?"":getSession().getAttribute(Constant.SESSION_USERID).toString();
		 if(!"".equals(id)){
			 if(uid.equals(id)){
				 mm.put("success", true);
				 mm.put("error", "其实这个帖子是您写的");
				 mm.put("check", false);
			 }else{ 
				 mm=tieservice.CheckCollect(id,uid);
			 }
		 }
		 renderJson(mm);
	 }
	 
	 /**
	  *  转帖
	  * @return
	  * @throws Excetion
	  */
	 public  void updateNotify() throws Exception{ 
		 Map<String,Object> mm = new HashMap<String,Object>();
		 String  id  = getPara("id")==null?"":getPara("id").toString();
		 String uid = getSession().getAttribute(Constant.SESSION_USERID)==null?"":getSession().getAttribute(Constant.SESSION_USERID).toString();
		 if(!"".equals(id)){
			 if(uid.equals(id)){
				 mm.put("success", true);
				 mm.put("error", "其实这个是你的帖子");
				 mm.put("check", false);
			 }else{ 
				 mm=tieservice.updateNotify(id,uid);
			 }
		 }
		 renderJson(mm);
	 }
	 
	 
	}
 
