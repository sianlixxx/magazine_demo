package weimin.magazine.back.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import weimin.magazine.back.dao.CommonDao;
import weimin.magazine.back.dao.pojo.TDepartmentConfig;

public class TDepartmentConfigDaoImpl extends SqlMapClientDaoSupport implements CommonDao {

	@Override
	public Object queryById(long id) {
		// TODO Auto-generated method stub
		TDepartmentConfig tDepartmentConfig = (TDepartmentConfig) getSqlMapClientTemplate().queryForObject("queryTDepartmentConfigById", id);
		return tDepartmentConfig;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> queryAll() {
		// TODO Auto-generated method stub
		List<Object> l = getSqlMapClientTemplate().queryForList("queryAllTDepartmentConfig");
		return l;
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		TDepartmentConfig tDepartmentConfig = (TDepartmentConfig) o;
		getSqlMapClientTemplate().update("updateTDepartmentConfig", tDepartmentConfig);
	}

	@Override
	public Object insert(Object o) {
		// TODO Auto-generated method stub
		TDepartmentConfig tDepartmentConfig = (TDepartmentConfig) o;
		long id = (Long) getSqlMapClientTemplate().insert("insertTDepartmentConfig", tDepartmentConfig);
		return id;
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("deleteTDepartmentConfigById", id);
	}

}
