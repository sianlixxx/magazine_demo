package weimin.magazine.front.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import weimin.magazine.back.dao.pojo.TAccessToken;
import weimin.magazine.back.service.BaseService;
import weimin.magazine.back.service.ReadService;
import weimin.magazine.back.service.RecommendService;
import weimin.magazine.util.MethodRoute;

/**
 * 首页Action类
 * 
 * @author lify
 *
 */
@WebServlet("/home/*")
public class HomeAction extends Action {
	private static final long serialVersionUID = 5935791065991392099L;

    private static final Log log = LogFactory.getLog(HomeAction.class);

    private static final String ACTION_PATH = "/WEB-INF/jsp/home";
    
    private RecommendService recommendService;
    private ReadService readService;

    /**
     * 初始化该servlet，通过spring中获取service
     */
    @Override  
    public void init() throws ServletException {
        super.init();
        this.recommendService = (RecommendService) webapplicationcontext.getBean("recommendService");
        this.readService = (ReadService) webapplicationcontext.getBean("readService");
    }  
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html; charset=utf-8");
		String pathInfo = request.getPathInfo();
		if(StringUtils.isEmpty(pathInfo)) {
//			out.println("pathInfo null");
//			oauth2Service.openURL();
		} else {
			try {
			    request.setAttribute("baseService", baseService);
			    request.setAttribute("readService", readService);
				request.setAttribute("recommendService", recommendService);
				request.setAttribute("readService", readService);
				MethodRoute.route(request, response, pathInfo.substring(1), "weimin.magazine.front.action.HomeAction");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    
    }
  
    /**
     * 转向首页
     * 
     * @param request
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
    public void initpage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BaseService base = (BaseService)request.getAttribute("baseService");
        ReadService read = (ReadService)request.getAttribute("readService");
        //从session中获取用户信息
        TAccessToken accToken = (TAccessToken)request.getSession().getAttribute("accToken");
        log.debug("用户Id:" + accToken.getUserId());
        
        //用户信息 TUser
        request.setAttribute("user", base.showUserInfo(accToken.getUserId()));
        //订阅和推荐的杂志 Set<subscribeRelations>
        request.setAttribute("subscribeRelations", read.showSubAndRec(accToken.getUserId()));
        //杂志排行榜 Map<Integer, TDepartment>
        request.setAttribute("showTop", base.showTop());
        
	    request.getRequestDispatcher(getJspPath(ACTION_PATH, request.getPathInfo())).forward(request, response);
    }
    
    /**
     * 取消订阅
     * 
     * @param request
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
    public void cancelSubscribe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReadService readService = (ReadService)request.getAttribute("readService");
        //从session中获取用户信息
        TAccessToken accToken = (TAccessToken)request.getSession().getAttribute("accToken");
        log.debug("用户Id:" + accToken.getUserId() + "取消订阅");
        //用户信息 TUser
        if (readService.cancelSubscribe(accToken.getUserId(), 
                Long.valueOf(request.getParameter("departmentId")))) {
            responseJson(response, "true",  "取消订阅失败");
        } else {
            responseJson(response, "false",  "取消订阅失败");
        }
    }
    
	public RecommendService getRecommendService() {
		return recommendService;
	}

	public void setRecommendService(RecommendService recommendService) {
		this.recommendService = recommendService;
	}

    public ReadService getReadService() {
        return readService;
    }

    public void setReadService(ReadService readService) {
        this.readService = readService;
    }
	
}