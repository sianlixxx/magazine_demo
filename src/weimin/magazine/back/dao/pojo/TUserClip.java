package weimin.magazine.back.dao.pojo;


import java.util.Date;

/**
 * TUserClip entity. @author MyEclipse Persistence Tools
 */

public class TUserClip implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long userClipId;//剪报id
	
	private Long userId;//剪报用户id
	
	private Long departmentId;//编辑部id
	
	private Long magazineId;//杂志id
	
	private String content;//内容
	
	private String domain;//剪报地址url
	
	private Date clipTime;//剪报时间

	// Constructors

	/** default constructor */
	public TUserClip() {
	}

	/** full constructor */
	public TUserClip(Long userId, Long departmentId, Long magazineId,
			String content, String domain, Date clipTime) {
		this.userId = userId;
		this.departmentId = departmentId;
		this.magazineId = magazineId;
		this.content = content;
		this.domain = domain;
		this.clipTime = clipTime;
	}

	// Property accessors

	public Long getUserClipId() {
		return this.userClipId;
	}

	public void setUserClipId(Long userClipId) {
		this.userClipId = userClipId;
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

	public Long getMagazineId() {
		return this.magazineId;
	}

	public void setMagazineId(Long magazineId) {
		this.magazineId = magazineId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Date getClipTime() {
		return this.clipTime;
	}

	public void setClipTime(Date clipTime) {
		this.clipTime = clipTime;
	}

}