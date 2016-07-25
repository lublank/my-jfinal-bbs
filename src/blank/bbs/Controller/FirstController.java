package blank.bbs.Controller;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import blank.bbs.common.Constant;
import blank.bbs.model.Card;
import blank.bbs.model.Score;
import blank.bbs.model.Sign;
import blank.bbs.service.Firstservice;

import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.plugin.spring.Inject;
import com.jfinal.aop.Before;
import com.jfinal.plugin.spring.IocInterceptor; 
@Before(IocInterceptor.class)
public class FirstController extends BaseController {  
	public static final Logger log = Logger.getLogger(FirstController.class);
	
	@Inject.BY_NAME 
	Firstservice firstservice;

	/**
	 *  根据条件搜索帖子列表
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
		Map<String,Object>mp = firstservice.tieList(map); 
		renderJson(mp);
	}
	
	/**
	 *  根据条件搜索转帖列表
	 * @return
	 * @throws Excetion
	 */
	public void notifyList() throws Exception{ 
		String  curpage = getPara("curpage")==null?"1":getPara("curpage").toString();
		String pagesize = getPara("pagesize")==null?"15":getPara("pagesize").toString();
		int uid = Integer.valueOf(getSession().getAttribute(Constant.SESSION_USERID).toString());
	    Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("uid",uid );
		map.put("curpage",curpage );
		map.put("pagesize",pagesize );
		Map<String,Object>mp = firstservice.notifyList(map); 
		renderJson(mp);
	} 
	
	/**
	 * 签到 
	 * @return
	 * @throws Excetion
	 */ 
	@Before(Tx.class)
	public void signin() throws Exception{
		DateTime datetime = new DateTime();
		String ss =datetime.toString("yyyy-MM-dd");  
		int uid = Integer.valueOf(getSession().getAttribute(Constant.SESSION_USERID).toString());
		try{ 
		Sign.dao.set("uid",uid).set("stime", ss).save();
		Score score = new Score();
		score=Score.dao.findFirst("select * from score where uid =?",uid);
		if(score!=null){
			score.set("intergral", score.getInt("intergral")+10).update();
		}else{
			 new Score().set("uid", uid).set("intergral", 10).save();
		} 
		getSession().setAttribute(Constant.SIGN_IN, true);
		renderJson("success",true);
		}catch(Exception e){
			renderJson("success",false);
			e.printStackTrace();
		}
	}
	
}
