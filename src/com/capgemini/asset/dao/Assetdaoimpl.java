package com.capgemini.asset.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.capgemini.asset.bean.AssetBean;
import com.capgemini.asset.bean.AssetRequestBean;
import com.capgemini.asset.bean.AssetRequestFormBean;
import com.capgemini.asset.bean.EmployeeBean;
import com.capgemini.asset.bean.UserBean;
import com.capgemini.asset.exception.AssetException;
import com.capgemini.asset.util.DBConnection;


public class Assetdaoimpl implements IAssetdao{
	
	
	
	
	//------------------------ 1.Asset Management System  --------------------------
		/*******************************************************************************************************
		 - Function Name	:	dataAuthentication(UserBean a)
		 - Input Parameters	:	UserBean
		 - Return Type		:	boolean
		 - Throws			:  	AssetException
		 - Author			:	
		 - Creation Date	:	20/6/2018
		 - Description		:	Authenticating User and Admin
		 * @throws AssetException 
		 ********************************************************************************************************/
public boolean dataAuthentication(UserBean a) throws AssetException{
		Statement stmt=null;
		boolean temp=false;
		ResultSet rs=null;
		Connection conn=null;
		try{
			conn=DBConnection.getConnection();
		 stmt=conn.createStatement(); 
		rs=stmt.executeQuery(IQueryMapper.RETRIEVE_USER_DATA);
		
		if(rs!=null){
		while(rs.next()){
			//System.out.println(rs.getString(2));
		if((rs.getString(1).equals(a.getUserNameId())||rs.getString(2).equals(a.getUserNameId()))&&(rs.getString(3).equals(a.getPassword()))){
		
			temp=true;
		break;
		}
		else{
			temp=false;
		}
		}
		}
		/*if(temp==false)
		{
			throw new AssetException("Please Enter valid Credentials");
		}*/
		if(temp==true){
		a.setUserType(rs.getString(4));
		a.setMgr(rs.getInt(5));
		//System.out.println(a.getUserType());
		}
		else
			{
			System.err.println("Please Enter valid name and password.");
			}
			}
		catch(SQLException s){
			throw new AssetException(s.getMessage());
		}
		
		finally{
			try{			
			rs.close();
			stmt.close();
			conn.close();
		}
			catch(Exception e)
			{
				System.out.println(e);
			}
			}
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
		PreparedStatement stmt=null;
		boolean temp=false;
		ResultSet rs=null;
		
		try{
			Connection conn=DBConnection.getConnection();
			stmt=conn.prepareStatement(IQueryMapper.UPDATE_PASSWORD);
			stmt.setString(1,password);
			stmt.setString(2, username);
			int queryresult=stmt.executeUpdate();
			if(queryresult==0) {
				temp=false;
			}
			else
			{
				temp=true;
			}
		
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return temp;
	}

	
	//------------------------ 1.Asset Management System  --------------------------
			/*******************************************************************************************************
			 - Function Name	:	addNewAsset(AssetBean as)
			 - Input Parameters	:	AssetBean
			 - Return Type		:	void
			 - Throws			:  	AssetException
			 - Author			:	
			 - Creation Date	:	20/6/2018
			 - Description		:	Adding New Asset(Functionality of ADMIN)
			 ********************************************************************************************************/
	@Override
	public int addNewAsset(AssetBean as) throws AssetException {
		Connection con=null;
		int queryResult;
		PreparedStatement stmt=null;
			try{
			con=DBConnection.getConnection();
			stmt=con.prepareStatement(IQueryMapper.INSERT_ASSET_DATA);
			stmt.setLong(1, as.getAssetId());
			stmt.setString(2, as.getAssetName());
			stmt.setString(3, as.getAssetDes());
			stmt.setInt(4, as.getQuantity());
			stmt.setString(5, as.getStatus());
			stmt.setString(6, as.getImage());
			 queryResult=stmt.executeUpdate();
			System.out.println(queryResult);
			}
			catch(SQLException s){
				throw new AssetException(s.getMessage());
			}
			finally{
			try{
				con.close();
			stmt.close();
			}
			catch(SQLException s){
				throw new AssetException(s.getMessage());
			}
			}
			return queryResult;
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
	
		Connection con=null;
		PreparedStatement stmt=null;
	
		try{
			con=DBConnection.getConnection();
			stmt=con.prepareStatement(IQueryMapper.UPDATE_ASSET_DATA);
			
			
			stmt.setString(1, as.getAssetName());
			stmt.setString(2, as.getAssetDes());
			stmt.setInt(3, as.getQuantity());
			stmt.setString(4,as.getStatus());
			stmt.setLong(5,as.getAssetId());
			int queryResult=stmt.executeUpdate();
			if(queryResult==0)
			{
				throw new AssetException("Can,t Modify Data.");
			}
			else
			{
				System.out.println("Modification done successfully.");
			}
		}
		catch(SQLException s){
			throw new AssetException(s.getMessage());
		}
		finally{
		try{
			con.close();
		stmt.close();
		}
		catch(SQLException s){
			throw new AssetException(s.getMessage());
		}
		}
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
public ArrayList<AssetRequestBean> viewRequestDetails(int requestId) throws 
					AssetException {
				ArrayList<AssetRequestBean> l=new ArrayList();
				Connection con=null;
				PreparedStatement stmt=null;
			ResultSet rs1;
				try{
					con=DBConnection.getConnection();
					stmt=con.prepareStatement(IQueryMapper.HAS_REQUEST_DETAILS);
					stmt.setLong(1,requestId);
					ResultSet rs=stmt.executeQuery();
					rs.next();
					
					if(rs.getInt(1)>0){
						stmt.close();
						rs.close();
						stmt=con.prepareStatement(IQueryMapper.VIEW_MANAGER_REQUEST_DETAILS1);
						stmt.setLong(1,requestId);
						rs=stmt.executeQuery();
						rs.next();
						
						AssetRequestBean ar=new AssetRequestBean();
							ar.setRequestId(rs.getInt(1));
							ar.setAssetId(rs.getInt(2));
							ar.setAssetName(rs.getString(3));
							ar.setEmpid(rs.getInt(4));
							ar.setEname(rs.getString(5));
							ar.setQuantity(rs.getInt(6));
							ar.setRequestStatus(rs.getString(7));
							
							stmt=con.prepareStatement(IQueryMapper.VIEW_MANAGER_REQUEST_DETAILS2);
							stmt.setLong(1,ar.getAssetId());
							rs1=stmt.executeQuery();
							
							boolean flag=rs1.next();
							if(!flag) {
							System.out.println("no rows");
							}
							
							else
								{ar.setAllocationDate(rs1.getDate(1));
							ar.setReleaseDate(rs1.getDate(2));
								}
						l.add(ar);
						
						
					}
					else
					{
						System.out.println("No Requests for the corresponding Request Id.");
					}
				}
				catch(SQLException s){
					throw new AssetException(s.getMessage());
				}
				finally{
				try{
					con.close();
				stmt.close();
				}
				catch(SQLException s){
					throw new AssetException(s.getMessage());
				}
				}
				return l;
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
		ArrayList<AssetBean> al=new ArrayList();
		Connection con=null;
		
		PreparedStatement stmt=null;
	try{
			con=DBConnection.getConnection();
			stmt=con.prepareStatement(IQueryMapper.VIEW_ASSETS);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				AssetBean b=new AssetBean();
				b.setAssetName(rs.getString(1));
				al.add(b);
			}
	}
	catch(SQLException s){
		throw new AssetException(s.getMessage());
	}
	finally{
	try{
		con.close();
	stmt.close();
	}
	catch(SQLException s){
		throw new AssetException(s.getMessage());
	}
	}
			
		return al;
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
			throws AssetException {
		Connection con=null;
		PreparedStatement prestmt=null;
		con=DBConnection.getConnection();
		Statement stmt;
		ResultSet rs;
		try{
		stmt=con.createStatement();  
			prestmt=con.prepareStatement(IQueryMapper.INSERT_REQUEST);
			prestmt.setInt(1,assetRequest.getEmpId());
			prestmt.setString(2,assetRequest.getEmpName());
			prestmt.setInt(3,assetRequest.getDeptId());
			prestmt.setString(4,assetRequest.getAssetName());
			prestmt.setString(5,assetRequest.getAssetPurpose());
			prestmt.setInt(6,assetRequest.getQuan());
			int queryResult=prestmt.executeUpdate();
			if(queryResult==0){
				throw new AssetException("Request not raised");
			}
			
			stmt=con.createStatement();
			rs=stmt.executeQuery("select max(requestid) from Asset_Request WHERE empid="+assetRequest.getEmpId()); 
			rs.next();
		}
		catch(SQLException s){
			throw new AssetException(s.getMessage());
		}
		try{
		return rs.getInt(1);
		}
		catch(SQLException s){
			throw new AssetException(s.getMessage());
		}
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
		Connection con=null;
		PreparedStatement prestmt=null;
		con=DBConnection.getConnection();
		ArrayList<AssetRequestBean> al=new ArrayList();
		try{
			prestmt=con.prepareStatement(IQueryMapper.VIEW_ASSET_REQUEST_DETAILS);
			prestmt.setInt(1,assetId);
			ResultSet rs=prestmt.executeQuery();
			while(rs.next()){
				AssetRequestBean ar=new AssetRequestBean();
				ar.setRequestId(rs.getInt(1));
				ar.setAssetId(rs.getInt(2));
				ar.setEmpid(rs.getInt(3));
				ar.setAssetName(rs.getString(4));
				ar.setAssetDes(rs.getString(5));
				ar.setQuantity(rs.getInt(6));
				al.add(ar);
			}
		}
		catch(SQLException s){
			throw new AssetException(s.getMessage());
		}
		finally{
		try{
			con.close();
			prestmt.close();
		}
		catch(SQLException s){
			throw new AssetException(s.getMessage());
		}
		}
		
		return al;	
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
	public String modifyStatus(String msg,AssetRequestBean ar) throws AssetException {
		Connection con=null;
		PreparedStatement prestmt=null;
		String str=null;
		con=DBConnection.getConnection();
		ArrayList<AssetRequestBean> al=new ArrayList();
		try{
		if(msg.equals("Accept")){
			prestmt=con.prepareStatement(IQueryMapper.UPDATE_ASSET_STATUS);
			prestmt.setInt(1,ar.getQuantity());
			prestmt.setInt(2,ar.getAssetId());
			int queryResult=prestmt.executeUpdate();
			if(queryResult==0){
				
			}
			else
			{
				str="Asset Allocated";
				prestmt=con.prepareStatement(IQueryMapper.INSERT_ALLOCATED_DATA);
				prestmt.setInt(1, ar.getAssetId());
				prestmt.setInt(2,ar.getEmpid());
				prestmt.setInt(3,5);
				prestmt.executeUpdate();
				prestmt=con.prepareStatement(IQueryMapper.UPDATE_STATUS);
				prestmt.setString(1,"Allocated");
				prestmt.setInt(2, ar.getRequestId());
				prestmt.executeUpdate();
			}
		}
		else
		{
			str="Asset Not allocated";
			prestmt=con.prepareStatement(IQueryMapper.UPDATE_STATUS);
			prestmt.setString(1,"Not Allocated");
			prestmt.setInt(2, ar.getRequestId());
			prestmt.executeUpdate();
		}
		}
		catch(SQLException s){
			throw new AssetException(s.getMessage());
		}
		finally{
		try{
			con.close();
			prestmt.close();
		}
		catch(SQLException s){
			throw new AssetException(s.getMessage());
		}
		}
		return str;
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
		Connection con=null;
		PreparedStatement prestmt=null;
		con=DBConnection.getConnection();
			
		boolean temp=false;
		try{
		prestmt=con.prepareStatement(IQueryMapper.CHECK_QUANTITY);
			prestmt.setInt(1,ar.getQuantity());
			prestmt.setInt(2,ar.getAssetId());
			ResultSet rs=prestmt.executeQuery();
			rs.next();
			if(rs.getInt(1)==1)
				temp=true;
			else
				temp=false;
		}
		catch(SQLException s){
			throw new AssetException(s.getMessage());
		}
		finally{
		try{
			con.close();
			prestmt.close();
		}
		catch(SQLException s){
			throw new AssetException(s.getMessage());
		}
		}
		return temp;	
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
		Connection con=null;
		PreparedStatement prestmt=null;
		con=DBConnection.getConnection();
		ArrayList<AssetBean> al=new ArrayList();
		try{
			prestmt=con.prepareStatement(IQueryMapper.GET_ASSETS);
			
			ResultSet rs=prestmt.executeQuery();
			while(rs.next()){
				AssetBean asset=new AssetBean();
				asset.setAssetId(rs.getLong(1));
				asset.setAssetName(rs.getString(2));
				asset.setAssetDes(rs.getString(3));
				asset.setQuantity(rs.getInt(4));
				asset.setStatus(rs.getString(5));
				asset.setImage(rs.getString(6));
				al.add(asset);
			}
		}
		catch(SQLException s){
			throw new AssetException(s.getMessage());
		}
		finally{
		try{
			con.close();
			prestmt.close();
		}
		catch(SQLException s){
			throw new AssetException(s.getMessage());
		}
		}
		return al;	
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
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement prestmt=null;
		con=DBConnection.getConnection();
			
			ArrayList<AssetBean> beanList=new ArrayList<AssetBean>();
			try {
				PreparedStatement preparedstatement=con.prepareStatement(IQueryMapper.GET_UNALLOCATED_ASSETS);
				ResultSet result=preparedstatement.executeQuery();
				while(result.next())
				{
					AssetBean consBean=new AssetBean();
					consBean.setAssetId(Integer.parseInt(result.getString(1)));
					consBean.setAssetName(result.getString(2));
					consBean.setAssetDes(result.getString(3));
					consBean.setQuantity(result.getInt(4));
					beanList.add(consBean);
				}
			} catch (SQLException e) {
				throw new AssetException(e.getMessage());
			}
			return beanList;
	
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
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement prestmt=null;
		con=DBConnection.getConnection();
			
			ArrayList<AssetBean> beanList=new ArrayList<AssetBean>();
			try {
				PreparedStatement preparedstatement=con.prepareStatement(IQueryMapper.GET_ALLOCATED_ASSETS);
				ResultSet result=preparedstatement.executeQuery();
				while(result.next())
				{
					AssetBean consBean=new AssetBean();
					consBean.setAssetId(Integer.parseInt(result.getString(1)));
					consBean.setAssetName(result.getString(2));
					consBean.setAssetDes(result.getString(3));
					consBean.setQuantity(result.getInt(4));
					beanList.add(consBean);
				}
			} catch (SQLException e) {
				throw new AssetException(e.getMessage());
			}
			return beanList;
	
	}


	@Override
	public ArrayList<EmployeeBean> getEmployeeDetails(int mgr)
					throws  AssetException {
		Connection con=null;
		PreparedStatement prestmt=null;
		con=DBConnection.getConnection();
			
			ArrayList<EmployeeBean> employeeList=new ArrayList();
			try {
				PreparedStatement preparedstatement=con.prepareStatement(IQueryMapper.GET_EMPLOYEE_DETAILS);
				preparedstatement.setInt(1, mgr);
				ResultSet result=preparedstatement.executeQuery();
				while(result.next())
				{
					EmployeeBean employee=new EmployeeBean();
				employee.setEmpno(result.getInt(1));
				employee.setEmpName(result.getString(2));
				employee.setDeptId(result.getInt(3));
				employeeList.add(employee);
				}
			} catch (SQLException e) {
				throw new AssetException(e.getMessage());
			}
			return employeeList;
	}
}
		


