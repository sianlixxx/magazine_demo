package weimin.magazine.back.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weimin.magazine.back.cache.JedisQueue;
import weimin.magazine.back.cache.message.MSGWeibo;
import weimin.magazine.back.dao.pojo.TDepartment;
import weimin.magazine.back.dao.pojo.TDepartmentEditor;
import weimin.magazine.back.dao.pojo.TMagazineFinal;
import weimin.magazine.back.dao.pojo.TMagazineModel;
import weimin.magazine.back.dao.pojo.TUser;
import weimin.magazine.back.dao.pojo.TUserContribute;
import weimin.magazine.back.exception.PublishMagzineException;
import weimin.magazine.back.vo.Department;
import weimin.magazine.back.vo.Weibo;
import weimin.magazine.util.Constants;
import weimin.magazine.util.SpringContextTool;
import weimin.magazine.util.Tools;

/**
 * 编辑服务：
 * 1.创建编辑部；
 * 2.查询某编辑部所有编辑；
 * 3.增加，删除编辑，更改编辑权限；
 * 4.修改编辑部设置和信息；
 * 
 * @author tianhao
 *
 */
public class EditService extends BaseService{
	
    private static final Log log = LogFactory.getLog(EditService.class);
    
    private JedisQueue<MSGWeibo> weiboQueue = JedisQueue.newQ(MSGWeibo.class);
    
	/**
	 * <br> 创建编辑部:
	 * 1.判断用户是否有资格创建；
	 * 2.若有则创建编辑部，并更新用户权限；
	 * 3.为此编辑部增加一个主编；
	 * 4.发布宣传微博;
	 * 5.若创建新的编辑部则返回新建立的编辑部；否则返回null
	 * @param TAccessToken
	 * @return TDepartment
	 */
	public TDepartment  createDepartment(long userId){
		TUser tUser = (TUser) tUserDaoImpl.queryById(userId);
		if (!tUser.isCreateEnabled()){
			//1.创建新的编辑部，并获得系统id
			TDepartment tDepartment = new TDepartment();
			//设置编辑部创建者的系统id
			tDepartment.setCreaterUserId(userId);
			//设置编辑部的创建时间
			tDepartment.setCreatedAt(new Date());
			long departmentId = (Long) tDepartmentDaoImpl.insert(tDepartment);
			log.info("用户："+userId +"    新建一个编辑部！编辑部id为："+departmentId);
			//2.修改用户创建编辑部的权限：0为未创建（false）；1为已创建（true）
			tUser.setCreateEnabled(true);
			tUserDaoImpl.update(tUser);
			//3.为此编辑部增加一个主编
			TDepartmentEditor tDepartmentEdito = new TDepartmentEditor();
			tDepartmentEdito.setDepartmentId(departmentId);
			tDepartmentEdito.setUserId(userId);
			tDepartmentEdito.setEditerChief(true);
			tDepartmentEdito.setEditerAt(Tools.getDate());
			long edtorId = (Long) tDepartmentEditorDaoImpl.insert(tDepartmentEdito);
			log.info("编辑部："+departmentId+ "    新建增加一个主编，编辑id为："+edtorId);
			//4.发布宣传微博;
			this.broadcast4createDepartment(tDepartment);
			return tDepartment;
		}else{
		    //TODO定义异常:用户不具有创建编辑部权限！
		}
		return null;
	}
	
    /**
	 * <br>新建一本杂志</br>
	 * <br>步骤：
	 * 1.db中插入一本杂志，获得杂志id；
	 * 2.发布一条微博用作宣传；
	 * 3.返回已生成的杂志
	 * @param tMagazineFinal
	 * @return tMagazineFinal
	 */
    public TMagazineFinal createMagazine(TMagazineFinal tMagazineFinal) {
        // 1.db中插入一本杂志，获得杂志id；
        tMagazineFinal = tMagazineFinalDaoImpl.createMagazine(tMagazineFinal);
        // 2.发布一条微博用作宣传
        this.broadcast4CreateMagazine(tMagazineFinal);
        return tMagazineFinal;
    }
    
