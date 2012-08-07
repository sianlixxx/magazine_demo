/**
 * 
 */
package weimin.magazine.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weimin.magazine.back.dao.pojo.TDepartment;
import weimin.magazine.back.dao.pojo.TMagazineFinal;
import weimin.magazine.back.dao.pojo.TUserContribute;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author tianhao
 *
 */
public class Tools {

    private static final Log log = LogFactory.getLog(Tools.class);
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean toBoolean(String str) {
		if ("true".equalsIgnoreCase(str)) {
		return true;
		} else if ("on".equalsIgnoreCase(str)) {
		return true;
		} else if ("yes".equalsIgnoreCase(str)) {
		return true;
		}
		// no match
		return false;
	}
	
	@SuppressWarnings("resource")
    public static byte[] readFileImage(String filename)throws IOException{
		BufferedInputStream bufferedInputStream=new BufferedInputStream(
				new FileInputStream(filename));
		int len =bufferedInputStream.available();
		byte[] bytes=new byte[len];
		int r=bufferedInputStream.read(bytes);
		if(len !=r){
			bytes=null;
			throw new IOException("读取文件不正确");
		}
		bufferedInputStream.close();
		return bytes;
	}
	
	/**
	 * @return
	 * @throws ParseException 
	 */
	public static Date getDate()  {
		Date d = getDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss");
		return d;
	}
	
	/** @param date 当前日期
	 * @param format 转换格式 yyyyMMddHHmmss
	 * @return
	 */
	public static Date getDateFormat(Date date, String format)  {
		 
		DateFormat df = new SimpleDateFormat(format);
		String result = df.format(date);
		Date d = null;
		try {
			d = df.parse(result);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
	
	/**
	 * @return
	 * @throws ParseException 
	 */
	public static Date String2date(String str)  {
		Date d = String2date(str, "yyyy-MM-dd HH:mm:ss");
		return d;
	}	
	
	/**
	 * 
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date String2date(String str, String format) {
		DateFormat df = new SimpleDateFormat(format);
		Date d = null;
		try {
			d = df.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
	
	
	public static String date2String(Date date) {
		String d = date2String(date,"yyyy-MM-dd HH:mm:ss");
		return d;
	}
	
	public static String date2String(Date date, String format) {
		DateFormat df = new SimpleDateFormat(format);
		String d = df.format(date);
		return d;
	}
	
	/**
	 * <br>获取之前某天的当前时间，
	 * @param num 之前多少天
	 * @return 之前某天的当前时间
	 */
	public static Date getEarlyDate(int num){
	    Date date = null;
        GregorianCalendar calendar = new  GregorianCalendar();
        calendar.add(GregorianCalendar.DATE, -num);
        date =  new  java.sql.Date(calendar.getTime().getTime());
        return date;
	}
	
	/**
	 * 将长文本转化为本地图片
	 * @param text
	 * @return
	 */
	public static String String2Pic(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 抽取文本摘要。
	 * @param text
	 * @return
	 */
	public static String summary(String text) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
     * <br>构造广播消息；
     * TODO 增加可链接的信息！
     * @param o
     * @param type
     * @return 广播消息
     */
	public static String createText(Object o ,int type) {
        String text = "";
        switch (type) {
            //注册系统用户
        case Constants.BROADCAST_TYPE_REGEDIT_USER:
            text = "我关注了微民杂志！";
            //创建杂志社
        case Constants.BROADCAST_TYPE_CREATEDEPARTMENT:
            TDepartment tDepartment = (TDepartment) o;
            text = "我创建了杂志社——" +tDepartment.getName();
            break;
            //创建杂志
        case Constants.BROADCAST_TYPE_CREATEMAGAZINE:
            TMagazineFinal tMagazineFinal = (TMagazineFinal) o;
            text = "我创建了一本杂志——" +tMagazineFinal.getName();
            break;
            //修改杂志封面
        case Constants.BROADCAST_TYPE_SETCOEVERPIC:
            TMagazineFinal tMagazineFinal2 = (TMagazineFinal) o;
            text = "我修改了杂志——" +tMagazineFinal2.getName()+"的封面！";
            break;
        }
        log.debug("广播消息内容为："+text);
        return text;
    }
    
    /**
     * <br>创建应用自定义规则
     * <br>直接将对象转化为json作为规则。
     * @param o
     * @return json
     */
    public static String createAnnotations(Object o) {
        Gson gson = new Gson();
        String annotations = "";
        if (o instanceof TUserContribute) {
            TUserContribute tUserContribute = (TUserContribute) o;
            annotations = gson.toJson(tUserContribute, new TypeToken<TUserContribute>(){}.getType());
        }
        //必须转化为数组的形式
        annotations = "["+annotations+"]";
        log.debug("创建自定义规则为："+annotations+ "annotations的长度为："+annotations.length());
        return annotations;
    }
	
	

}
