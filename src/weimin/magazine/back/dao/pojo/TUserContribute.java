package weimin.magazine.back.dao.pojo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * TUserContribute entity. @author MyEclipse Persistence Tools
 */

public class TUserContribute implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long contributeId;//稿件id
	
	private Long userId;//投稿者-用户id
	
	private Long departmentId;//投稿对象-编辑部id
	
	private long weiboId;//稿件微博id
	
	private Date createdAt;//投稿时间
	
	private int status;//状态 0新稿件1已录用2已删除
	
	private Date selectedAt;//被编辑选取录用的时间
	
	private int serialNumber;//被选中的期刊号
	
	private String content;//稿件内容
	
	private int contentType;//内容类型 TODO ?
	
	private String contentPic;//微博图片地址
	
	private int contributeComment;//评论数
	
	private int contributeForward;//转发数

	// Constructors

	/** default constructor */
	public TUserContribute() {
	}

	/** full constructor */
	public TUserContribute(Long userId, Long departmentId, int weiboId,
			Timestamp createdAt, int status, Timestamp selectedAt,
			int serialNumber, String content, int contentType,
			String contentPic, int contributeComment,
			int contributeForward) {
		this.userId = userId;
		this.departmentId = departmentId;
		this.weiboId = weiboId;
		this.createdAt = createdAt;
		this.status = status;
		this.selectedAt = selectedAt;
		this.serialNumber = serialNumber;
		this.content = content;
		this.contentType = contentType;
		this.contentPic = contentPic;
		this.contributeComment = contributeComment;
		this.contributeForward = contributeForward;
	}

	// Property accessors

	public Long getContributeId() {
		return this.contributeId;
	}

	public void setContributeId(Long contributeId) {
		this.contributeId = contributeId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public long getWeiboId() {
		return this.weiboId;
	}

	public void setWeiboId(long weiboId) {
		this.weiboId = weiboId;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getSelectedAt() {
		return this.selectedAt;
	}

	public void setSelectedAt(Timestamp selectedAt) {
		this.selectedAt = selectedAt;
	}

	public int getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getContentType() {
		return this.contentType;
	}

	public void setContentType(int contentType) {
		this.contentType = contentType;
	}

	public String getContentPic() {
		return this.contentPic;
	}

	public void setContentPic(String contentPic) {
		this.contentPic = contentPic;
	}

	public int getContributeComment() {
		return this.contributeComment;
	}

	public void setContributeComment(int contributeComment) {
		this.contributeComment = contributeComment;
	}

	public int getContributeForward() {
		return this.contributeForward;
	}

	public void setContributeForward(int contributeForward) {
		this.contributeForward = contributeForward;
	}

}