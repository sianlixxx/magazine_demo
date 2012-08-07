package weimin.magazine.front.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weimin.magazine.back.dao.pojo.TAccessToken;
import weimin.magazine.back.exception.SinaWeiboExpiredTokenException;
import weimin.magazine.back.service.BaseService;
import weimin.magazine.back.service.WeiboService;
import weimin.magazine.back.vo.Weibo;
import weimin.magazine.util.MethodRoute;
import weimin.magazine.util.SystemConfig;

/**
 * 微博页Action类
 * 
 * @author lify
 *
 */
@WebServlet("/weibo/*")
public class WeiboAction extends Action {
	private static final long serialVersionUID = 5935791065991392099L;

    private static final Log log = LogFactory.getLog(WeiboAction.class);

    private static final String ACTION_PATH = "/WEB-INF/jsp/weibo";
    
    /**
     * 初始化该servlet，通过spring中获取service
     */
    @Override  
    public void init() throws ServletException {
        super.init();
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
			    if(StringUtils.isNotEmpty(pathInfo.substring(1))){
			        MethodRoute.route(request, response, pathInfo.substring(1), "weimin.magazine.front.action.WeiboAction");
			    }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    
    }
  
    /**
     * 转向微博主页
     * 
     * @param request
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
    public void initpage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BaseService base = (BaseService)request.getAttribute("baseService");
        WeiboService weiboService = new WeiboService();
        //从session中获取用户信息
        TAccessToken accToken = (TAccessToken)request.getSession().getAttribute("accToken");
        log.debug("用户Id:" + accToken.getUserId());
        //用户信息 TUser
        request.setAttribute("user", base.showUserInfo(accToken.getUserId()));
        
        StatusWapper statusWapper = weiboService.showFriendsTimeline(accToken);
        List<Status> statuses = statusWapper.getStatuses().subList(0, 10);
        request.setAttribute("statuses", statuses);
        request.setAttribute("accToken", accToken);
        
	    request.getRequestDispatcher(getJspPath(ACTION_PATH, request.getPathInfo())).forward(request, response);
    }
    
    /**
     * 上传图片
     * 
     * @param request
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
    public void uploadImg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        String userId = request.getParameter("userId");
        String savePath = getPicPath();

        //路径不存在就创建
        File picFile = new File(savePath);
        if (!picFile.exists()) {
            picFile.mkdirs();
        }

        DiskFileItemFactory fac = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fac);
        upload.setHeaderEncoding("utf-8");

        //上传的文件
        List fileList = null;
        try {
            fileList = upload.parseRequest(request);
        } catch (FileUploadException ex) {
            return;
        }

        Iterator<FileItem> it = fileList.iterator();
        String name = "";
        String extName = "";
        while (it.hasNext()) {
            FileItem item = it.next();
            if (!item.isFormField()) {
                name = item.getName();
                if (StringUtils.isEmpty(name)) {
                    continue;
                }
                log.debug("name: " + name + ",size:" + item.getSize() + ",type: " + item.getContentType());
                
                //扩展名格式： 
                if (name.lastIndexOf(".") >= 0) {
                    extName = name.substring(name.lastIndexOf("."));
                }
                File saveFile = new File(savePath + File.separator + userId + extName);
                try {
                    item.write(saveFile);
                } catch (Exception e) {
                    log.error("保存微博图片时发生错误", e);
                }
            }
        }
//        responseJson(response, "true",  "发布微博失败");
    }
    
    /**
     * 发布微博
     * 
     * @param request
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
    public void publish(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        BaseService base = (BaseService)request.getAttribute("baseService");
        WeiboService weiboService = new WeiboService();
        
        //从session中获取用户信息
        TAccessToken accToken = (TAccessToken)request.getSession().getAttribute("accToken");
        log.debug("用户Id:" + accToken.getUserId());
        //用户信息 TUser
        request.setAttribute("user", base.showUserInfo(accToken.getUserId()));
        
        //发布微博
        Weibo weibo = new Weibo();
        weibo.setText(request.getParameter("content"));// 微博内容
        if(StringUtils.isNotEmpty(request.getParameter("extName"))) {
            weibo.setLocPicUrl(getPicPath() + File.separator + accToken.getUserId() + request.getParameter("extName"));//图片存放地址
        }
        weibo.settAccessToken(accToken);
        try {
            weiboService.publishWeibo(weibo);
        } catch (SinaWeiboExpiredTokenException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        responseJson(response, "true",  "发布微博失败");
    }
    
    /**
     * 删除微博
     * 
     * @param request
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
    public void deleteWeibo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String errMsg = "删除微博失败";
        response.setContentType("text/html; charset=utf-8");
        WeiboService weiboService = new WeiboService();
        
        //从session中获取用户信息
        TAccessToken accToken = (TAccessToken)request.getSession().getAttribute("accToken");
        log.debug("用户Id:" + accToken.getUserId());
        
        //删除微博
        try {
            weiboService.destoryWeibo(accToken, request.getParameter("mid"));
            responseJson(response, "true",  errMsg);
        } catch (Exception e) {
            log.error(errMsg, e);
            responseJson(response, "false",  errMsg);
        }
        
    }
    
    /**
     * 获取存放微博图片的临时文件夹
     * 
     * @return 文件夹路径
     */
    private String getPicPath() {
        return new StringBuilder().
            append(SystemConfig.getValue("sys.weibo.img.folder")).
            append(File.separator).
            append(DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMdd"))
            .toString();
    }
    
}