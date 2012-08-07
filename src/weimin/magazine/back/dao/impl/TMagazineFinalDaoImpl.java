/**
 * 
 */
package weimin.magazine.back.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import weimin.magazine.back.dao.CommonDao;
import weimin.magazine.back.dao.pojo.TMagazineFinal;


/**
 * @author tianhao
 *
 */
public class TMagazineFinalDaoImpl extends SqlMapClientDaoSupport implements CommonDao {

    private static final Log log = LogFactory.getLog(TMagazineFinalDaoImpl.class);
    
	/* (non-Javadoc)
	 * @see weimin.magazine.back.dao.CommonDao#queryById(int)
	 */
	@Override
	public Object queryById(long id) {
		// TODO Auto-generated method stub
		TMagazineFinal tMagazineFinal = (TMagazineFinal) getSqlMapClientTemplate().queryForObject("queryTMagazineFinalById", id);
		log.debug("查询杂志，杂志id为："+id);
		return tMagazineFinal;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> queryAll() {
		// TODO Auto-generated method stub
		List<Object> l = getSqlMapClientTemplate().queryForList("queryAllTMagazineFinal");
		log.debug("检索系统所有杂志！");
		return l;
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		TMagazineFinal tMagazineFinal = (TMagazineFinal) o;
		log.debug("更新杂志信息！");
		getSqlMapClientTemplate().update("updateTMagazineFinal", tMagazineFinal);
	}

	@Override
	public Object insert(Object o) {
		// TODO Auto-generated method stub
		TMagazineFinal tMagazineFinal = (TMagazineFinal) o;
		long id = (Long) getSqlMapClientTemplate().insert("insertTMagazineFinal", tMagazineFinal);
		log.debug("新建一本杂志，系统生成的杂志id为："+id);
		return id;
	}

	@Override
	public void deleteById(long id) {
		getSqlMapClientTemplate().delete("deleteTMagazineFinalById", id);
		log.debug("删除一本杂志，杂志id为："+id);
	}

	
    /**
     * 
     * @param tMagazineFinal
     * @return
     */
    private int createSerialNumber(TMagazineFinal tMagazineFinal) {
        Integer serialNumber = (Integer)getSqlMapClientTemplate().queryForObject("createSerialNumber",tMagazineFinal);
        serialNumber = serialNumber+1;
        log.debug("新建一本杂志，系统生成的杂志序列号为："+serialNumber);
        return serialNumber;
    }
    

     /**
      * 1.插入一条杂志记录，返回杂志id
      * 2.设置杂志状态为1（已创建）
      * 3.设置杂志序列号，（序列号递增)
      * 4.更新杂志信息
      * @param tMagazineFinal
      * @return
      */
    public TMagazineFinal createMagazine(TMagazineFinal tMagazineFinal) {
        long magazineId = (Long) insert(tMagazineFinal);
        tMagazineFinal.setMagazineId(magazineId);
        // 2.设置杂志状态为1（已创建）
        tMagazineFinal.setStatus(1);
        // 3.设置杂志序列号，（序列号递增）
        int serialNumber = createSerialNumber(tMagazineFinal);
        tMagazineFinal.setSerialNumber(serialNumber);
        update(tMagazineFinal);
        log.info("成功创建一本杂志，杂志id为："+magazineId + "   杂志期刊号为"+tMagazineFinal.getDepartmentId()+"_"+serialNumber);
        return tMagazineFinal;
    }

    /**
     * </br>查询当前杂志社的最新期刊
     * @param departmentId
     * @return
     */
    public TMagazineFinal queryCurrent(long departmentId) {
         Object o = getSqlMapClientTemplate().queryForObject("queryCurrentTMagazineFinalBydepartmentId", departmentId);
         if(o != null){
             TMagazineFinal tMagazineFinal = (TMagazineFinal)o;
             log.debug("杂志社："+departmentId+"   的最新杂志id为："+tMagazineFinal.getMagazineId()); 
             return tMagazineFinal;
         }
        return null;
    }
    

}
