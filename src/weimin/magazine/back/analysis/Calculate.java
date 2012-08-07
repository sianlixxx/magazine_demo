package weimin.magazine.back.analysis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import weimin.magazine.back.dao.pojo.TDepartment;


public class Calculate {
    
    private static final Log log = LogFactory.getLog(Calculate.class);

    public Calculate() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * TODO 计算综合积分的算法改进和优化。
     * @param tDepartment
     * @return
     */
    public int calculateDepartmentScore(TDepartment tDepartment){
        int departmentScore = 0;
        //综合积分 = 订阅数+发版数+投稿数
        departmentScore = tDepartment.getSubscribeCount() + tDepartment.getReleaseCount() + tDepartment.getTotalContributeCount();
        log.debug("经过计算，杂志社："+tDepartment.getDepartmentId()+"    的综合积分为："+departmentScore);
        return departmentScore;
    }

    

}
