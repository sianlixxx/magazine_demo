/**
 * 
 */
package weimin.magazine.back.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import weimin.magazine.back.dao.CommonDao;
import weimin.magazine.back.dao.pojo.TTopMagazine;

/**
 * @author tianhao
 *
 */
public class TTopMagazineDaoImpl extends SqlMapClientDaoSupport implements CommonDao {

	@Override
	public Object queryById(long id) {
		// TODO Auto-generated method stub
		TTopMagazine topMagazine = (TTopMagazine) getSqlMapClientTemplate().queryForObject("queryTTopMagazineById", id);
		return topMagazine;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> queryAll() {
		// TODO Auto-generated method stub
		List<Object> l = getSqlMapClientTemplate().queryForList("queryAllTTopMagazine");
		return l;
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		TTopMagazine Model = (TTopMagazine) o;
		getSqlMapClientTemplate().update("updateTTopMagazine", Model);
	}

	@Override
	public Object insert(Object o) {
		// TODO Auto-generated method stub
		TTopMagazine Model = (TTopMagazine) o;
		long id = (Long) getSqlMapClientTemplate().insert("insertTTopMagazine", Model);
		return id;
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("deleteTTopMagazineById", id);
	}

}
