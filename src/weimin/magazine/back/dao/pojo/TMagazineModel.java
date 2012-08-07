package weimin.magazine.back.dao.pojo;


import java.util.Date;

/**
 * TMagazineModel entity. @author MyEclipse Persistence Tools
 */

public class TMagazineModel implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long modelId;//模板id
	
	private Integer modelType;//模板内型： 0页面模板；1微博模板
	
	private String modelContent;//模板内容，div格式信息
	
	private Date createAt;//模板创建时间
	
	private Long cteateBy;//模板创建者id

	// Constructors

	/** default constructor */
	public TMagazineModel() {
	}

	/** full constructor */
	public TMagazineModel(Integer modelType, String modelContent,
			Date createAt, Long cteateBy) {
		this.modelType = modelType;
		this.modelContent = modelContent;
		this.createAt = createAt;
		this.cteateBy = cteateBy;
	}

	// Property accessors

	public long getModelId() {
		return this.modelId;
	}

	public void setModelId(long modelId) {
		this.modelId = modelId;
	}

	public Integer getModelType() {
		return this.modelType;
	}

	public void setModelType(Integer modelType) {
		this.modelType = modelType;
	}

	public String getModelContent() {
		return this.modelContent;
	}

	public void setModelContent(String modelContent) {
		this.modelContent = modelContent;
	}

	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Long getCteateBy() {
		return this.cteateBy;
	}

	public void setCteateBy(Long cteateBy) {
		this.cteateBy = cteateBy;
	}

}