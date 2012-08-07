/**
 * 
 */
package weimin.magazine.back.dao;

import java.util.List;


/**
 * @author tianhao
 *
 */
public interface CommonDao {
	
	/**
	 * 根据主键id返回记录
	 * @param 主键id
	 * @return
	 */
	public Object queryById(long id);
	
	/**
	 * 检索全表
	 * @return
	 */
	public List<Object> queryAll();
	
	/**
	 * 更新记录，
	 * @param o
	 */
	public void update(Object o);
	
	/**
	 * 新增记录
	 * @param o
	 * @return
	 */
	public Object insert(Object o);
	
	/**
	 * 根据主键id删除某条记录
	 * @param id
	 */
	public void deleteById(long id);

}
