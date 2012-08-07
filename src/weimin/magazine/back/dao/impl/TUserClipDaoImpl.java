package weimin.magazine.back.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import weimin.magazine.back.dao.CommonDao;
import weimin.magazine.back.dao.pojo.TUserClip;

public class TUserClipDaoImpl extends SqlMapClientDaoSupport implements CommonDao {

	@Override
	public Object queryById(long id) {
		// TODO Auto-generated method stub
		TUserClip userClip = (TUserClip) getSqlMapClientTemplate().queryForObject("queryTUserClipById", id);
		return userClip;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> queryAll() {
		// TODO Auto-generated method stub
		List<Object> l = getSqlMapClientTemplate().queryForList("queryAllTUserClip");
		return l;
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		TUserClip userClip = (TUserClip) o;
		getSqlMapClientTemplate().update("updateTUserClip", userClip);
	}

	@Override
	public Object insert(Object o) {
		// TODO Auto-generated method stub
		TUserClip userClip = (TUserClip) o;
		long id = (Long) getSqlMapClientTemplate().insert("insertTUserClip", userClip);
		return id ;
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("deleteTUserClipById", id);
	}

}
