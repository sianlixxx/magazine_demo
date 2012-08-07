package weimin.magazine.back.dao.pojo;

/**
 * TUserConfig entity. @author MyEclipse Persistence Tools
 */

public class TUserConfig implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long userConfigId;//配置id
	
	private long userId;//用户id
	
	private Integer configType;//配置类别：0编辑部配置；1用户配置
	
	private Integer configValue;//配置值

	// Constructors

	/** default constructor */
	public TUserConfig() {
	}

	/** full constructor */
	public TUserConfig(long userId, Integer configType, Integer configValue) {
		this.setUserId(userId);
		this.configType = configType;
		this.configValue = configValue;
	}

	// Property accessors

	public long getUserConfigId() {
		return this.userConfigId;
	}

	public void setUserConfigId(long userConfigId) {
		this.userConfigId = userConfigId;
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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}