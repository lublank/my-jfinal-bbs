package blank.bbs.service;

import java.util.Map;

public interface Personservice {

	/**
	  * 显示用户个人信息
	  * @return
	  * @throws Exception
	  */
	 Map<String,Object> userInfo(String id) throws Exception;
	 
	 /**
	  * 更新用户个人信息
	  * @return
	  * @throws Exception
	  */
	 Map<String,Object> updateInfo(Map<String,Object>map) throws Exception;

	 /**
	  * 更新密码
	  * @return
	  * @throws Exception
	  */
	 Map<String,Object> updatepwd(Map<String,Object>map) throws Exception;
	 
	 /**
	  * 选择系统头像
	  * @return
	  * @throws Exception
	  */
	 Map<String,Object> updatehead(Map<String,Object>map) throws Exception;
	 
	 
	 /**
	  * 移除粉丝
	  * @return
	  * @throws Excetion
	  */
	 Map<String,Object> removefans(Map<String,Object>map) throws Exception;
	 
	 /**
	  * 取消关注 
	  * @return
	  * @throws Excetion
	  */
	 Map<String,Object> cancelAttention(Map<String,Object>map) throws Exception;

	 /**
	  *  取消收藏
	  * @return
	  * @throws Excetion
	  */
	 Map<String,Object> cancelCollect(Map<String,Object>map) throws Exception;
}
