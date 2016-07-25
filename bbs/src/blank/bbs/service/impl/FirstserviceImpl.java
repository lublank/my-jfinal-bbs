package blank.bbs.service.impl;
  
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator; 
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
import org.springframework.stereotype.Service; 
import blank.bbs.model.Card;
import blank.bbs.model.Notify;
import blank.bbs.service.Firstservice;
import blank.bbs.util.PageHelp;
@Service("firstservice")
public class FirstserviceImpl implements Firstservice{
	 
	/**
	 * 首页--列表
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> tieList (Map<String,Object>map) throws Exception{ 
		String key = map.get("key").toString(); 
		int curpage = Integer.valueOf(map.get("curpage").toString());
		int pagesize = Integer.valueOf(map.get("pagesize").toString());
		String hql ="select COUNT(DISTINCT  t.id)  as count ";
		String sql = "select DISTINCT  ( t.id ), t.title,h.nickname,h.head,t.ctime,t.section ,h.id as uid ,  " +
				"(select MAX(q.rtime)from reverts q where q.tid =t.id ) as rtime  ," +
				"(select count(*) from reverts q where q.tid =t.id ) as count ";
		String str=	" from  card t left join reverts a on t.id =a.tid left join user h  on  t.uid = h.id  where t.style=0 ";
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
	/**
	 * 转帖列表
	 */
	@SuppressWarnings("unchecked")
	public  Map<String,Object> notifyList (Map<String,Object>map) throws Exception{ 
		int curpage = Integer.valueOf(map.get("curpage").toString());
		int pagesize = Integer.valueOf(map.get("pagesize").toString());
		int uid = Integer.valueOf(map.get("uid").toString());
		String hql ="select COUNT(DISTINCT  n.id)  as count ";
		String sql = "select  t.id , t.title,h.nickname,h.id as uid, n.stime  "  ;
		String str =" from notify n , card t , user h  where    n.tid = t.id and t.uid = h.id and n.uid="+uid+""; 
		str +=" ORDER BY stime DESC ";  
		String sstr =str;
		sstr +=" limit  "+((curpage-1)*pagesize) + ","+pagesize  ; 
		List<Notify> cardlist = Notify.dao.getListByHql(sql+sstr); 
		int total = Card.dao.count(hql+str); 
		List<Map<String,Object>> lit= new ArrayList<Map<String,Object>>();
		Long pagecount = PageHelp.getRowNoByTotalAndPageSize(
				Long.valueOf(total), pagesize + "");// 总页数
		Long num = PageHelp.getStatrRow(Long.valueOf(pagesize),
				Long.valueOf(curpage), pagecount);
		for(Notify ca : cardlist){
			Map<String,Object > cap = new HashMap<String,Object>();
			cap.put("num", ++num); 
			cap.put("title", ca.get("title")); 
			cap.put("tid", ca.get("id"));
			cap.put("uid", ca.get("uid"));
			cap.put("nickname", ca.get("nickname")); 
			cap.put("stime", ca.get("stime").toString());  
		    lit.add(cap); 
		}
		Collections.sort(lit, new Comparator<Map<String,Object>>(){
			public int compare(Map<String,Object> m1,Map<String,Object>m2){
				String ddtime = m1.get("stime").toString().replace("-", "").replace(":", "").replace(" ", "");
				Long ddtimeint = Long.valueOf(ddtime);
				String date = m1.get("stime").toString().replace("-", "").replace(":", "").replace(" ", "");
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
