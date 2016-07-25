package blank.bbs.model;
 
import java.util.List; 

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;  
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings({ "serial", "rawtypes" })
public class BaseModel<M extends Model> extends Model<M> {
	
	/**
	 * 根据sql搜索对象 
	 * @return
	 * @throws Excetion
	 */
	public M getbysql(String sql ){  
		return findFirst(sql);
	}
	
	/**
	 * 根据sql搜索列表
	 */ 
	public List  getListByHql(String sql) {
		return find(sql);
	}
	
	/**
	 * 根据id搜索数据
	 */
	public 	M getById(Long id){
		return findById(id);
	}
	
	/**
	 * 保存操作
	 */
	 
	public void save(M object){
		object.save();
	}
	
	/**
	 *  分页操作
	 */
	public Page<M> getpage(int pageNumber, int pageSize, String select, String sqlExceptSelect){
		return paginate(pageNumber, pageSize, select, sqlExceptSelect);
	} 
	
	/**
	 * 查询数量
	 */
	
	public int count(String sql){
		int count=0; 
		M  result = findFirst(sql);   
		if(result!=null) 
			count=Integer.valueOf(result.get("count").toString());
		return count;
	}
	
	/**
	 *  根据sql删除记录
	 * @return
	 * @throws Excetion
	 */
	public boolean delete(String sql){
		int result=Db.update(sql);
		
		return false;
	}
	
}
