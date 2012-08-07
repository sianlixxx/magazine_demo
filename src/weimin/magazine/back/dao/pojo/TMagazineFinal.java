package weimin.magazine.back.dao.pojo;

import java.util.Date;

/**
 * TMagazineFinal entity. @author MyEclipse Persistence Tools
 */

public class TMagazineFinal implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long magazineId;//某期杂志的id,系统自动生成
	
	private Long departmentId;//编辑部id
	
	private Long createrUserId;//创建此期杂志的用户id
	
	private String name;//本期杂志名
	
	private Integer serialNumber;//本期杂志期刊号，系统自动生成
	
	private Date createdAt;//创建本期杂志的时间
	
	private String magazineUrl;//本期杂志的url，系统生成
	
	private Integer readCount;//本期杂志被阅读次数
	
	private Integer commentCount;//本期杂志被评论次数
	
	private Integer contributeCount;//本期杂志收到投稿数
	
	private Integer status;//本期杂志状态：0未创建；1已创建；2已选稿；3已编辑；4已发布
	
	private Date publishAt;//本期杂志发布时间
	
	private String description;//本期杂志描述
	
	private String coverPic;//封面图片地址url，图片微博地址（设置或更改图片，系统自动发布一条微博）
	
	private String tempContent;//编辑过程中编辑添加的临时内容

	// Constructors

	/** default constructor */
	public TMagazineFinal() {
	}

	/** full constructor */
	public TMagazineFinal(Long departmentId, Long createrUserId, String name,
			Integer serialNumber, Date createdAt, String magazineUrl,
			Integer readCount, Integer commentCount, Integer contributeCount,
			Integer status, Date publishAt, String description,
			String coverPic, String tempContent) {
		this.departmentId = departmentId;
		this.createrUserId = createrUserId;
		this.name = name;
		this.serialNumber = serialNumber;
		this.createdAt = createdAt;
		this.magazineUrl = magazineUrl;
		this.readCount = readCount;
		this.commentCount = commentCount;
		this.contributeCount = contributeCount;
		this.status = status;
		this.publishAt = publishAt;
		this.description = description;
		this.coverPic = coverPic;
		this.tempContent = tempContent;
	}

	// Property accessors

	public Long getMagazineId() {
		return this.magazineId;
	}

	public void setMagazineId(Long magazineId) {
		this.magazineId = magazineId;
	}

	public Long getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long getCreaterUserId() {
		return this.createrUserId;
	}

	public void setCreaterUserId(Long createrUserId) {
		this.createrUserId = createrUserId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getMagazineUrl() {
		return this.magazineUrl;
	}

	public void setMagazineUrl(String magazineUrl) {
		this.magazineUrl = magazineUrl;
	}

	public Integer getReadCount() {
		return this.readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	public Integer getCommentCount() {
		return this.commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getContributeCount() {
		return this.contributeCount;
	}

	public void setContributeCount(Integer contributeCount) {
		this.contributeCount = contributeCount;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getPublishAt() {
		return this.publishAt;
	}

	public void setPublishAt(Date publishAt) {
		this.publishAt = publishAt;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCoverPic() {
		return this.coverPic;
	}

	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}

	public String getTempContent() {
		return this.tempContent;
	}

	public void setTempContent(String tempContent) {
		this.tempContent = tempContent;
	}

}