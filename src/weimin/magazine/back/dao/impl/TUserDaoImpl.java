package weimin.magazine.back.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import weimin.magazine.back.dao.CommonDao;
import weimin.magazine.back.dao.pojo.TUser;


public class TUserDaoImpl extends SqlMapClientDaoSupport implements CommonDao {

    private static final Log log = LogFactory.getLog(TUserDaoImpl.class);
    
	@Override
	public Object queryById(long id) {
	    log.debug("查询用户信息，用户id为："+id);
		return getSqlMapClientTemplate().queryForObject("queryTUserById", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> queryAll() {
		// TODO Auto-generated method stub
		List<Object> l = getSqlMapClientTemplate().queryForList("queryAllTUser");
		return l;
	}

	@Override
	public void update(Object o) {
		TUser userContribute = (TUser) o;
		getSqlMapClientTemplate().update("updateTUser", userContribute);
		log.debug("更新用户信息！");
	}

	@Override
	public Object insert(Object o) {
		TUser tUser = (TUser) o;
		long id = (Long) getSqlMapClientTemplate().insert("insertTUser", tUser);
		log.debug("后台系统注册新用户，系统用户id为："+id);
		return id;
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("deleteTUserById", id);
	}
	
	/**
	 * 根据微博uid查询记录
	 * @param id
	 * @return
	 */
	public Object queryByUid(long id) {
		// TODO Auto-generated method stub
		TUser tUser = (TUser) getSqlMapClientTemplate().queryForObject("queryTUserByUid", id);
		return tUser;
	}

	
	/**
	 * <br>用户增加条订阅
	 * @param userId
	 * @param i 新增订阅数，当为负数时为取消订阅
	 */
    public void addSubscribeCount(Long userId, int i) {
        TUser tUser = (TUser) getSqlMapClientTemplate().queryForObject("queryTUserById", userId);
        tUser.setSubscribeCount(tUser.getSubscribeCount() + i);
        this.update(tUser);
    }

}
