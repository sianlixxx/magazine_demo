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
public class TLabel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long labelId; //数据库唯一标识
	
	private String name; //标签名
	
	private Date createdAt ; //创建时间
	
	private int frequency ;//计数器 被使用次数
	
	private long userId; //使用人id
	
	private boolean domainType; //标签类别 0用户标签，1杂志标签
	
	

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

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	

	public boolean getDomainType() {
		return domainType;
	}

	public void setDomainType(boolean domainType) {
		this.domainType = domainType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Long getLabelId() {
		return labelId;
	}

	public void setLabelId(Long labelId) {
		this.labelId = labelId;
	}

	
	
	
	
	
	

	
	
	

}
