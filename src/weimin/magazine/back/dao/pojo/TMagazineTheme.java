/**
 * 
 */
package weimin.magazine.back.dao.pojo;

import java.io.Serializable;

/**
 * @author tianhao
 *
 */
public class TMagazineTheme implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long magazineThemeId ;//数据库唯一标识
	
	private int magazineThemeType;//类别，
	
	private String magazineThemeDes;//描述
	
	private String magazineThemeContent;//主题内容
	
	private int magazineThemeUsed;//被使用次数
	
	private long departmentId; //使用者id，仅编辑部使用
	
	public long getMagazineThemeId() {
		return magazineThemeId;
	}

	public void setMagazineThemeId(long magazineThemeId) {
		this.magazineThemeId = magazineThemeId;
	}

	public int getMagazineThemeType() {
		return magazineThemeType;
	}

	public void setMagazineThemeType(int magazineThemeType) {
		this.magazineThemeType = magazineThemeType;
	}

	public String getMagazineThemeDes() {
		return magazineThemeDes;
	}

	public void setMagazineThemeDes(String magazineThemeDes) {
		this.magazineThemeDes = magazineThemeDes;
	}

	public String getMagazineThemeContent() {
		return magazineThemeContent;
	}

	public void setMagazineThemeContent(String magazineThemeContent) {
		this.magazineThemeContent = magazineThemeContent;
	}

	public int getMagazineThemeUsed() {
		return magazineThemeUsed;
	}

	public void setMagazineThemeUsed(int magazineThemeUsed) {
		this.magazineThemeUsed = magazineThemeUsed;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

	
	
	





}
