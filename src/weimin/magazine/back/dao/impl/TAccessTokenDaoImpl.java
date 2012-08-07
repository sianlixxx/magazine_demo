package weimin.magazine.back.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import weimin.magazine.back.dao.CommonDao;
import weimin.magazine.back.dao.pojo.TAccessToken;

public class TAccessTokenDaoImpl extends SqlMapClientDaoSupport implements CommonDao {

    private static final Log log = LogFactory.getLog(TAccessTokenDaoImpl.class);
    
	@Override
	public Object queryById(long id) {
		// TODO Auto-generated method stub
		TAccessToken tAccessToken = (TAccessToken) getSqlMapClientTemplate().queryForObject("queryTAccessTokenById", id);
		return tAccessToken;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> queryAll() {
		// TODO Auto-generated method stub
		List<Object> l = getSqlMapClientTemplate().queryForList("queryAllTAccessToken");
		return l;
	}

	@Override
	public void update(Object o) {
		TAccessToken tAccessToken = (TAccessToken) o;
		getSqlMapClientTemplate().update("updateTAccessToken", tAccessToken);
		log.debug("成功更新后台用户授权信息！");
	}

	@Override
	public Object insert(Object o) {
		TAccessToken tAccessToken = (TAccessToken) o;
		//accesstoken并不是自增加的，默认返回为0
		long id  = (Long) getSqlMapClientTemplate().insert("insertTAccessToken", tAccessToken);
		log.debug("成功新建系统用户授权信息！");
		return  id ;
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("deleteTAccessTokenById", id);
	}

	/**
	 * 根据微博uid查询记录
	 * @param uid
	 * @return
	 */
	public TAccessToken queryByUid(long uid) {
		Object o = getSqlMapClientTemplate().queryForObject("queryTAccessTokenByUid", uid);
		if(o == null){
		    log.debug("成功从后台获取用户授权信息失败！用户微博id为："+uid);
		}
		TAccessToken tAccessToken = (TAccessToken) o;
		log.debug("成功从后台获取用户授权信息！用户微博id为："+uid);
		return tAccessToken;
	}
	
	

}
