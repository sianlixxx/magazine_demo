package weimin.magazine.front.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import weimin.magazine.back.dao.pojo.TAccessToken;
import weimin.magazine.back.dao.pojo.TDepartment;
import weimin.magazine.back.service.BaseService;
import weimin.magazine.back.service.OAuth2Service;
import weimin.magazine.back.service.ReadService;
import weimin.magazine.back.service.RecommendService;
import weimin.magazine.util.MethodRoute;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 根据不同的ajax请求，转到不同的jsp页面
 * 
 * @author lify
 *
 */
@WebServlet("/init/*")
public class InitAction extends Action {
	private static final long serialVersionUID = 5935791065991392099L;

    private static final Log log = LogFactory.getLog(InitAction.class);

    private static final String ACTION_PATH = "/WEB-INF/jsp/init";
    
    private OAuth2Service oauth2Service;
    private RecommendService recommendService;
    private ReadService readService;

    /**
     * 初始化该servlet，通过spring中获取service
     */
    @Override  
    public void init() throws ServletException {
        super.init();
        this.oauth2Service = (OAuth2Service) webapplicationcontext.getBean("oAuth2Service");
        this.recommendService = (RecommendService) webapplicationcontext.getBean("recommendService");
        this.readService = (ReadService) webapplicationcontext.getBean("readService");
    }  
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html; charset=utf-8");
//    	PrintWriter out = response.getWriter();
		String pathInfo = request.getPathInfo();
		if(StringUtils.isEmpty(pathInfo)) {
//			out.println("pathInfo null");
//			oauth2Service.openURL();
		} else {
			try {
			    request.setAttribute("baseService", baseService);
				request.setAttribute("oauth2Service", oauth2Service);
				request.setAttribute("recommendService", recommendService);
				request.setAttribute("readService", readService);
				MethodRoute.route(request, response, pathInfo.substring(1), "weimin.magazine.front.action.InitAction");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    
    }
  
    /**
     * 显示用户标签
     * 
     * @param request
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
    public void showlabel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String code = request.getParameter("code");
    	System.out.println("code: " + code);
    	
        this.oauth2Service = (OAuth2Service)request.getAttribute("oauth2Service");
        
    	TAccessToken accToken = oauth2Service.oAuthByCode(code);
    	//设置用户信息到session
    	request.getSession().setAttribute("accToken", accToken);
    	
    	Gson gson = new Gson();
    	//用户标签显示
    	request.setAttribute("showUserLabels", gson.toJson(oauth2Service.showUserLabels(accToken.getUserId())));
    	//所有标签显示
    	request.setAttribute("showLabels", gson.toJson(oauth2Service.showLabels()));
    	
	    request.getRequestDispatcher(getJspPath(ACTION_PATH, request.getPathInfo())).forward(request, response);
    }
    
    /**
     * 根据标签，推荐杂志
     * 
     * @param request
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
    public void getRecomMagazine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html; charset=utf-8");
    	oauth2Service = (OAuth2Service)request.getAttribute("oauth2Service");
    	recommendService = (RecommendService)request.getAttribute("recommendService");
    	
    	TAccessToken accToken = (TAccessToken)request.getSession().getAttribute("accToken");
    	Gson gson = new Gson();
    	Set<String> labelSet = gson.fromJson(request.getParameter("selectLabels"), new TypeToken<Set<String>>(){}.getType());
    	
    	oauth2Service.modifyUserLabels(accToken.getUserId(), labelSet);
    	Set<TDepartment> department = recommendService.recommend(accToken.getUserId());
    	Type type = new TypeToken<Set<TDepartment>>(){}.getType();
    	String json = gson.toJson(department, type);
    	
    	response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(json);
		} catch (IOException e) {
			log.error("根据标签，推荐杂志失败", e);
		} finally {
			out.flush();
			out.close();
		}
    	
//    	request.getRequestDispatcher(getJspPath(request.getPathInfo())).include(request, response);
    }
    
    /**
     * 预览杂志
     * 
     * @param request
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
    public void viewMagazine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        String magId = request.getParameter("magId");
        if(magId == null) {
            //TODO
        }
        
        this.baseService = (BaseService)request.getAttribute("baseService");
        
        //获取杂志地址
//        request.setAttribute("magurl", baseService.previewCurrent(Long.valueOf(magId)));
        request.setAttribute("magurl", "http://127.0.0.1:8080/magazine_demo/html/show/showMag.html");
             
    	request.getRequestDispatcher(getJspPath(ACTION_PATH, request.getPathInfo())).forward(request, response);
    }
    
    /**
     * 订阅杂志
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void subscibeMagazine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        String magId = request.getParameter("magId");
        if(magId == null) {
            //TODO
        }
        readService = (ReadService)request.getAttribute("readService");
        
        TAccessToken accToken = (TAccessToken)request.getSession().getAttribute("accToken");
        HashMap<String, String> subscibeRes = readService.batchSubscribe(accToken.getUserId(), 
                Arrays.asList(magId.trim()));
        
        String json = new Gson().toJson(subscibeRes,
                new TypeToken<HashMap<String, String>>() {}.getType());
        
        responseJson(response, json,  "订阅杂志失败");
    }
    
    /**
     * 批量订阅杂志
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void subscibeMagazineBat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        String magIds = request.getParameter("magIds");
        if(magIds == null) {
            //TODO
        }
        readService = (ReadService)request.getAttribute("readService");
        
        TAccessToken accToken = (TAccessToken)request.getSession().getAttribute("accToken");
        
        HashMap<String, String> subscibeRes = readService.batchSubscribe(accToken.getUserId(), 
                Arrays.asList(magIds.split(",")));
        
        String json = new Gson().toJson(subscibeRes, new TypeToken<HashMap<String, String>>(){}.getType());
        
        responseJson(response, json, "批量订阅杂志失败");
    }
    
    /**
     * 预览杂志
     * 
     * @param request
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
    public void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        String magId = request.getParameter("magId");
        if(magId == null) {
            //TODO
        }
        
        this.baseService = (BaseService)request.getAttribute("baseService");
        
        //获取杂志地址
//        request.setAttribute("magurl", baseService.previewCurrent(Long.valueOf(magId)));
        request.setAttribute("magurl", "http://127.0.0.1:8080/magazine_demo/html/show/showMag.html");
             
        request.getRequestDispatcher(getJspPath(ACTION_PATH, request.getPathInfo())).forward(request, response);
    }

	public BaseService getBaseService() {
        return baseService;
    }

    public void setBaseService(BaseService baseService) {
        this.baseService = baseService;
    }

    public OAuth2Service getOauth2Service() {
		return oauth2Service;
	}

	public void setOauth2Service(OAuth2Service oauth2Service) {
		this.oauth2Service = oauth2Service;
	}

	public RecommendService getRecommendService() {
		return recommendService;
	}

	public void setRecommendService(RecommendService recommendService) {
		this.recommendService = recommendService;
	}
	
}