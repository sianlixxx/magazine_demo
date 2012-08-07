/**
 * 
 */
package weimin.magazine.back.dao.impl;


import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import weimin.magazine.back.dao.CommonDao;
import weimin.magazine.back.dao.pojo.TUserSubscribe;


/**
 * @author tianhao
 *
 */
public class TUserSubscribeDaoImpl extends SqlMapClientDaoSupport implements
		CommonDao {

    private static final Log log = LogFactory.getLog(TUserSubscribeDaoImpl.class);
    
	@Override
	public Object queryById(long id) {
		TUserSubscribe userSubscribe = (TUserSubscribe) getSqlMapClientTemplate().queryForObject("queryTUserSubscribeById", id);
		return userSubscribe;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> queryAll() {
		List<Object> l = getSqlMapClientTemplate().queryForList("queryAllTUserSubscribe");
		return l;
	}

	@Override
	public void update(Object o) {
		TUserSubscribe userSubscribe = (TUserSubscribe) o;
		getSqlMapClientTemplate().update("updateTUserSubscribe", userSubscribe);
		log.debug("更新订阅关系！");
	}

	@Override
	public Object insert(Object o) {
		TUserSubscribe userSubscribe = (TUserSubscribe) o;
		long id = (Long) getSqlMapClientTemplate().insert("insertTUserSubscribe", userSubscribe);
		log.debug("增加订阅关系！");
		return id;
	}

	@Override
	public void deleteById(long id) {
		getSqlMapClientTemplate().delete("deleteTUserSubscribeById", id);
	}
	
	/**
	 * 根据用户id和编辑部id查找订阅关系 </br>
	 *     可用于判断此关系是否存在，若不存在此关系则返回null
	 * @param userId 
	 * @param departmentId
	 * @return TUserSubscribe,若不存在此关系则返回null
	 * 		订阅关系为唯一值
	 */
	public Object  findSubscribe(long userId , long departmentId){
		TUserSubscribe userSubscribe = new TUserSubscribe();
		userSubscribe.setUserId(userId);
		userSubscribe.setDepartmentId(departmentId);
		return getSqlMapClientTemplate().queryForObject("findSubscribe",userSubscribe);
	}
	
	
	/**
	 * <br>检索用户订阅的杂志；
	 * @param userId
	 * @return 订阅关系列表
	 */
 
    @SuppressWarnings("unchecked")
    public List<Object> querySubscribeByUserId(long userId){
	    List<Object> l = getSqlMapClientTemplate().queryForList("querySubscribeByUserId",userId);
	    if(l != null){
	        log.debug("检索用户订阅的杂志，用户id为："+userId + "    用户订阅杂志数目为："+l.size());
	    }else{
	        log.debug("检索用户订阅的杂志，用户id为："+userId + "    用户暂未订阅杂志");
	    }
        return l;
	}
	
	@SuppressWarnings("unchecked")
	/**
	 *<br> 检索杂志被哪些用户订阅
	 * @param userId
	 * @return 订阅关系列表
	 */
    public List<Object> querySubscribeByDepartmentId(long departmentId){
        List<Object> l = getSqlMapClientTemplate().queryForList("querySubscribeByDepartmentId",departmentId);
        return l;
    }

	

}
