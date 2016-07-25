package blank.bbs.service;

import java.util.Map;
 

import blank.bbs.model.Card;

import com.jfinal.plugin.activerecord.Page;

public interface Firstservice {
	
	/**
	 *  搜索帖子列表
	 * @return
	 * @throws Excetion
	 */
	Map<String,Object> tieList (Map<String,Object>map) throws Exception;
	
	/**
	 * 转帖列表 
	 * @return
	 * @throws Excetion
	 */
	Map<String,Object> notifyList (Map<String,Object>map) throws Exception;
}
