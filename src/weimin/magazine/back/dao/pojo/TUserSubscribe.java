package weimin.magazine.back.dao.pojo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * TUserSubscribe entity. @author MyEclipse Persistence Tools
 */

public class TUserSubscribe implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long subscribeId;//订阅id
	
	private Long userId;//用户id
	
	private Long departmentId;//编辑部id
	
	private Date createdAt;//订阅时间
	
	private Date cancelAt;//取消订阅时间
	
	private Integer status;//状态0未订阅；1为已订阅

	// Constructors

	/** default constructor */
	public TUserSubscribe() {
	}

	/** full constructor */
	public TUserSubscribe(Long userId, Long departmentId, Timestamp createdAt,
			Timestamp cancelAt, Integer status) {
		this.userId = userId;
		this.departmentId = departmentId;
		this.createdAt = createdAt;
		this.cancelAt = cancelAt;
		this.status = status;
	}

	// Property accessors

	public Long getSubscribeId() {
		return this.subscribeId;
	}

	public void setSubscribeId(Long subscribeId) {
		this.subscribeId = subscribeId;
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

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getCancelAt() {
		return this.cancelAt;
	}

	public void setCancelAt(Date cancelAt) {
		this.cancelAt = cancelAt;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}