package com.capgemini.asset.bean;

public class AssetRequestFormBean {
	int empId,assetId,deptId;
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	String assetName,assetPurpose,empName;
	int Quan;
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public int getAssetId() {
		return assetId;
	}
	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}
	public String getAssetPurpose() {
		return assetPurpose;
	}
	public void setAssetPurpose(String assetPurpose) {
		this.assetPurpose = assetPurpose;
	}
	public int getQuan() {
		return Quan;
	}
	public void setQuan(int quan) {
		Quan = quan;
	}
	
	
	
}
