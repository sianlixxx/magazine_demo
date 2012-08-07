/**
 * 
 */
package weimin.magazine.back.service;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import weimin.magazine.back.analysis.Classification;
import weimin.magazine.back.dao.pojo.TLabel;
import weimin.magazine.back.dao.pojo.TDepartment;
import weimin.magazine.back.dao.pojo.TUser;
import weimin.magazine.back.dao.pojo.TUserSubscribe;
import weimin.magazine.util.SystemConfig;

/**
 * @author tianhao
 * 
 */
public class RecommendService extends BaseService {
	
	private static final Log log = LogFactory.getLog(RecommendService.class);
	

	/**
	 * <br>为用户推荐杂志:</br>
	 * 		首次登陆时使用/其它需要为用户推荐时使用；
	 * <br>步骤：
	 * 1.检索缓存中推荐杂志ids和杂志信息；
	 * 2.若缓存丢失，或者系统新用户，则为用户计算推荐。
	 * @param userId
	 * @return 编辑部列表
	 */
	public Set<TDepartment> recommend(long userId) {
	 // 为用户推荐杂志的编辑部集合，使用set避免重复；
	    Set<TDepartment> recomments = new LinkedHashSet<TDepartment>();
		{
			// 检索redis获得为用户推荐的杂志
			recomments = this.queryRecomments(userId);
		}
		// 缓存redis没有推荐结果，则分析用户标签，并为用户推荐杂志
		if (recomments.size() == 0) {
			log.info("缓存中无为用户推荐的杂志，系统开始计算为用户推荐的杂志！");
			recomments = calculateRecomments(userId);
			// 将计算后的推荐为用户存入缓存--redis
			redis.addRecomments(userId, recomments);
		}
		return recomments;
	}
	
	/**
	 * <br>索缓存中推荐杂志ids和杂志信息；
	 * <br>步骤：
	 * 1.检索缓存杂志id
	 * 2.根据id在缓存中检索杂志社信息
	 * 3.若缓存中杂志社信息丢失则从后台db中取并更新缓存；
	 * @param userId
	 * @return
	 */
    public Set<TDepartment> queryRecomments(long userId) {
        Set<TDepartment> recomments = new LinkedHashSet<TDepartment>();
        Set<String> departmentIdSet = redis.queryRecomments(userId);
        if (departmentIdSet != null) {
            // 遍历集合取出所有杂志社信息
            for (String departmentId : departmentIdSet) {
                TDepartment department = redis.queryDepartment(Long
                        .valueOf(departmentId));
                if (department != null) {
                    // 3.若缓存中杂志社信息丢失则从后台db中取并更新缓存；
                    department = (TDepartment) tDepartmentDaoImpl.queryById(Long.valueOf(departmentId));
                    redis.addDepartment(department);
                }
                recomments.add(department);
            }
            log.debug("从缓存中获取推荐给用户的在杂志，用户id为：" + userId);
            return recomments;
        } else {
            log.debug("从缓存中不存在推荐给用户的在杂志，用户id为：" + userId);

        }
        return null;
    }

    /**
	 * <br> TODO 
	 * @param userId
	 * @return
	 */
	public Set<TUser> recommendEditors(long userId) {
	    Set<TUser> editors = new LinkedHashSet<TUser>();
	    editors = redis.queryRecommentEditors(userId);
	    if(editors.size() == 0){
	        log.debug("暂不需要为此用户推荐编辑！" );
	    }
        return editors;
	}
	

    

    /**
     * <br>计算为用户推荐的杂志
     * 1.根据用户标签为用户匹配合适的杂志:选择与用户具有相同标签的杂志，具有相同标签的话具有相同兴趣的可能非常大；</br>
     * 2.根据用户标签对用户分类，然后在类别排行榜中选择排名较高的杂志进行推荐；</br> 
     * 3.根据过滤规则过滤部分不适合推荐的杂志；</br>
     * @param userId
     * @return
     */
	public Set<TDepartment> calculateRecomments(long userId) {
	    Set<TDepartment> recomments = new LinkedHashSet<TDepartment>();
	    // 用户标签，使用set避免重复；
	    Set<String> labels = new LinkedHashSet<String>();
		// 获得用户标签 DomainLabel
		labels = showUserLabels(userId);
		// 1、匹配与用户具有相同标签的杂志
		recomments = this.matchMagazine(labels,recomments);
		// 2、在与用户相匹配的排行榜中选取推荐
		recomments = this.matchTop(labels,recomments);
		// 3、根据规则过滤部分杂志
		recomments = this.filter(userId ,recomments);
		return recomments;
	}
	
	/**
	 * TODO 
	 * @param department
	 * @return
	 */
	public Set<TUser> calculateEditors(TDepartment department) {
	   
        return null;
    }

