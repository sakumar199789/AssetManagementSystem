package com.capgemini.asset.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.asset.bean.AssetBean;
import com.capgemini.asset.bean.AssetRequestBean;
import com.capgemini.asset.bean.AssetRequestFormBean;
import com.capgemini.asset.bean.EmployeeBean;
import com.capgemini.asset.bean.UserBean;
import com.capgemini.asset.dao.*;
import com.capgemini.asset.exception.AssetException;


public class AssetImpl implements IAssetInterface{

	
	@Override
	public boolean isValidUsername(String name) {
	Pattern namePattern=Pattern.compile("^[A-Z][A-Za-z]{2,}$");
	Pattern idPattern=Pattern.compile("^[1-9][0-9]{5,5}$");
	Matcher nameMatcher=namePattern.matcher(name);
	Matcher idMatcher=idPattern.matcher(name);
	if(nameMatcher.matches()||idMatcher.matches())
	{
		return true;
	}
	else 
	{
		System.err.println("Please Enter Valid Name");
		return false;
	}
	}
	@Override
	public boolean isValidPassword(String pass) {
		Pattern namePattern=Pattern.compile("^[A-Z][[A-Z]a-z0-9[@#$%^&*()]{1}]{7,16}$");
		Matcher nameMatcher=namePattern.matcher(pass);
		
		if(nameMatcher.matches()){
			return true;
		}
		else
		{
			System.err.println("Please Enter password in specified format");
			return false;
		}
	}


	
	

	//------------------------ 1.Asset Management System  --------------------------
		/*******************************************************************************************************
		 - Function Name	:	dataAuthentication(UserBean a)
		 - Input Parameters	:	UserBean
		 - Return Type		:	boolean
		 - Throws			:  	AssetException,SQLException
		 - Author			:	
		 - Creation Date	:	20/6/2018
		 - Description		:	Authenticating User and Admin
		 ********************************************************************************************************/
	@Override
	public boolean dataAuthentication(UserBean a) throws AssetException{
		IAssetdao id=new Assetdaoimpl();
		boolean temp=id.dataAuthentication(a);
		return temp;
	}
	
	//------------------------ 1.Asset Management System  --------------------------
			/*******************************************************************************************************
			 - Function Name	:	updatePassword(String username,String password)
			 - Input Parameters	:	String
			 - Return Type		:	boolean
			 - Throws			:  	AssetException,SQLException
			 - Author			:	
			 - Creation Date	:	20/6/2018
			 - Description		:	Updating password for forgotpassword option.
			 ********************************************************************************************************/
	@Override
	public boolean updatePassword(String username,String password) {
		IAssetdao ie=new Assetdaoimpl();
		
		return ie.updatePassword(username,password);
		
	}
	
	
	
	
	//------------------------ 1.Asset Management System  --------------------------
	/*******************************************************************************************************
	 - Function Name	:	addNewAsset(AssetBean as)
	 - Input Parameters	:	AssetBean
	 - Return Type		:	void
	 - Throws			:  	AssetException,SQLException
	 - Author			:	
	 - Creation Date	:	20/6/2018
	 - Description		:	Adding New Asset(Functionality of ADMIN)
	 ********************************************************************************************************/
	@Override
	public int addNewAsset(AssetBean as) throws  AssetException{
		IAssetdao id=new Assetdaoimpl();
		return id.addNewAsset(as);
		}
	
	
	
//------------------------ 1.Asset Management System  --------------------------
	/*******************************************************************************************************
	 - Function Name	:	modifyAssetDetails(AssetBean as,long oldAssetId)
	 - Input Parameters	:	AssetBean,long
	 - Return Type		:	boolean
	 - Throws			:  	AssetException,SQLException
	 - Author			:	
	 - Creation Date	:	23/6/2018
	 - Description		:	Modifing Asset details.
	 ********************************************************************************************************/
	@Override
	public void modifyAssetDetails(AssetBean as) throws
			AssetException {
		IAssetdao id=new Assetdaoimpl();
		id.modifyAssetDetails(as);
		
	}
	
	//------------------------ 1.Asset Management System  --------------------------
		/*******************************************************************************************************
		 - Function Name	:	viewRequestDetails(long assetId)
		 - Input Parameters	:	void
		 - Throws			:  	AssetException,SQLException
		 - Author			:	
		 - Creation Date	:	27/6/2018
		 - Description		:	Viewing Request for Asset
		 ********************************************************************************************************/
	
	@Override
	public ArrayList<AssetRequestBean> viewRequestDetails(int requestId) throws AssetException {
		IAssetdao id=new Assetdaoimpl();
		return id.viewRequestDetails(requestId);
	}
	
	
	
	
	
	//------------------------ 1.Asset Management System  --------------------------
	/*******************************************************************************************************
	 - Function Name	:	viewAssets()
	 - Input Parameters	:	
	 - Return Type		:	ArrayList
	 - Throws			:  	AssetException,SQLException
	 - Author			:	
	 - Creation Date	:	20/6/2018
	 - Description		:	Displays assets in organization
	 ********************************************************************************************************/
	@Override
	public ArrayList<AssetBean> viewAssets() throws
			AssetException {
		IAssetdao id=new Assetdaoimpl();
		return id.viewAssets();
		
	}
	
	
	
	
	
