package weimin.magazine.back.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import weimin.magazine.back.dao.CommonDao;
import weimin.magazine.back.dao.pojo.TDepartmentComment;


public class TDepartmentCommentDaoImpl extends SqlMapClientDaoSupport implements CommonDao {
    
    private static final Log log = LogFactory.getLog(TDepartmentCommentDaoImpl.class);

	@Override
	public Object queryById(long id) {
		// TODO Auto-generated method stub
		TDepartmentComment tDepartmentComment = (TDepartmentComment) getSqlMapClientTemplate().queryForObject("queryTDepartmentCommentById", id);
		log.debug("查找评论，评论id为："+id);
		return tDepartmentComment;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> queryAll() {
		// TODO Auto-generated method stub
		List<Object> l = getSqlMapClientTemplate().queryForList("queryAllTDepartmentComment");
		return l;
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		TDepartmentComment tDepartmentComment = (TDepartmentComment) o;
		getSqlMapClientTemplate().update("updateTDepartmentComment", tDepartmentComment);
		log.debug("更新一条评论！");
	}

	@Override
	public Object insert(Object o) {
		// TODO Auto-generated method stub
		TDepartmentComment tDepartmentComment = (TDepartmentComment) o;
		long id  = (Long) getSqlMapClientTemplate().insert("insertTDepartmentComment", tDepartmentComment);
		log.debug("新建一条评论，评论id为："+id);
		return id;
		
	}

	@Override
	public void deleteById(long id) {
		
		getSqlMapClientTemplate().delete("deleteTDepartmentCommentById", id);
	}

    @SuppressWarnings("unchecked")
    /**
     * <br>检索用户所发表的所有评论
     * @param userId
     * @return
     */
    public List<TDepartmentComment> queryCommentsByUserId(long userId) {
        List<TDepartmentComment> comment = getSqlMapClientTemplate().queryForList("queryCommentsByUserId",userId);
        log.debug("检索用户所发表的评论"+userId);
        return comment;
    }

}
