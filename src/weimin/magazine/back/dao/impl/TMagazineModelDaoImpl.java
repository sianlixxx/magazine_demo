package weimin.magazine.back.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import weimin.magazine.back.dao.CommonDao;
import weimin.magazine.back.dao.pojo.TMagazineModel;

public class TMagazineModelDaoImpl extends SqlMapClientDaoSupport implements CommonDao {

	@Override
	public Object queryById(long id) {
		// TODO Auto-generated method stub
		TMagazineModel model = (TMagazineModel) getSqlMapClientTemplate().queryForObject("queryTMagazineModelById", id);
		return model;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> queryAll() {
		// TODO Auto-generated method stub
		List<Object> l = getSqlMapClientTemplate().queryForList("queryAllTMagazineModel");
		return l;
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		TMagazineModel Model = (TMagazineModel) o;
		getSqlMapClientTemplate().update("updateTMagazineModel", Model);
	}

	@Override
	public Object insert(Object o) {
		// TODO Auto-generated method stub
		TMagazineModel Model = (TMagazineModel) o;
		long id = (Long) getSqlMapClientTemplate().insert("insertTMagazineModel", Model);
		return id;
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("deleteTMagazineModelById", id);
	}

}
