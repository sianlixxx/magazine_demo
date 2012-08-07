/**
 * 
 */
package weimin.magazine.back.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import weimin.magazine.back.dao.CommonDao;
import weimin.magazine.back.dao.pojo.TDepartmentEditor;


/**
 * @author tianhao
 *
 */
public class TDepartmentEditorDaoImpl  extends SqlMapClientDaoSupport implements CommonDao {
    
    private static final Log log = LogFactory.getLog(TDepartmentEditorDaoImpl.class);

	/* (non-Javadoc)
	 * @see weimin.magazine.back.dao.CommonDao#queryById(int)
	 */
	@Override
	public Object queryById(long id) {
		// TODO Auto-generated method stub
		TDepartmentEditor departmentEditor = (TDepartmentEditor) getSqlMapClientTemplate().queryForObject("queryTDepartmentEditorById", id);
		return departmentEditor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> queryAll() {
		// TODO Auto-generated method stub
		List<Object> l = getSqlMapClientTemplate().queryForList("queryAllTDepartmentEditor");
		return l;
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		TDepartmentEditor departmentEditor = (TDepartmentEditor) o;
		getSqlMapClientTemplate().update("updateTDepartmentEditor", departmentEditor);
	}

	@Override
	public Object insert(Object o) {
		// TODO Auto-generated method stub
		TDepartmentEditor departmentEditor = (TDepartmentEditor) o;
		long id = (Long) getSqlMapClientTemplate().insert("insertTDepartmentEditor", departmentEditor);
		return id;
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("deleteTDepartmentEditorById", id);
	}

	/**
	 * 查询某编辑部所有主编；
	 * @param departmentId
	 * @return 主编列表
	 */
	@SuppressWarnings("unchecked")
	public List<TDepartmentEditor> queryByDepartmentId(Long departmentId) {
		log.debug("查询编辑部所有主编，所查编辑部id为："+departmentId);
		List<TDepartmentEditor> editors = getSqlMapClientTemplate().queryForList("queryAllEditorByDepartmentId",departmentId);
		log.debug("查询编辑部所有主编，所查编辑部id为："+departmentId + "  最终查到的主编个数为:"+editors.size());
		return editors;
	}

}