	//------------------------ 1.Asset Management System  --------------------------
	/*******************************************************************************************************
	 - Function Name	:	raiseRequest
	 - Input Parameters	:	AssetRequestFormBean
	 - Return Type		:	int
	 - Throws			:  	AssetException,SQLException
	 - Author			:	
	 - Creation Date	:	20/6/2018
	 - Description		:	Used to Raise Request
	 ********************************************************************************************************/
	@Override
	public int raiseRequest(AssetRequestFormBean assetRequest)
			throws  AssetException {
		IAssetdao id=new Assetdaoimpl();
		return id.raiseRequest(assetRequest);
		
	}
	
	
	
	
	@Override
	public boolean isValidAssetName(ArrayList<AssetBean> al, String assetName) {
		boolean flag=false;
		for(AssetBean o:al){
			if(o.getAssetName().equals(assetName)){
				flag= true;
				break;
			}
			else
			{
		flag=false;
			}
			}
		if(flag==false)
			System.err.println("Enter available asset name");
		
		return flag;
	}
	
	
//------------------------ 1.Asset Management System  --------------------------
		/*******************************************************************************************************
		 - Function Name	:	viewAssetReqeustDetails
		 - Input Parameters	:	int
		 - Return Type		:	ArrayList<AssetRequestBean>
		 - Throws			:  	AssetException,SQLException
		 - Author			:	
		 - Creation Date	:	20/6/2018
		 - Description		:	It displays requests for an asset to admin
		 ********************************************************************************************************/
	@Override
	public ArrayList<AssetRequestBean> viewAssetRequestDetails(int assetId)
			throws AssetException {
		IAssetdao id=new Assetdaoimpl();
		return id.viewAssetRequestDetails(assetId);
		
		
	}

	
	
	//------------------------ 1.Asset Management System  --------------------------
	/*******************************************************************************************************
	 - Function Name	:	modifyStatus
	 - Input Parameters	:	String msg,AssetRequestBean ar
	 - Return Type		:	String
	 - Throws			:  	AssetException,SQLException
	 - Author			:	
	 - Creation Date	:	20/6/2018
	 - Description		:	Used to Accept or Reject an Asset for particular RequestId
	 ********************************************************************************************************/
	@Override
	public String modifyStatus(String msg,AssetRequestBean ar) throws  AssetException{
		IAssetdao id=new Assetdaoimpl();
		return id.modifyStatus(msg,ar);
	}
	
	
	
	

	//------------------------ 1.Asset Management System  --------------------------
			/*******************************************************************************************************
			 - Function Name	:	isValidQuantity()
			 - Input Parameters	:	AssetRequestBean
			 - Return Type		:	boolean
			 - Throws			:  	AssetException,SQLException
			 - Author			:	
			 - Creation Date	:	20/6/2018
			 - Description		:	checking quantity for an Asset
			 ********************************************************************************************************/
	@Override
	public boolean isValidQuantity(AssetRequestBean ar) throws
			AssetException {
		IAssetdao id=new Assetdaoimpl();
		return id.isValidQuantity(ar);
		
	}
	
	
	
	//------------------------ 1.Asset Management System  --------------------------
	/*******************************************************************************************************
	 - Function Name	:	getAssetDetails()
	 - Input Parameters	:	
	 - Return Type		:	ArrayList<AssetBean>
	 - Throws			:  	AssetException,SQLException
	 - Author			:	
	 - Creation Date	:	20/6/2018
	 - Description		:	Displaying all Assets in organization the Admin
	 ********************************************************************************************************/
	@Override
	public ArrayList<AssetBean> getAssetDetails() throws
			AssetException {
		IAssetdao id=new Assetdaoimpl();
		return id.getAssetDetails();
		
	}
	
	
	//------------------------ 1.Asset Management System  --------------------------
	/*******************************************************************************************************
	 - Function Name	:	nonAllocatedDetails()
	 - Input Parameters	:	
	 - Return Type		:	ArrayList<AssetBean>
	 - Throws			:  	AssetException,SQLException
	 - Author			:	
	 - Creation Date	:	5/7/2018
	 - Description		:	Displays all Non Allocated Assets in organization
	 ********************************************************************************************************/
	@Override
	public ArrayList<AssetBean> nonAllocatedDetails() throws
			AssetException {
		IAssetdao id=new Assetdaoimpl();
		return id.nonAllocatedDetails();
	}
	//------------------------ 1.Asset Management System  --------------------------
	/*******************************************************************************************************
	 - Function Name	:	allocatedDetails()
	 - Input Parameters	:	
	 - Return Type		:	ArrayList<AssetBean>
	 - Throws			:  	AssetException,SQLException
	 - Author			:	
	 - Creation Date	:	5/7/2018
	 - Description		:	Displays Allocated assets in Organization.
	 ********************************************************************************************************/
	@Override
	public ArrayList<AssetBean> allocatedDetails() throws 
			AssetException {
		IAssetdao id=new Assetdaoimpl();
		return id.allocatedDetails();
	}
	@Override
	public ArrayList<EmployeeBean> getEmployeeDetails(int mgr)
			throws AssetException {
		// TODO Auto-generated method stub
		IAssetdao id=new Assetdaoimpl();
		return id.getEmployeeDetails(mgr);
	}

}
