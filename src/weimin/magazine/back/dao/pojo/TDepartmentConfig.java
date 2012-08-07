package weimin.magazine.back.dao.pojo;

/**
 * TDepartmentConfig entity. @author MyEclipse Persistence Tools
 */

public class TDepartmentConfig implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long departmentConfigId;//配置id
	
	private  long departmentId;//编辑部id
	
	private Integer configType;//配置类型
	
	private Integer configValue;//配置值

	// Constructors

	/** default constructor */
	public TDepartmentConfig() {
	}

	/** full constructor */
	public TDepartmentConfig(Integer departmentId, Integer configType,
			Integer configValue) {
		this.departmentId = departmentId;
		this.configType = configType;
		this.configValue = configValue;
	}

	// Property accessors

	public long getDepartmentConfigId() {
		return this.departmentConfigId;
	}

	public void setDepartmentConfigId(long departmentConfigId) {
		this.departmentConfigId = departmentConfigId;
	}

	public long getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getConfigType() {
		return this.configType;
	}

	public void setConfigType(Integer configType) {
		this.configType = configType;
	}

	public Integer getConfigValue() {
		return this.configValue;
	}

	public void setConfigValue(Integer configValue) {
		this.configValue = configValue;
	}

}