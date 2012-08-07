package weimin.magazine.back.dao.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import weimin.magazine.back.dao.CommonDao;
import weimin.magazine.back.dao.pojo.TUserContribute;


public class TUserContributeDaoImpl extends SqlMapClientDaoSupport implements
		CommonDao {

    private static final Log log = LogFactory.getLog(TUserContributeDaoImpl.class);
    
	@Override
	public Object queryById(long id) {
		// TODO Auto-generated method stub
		TUserContribute userContribute = (TUserContribute) getSqlMapClientTemplate().queryForObject("queryTUserContributeById", id);
		return userContribute;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> queryAll() {
		// TODO Auto-generated method stub
		List<Object> l = getSqlMapClientTemplate().queryForList("queryAllTUserContribute");
		return l;
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		TUserContribute userContribute = (TUserContribute) o;
		getSqlMapClientTemplate().update("updateTUserContribute", userContribute);
	}

	@Override
	public Object insert(Object o) {
		// TODO Auto-generated method stub
		TUserContribute userContribute = (TUserContribute) o;
		long id  = (Long) getSqlMapClientTemplate().insert("insertTUserContribute", userContribute);
		log.debug("新建一条投稿，投稿id为："+id);
		return id;
	}

	@Override
	public void deleteById(long id) {
		getSqlMapClientTemplate().delete("deleteTUserContributeById", id);
	}

	/**
	 * <br>判断此投稿记录是否存在
	 * @param tUserContribute
	 * @return
	 */
    public Object queryContribute(TUserContribute tUserContribute) {
        return  getSqlMapClientTemplate().queryForObject("queryContribute", tUserContribute);
    }

    @SuppressWarnings("unchecked")
    /**
     * <br>从db中收集所有新稿件；
     * @param userId
     * @return
     */
    public List<TUserContribute> queryNewContruibuteByUserId(long userId) {
        List<TUserContribute> contributes =  getSqlMapClientTemplate().queryForList("queryNewContruibuteByUserId",userId);
        if(contributes == null){
            log.debug("用户："+userId+"    没有新稿件！");
        }else{
            log.debug("用户："+userId+"    收到稿件数为："+contributes.size());
        }
        return null;
    }

   

}
