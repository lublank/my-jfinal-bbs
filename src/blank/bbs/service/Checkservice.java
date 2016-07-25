package blank.bbs.service;


public interface Checkservice {
	
	/**
	 *  检查登录名
	 * @return
	 * @throws Excetion
	 */
	 boolean checkLoginname(String loginname) throws Exception;

}