    /**
     * <br>发布杂志
     * @param contributes
     *  Map<Integer ,Map<Integer ,contributeId>>
     *  Map<pageNum ,Map<localNum ,contributeId>>
     * @param models
     *   Map<Integer ,Map<Integer ,modelId>>
     *   Map<pageNum ,Map<localNum ,modelId>>
     * @return
     * @throws PublishMagzineException 
     * @throws Exception 
     */
    public boolean publishMagazine(Map<Integer ,Map<Integer ,Long>> contributes,
            Map<Integer ,Map<Integer ,Long>>  models ) throws PublishMagzineException {
       
        if( contributes.size() != contributes.size()){
            log.error("总页数不匹配！");
            throw new PublishMagzineException();
        }
        // TODO 构造头
        // 构造内容
        int pageNum = contributes.size();
        for(int i = 1 ;i<= pageNum ;i++){
            if(contributes.get(i).size() != models.get(i).size() ){
                log.error("页模块数不匹配！");
                throw new PublishMagzineException(); 
            }
            //构造页面主体
            this.addPage(contributes.get(i).get(0),models.get(i).get(0));
           
            //构造模块内容
            int pageDivNum = contributes.get(i).size() - 1;
            for(int j = 1 ;i<= pageDivNum ;j++){
                this.addDiv(contributes.get(i).get(j),models.get(i).get(j));
            }
        }
        // TODO 构造尾
        
        // TODO 生成html 文档
        return false;
    }
    
   

private void addDiv(Long long1, Long long2) {
        // TODO Auto-generated method stub
        
    }

private void addPage(Long long1, Long long2) {
        // TODO Auto-generated method stub
        
    }

/**
	 * <br>用户进行一次投稿</br>
	 * <br>步骤：
	 * 1.记录投稿时间，db中新建一条投稿记录；
	 * 2.用户发布一条微博，进行宣传；
	 * 3.更新投稿记录
	 * 4.投稿列表增加一个对象
	 * 5.返回投稿记录
	 * 
	 * @param tUserContribute
	 * @return
	 */
    public TUserContribute contribute(TUserContribute tUserContribute) {
        // 1.记录投稿时间，db中新建一条投稿记录；
        tUserContribute.setCreatedAt(Tools.getDate());
        long contributeId = (Long) tUserContributeDaoImpl.insert(tUserContribute);
        tUserContribute.setContributeId(contributeId);
        // 2.用户发布一条微博，进行宣传；
        this.broadcast4Contribute(tUserContribute);
        // 3.更新投稿记录
        tUserContributeDaoImpl.update(tUserContribute);
        // 4.投稿列表增加一个对象
        redis.addEditor4Contribute(tUserContribute.getUserId(),tUserContribute.getDepartmentId());
        return tUserContribute;
    } 
    

    /**
     * <br> 查询某个编辑部所有编辑列表
     * @param departmentId
     * @return
     */
    public List<TDepartmentEditor> showEditors(Long departmentId){
         List<TDepartmentEditor> editors =  new  ArrayList<TDepartmentEditor>();
         editors = tDepartmentEditorDaoImpl.queryByDepartmentId(departmentId);
         return editors;
    }
    
    /**
     * <br>取出系统推荐主题列表，列表中的主题按照优先级排列，
     * @return List<String> 主题列表
     */
    public List<String> showTheme(){
        log.debug("获取系统推荐标签！");
        ArrayList<String> themes = (ArrayList<String>) redis.querySuggestionsThemes();
        //如果缓存丢失则取db
        if(themes.size()==0){
            log.debug("缓存丢失系统推荐标签！");
            BackService backService = (BackService) SpringContextTool.getApplicationContext().getBean("backService");
            themes = backService.replaceThemes();
        }
        return themes;
    
    }
    
    /**
     *  <br>显示稿件箱稿件
     *  <br>步骤：
     *  1.从db中收集所有稿件；
     * @param userId
     * @return
     */
    public List<TUserContribute> showContribute(long userId){
        //2.从db中收集所有新稿件；
        List<TUserContribute>  tUserContributes = tUserContributeDaoImpl.queryNewContruibuteByUserId(userId);
        return tUserContributes;
    }
	
