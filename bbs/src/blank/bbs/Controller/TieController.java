package blank.bbs.Controller;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.spring.Inject;
import com.jfinal.plugin.spring.IocInterceptor; 

import blank.bbs.common.Constant;
import blank.bbs.model.Card;
import blank.bbs.model.Reverts;
import blank.bbs.service.Tieservice;

@Before(IocInterceptor.class)
public class TieController extends BaseController {
	
	
	@Inject.BY_NAME 
	Tieservice tieservice;
	/**
	 * 帖子管理
	 * 根据条件搜索帖子列表
	 * @return
	 * @throws Excetion
	 */
	public void tieList() throws Exception{
		String key = getPara("key")==null?"": getPara("key").toString();
		String  curpage = getPara("curpage")==null?"1":getPara("curpage").toString();
		String pagesize = getPara("pagesize")==null?"15":getPara("pagesize").toString();
	    Map<String,Object> map = new HashMap<String,Object>();
		map.put("key",key ); 
		map.put("curpage",curpage );
		map.put("pagesize",pagesize );
		Map<String,Object>mp = tieservice.tieList(map); 
		renderJson(mp);
	}
	
	/**
	 *  发帖
	 * @return
	 * @throws Excetion
	 */
	public void  write() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String title = getPara("title")==null?"":getPara("title").toString();
		String content = getPara("content")==null?"":getPara("content").toString();
		String section = getPara("section")==null?"":getPara("section").toString();
		String uid = getSession().getAttribute(Constant.SESSION_USERID).toString();
		map.put("title", title);
		map.put("content",content);
		map.put("section", section);
		map.put("uid", uid);
		Map<String,Object> mm=tieservice.write(map);
		renderJson(mm);
	}
	
	/**
	 *  获取帖子中发帖人的相关信息
	 * @return
	 * @throws Excetion
	 */
	public void getmessage() throws Exception{
		String id  = getPara("id")==null?"":getPara("id").toString();
		Card card = tieservice.getCard(id);
		Map<String,Object>mm = new HashMap<String,Object>();
		if(card!=null){   
			 mm=tieservice.getMessage(card.get("uid").toString());
		}
		renderJson(mm);
	}
	 
	/**
	 *  获取帖子的具体信息
	 * @return
	 * @throws Excetion
	 */
	public void getCardmessage() throws Exception{
		String id  = getPara("id")==null?"":getPara("id").toString();
		Card card = tieservice.getCard(id);
		Map<String,Object>mm = new HashMap<String,Object>();
		if(card!=null){ 
			mm.put("title", card.get("title"));
			mm.put("content", card.get("content"));
			mm.put("ctime", card.get("ctime"));
			mm.put("id", card.get("id"));
			mm.put("success", true);
		}else{
			mm.put("success", false);
			mm.put("error", "不存在该帖子，或被管理员删了");
		}
		renderJson(mm);
	}
	
	/** 
	 *根据帖子id获取相关回复
	 * @return
	 * @throws Excetion
	 */
	public void getreturn() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String pagesize = getPara("pagesize")==null?"15":getPara("pagesize").toString();
		String curpage = getPara("curpage")==null?"1":getPara("curpage").toString();
		String id  = getPara("id")==null?"":getPara("id").toString(); 
		map.put("pagesize", pagesize);
		map.put("curpage", curpage);
		map.put("id", id);
		Page<Reverts>reverts = tieservice.getreverts(map);
		renderJson(reverts);
		
	}
	
	/**
	 *  回帖
	 * @return
	 * @throws Excetion
	 */
	public void answertie() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String  id = getPara("id")==null?"":getPara("id").toString();
		String reply = getPara("reply")==null?"":getPara("reply").toString();
		String uid = getSession().getAttribute(Constant.SESSION_USERID).toString();
		map.put("id", id);
		map.put("reply", reply);
		map.put("uid", uid); 
		Map<String,Object> mm =tieservice.answertie(map);	
		renderJson(mm);
	}
	
	
	/**
	 *  获取活跃会员和推荐阅读
	 * @return
	 * @throws Excetion
	 */
	public void getActive() throws Exception{
		Map<String,Object> mm = tieservice.getActive();
		renderJson(mm);
	}
	
	/**
	 *  获取活跃会员
	 * @return
	 * @throws Excetion
	 */
	public void getActiveperson() throws Exception{
		Map<String,Object> mm = tieservice.getActiveperson();
		renderJson(mm);
	}
	
	/**
	 *  获取我的发表
	 * @return
	 * @throws Excetion
	 */
	public void getMycard() throws Exception{
		String  userid = getSession().getAttribute(Constant.SESSION_USERID)==null?"":getSession().getAttribute(Constant.SESSION_USERID).toString();
		String curpage = getPara("curpage")==null?"1":getPara("curpage").toString();
		String pagesize=getPara("pagesize")==null?"15":getPara("pagesize").toString();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userid", userid);
		map.put("curpage", curpage);
		map.put("pagesize", pagesize);
		renderJson(tieservice.getmycard(map));
	}
	
	/**
	 *  获取我的回复
	 * @return
	 * @throws Excetion
	 */
	public void getMyreverts() throws Exception{
		String  userid = getSession().getAttribute(Constant.SESSION_USERID)==null?"":getSession().getAttribute(Constant.SESSION_USERID).toString();
		String curpage = getPara("curpage")==null?"1":getPara("curpage").toString();
		String pagesize=getPara("pagesize")==null?"15":getPara("pagesize").toString();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userid", userid);
		map.put("curpage", curpage);
		map.put("pagesize", pagesize);
		renderJson(tieservice.getMyreverts(map));
	}
	
	/**
	 *  获取我的收藏
	 * @return
	 * @throws Excetion
	 */
	public void getMycollect() throws Exception{
		String  userid = getSession().getAttribute(Constant.SESSION_USERID)==null?"":getSession().getAttribute(Constant.SESSION_USERID).toString();
		String curpage = getPara("curpage")==null?"1":getPara("curpage").toString();
		String pagesize=getPara("pagesize")==null?"15":getPara("pagesize").toString();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userid", userid);
		map.put("curpage", curpage);
		map.put("pagesize", pagesize);
		renderJson(tieservice.getMycollect(map));
	}
	/**
	 * 隐藏帖子
	 * @return
	 * @throws Exception
	 */
	public void hidden() throws Exception{
		String  id = getPara("tid")==null?"":getPara("tid").toString();
		renderJson(tieservice.hidden(id));
		redirect("/bbs/tiezi") ;
	}
	/**
	 * 取消隐藏帖子
	 * @return
	 * @throws Exception
	 */
	public void unhidden() throws Exception{
		String  id = getPara("tid")==null?"":getPara("tid").toString();
		renderJson(tieservice.unhidden(id));
		redirect("/bbs/tiezi") ;
	}
}
