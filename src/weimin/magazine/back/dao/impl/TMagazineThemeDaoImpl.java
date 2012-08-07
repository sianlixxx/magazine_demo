/**
 * 
 */
package weimin.magazine.back.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import weimin.magazine.back.dao.CommonDao;
import weimin.magazine.back.dao.pojo.TMagazineTheme;

/**
 * @author tianhao
 *
 */
public class TMagazineThemeDaoImpl extends SqlMapClientDaoSupport implements CommonDao{
    
    private static final Log log = LogFactory.getLog(TMagazineThemeDaoImpl.class);

	@Override
	public Object queryById(long id) {
		TMagazineTheme theme = (TMagazineTheme) getSqlMapClientTemplate().queryForObject("queryTMagazineThemeById", id);
		log.debug("检索主题，主题id为："+id);
		return theme;
	}

	
	@Override
	public List<Object> queryAll() {
		@SuppressWarnings("unchecked")
        List<Object> l = getSqlMapClientTemplate().queryForList("queryAllTMagazineTheme");
		return l;
	}

	@Override
	public void update(Object o) {
		TMagazineTheme theme = (TMagazineTheme) o;
		getSqlMapClientTemplate().update("updateTMagazineTheme", theme);
	}

	@Override
	public Object insert(Object o) {
		TMagazineTheme theme = (TMagazineTheme) o;
		long id = (Long) getSqlMapClientTemplate().insert("insertTMagazineTheme", theme);
		return id ;
	}

	@Override
	public void deleteById(long id) {
		getSqlMapClientTemplate().delete("deleteTMagazineThemeById", id);
	}

	/**
	 * <br>获取所有杂志类别，去除重复，按照现有表中使用次数排列
	 * @return
	 */
    public List<TMagazineTheme> queryRecommendThemes() {
        @SuppressWarnings("unchecked")
        List<TMagazineTheme> themes = getSqlMapClientTemplate().queryForList("queryRecommendThemes");
        return themes;
    }
    
    /**
     * <br>获取杂志类别列表
     * @return
     */
    public List<Integer> queryThemeTypes(){
        @SuppressWarnings("unchecked")
        List<Integer> types = getSqlMapClientTemplate().queryForList("queryThemeTypes");
        return types;
    }

}
