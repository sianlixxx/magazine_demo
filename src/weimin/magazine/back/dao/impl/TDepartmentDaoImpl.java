/**
 * 
 */
package weimin.magazine.back.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import weimin.magazine.back.dao.CommonDao;
import weimin.magazine.back.dao.pojo.TDepartment;


/**
 * @author tianhao
 *
 */
public class TDepartmentDaoImpl extends SqlMapClientDaoSupport implements CommonDao{

    private static final Log log = LogFactory.getLog(TDepartmentDaoImpl.class);
    
	@Override
	public Object queryById(long id) {
	    log.debug("检索杂志社，杂志社id为："+id);
		return getSqlMapClientTemplate().queryForObject("queryTDepartmentById", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> queryAll() {
	    log.debug("检索所有杂志社");
		return getSqlMapClientTemplate().queryForList("queryAllTDepartment");
	}

	@Override
	public void update(Object o) {
		TDepartment department = (TDepartment) o;
		getSqlMapClientTemplate().update("updateTDepartment", department);
		log.debug("更新杂志社，杂志社id为："+department.getDepartmentId());
	}

	@Override
	public Object insert(Object o) {
		TDepartment department = (TDepartment) o;
		long id = (Long) getSqlMapClientTemplate().insert("insertTDepartment", department);
		return id;
	}

	@Override
	public void deleteById(long id) {
		getSqlMapClientTemplate().delete("deleteTDepartmentById", id);
	}
	
	/**
	 * 增加订阅人数
	 * @param count 新增订阅人数，当为负数时为取消订阅
	 */
	public void addSubscribeCount(long departmentId,int count){
		TDepartment department = (TDepartment) getSqlMapClientTemplate().queryForObject("queryTDepartmentById", departmentId);
		department.setSubscribeCount(department.getSubscribeCount()+count);
		getSqlMapClientTemplate().update("updateTDepartment", department);
	}

	/**
	 * 安照综合积分排序，总排行榜；
	 * @return
	 */
    @SuppressWarnings("unchecked")
    public List<Object> getTop() {
        List<Object> l = getSqlMapClientTemplate().queryForList("getTop");
        log.debug("检索系统排行榜，排行榜大小为："+l.size());
        return l;
    }

    /**
     * <br>查找用户所创建的杂志社
     * @param userId
     * @return
     */
    public TDepartment queryByCreater(long userId) {
        log.debug("查找用户所创建的杂志社");
          Object o = getSqlMapClientTemplate().queryForObject("queryByCreater",userId);
          if(o != null){
              log.debug("查找用户所创建的杂志社，用户id为："+userId);
              return  (TDepartment) o; 
          }else{
              log.debug("此用户没有创建杂志社！");
              return null;
          }
    }
    
    /**
     * <br>安照综合积分排序，分类排行榜；
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Object> getTopByTheme(int type) {
        List<Object> l = getSqlMapClientTemplate().queryForList("getTopByTheme",type);
        log.debug("检索系统分类排行榜，排行榜大小为："+l.size());
        return l;
    }

}
