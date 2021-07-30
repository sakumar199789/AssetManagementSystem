package com.capgemini.asset.bean;

public class UserBean {
	String userNameId,password,userType;
	int mgr;
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}


	public String getPassword() {
		return password;
	}

	

	public int getMgr() {
		return mgr;
	}

	public void setMgr(int mgr) {
		this.mgr = mgr;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserNameId() {
		return userNameId;
	}

	public void setUserNameId(String userNameId) {
		this.userNameId = userNameId;
	}
	
}
