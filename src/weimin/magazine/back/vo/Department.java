/**
 * 
 */
package weimin.magazine.back.vo;

import weimin.magazine.back.dao.pojo.TDepartment;
import weimin.magazine.back.dao.pojo.TUser;

/**
 * @author tianhao
 *
 */
public class Department {

    private TDepartment tDepartment;
    
    private TUser creater;

    public TDepartment gettDepartment() {
        return tDepartment;
    }

    public void settDepartment(TDepartment tDepartment) {
        this.tDepartment = tDepartment;
    }

    public TUser getCreater() {
        return creater;
    }

    public void setCreater(TUser creater) {
        this.creater = creater;
    }
    
    

}
