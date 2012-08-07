/**
 * 
 */
package weimin.magazine.back.dao.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tianhao
 *
 */
public class TDepartment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long departmentId;//编辑部，数据库唯一标识
	
	private Long createrUserId;//创建者用户id
	
	private String name;//编辑部名
	
	private Date createdAt;//创建时间
	
	private int releaseCount;//总发版次数
	
	private int subscribeCount;//订阅人数
	
	private int editerCount;//编辑人数，包括主编
	
	private int editerChiefCount;//主编人数

	private int totalReadCount;//被阅读总次数
	
	private int totalContributeCount;//总投稿数
	
	private int totalPublishCount;//累计发版稿件数
	
	private int departmentLevel;//编辑部级别
	
	private int departmentScore;//编辑部综合积分
	
	private int departmentType;//编辑部类别
	
	private String departmentDomain;//编辑部域名
		
	private String description;//编辑部描述
	
	private String logoUrl;//编辑部logo的url地址
	

	public Long getCreaterUserId() {
		return createrUserId;
	}

	public void setCreaterUserId(Long createrUserId) {
		this.createrUserId = createrUserId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getReleaseCount() {
		return releaseCount;
	}

	public void setReleaseCount(int releaseCount) {
		this.releaseCount = releaseCount;
	}

	public int getSubscribeCount() {
		return subscribeCount;
	}

	public void setSubscribeCount(int subscribeCount) {
		this.subscribeCount = subscribeCount;
	}

	public int getEditerCount() {
		return editerCount;
	}

	public void setEditerCount(int editerCount) {
		this.editerCount = editerCount;
	}

	public int getEditerChiefCount() {
		return editerChiefCount;
	}

	public void setEditerChiefCount(int editerChiefCount) {
		this.editerChiefCount = editerChiefCount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTotalReadCount() {
		return totalReadCount;
	}

	public void setTotalReadCount(int totalReadCount) {
		this.totalReadCount = totalReadCount;
	}

	public int getTotalContributeCount() {
		return totalContributeCount;
	}

	public void setTotalContributeCount(int totalContributeCount) {
		this.totalContributeCount = totalContributeCount;
	}

	public int getTotalPublishCount() {
		return totalPublishCount;
	}

	public void setTotalPublishCount(int totalPublishCount) {
		this.totalPublishCount = totalPublishCount;
	}

	public int getDepartmentLevel() {
		return departmentLevel;
	}

	public void setDepartmentLevel(int departmentLevel) {
		this.departmentLevel = departmentLevel;
	}

	public int getDepartmentScore() {
		return departmentScore;
	}

	public void setDepartmentScore(int departmentScore) {
		this.departmentScore = departmentScore;
	}

	public int getDepartmentType() {
		return departmentType;
	}

	public void setDepartmentType(int departmentType) {
		this.departmentType = departmentType;
	}

	public String getDepartmentDomain() {
		return departmentDomain;
	}

	public void setDepartmentDomain(String departmentDomain) {
		this.departmentDomain = departmentDomain;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	
	
	

}
