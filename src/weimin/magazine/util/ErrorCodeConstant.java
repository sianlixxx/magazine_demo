/**
 * 
 */
package weimin.magazine.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tianhao
 *
 */
public class ErrorCodeConstant {
    
    static Map<Integer,Integer> ErrorcodeMap = new HashMap<Integer,Integer>();
   
    //系统自定义errorcode
    public static final int SINA_WEIBO_EXPIRED_TOKEN = 1001;//

    //SINA WEIBO ERRORCODE
    public static final int EXPIRED_TOKEN = 21327  ;// token过期 
    
    public static final int TOKEN_EXPIRED = 21315   ;// Token已经过期
    
    //构造系统外errorcede与系统自定义errorcode的映射关系
    static{
        ErrorcodeMap.put(21327, 1001);
        ErrorcodeMap.put(21315, 1001);
    }
    
    //将系统外errorcede转换为系统自定义errorcode
   public static int trancErrorCode(int errorCode){
        return ErrorcodeMap.get(errorCode);
    }

}
