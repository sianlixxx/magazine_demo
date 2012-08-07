package weimin.magazine.back.dao.pojo;


import java.util.Date;

/**
 * TDepartmentEditor entity. @author MyEclipse Persistence Tools
 */

public class TDepartmentEditor implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long editorId;//编辑id
	
	private Long userId;//用户id
	
	private Long departmentId;//编辑部id
	
	private Boolean editerChief;//是否主编
	
	private Date editerAt;//成为编辑的时间

	// Constructors

	/** default constructor */
	public TDepartmentEditor() {
	}

	/** full constructor */
	public TDepartmentEditor(Long userId, Long departmentId,
			Boolean editerChief, Date editerAt) {
		this.userId = userId;
		this.departmentId = departmentId;
		this.editerChief = editerChief;
		this.editerAt = editerAt;
	}

	// Property accessors

	public Long getEditorId() {
		return this.editorId;
	}

	public void setEditorId(Long editorId) {
		this.editorId = editorId;
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

	public Boolean getEditerChief() {
		return this.editerChief;
	}

	public void setEditerChief(Boolean editerChief) {
		this.editerChief = editerChief;
	}

	public Date getEditerAt() {
		return this.editerAt;
	}

	public void setEditerAt(Date editerAt) {
		this.editerAt = editerAt;
	}

}