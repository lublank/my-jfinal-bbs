package blank.bbs.service.impl;
import org.springframework.stereotype.Service;

import blank.bbs.model.User;
import blank.bbs.service.Checkservice;

@Service("checkservice")
public class CheckserviceImpl implements Checkservice{
	
	
	/**
	 * 检查注册名是否已经存在
	 * @return
	 * @throws Excetion
	 */
	public boolean checkLoginname(String loginname) throws Exception{ 
		boolean result = true;
		String sql  = " select * from user where username = '"+loginname+"' ";
		User user = User.dao.getbysql(sql);
		if(user!=null){
			result = false;
		}
		return result;
	}
	
	
}
