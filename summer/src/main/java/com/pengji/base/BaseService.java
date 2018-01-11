package com.pengji.base;

import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.TableMapping;
import com.pengji.utils.SQLUtils;
import com.pengji.utils.UserUtils;

/**
 * BaseService
 * 
 * @author summer
 * 2017-11-27
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class BaseService<T extends Model> {

//	protected Logger log= LoggerFactory.getLogger(getClass());
	protected  Log log = Log.getLog(getClass());
	
	protected T dao  ;
	
	protected String tableName  ;
	
	protected String primaryKey ;
	
	
	public BaseService() {
		
	} 
	
	public BaseService(T dao) {
		this.dao = (T) dao.dao();
		this.tableName = TableMapping.me().getTable(this.dao.getClass()).getName();
		this.primaryKey = TableMapping.me().getTable(this.dao.getClass()).getPrimaryKey()[0];
	}
	
	/**
	* 列表-分页
	*/
	public Page<T> paginate(int pageNumber, int pageSize) {
		return dao.paginate(pageNumber, pageSize, "SELECT * ", "FROM "+tableName);
	}
	

	public Page<T> paginate(int pageNumber, int pageSize, String select, SQLUtils sqlUtils, Object... paras) {
		return dao.paginate(pageNumber, pageSize, select, sqlUtils.toString(), paras);
	}
	
	/**
	* 保存
	*/
	public void save(T t) {
		UserUtils.preInsert(t);
		t.save();
	}
	
	/**
	* 更新
	*/
	public void update(T t) {
		UserUtils.preUpdate(t);
		t.update();
	}
	
	/**
	* 查询
	*/
	public T findById(int id) {
		return (T)dao.findFirst("select * from "+tableName+" where "+primaryKey+"=?", id);
	}
	
	/**
	* 删除
	*/
	public void delete(int id) {
		Db.update("delete from "+tableName+" where "+primaryKey+"=?", id);
	}
	
	
}
