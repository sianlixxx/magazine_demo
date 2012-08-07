/**
 * 
 */
package weimin.magazine.back.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import weimin.magazine.back.dao.CommonDao;
import weimin.magazine.back.dao.pojo.TLabel;
import weimin.magazine.util.Tools;

/**
 * @author tianhao
 *
 */

public class TLabelDaoImpl extends SqlMapClientDaoSupport implements CommonDao{

    private static final Log log = LogFactory.getLog(TLabelDaoImpl.class);
	
	
	@Override
	public Object queryById(long id) {
		// TODO Auto-generated method stub
		TLabel label = (TLabel) getSqlMapClientTemplate().queryForObject("queryTLabelById", id);
		return label;
	}
	

	@SuppressWarnings("unchecked")
    @Override
	public List<Object> queryAll() {
		// TODO Auto-generated method stub
		List<Object> l = getSqlMapClientTemplate().queryForList("queryAllTLabel");
		return l;
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		TLabel label = (TLabel) o;
		getSqlMapClientTemplate().update("updateTLabel", label);
	}

	@Override
	public Object insert(Object o) {
		// TODO Auto-generated method stub
		TLabel label = (TLabel) o;
		long id  = (Long) getSqlMapClientTemplate().insert("insertTLabel", label);
		return id;
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("deleteTLabelById", id);
	}
	
	
	//获得用户标签
	@SuppressWarnings("unchecked")
    public List<TLabel> queryLabelsByUserId(long id){
		List<TLabel> l = getSqlMapClientTemplate().queryForList("queryLabelsByUserId",id);
		log.debug("获取用户标签，用户id为："+id+"    用户标签个数为："+l.size());
		return l;
	}
	
	
	//根据标签检索编辑部
	@SuppressWarnings("unchecked")
    public List<TLabel> queryDepartmentIdsByLabel(String name){
		List<TLabel> l = getSqlMapClientTemplate().queryForList("queryDepartmentIdsByLabel",name);
		return l;
	}


	//获取某个标签当前被使用的总次数,已删除的标签不计算在内
	public int countLabelsFrequency(String label) {
		int count = (Integer) getSqlMapClientTemplate().queryForObject("countLabelsFrequency",label);
		return count;
	}
	
	//获取某个标签被被使用的最大次数，包括已删除的标签
	public int maxLabelsFrequency(String label) {
		Object o = getSqlMapClientTemplate().queryForObject("maxLabelsFrequency", label);
		if(o == null ){
		    log.debug("系统用户未使用过标签："+label );
		    o = 0;
		}
		int count = (Integer)o;
		log.debug("标签："+label + " 已使用次数为："+count);
		return count;
	}
	
	/**
	 * 为用户增加一个标签</br>
	 * 	步骤：
	 * 1.设置类型为用户标签0；
	 * 2.设置标签名；
	 * 3.设置用户id；
	 * 4.设置标签创建时间；
	 * 5.获取标签已被使用总次数，并将使用次数增1；
	 * 6.插入数据对象
	 * @param userId
	 * @param l
	 */
	public void addUserLabel(long userId, String label) {
	    log.debug("为系统用户："+userId+"    添加标签："+label);
		TLabel tLabel = new TLabel();
		tLabel.setDomainType(false);// 0为用户标签
		tLabel.setName(label);// 标签名
		tLabel.setUserId(userId);// 系统用户id
		tLabel.setCreatedAt(Tools.getDate());//标签创建时间为用户注册时间
		//获取标签已被使用总次数，若返回null则表明未使用过。
		int count = 0;//初始化为0
		Object num =  this.maxLabelsFrequency(label);
		if(num != null){ count = (Integer)num;}
		tLabel.setFrequency(count+1);
		this.insert(tLabel);;
	}

	// 删除某个标签,不区分杂志标签和用户标签
	public void deleteLabel(long userId,String label) {
		TLabel tLabel = new TLabel();
		tLabel.setName(label);// 标签名
		tLabel.setUserId(userId);// 系统用户id
		getSqlMapClientTemplate().delete("deleteLabel", tLabel);
		log.debug("删除用户标签，用户id为："+userId+"    标签为："+label);
	}

	//获得系统标签，标签按照使用次数排序
	@SuppressWarnings("unchecked")
    public List<TLabel> queryRecommendLabels() {
		List<TLabel> labels = getSqlMapClientTemplate().queryForList("queryRecommendLabels");
		log.debug("获取系统标签！");
		return labels;
	}


	
	
	
	
	

}
