/**
 * 
 */
package weimin.magazine.back.vo;

import weimin.magazine.back.dao.pojo.TDepartment;

/**
 * @author lify
 *
 */
public class SubscribeRelation {
    
    private TDepartment department;
    
    private long userId;
    
    private int flag; //0未订阅，1已订阅

    public TDepartment getDepartment() {
        return department;
    }

    public void setDepartment(TDepartment department) {
        this.department = department;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

}