    /**
     *  <br>用户投稿列表
     *  <br>步骤：
     *  1.检索缓存中投稿对象列表；
     *  2.构造vo对象
     *  3.返回vo对象集合
     * @param userId
     * @return
     */
    public List<Department> showDepartment4Contribute(long userId){
        List<Department> departments = new ArrayList<Department>();
        //1.获得用户投稿对象列表
        Set<Long> set = redis.queryEditor4contribute(userId);
        //2.
        Iterator<Long> iterator =  set.iterator();
        while (iterator.hasNext()){
            long departmentId = iterator.next();
            //检索杂志社信息
            TDepartment tDepartment = this.find4TDepartment(departmentId);
            long createrId = tDepartment.getCreaterUserId();
            TUser creater = this.find4TUser(createrId);
            //2.构造vo对象
            Department department = new Department();
            department.setCreater(creater);
            department.settDepartment(tDepartment);
            //3.加入列表
            departments.add(department);
        }
        return departments;
    }
    
	/**
	 * <br>设置本地图片为杂志封面</br>
	 * @param tMagazineFinal
	 * @param locUrl
	 * @return
	 */
	public TMagazineFinal modifyCoeverPic(TMagazineFinal tMagazineFinal ){
	   this.broadcast4SetCoeverPic(tMagazineFinal);
	   tMagazineFinalDaoImpl.update(tMagazineFinal);
       return tMagazineFinal;
	}
	
	
    
    /**
	 * <br>广播微博——创建杂志社
	 * @param tDepartment
	 */
    protected void broadcast4createDepartment(TDepartment tDepartment) {
        // 1.生成宣传微博的内容
        String text = Tools.createText(tDepartment,Constants.BROADCAST_TYPE_CREATEDEPARTMENT);
        // 2.发布一条微博用作宣传
        long createrId = tDepartment.getCreaterUserId();
        //
        Weibo weibo = new Weibo();
        weibo.setText(text);
        weibo.settAccessToken(this.find4TAccessToken(createrId));
        MSGWeibo msgWeibo = new MSGWeibo(weibo);
        //将微博加入发送队列
        weiboQueue.push(msgWeibo);
    }

    /**
	 * <br>广播微博——创建杂志
	 * @param tMagazineFinal
	 * @return
	 */
    protected void broadcast4CreateMagazine(TMagazineFinal tMagazineFinal) {
        // 1.生成宣传微博的内容
        String text = Tools.createText(tMagazineFinal,Constants.BROADCAST_TYPE_CREATEMAGAZINE);
        // 2.发布一条微博用作宣传
        long createrId = tMagazineFinal.getCreaterUserId();
        //
        Weibo weibo = new Weibo();
        weibo.setText(text);
        weibo.settAccessToken(this.find4TAccessToken(createrId));
        MSGWeibo msgWeibo = new MSGWeibo(weibo);
        //将微博加入发送队列
        weiboQueue.push(msgWeibo);
    }
    
    /**
     * <br>广播微博——设置杂志封面
     * @param tAccessToken
     * @param tMagazineFinal
     * @param locUrl
     * @return
     */
    protected void broadcast4SetCoeverPic(TMagazineFinal tMagazineFinal) {
        //1.生成宣传微博的内容  
        String text = Tools.createText(tMagazineFinal,Constants.BROADCAST_TYPE_SETCOEVERPIC);
        //2.发布一条微博用作宣传
        long createrId = tMagazineFinal.getCreaterUserId();
        //
        Weibo weibo = new Weibo();
        weibo.setText(text);
        weibo.settAccessToken(this.find4TAccessToken(createrId));
        MSGWeibo msgWeibo = new MSGWeibo(weibo);
        //将微博加入发送队列
        weiboQueue.push(msgWeibo);
    }
    
    /**
     * <br>用户投稿，用户发布一条微博，进行宣传</br>
     *  <br>步骤：
     *  1.生成宣传微博的内容
     *  2.发布一条微博用作宣传
     * @param tAccessToken
     * @param tUserContribute
     * @return
     */
    protected void broadcast4Contribute(
            TUserContribute tUserContribute) {
        // 1.生成宣传微博的内容 
        String text = Tools.createText(tUserContribute,
                Constants.BROADCAST_TYPE_CONTRIBUTE);
        // 2.发布一条微博用作宣传
        long createrId = tUserContribute.getUserId();
        //
        Weibo weibo = new Weibo();
        weibo.setText(text);
        weibo.settAccessToken(this.find4TAccessToken(createrId));
        MSGWeibo msgWeibo = new MSGWeibo(weibo);
        //将微博加入发送队列
        weiboQueue.push(msgWeibo);
    }
    
	
}
