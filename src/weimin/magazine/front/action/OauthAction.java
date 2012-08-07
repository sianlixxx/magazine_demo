package weimin.magazine.front.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import weimin.magazine.back.service.OAuth2Service;
import weimin.magazine.util.MethodRoute;

/**
 * 根据不同的ajax请求，转到不同的jsp页面
 * 
 * @author lify
 *
 */
@WebServlet("/oauth/*")
public class OauthAction extends HttpServlet {
	private static final long serialVersionUID = 5935791065991392099L;

    private static final Log log = LogFactory.getLog(OauthAction.class);

    private static final String ACTION_PATH = "/jsp/test";
    
    private OAuth2Service oauth2Service;

    /**
     * 初始化该servlet，通过spring中获取service
     */
    @Override  
    public void init() throws ServletException {
        WebApplicationContext webapplicationcontext = WebApplicationContextUtils
                .getRequiredWebApplicationContext(getServletContext());
        this.oauth2Service = (OAuth2Service) webapplicationcontext.getBean("oAuth2Service");
    }  
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
//		PrintWriter out = response.getWriter();
		if(pathInfo == null || "".equals(pathInfo)) {
//			out.println("pathInfo null");
			response.sendRedirect(oauth2Service.openURL());
		} else {
			try {
				MethodRoute.route(request, response, pathInfo.substring(1), "weimin.magazine.front.action.OauthAction");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    
    }
  
    /**
     * 根据不同的请求，导向不同的jsp页面
     * 
     * @param request
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
    public void getoptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.getRequestDispatcher(getJspPath(request.getPathInfo())).include(request, response);
    }
  
    /**
     * 返回一个名字测试
     * 
     * @param request
     * @param response
     */
    public void getName(HttpServletRequest request, HttpServletResponse response) {
	    String res = "123请求测试";
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(res);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
    }
  
    /**
     * 返回jsp路径
     * 
     * @param jspName jsp文件名称
     * @return jsp路径字符串
     */
    private String getJspPath(String jspName) {
	    return new StringBuilder().append(ACTION_PATH).
			  append(jspName).append(".jsp").toString();
    }

	public OAuth2Service getOauth2Service() {
		return oauth2Service;
	}

	public void setOauth2Service(OAuth2Service oauth2Service) {
		this.oauth2Service = oauth2Service;
	}
    
}