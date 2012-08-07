package weimin.magazine.back.dao.pojo;

/**
 * TTopMagazine entity. @author MyEclipse Persistence Tools
 */

public class TTopMagazine implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long topMagazineId;//排行id
	
	private Integer departmentType;//编辑部类型
	
	private String departmentDomain;//编辑部域名
	
	private Integer exRanking;//上次总排名
	
	private Integer exSubRanking;//上次分类排名
	
	private Integer ranking;//当前总排名
	
	private Integer subRanking;//当前分类排名

	// Constructors

	/** default constructor */
	public TTopMagazine() {
	}

	/** full constructor */
	public TTopMagazine(Integer departmentType, String departmentDomain,
			Integer exRanking, Integer exSubRanking, Integer ranking,
			Integer subRanking) {
		this.departmentType = departmentType;
		this.departmentDomain = departmentDomain;
		this.exRanking = exRanking;
		this.exSubRanking = exSubRanking;
		this.ranking = ranking;
		this.subRanking = subRanking;
	}

	// Property accessors

	public long getTopMagazineId() {
		return this.topMagazineId;
	}

	public void setTopMagazineId(long topMagazineId) {
		this.topMagazineId = topMagazineId;
	}

	public Integer getDepartmentType() {
		return this.departmentType;
	}

	public void setDepartmentType(Integer departmentType) {
		this.departmentType = departmentType;
	}

	public String getDepartmentDomain() {
		return this.departmentDomain;
	}

	public void setDepartmentDomain(String departmentDomain) {
		this.departmentDomain = departmentDomain;
	}

	public Integer getExRanking() {
		return this.exRanking;
	}

	public void setExRanking(Integer exRanking) {
		this.exRanking = exRanking;
	}

	public Integer getExSubRanking() {
		return this.exSubRanking;
	}

	public void setExSubRanking(Integer exSubRanking) {
		this.exSubRanking = exSubRanking;
	}

	public Integer getRanking() {
		return this.ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	public Integer getSubRanking() {
		return this.subRanking;
	}

	public void setSubRanking(Integer subRanking) {
		this.subRanking = subRanking;
	}

}