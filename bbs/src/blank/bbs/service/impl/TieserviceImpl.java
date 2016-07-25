package blank.bbs.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;

import blank.bbs.model.Attention;
import blank.bbs.model.Card;
import blank.bbs.model.Collect;
import blank.bbs.model.Fans;
import blank.bbs.model.Notify;
import blank.bbs.model.Reverts;
import blank.bbs.model.Score;
import blank.bbs.model.User;
import blank.bbs.service.Tieservice;
import blank.bbs.util.PageHelp;

@Service("tieservice")
public class TieserviceImpl implements Tieservice{
	
	/**
	 * 发帖
	 */
	@Before(Tx.class)
	public Map<String,Object> write(Map<String,Object>map) throws Exception{
		Map<String,Object> mm = new HashMap<String,Object>();
		int uid = Integer.valueOf(map.get("uid").toString());
		DateTime date = new DateTime();
		String section= map.get("section").toString();
		String datetime = date.toString("yyyy-MM-dd hh:mm:ss");
		try{
		new Card().set("uid", uid).set("content", map.get("content").toString())
		.set("title", map.get("title").toString())
		.set("ctime", datetime).set("section", section)
		.set("style", 0).save();
		mm.put("success", true);
		mm.put("error", "");
		}catch(Exception e){
			e.printStackTrace();
			mm.put("success", false);
			mm.put("error", "发帖失败");
		}
		return mm;
	}
	
	public Card getCard(String id ) throws Exception{
		Card card  = new Card();
		if(!"".equals(id)){
			String sql = "select * from card where id="+id+" and style=0";
			card = Card.dao.findFirst(sql);
//			card = Card.dao.findById(id);
		}
		return card;
	}
	
	/**
	 * 根据个人id获取个人相关信息
	 */
	public Map<String,Object> getMessage(String id ) throws Exception{ 
		Map<String,Object> mm = new HashMap<String,Object>(); 
		Card count = new Card();
		User user = new User();
		Attention attention = new Attention();
		Fans fans = new Fans(); 
		String hql = " select count(*) as count ";
		
		Score score = new Score(); 
			int uid = Integer.valueOf(id);
			String sql = "where uid=?"; 
			 user = User.dao.findById(id);
			 try{
			 count = Card.dao.findFirst(hql +"  from card "+sql,uid);
			 fans=Fans.dao.findFirst(hql+" from fans "+sql,uid);
			 attention = Attention.dao.findFirst(hql+" from attention "+sql,uid);
			 score = Score.dao.findFirst("select intergral from score where uid = ?",uid);
		if(user!=null){ 
			mm.put("uid", user.get("id"));
			mm.put("username", user.get("username")); 
			mm.put("time", user.get("regtime"));
			mm.put("photo", user.get("head"));
			mm.put("nickname", user.get("nickname"));
		}
		if(count!=null){ 
			mm.put("tiecount", count.get("count"));
		}
		if(attention!=null){ 
			mm.put("attention", attention.get("count"));
		}
		if(fans!=null){ 
			mm.put("fans", fans.get("count"));
		}
		if(score!=null){ 
			mm.put("score", score.get("intergral"));
		} 
			 }catch(Exception e){
				 e.printStackTrace();
			 }
		return mm;
	}
	
	/**
	 * 搜索回复列表
	 */
	public Page<Reverts> getreverts(Map<String,Object> map ) throws Exception{
		int curpage = Integer.valueOf(map.get("curpage").toString());
		int pagesize = Integer.valueOf(map.get("pagesize").toString());
		String id = map.get("id").toString();
		String sql = "select t.*,u.nickname ";
		String hql = " from reverts t, user u where t.uid = u.id and t.tid = '"+id+"'"; 
		Page<Reverts> reverts = Reverts.dao.getpage(curpage, pagesize, sql, hql); 
		return reverts;
	}
	
	
	/**
	 *  回帖
	 * @return
	 * @throws Excetion
	 */
	@Before(Tx.class)
	public Map<String,Object> answertie(Map<String,Object> map) throws Exception{
			Map<String,Object> mm = new HashMap<String,Object>();
			DateTime rtime = new DateTime();
			try{
			new Reverts().set("uid", Integer.valueOf(map.get("uid").toString()))
			.set("tid", Integer.valueOf(map.get("id").toString()))
			.set("reply", map.get("reply"))
			.set("rtime", rtime.toString("yyyy-MM-dd HH:mm:ss"))
			.save(); 
			mm.put("success", true);
			mm.put("error", "");
			}catch(Exception e){
				mm.put("success", false);
				mm.put("error", "回帖失败");
				e.printStackTrace();
			}
			return mm;
	}
	
