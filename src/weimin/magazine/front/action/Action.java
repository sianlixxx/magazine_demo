package weimin.magazine.front.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import weimin.magazine.back.service.BaseService;
import weimin.magazine.back.service.ReadService;

/**
 * Servlet implementation class Action
 */
public class Action extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(InitAction.class);
	 
	//获取spring的WebApplicationContext对象，从该对象中取得注册的bean
	protected WebApplicationContext webapplicationcontext;
	protected BaseService baseService;
	protected ReadService readService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Action() {
        super();
    }
    
    /**
     * 初始化该servlet，通过spring中获取service
     */
    @Override  
    public void init() throws ServletException {
    	webapplicationcontext = WebApplicationContextUtils.
    			getRequiredWebApplicationContext(getServletContext());
    	this.baseService = (BaseService) webapplicationcontext.getBean("baseService");
    	this.readService = (ReadService) webapplicationcontext.getBean("readService");
    }
    
    /**
     * 写json给前台
     * 
     * @param response HttpServletResponse对象
     * @param json 返回的json串
     * @param out PrintWriter对象
     * @param errorMsg 发生异常时的message
     */
    protected void responseJson(HttpServletResponse response, String json, String errorMsg) {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(json);
        } catch (IOException e) {
            log.error(errorMsg, e);
        } finally {
            out.flush();
            out.close();
        }
    }
    
    /**
     * 返回jsp路径
     * 
     * @param actionPath jsp文件存放目录
     * @param jspName jsp文件名称
     * @return jsp路径字符串
     */
    protected String getJspPath(String actionPath, String jspName) {
        return new StringBuilder().append(actionPath).
              append(jspName).append(".jsp").toString();
    }

}
