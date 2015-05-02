package com.amp.helper;

public class DepartmentsData 
{
	public String departmentName;
	public String deptURL;
	String colorCode;
	
    public DepartmentsData()
    {
        super();
    }
   
    public String getColorCodes() {
		return colorCode;
	}

	public void setColorCodes(String colorCodes) {
		this.colorCode = colorCodes;
	}

	public DepartmentsData(String deptName, String dURL,String dColorCode) 
    {
        super();
        this.departmentName = deptName;
        this.deptURL =dURL;
      this.colorCode = dColorCode;
    }

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDeptURL() {
		return deptURL;
	}

	public void setDeptURL(String deptURL) {
		this.deptURL = deptURL;
	}
}