	/**
	 * 搜索活跃会员和推荐阅读
	 */
	@SuppressWarnings("unchecked")
	public  Map<String,Object> getActive() throws Exception{
		Map<String,Object> mm = new HashMap<String,Object>();
		String  sql = "select t.title,u.id as uid,u.nickname,u.head,t.id as tid " +
				" from card t,user u where t.uid = u.id and t.style=0 order by t.ctime desc limit 0,5 ";
		List<Card> cardlist = Card.dao.getListByHql(sql);
		mm.put("result", cardlist);
		return mm;
	}
	
	/**
	 * 获取活跃会员
	 */
	@SuppressWarnings("unchecked")
	public 	 Map<String,Object> getActiveperson() throws Exception{
		Map<String,Object> mm = new HashMap<String,Object>();
		String  sql = "select  distinct(u.id) as uid,u.nickname,u.head " +
				" from card t,user u where t.uid = u.id and t.style=0 order by t.ctime desc limit 0,5 ";
		List<Card> cardlist = Card.dao.getListByHql(sql);
		mm.put("result", cardlist);
		return mm;
	}
	
	/**
	 * 我的发表
	 */
	public  Map<String,Object>getmycard(Map<String,Object>map) throws Exception{
		int curpage = Integer.valueOf(map.get("curpage").toString());
		int pagesize = Integer.valueOf(map.get("pagesize").toString());
		int id = Integer.valueOf(map.get("userid").toString());
		String sql = "select t.* ",
				str = "from card t where t.uid ='"+id+"' and t.style=0"; 
		Page<Card> cardlist = Card.dao.paginate(curpage, pagesize, sql, str); 
		List<Map<String,Object>> lit= new ArrayList<Map<String,Object>>();
		int total = cardlist.getTotalRow();
		Long pagecount = PageHelp.getRowNoByTotalAndPageSize(
				Long.valueOf(total), pagesize + "");// 总页数
		Long num = PageHelp.getStatrRow(Long.valueOf(pagesize),
				Long.valueOf(curpage), pagecount);
		for(Card ca : cardlist.getList()){
			Map<String,Object > cap = new HashMap<String,Object>(); 
			int count = Reverts.dao.count("select count(*) as count from reverts where tid = '"+ca.get("id")+"'");
			cap.put("tid", ca.get("id"));
			cap.put("count", count);
			cap.put("num", ++num);
			cap.put("title", ca.get("title"));
			cap.put("section", ca.get("section"));
			cap.put("ctime", ca.get("ctime"));
			lit.add(cap); 
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", lit);
		result.put("totalRow", total);
		result.put("pageNumber", curpage);
		result.put("pageSize", pagesize);
		result.put("totalPage", pagecount);
		return result;
	}
	
	/**
	 * 我的回复
	 */
	public  Map<String,Object>getMyreverts(Map<String,Object>map) throws Exception{
		int curpage = Integer.valueOf(map.get("curpage").toString());
		int pagesize = Integer.valueOf(map.get("pagesize").toString());
		int id = Integer.valueOf(map.get("userid").toString());
		String sql = "select t.*,r.rtime,r.id as rid ,u.nickname",
				str = "from reverts r ,card t ,user u where u.id = r.uid and  t.id = r.tid and r.uid ='"+id+"' and t.style=0";
		
		Page<Card> cardlist = Card.dao.paginate(curpage, pagesize, sql, str);
		List<Map<String,Object>> lit= new ArrayList<Map<String,Object>>();
		int total = cardlist.getTotalRow();
		Long pagecount = PageHelp.getRowNoByTotalAndPageSize(
				Long.valueOf(total), pagesize + "");// 总页数
		Long num = PageHelp.getStatrRow(Long.valueOf(pagesize),
				Long.valueOf(curpage), pagecount);
		for(Card ca : cardlist.getList()){
			Map<String,Object > cap = new HashMap<String,Object>();
			cap.put("tid", ca.get("id"));
			cap.put("nickname", ca.get("nickname"));
			cap.put("num", ++num);
			cap.put("title", ca.get("title"));
			cap.put("section", ca.get("section"));
			cap.put("rtime", ca.get("rtime"));
			cap.put("id", ca.get("rid"));
			lit.add(cap); 
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", lit);
		result.put("totalRow", total);
		result.put("pageNumber", curpage);
		result.put("pageSize", pagesize);
		result.put("totalPage", pagecount);
		return result;
	}
	
	/**
	 * 获取我的收藏
	 */
	public  Map<String,Object>getMycollect(Map<String,Object>map) throws Exception{
		int curpage = Integer.valueOf(map.get("curpage").toString());
		int pagesize = Integer.valueOf(map.get("pagesize").toString());
		int id = Integer.valueOf(map.get("userid").toString());
		String sql = "select t.*,c.ctime ,c.id as cid ,u.nickname",
				str = "from collect c ,card t ,user u where u.id = c.uid and  t.id = c.tid and c.uid ='"+id+"' and t.style=0";
		
		Page<Collect> cardlist = Collect.dao.paginate(curpage, pagesize, sql, str);
		List<Map<String,Object>> lit= new ArrayList<Map<String,Object>>();
		int total = cardlist.getTotalRow();
		Long pagecount = PageHelp.getRowNoByTotalAndPageSize(
				Long.valueOf(total), pagesize + "");// 总页数
		Long num = PageHelp.getStatrRow(Long.valueOf(pagesize),
				Long.valueOf(curpage), pagecount);
		for(Collect ca : cardlist.getList()){
			Map<String,Object > cap = new HashMap<String,Object>();
			cap.put("tid", ca.get("id"));
			cap.put("nickname", ca.get("nickname"));
			cap.put("num", ++num);
			cap.put("title", ca.get("title"));
			cap.put("section", ca.get("section"));
			cap.put("ctime", ca.get("ctime"));
			cap.put("cid", ca.get("cid"));
			lit.add(cap); 
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", lit);
		result.put("totalRow", total);
		result.put("pageNumber", curpage);
		result.put("pageSize", pagesize);
		result.put("totalPage", pagecount);
		return result;
	}
	
	/**
	 * 判断是否关注了此人
	 */
	@Before(Tx.class)
	public  Map<String,Object>Checkattention(String  id,String uid ) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		String sql = "select * from attention where uid ='"+uid+"' and uuid ='"+id+"'";
		Attention attention = Attention.dao.findFirst(sql);
		if(attention!=null){
			result.put("success", true);
			result.put("error", "你已关注此人");
			result.put("check", false);
		}else{
			new Attention().set("uid", uid).set("uuid", id).set("atime", new DateTime().toString("yyyy-MM-dd")).save();
			new Fans().set("uid", id).set("uuid", uid).set("ftime", new DateTime().toString("yyyy-MM-dd")).save();
			result.put("success", true);
			result.put("error", "你还没关注此人");
			result.put("check", true);
		}
		return result;
	}
	
	
	/**
	 * 获取我的关注列表
	 */
	public  Map<String,Object>Myattention(Map<String,Object>  map ) throws Exception{
		int curpage = Integer.valueOf(map.get("curpage").toString());
		int pagesize = Integer.valueOf(map.get("pagesize").toString());
		int id = Integer.valueOf(map.get("id").toString());
		String sql = "select c.*,u.nickname,u.head ",
				str = " from attention c ,user u where u.id = c.uuid  and c.uid ='"+id+"'";
		
		Page<Attention> cardlist = Attention.dao.paginate(curpage, pagesize, sql, str);
		List<Map<String,Object>> lit= new ArrayList<Map<String,Object>>();
		int total = cardlist.getTotalRow();
		Long pagecount = PageHelp.getRowNoByTotalAndPageSize(
				Long.valueOf(total), pagesize + "");// 总页数
		Long num = PageHelp.getStatrRow(Long.valueOf(pagesize),
				Long.valueOf(curpage), pagecount);
		for(Attention ca : cardlist.getList()){
			Map<String,Object > cap = new HashMap<String,Object>();
			cap.put("id", ca.get("id"));
			cap.put("photo", ca.get("head"));
			cap.put("nickname", ca.get("nickname"));
			cap.put("num", ++num);  
			cap.put("atime", ca.get("atime"));
			cap.put("uuid", ca.get("uuid"));
			lit.add(cap); 
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", lit);
		result.put("totalRow", total);
		result.put("pageNumber", curpage);
		result.put("pageSize", pagesize);
		result.put("totalPage", pagecount);
		return result;
	}
	
	
	/**
	 * 我的粉丝列表
	 */
	public  Map<String,Object>Myfans(Map<String,Object> map) throws Exception{
		int curpage = Integer.valueOf(map.get("curpage").toString());
		int pagesize = Integer.valueOf(map.get("pagesize").toString());
		int id = Integer.valueOf(map.get("id").toString());
		String sql = "select c.*,u.nickname ,u.head",
				str = " from fans c ,user u where u.id = c.uuid  and c.uid ='"+id+"'"; 
		Page<Fans> cardlist = Fans.dao.paginate(curpage, pagesize, sql, str);
		List<Map<String,Object>> lit= new ArrayList<Map<String,Object>>();
		int total = cardlist.getTotalRow();
		Long pagecount = PageHelp.getRowNoByTotalAndPageSize(
				Long.valueOf(total), pagesize + "");// 总页数
		Long num = PageHelp.getStatrRow(Long.valueOf(pagesize),
				Long.valueOf(curpage), pagecount); 
		for(Fans ca : cardlist.getList()){
			Map<String,Object > cap = new HashMap<String,Object>();
			cap.put("id", ca.get("id"));
			cap.put("photo", ca.get("head"));
			cap.put("nickname", ca.get("nickname"));
			cap.put("num", ++num);  
			cap.put("ftime", ca.get("ftime"));
			cap.put("uuid", ca.get("uuid"));
			lit.add(cap); 
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", lit);
		result.put("totalRow", total);
		result.put("pageNumber", curpage);
		result.put("pageSize", pagesize);
		result.put("totalPage", pagecount);
		return result;
	}
	
	/**
	 * 判断是否收藏了此文章
	 */
	@Before(Tx.class)
	public  Map<String,Object>CheckCollect(String  id,String uid ) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		String sql = "select * from collect where uid ='"+uid+"' and tid ='"+id+"'";
		Collect attention = Collect.dao.findFirst(sql);
		if(attention!=null){
			result.put("success", true);
			result.put("error", "你已收藏了此帖子");
			result.put("check", false);
		}else{
			new Collect().set("uid", uid).set("tid", id).set("ctime", new DateTime().toString("yyyy-MM-dd")).save();
			result.put("success", true);
			result.put("error", "你还没收藏此帖子");
			result.put("check", true);
		}
		return result;
	}

	/**
	 * 转帖
	 */
	@Before(Tx.class)
	public  Map<String,Object>updateNotify(String  id,String uid ) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		String sql = "select * from notify where uid ='"+uid+"' and tid ='"+id+"'";
		try{
		Notify attention = Notify.dao.findFirst(sql);
		if(attention!=null){
			result.put("success", true);
			result.put("error", "你已转载了此帖子");
			result.put("check", false);
		}else{  
			Notify.dao.save(new Notify().set("uid",  Integer.valueOf(uid)).set("tid", Integer.valueOf(id)).set("stime", new DateTime().toString("yyyy-MM-dd")));
			Card card = Card.dao.findById(id);
			Set<Entry<String, Object>> a = card.getAttrsEntrySet();
			Card newcard  = new Card();
			for( Entry<String, Object>  b : a){ 
					String key = b.getKey();
					Object value = b.getValue();
				 if(key.equals("id")||key.equals("uid")){
					 continue;
				 }
				 if(key.equals("title")){
					 value = "[转]"+value;
				 } 
				 newcard.set(key,value);
			}
			newcard.set("uid", uid);  
			Card.dao.save(newcard);
			result.put("success", true);
			result.put("error", "你已成功转载了此帖子");
			result.put("check", true); 
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	 
	/**
	 * 隐藏帖子
	 */
	@Before(Tx.class)
	public Map<String,Object>hidden(String id) throws Exception {
		int tid =Integer.valueOf(id);
		Map<String, Object> result = new HashMap<String, Object>();
		int style = 1;
			try{
			Card.dao.findById(tid).set("style", style).update();
			result.put("success", true);
			result.put("error", "");
			}catch(Exception e){
				e.printStackTrace();
				result.put("success", false);
				result.put("error", "隐藏失败");
			}
			return result; 
	}
	
	/**
	 * 取消隐藏帖子
	 */
	@Before(Tx.class)
	public Map<String,Object>unhidden(String id) throws Exception {
		int tid =Integer.valueOf(id);
		Map<String, Object> result = new HashMap<String, Object>();
		int style = 0;
			try{
			Card.dao.findById(tid).set("style", style).update();
			result.put("success", true);
			result.put("error", "");
			}catch(Exception e){
				e.printStackTrace();
				result.put("success", false);
				result.put("error", "取消隐藏失败");
			}

			return result; 
	}
	/**
	 * 帖子管理--列表
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> tieList (Map<String,Object>map) throws Exception{ 
		String key = map.get("key").toString(); 
		int curpage = Integer.valueOf(map.get("curpage").toString());
		int pagesize = Integer.valueOf(map.get("pagesize").toString());
		String hql ="select COUNT(DISTINCT  t.id)  as count ";
		String sql = "select DISTINCT  ( t.id ), t.title,h.nickname,h.head,t.ctime,t.section, t.style ,h.id as uid ,  " +
				"(select MAX(q.rtime)from reverts q where q.tid =t.id ) as rtime  ," +
				"(select count(*) from reverts q where q.tid =t.id ) as count ";
		String str=	" from  card t left join reverts a on t.id =a.tid left join user h  on  t.uid = h.id  where 1=1 ";  
		if(!"".equals(key)){
			str+=" and t.title like '%"+key+"%' or  h.nickname like '%"+key+"%'";
		}
		str +=" ORDER BY rtime DESC ";  
		String sstr =str;
		sstr +=" limit  "+((curpage-1)*pagesize) + ","+pagesize  ; 
		List<Card> cardlist = Card.dao.getListByHql(sql+sstr); 
		int total = Card.dao.count(hql+str); 
		List<Map<String,Object>> lit= new ArrayList<Map<String,Object>>();
		Long pagecount = PageHelp.getRowNoByTotalAndPageSize(
				Long.valueOf(total), pagesize + "");// 总页数
		Long num = PageHelp.getStatrRow(Long.valueOf(pagesize),
				Long.valueOf(curpage), pagecount);
		for(Card ca : cardlist){
			Map<String,Object > cap = new HashMap<String,Object>();
			cap.put("num", ++num);
			cap.put("photo", ca.get("head"));
			cap.put("title", ca.get("title"));
			cap.put("uid", ca.get("uid"));
			cap.put("tid", ca.get("id"));
			cap.put("style", ca.get("style"));//是否显示帖子
			cap.put("section", ca.get("section"));//版块
			cap.put("nickname", ca.get("nickname")); 
			cap.put("rtime", ca.get("rtime")==null?(ca.get("ctime")==null?"":ca.get("ctime").toString().substring(0, 19)):ca.get("rtime").toString().substring(0, 19)); 
			cap.put("count", ca.get("count"));
		    lit.add(cap); 
		}
		Collections.sort(lit, new Comparator<Map<String,Object>>(){
			public int compare(Map<String,Object> m1,Map<String,Object>m2){
				String ddtime = m1.get("rtime").toString().replace("-", "").replace(":", "").replace(" ", "");
				Long ddtimeint = Long.valueOf(ddtime);
				String date = m1.get("rtime").toString().replace("-", "").replace(":", "").replace(" ", "");
				Long datetime = Long.valueOf(date); 
				  return datetime.compareTo(ddtimeint);
			} 
		});   
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", lit);
		result.put("totalRow", total);
		result.put("pageNumber", curpage);
		result.put("pageSize", pagesize);
		result.put("totalPage", pagecount); 
		return result;
	}
}