	/**
	 * <br>根据用户标签为用户匹配合适的杂志 检索与用户具有相同标签的杂志,</br>
	 * 
	 * @param labels
	 */
	protected Set<TDepartment> matchMagazine(Set<String> labels,Set<TDepartment> recomments) {
		// 检索与用户具有相同标签的杂志
		log.debug("系统开始检索与用户具有相同标签的杂志！");
		for (String label : labels) {
			List<TLabel> tLabels = tLabelDaoImpl
					.queryDepartmentIdsByLabel(label);
			for (TLabel tLabel : tLabels) {
				long departmentId = tLabel.getUserId();
				TDepartment department = (TDepartment) tDepartmentDaoImpl
						.queryById(departmentId);
				recomments.add(department);
			}
		}
		log.debug("检索到与用户具有相同标签的杂志总计个数为："+recomments.size());
		return recomments;
	}

	/**
	 * <br>根据用户标签为用户匹配合适的杂志， 在与用户相匹配的排行榜中选取推荐 matchTopByLabels(labels);
	 * 
	 * @param labels
	 * @return List<DomainLabel>
	 */
	protected Set<TDepartment> matchTop(Set<String> labels,Set<TDepartment> recomments) {
		// 根据用户标签分析属于用户的theme
		Classification classification = new Classification();
		Set<String> themes = classification.classificationByLabels(labels);
		log.debug("系统将为用户推荐一下类别的杂志："+themes);
		// 把分类排行中与用户属于同一theme的杂志推荐几本
		for (String theme : themes) {
			Map<String, String> top = redis.queryTopByTheme(theme);
			// 获取排行榜中的前多少名，并推荐给用户
			int topSize = Integer.valueOf(SystemConfig.getValue("sys.suggestions.top.max.number.theme"));
			for (int i = 0; i < topSize; i++) {
				long departmentId = Long.parseLong(top.get(String.valueOf(i)));
				// TODO查询返回null值未做处理
				TDepartment department = (TDepartment) tDepartmentDaoImpl
						.queryById(departmentId);
				recomments.add(department);
			}
			log.debug("为用户推荐类别："+theme+" 的杂志数目为："+topSize);
		}
		log.debug("系统将为用户推荐总排行榜的杂志：");
		Map<String, String> top = redis.queryTop();
		// 获取排行榜中的前多少名，并推荐给用户
        int topSize = Integer.valueOf(SystemConfig.getValue("sys.suggestions.top.max.number"));
        for (int i = 0; i < topSize; i++) {
            long departmentId = Long.parseLong(top.get(String.valueOf(i)));
            // TODO查询返回null值未做处理
            TDepartment department = (TDepartment) tDepartmentDaoImpl
                    .queryById(departmentId);
            recomments.add(department);
        }
        log.debug("为用户推荐总排行榜中杂志数目为："+topSize);
		return recomments;
	}

	/**
	 * <br>根据规则过滤不适合作为系统推荐的杂志
	 * 
	 * @param recomments2
	 * @return
	 */
	protected Set<TDepartment> filter(long userId,Set<TDepartment> recomments) {
	    // 过滤已订阅杂志
	    recomments = this.filterSubscribe( userId, recomments);
		// TODO 其它过滤规则
	    log.debug("过滤部分杂志！");
		return recomments;
	}
	

	/**
	 * <br>过滤已订阅杂志 
	 * <br>步骤：
	 * 1.获取已订阅的杂志id的set集合
	 * 2.若推荐的杂志id可以add进已订阅的set集合，则表明此杂志未订阅过，系统需要推荐
	 * 3.返回系统推荐的杂志。
	 * @param userId
	 * @param recomments
	 * @return
	 */
    private Set<TDepartment> filterSubscribe(long userId,
            Set<TDepartment> recomments) {
        log.debug("开始为用户过滤已订阅杂志！");
        //定义已订阅的set集合
        Set<Long> oldDepartmentIds = new LinkedHashSet<Long>();
        //定义新推荐的杂志集合
        Set<TDepartment> newRecomments = new LinkedHashSet<TDepartment>();
        //1.获取已订阅的杂志id的set集合
        List<Object> Subscribes = tUserSubscribeDaoImpl.querySubscribeByUserId(userId);
        Iterator<Object> Ids = Subscribes.iterator();
        while (Ids.hasNext()) {
            TUserSubscribe Subscribe = (TUserSubscribe) Ids.next();
            oldDepartmentIds.add(Subscribe.getDepartmentId());
        }
        //2.若推荐的杂志id可以add进已订阅的set集合，则表明此杂志未订阅过，系统需要推荐
        Iterator<TDepartment> rms = recomments.iterator();
        while (rms.hasNext()) {
            TDepartment department = rms.next();
            if(oldDepartmentIds.add(department.getDepartmentId())){
                newRecomments.add(department);
            }
        }
        //3.返回系统推荐的杂志。
        return newRecomments;
    }

    

}
