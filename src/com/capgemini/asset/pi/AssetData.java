package com.capgemini.asset.pi;
import java.sql.SQLException;
import java.util.*;

import com.capgemini.asset.bean.AssetBean;
import com.capgemini.asset.bean.AssetRequestBean;
import com.capgemini.asset.bean.AssetRequestFormBean;
import com.capgemini.asset.bean.UserBean;
import com.capgemini.asset.exception.AssetException;
import com.capgemini.asset.service.*;
public class AssetData {

	public static void main(String[] args) throws Exception{
		Scanner s=new Scanner(System.in);
		String usernameId,password,assetName;
		boolean check=true;
		IAssetInterface ie=new AssetImpl();
		UserBean us=new UserBean();
		AssetBean as=new AssetBean();
		int option=0;
		
			System.out.println("Asset Management System");
			System.out.println("________________________");
			//Authentication of User name and Password
		
		do{
		do{
			System.out.println("Enter Name/User Id");
			usernameId=s.next();
		}while(!(ie.isValidUsername(usernameId)));
		do{
			System.out.println("Enter Password");
			password=s.next();
		}while(!(ie.isValidPassword(password)));
		us.setUserNameId(usernameId);
		us.setPassword(password);
		
		}while(!ie.dataAuthentication(us));
		
		
		//Admin Tasks
		
		if(us.getUserType().equals("ADMIN")){
			
			System.out.println("===============");
			System.out.println("Welcome "+us.getUserNameId());
			System.out.println("===============");
			do{
			System.out.println("1.Add New Asset \n2.Modify Asset details \n3.View Asset Requests \n4.Generate Reports \n5.Exit");
			
			System.out.println("Enter your option:");
			option=s.nextInt();
			
			
			switch(option){
				case 1:	
						System.out.println("Enter Asset Id:");
						as.setAssetId(s.nextLong());
						System.out.println("Enter Asset Name:");
						as.setAssetName(s.next());
						System.out.println("Enter Asset Description:");
						as.setAssetDes(s.next());
						System.out.println("Enter Asset Quantity:");
						as.setQuantity(s.nextInt());
						System.out.println("Enter Asset Status:");
						as.setStatus(s.next());
						try{
						
							if(ie.addNewAsset(as)>0)
							{
								System.out.println("Data Inserted Successfully");
							}
							else{
								System.out.println("Data Insertion Failed");
							}
						}
						catch(AssetException a){
							System.out.println(a);
						}
						/*catch(SQLException s1){
							System.out.println(s1.getMessage());
						}*/
						break;
				case 2:
						System.out.println("Enter the Asset Id for which you want to modify details.");
						long oldAssetId=s.nextLong();
						System.out.println("Enter Asset Id for which you want to update:");
						as.setAssetId(s.nextLong());
						System.out.println("Enter New Asset Name:");
						as.setAssetName(s.next());
						System.out.println("Enter Asset Description:");
						as.setAssetDes(s.next());
						System.out.println("Enter Asset Quantity:");
						as.setQuantity(s.nextInt());
						System.out.println("Enter Asset Status:");
						as.setStatus(s.next());
						try{
						ie.modifyAssetDetails(as);
						}
						catch(Exception e){
							System.out.println(e);
						}
						
						break;
					
				case 3:
					ArrayList<AssetRequestBean> al=null;
						System.out.println("Enter the Asset Id for which you want to check request:");
						int  assetId=s.nextInt();
						try{
							 al=ie.viewAssetRequestDetails(assetId);
						}
						catch(Exception e){
							System.out.println(e);
						}
						if(al.size()==0){
							System.out.println("No Requests available for entered asset id: "+assetId);
						}
						else{
						System.out.println("Request-Id Asset-Id EmpNo AssetName AssetDescription  Quantity");
					
						for(int i=0;i<al.size();i++){
							AssetRequestBean ar=al.get(i);
							System.out.println(ar.getRequestId()+"    "+ar.getAssetId()+"    "+ar.getEmpid()+"    "+ar.getAssetName()+"      "+ar.getAssetDes()+"     "+ar.getQuantity());
						if(ie.isValidQuantity(ar)){
							System.out.println("1.Accept\n2.Reject");
							int ch=s.nextInt();
							if(ch==1){
								System.out.println(ie.modifyStatus("Accept",ar)+"for Request Id:"+ar.getRequestId());
							}
							else if(ch==2){
								System.out.println(ie.modifyStatus("Reject",ar)+"for Request Id:"+ar.getRequestId());
							}
						}
						else
						{
							System.out.println("Asset is Not Available");
							//ie.modifyStatus("Reject",ar);
						}
						}
						}
						break;
				case 4:
						System.out.println("a.Allocated Assets \nb.Unallocated assets \nc.Export Data");
						String report=s.next();
						switch(report){
						case "a":
						
						
						
						
						}
						
				
				
				case 5:
					System.out.println("Thank You");
				break;
			}

			
			
		
			}		while(option!=4);			
	}
		
		
		
		//Manager Tasks
		
		else if(us.getUserType().equals("MANAGER")){
			System.out.println("===============");
			System.out.println("Welcome "+us.getUserNameId());
			System.out.println("===============");
			ArrayList<AssetBean> al=null;
			do{
			System.out.print("1.Raise a new request\n2.View Status of Request \n3.Exit");
			System.out.println("\nEnter your option:");
			option=s.nextInt();
			switch(option){
			case 1:
				AssetRequestFormBean assetRequest=new AssetRequestFormBean();
				System.out.println("\n1.Enter Employee Id");
				
				int empId=s.nextInt();
				
				try{
				al=ie.viewAssets();
				}
				catch(Exception a)
				{
					System.out.println(a);
				}
				System.out.println("Available Assets");
				System.out.println("______________");
				for(AssetBean o:al){
				
					System.out.println(o.getAssetName());
				}
				do{
				System.out.println("\n2.Enter Asset Name:");
				 assetName=s.next();
				}while(!ie.isValidAssetName(al, assetName));
		
				System.out.println("\n4.Purpose:");
				String assetPurpose=s.next();
				System.out.println("\n5.Enter Asset Quantity:");
				int quan=s.nextInt();
				assetRequest.setEmpId(empId);
				assetRequest.setAssetName(assetName);
				assetRequest.setAssetPurpose(assetPurpose);
				assetRequest.setQuan(quan);
				try{
				System.out.println("Your request is raised with Request Id:"+ie.raiseRequest(assetRequest));
				}
				catch(Exception e){
					e.printStackTrace();
				}
				break;
			
			
			case 2:
				ArrayList<AssetRequestBean> request=null;
				System.out.println("Enter request Id:");
				int requestId=s.nextInt();
				try{
				request=ie.viewRequestDetails(requestId);			
				}
				catch(Exception e){
					System.out.println(e);
				}
				for(int i=0;i<request.size();i++){
					AssetRequestBean ar=request.get(i);
					System.out.println(ar.getRequestId()+"   "+ar.getAssetId()+"   "+ar.getAssetName()+"   "+ar.getEmpid()+"   "+ar.getEname()+"  "+ar.getQuantity()+"   "+ar.getRequestStatus());
				}
				break;
			case 3:
				System.out.println("Thank you ");
				break;
			}
		}while(option!=3);
		}
	
		
	}
}
