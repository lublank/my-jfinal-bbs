package blank.bbs.service;

import java.util.Map;

import com.jfinal.plugin.activerecord.Page;

import blank.bbs.model.Card;
import blank.bbs.model.Reverts;
public interface Tieservice {
	
	/**
	 *  发帖
	 * @return
	 * @throws Excetion
	 */
	Map<String,Object> write(Map<String,Object>map) throws Exception;
	
	/**
	 *  获取帖子相关信息
	 * @return
	 * @throws Excetion
	 */
	Map<String,Object> getMessage(String id ) throws Exception;
	
	/**
	 *  根据id返回帖子相关内容
	 * @return
	 * @throws Excetion
	 */
	 Card getCard(String id ) throws Exception;
	 
	 /**
	  *  分页回复
	  * @return
	  * @throws Excetion
	  */
	 Page<Reverts> getreverts(Map<String,Object> map ) throws Exception;
	 
	 /**
	  * 回帖
	  * @return
	  * @throws Excetion
	  */
	 Map<String,Object> answertie(Map<String,Object> map) throws Exception;
	 
	 /**
	  *  获取活跃会员和推荐阅读
	  * @return
	  * @throws Excetion
	  */
	 Map<String,Object> getActive() throws Exception;
	 
	 /**
	  *  获取活跃会员
	  * @return
	  * @throws Excetion
	  */
	 Map<String,Object> getActiveperson() throws Exception;
	 
	 /**
	  * 搜索我的发表 
	  * @return
	  * @throws Excetion
	  */
	 Map<String,Object>getmycard(Map<String,Object>map) throws Exception;
	 
	 /**
	  * 搜索我的回复
	  * @return
	  * @throws Excetion
	  */
	 Map<String,Object>getMyreverts(Map<String,Object>map) throws Exception;
	 /**
	  * 获取我的收藏
	  * @return
	  * @throws Excetion
	  */
	 Map<String,Object>getMycollect(Map<String,Object>map) throws Exception;
	 
	 
	 /**
	  * 检查是否关注了此人
	  * @return
	  * @throws Excetion
	  */
	 Map<String,Object>Checkattention(String   id,String uid ) throws Exception;
	 
	 
	 /**
	  *  我的关注列表
	  * @return
	  * @throws Excetion
	  */
	 Map<String,Object>Myattention(Map<String,Object> map) throws Exception;
	 
	 
	 /**
	  *  我的粉丝列表
	  * @return
	  * @throws Excetion
	  */
	 Map<String,Object>Myfans(Map<String,Object> map) throws Exception;
	 
	 
	 /**
	  *  判断并执行我的收藏
	  * @return
	  * @throws Excetion
	  */
	 Map<String,Object>CheckCollect(String  id,String uid ) throws Exception;

	 /**
	  * 隐藏帖子
	  * @return
	  * @throws Excetion
	  */
	 Map<String,Object>hidden(String id) throws Exception;

	Map<String,Object>unhidden(String id) throws Exception;

	/**
	 *  搜索帖子列表
	 * @return
	 * @throws Excetion
	 */
	Map<String,Object> tieList (Map<String,Object>map) throws Exception;
	 
	 /**
	  *  转帖
	  * @return
	  * @throws Excetion
	  */
	 Map<String,Object>updateNotify(String  id,String uid ) throws Exception;
	 
	 
}
