package com.capgemini.asset.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.capgemini.asset.bean.AssetBean;
import com.capgemini.asset.bean.AssetRequestBean;
import com.capgemini.asset.bean.AssetRequestFormBean;
import com.capgemini.asset.bean.EmployeeBean;
import com.capgemini.asset.bean.UserBean;
import com.capgemini.asset.exception.AssetException;

public interface IAssetdao {
	public boolean dataAuthentication(UserBean a) throws AssetException;
	public boolean updatePassword(String username,String password);
	public int addNewAsset(AssetBean as) throws AssetException;
	public void modifyAssetDetails(AssetBean as) throws AssetException;
	public ArrayList<AssetRequestBean> viewRequestDetails(int requestId) throws AssetException;
	public ArrayList<AssetBean> viewAssets() throws AssetException;
	public int raiseRequest(AssetRequestFormBean assetRequest)
			throws AssetException;
	public String modifyStatus(String msg,AssetRequestBean ar) throws AssetException;
	public ArrayList<AssetRequestBean> viewAssetRequestDetails(int assetId) throws AssetException;
	public boolean isValidQuantity(AssetRequestBean ar) throws AssetException;
	public ArrayList<AssetBean> getAssetDetails() throws AssetException;
	public ArrayList<AssetBean> nonAllocatedDetails() throws AssetException; 
	public ArrayList<AssetBean> allocatedDetails() throws AssetException;
	public ArrayList<EmployeeBean> getEmployeeDetails(int mgr) throws AssetException;
}
