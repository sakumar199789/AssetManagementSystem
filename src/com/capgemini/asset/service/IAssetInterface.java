package com.capgemini.asset.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.capgemini.asset.bean.AssetBean;
import com.capgemini.asset.bean.AssetRequestBean;
import com.capgemini.asset.bean.AssetRequestFormBean;
import com.capgemini.asset.bean.EmployeeBean;
import com.capgemini.asset.bean.UserBean;
import com.capgemini.asset.exception.AssetException;

public interface IAssetInterface {
	public void modifyAssetDetails(AssetBean as) throws AssetException;
	public ArrayList<AssetRequestBean> viewRequestDetails(int assetId) throws AssetException;
	public int addNewAsset(AssetBean as) throws AssetException;
	public ArrayList<AssetBean> viewAssets() throws AssetException;
	public int raiseRequest(AssetRequestFormBean assetRequest) throws AssetException;
	public ArrayList<AssetRequestBean> viewAssetRequestDetails(int assetId) throws AssetException;
	public String modifyStatus(String msg,AssetRequestBean ar) throws AssetException;
	public boolean dataAuthentication(UserBean a) throws AssetException;
	public boolean updatePassword(String username,String password);
	public boolean isValidUsername(String name);
	public boolean isValidPassword(String pass);
	public boolean isValidAssetName(ArrayList<AssetBean> al,String assetName);
	public boolean isValidQuantity(AssetRequestBean ar) throws AssetException;
	public ArrayList<AssetBean> getAssetDetails() throws AssetException;
	public ArrayList<AssetBean> nonAllocatedDetails() throws AssetException; 
	public ArrayList<AssetBean> allocatedDetails() throws 
	AssetException;
	public ArrayList<EmployeeBean> getEmployeeDetails(int mgr) throws AssetException;
}
