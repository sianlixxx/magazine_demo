package weimin.magazine.back.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import weimin.magazine.back.dao.CommonDao;
import weimin.magazine.back.dao.pojo.TUserConfig;

public class TUserConfigDaoImpl extends SqlMapClientDaoSupport implements
		CommonDao {

	@Override
	public Object queryById(long id) {
		// TODO Auto-generated method stub
		TUserConfig userConfig = (TUserConfig) getSqlMapClientTemplate().queryForObject("queryTUserConfigById", id);
		return userConfig;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> queryAll() {
		// TODO Auto-generated method stub
		List<Object> l = getSqlMapClientTemplate().queryForList("queryAllTUserConfig");
		return l;
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		TUserConfig userConfig = (TUserConfig) o;
		getSqlMapClientTemplate().update("updateTUserConfig", userConfig);
	}

	@Override
	public Object insert(Object o) {
		// TODO Auto-generated method stub
		TUserConfig userConfig = (TUserConfig) o;
		long id = (Long) getSqlMapClientTemplate().insert("insertTUserConfig", userConfig);
		return id;
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("deleteTUserConfigById", id);
	}

}
