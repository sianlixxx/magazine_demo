package weimin.magazine.back.dao.pojo;


import java.util.Date;

/**
 * TDepartmentComment entity. @author MyEclipse Persistence Tools
 */

public class TDepartmentComment implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long commentId;//评论id
	
	private Long departmentId;//评论对象--编辑部id
	
	private Long userId;//发表评论的用户id
	
	private Date createdAt;//发表评论的时间
	
	private String content;//评论内容
	
	private Integer level;//
	
	private Integer score;//

	// Constructors

	/** default constructor */
	public TDepartmentComment() {
	}

	/** full constructor */
	public TDepartmentComment(Long departmentId, Long userId,
			Date createdAt, String content, Integer level, Integer score) {
		this.departmentId = departmentId;
		this.userId = userId;
		this.createdAt = createdAt;
		this.content = content;
		this.level = level;
		this.score = score;
	}

	// Property accessors

	public Long getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Long getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}