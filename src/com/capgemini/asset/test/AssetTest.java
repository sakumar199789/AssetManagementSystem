package com.capgemini.asset.test;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.asset.bean.AssetBean;
import com.capgemini.asset.bean.UserBean;
import com.capgemini.asset.dao.Assetdaoimpl;
import com.capgemini.asset.dao.IAssetdao;
import com.capgemini.asset.exception.AssetException;

public class AssetTest {
	Connection connection;
	IAssetdao iAsset=new Assetdaoimpl();
	@BeforeClass
	public static void initialize() {
		IAssetdao iAsset=new Assetdaoimpl();
	
	}
	
	
	@Test
	public void test() {
		UserBean user=new UserBean();
		user.setUserNameId("Sai");
		user.setPassword("Sai@1997");
		
		try {
			assertEquals(true,iAsset.dataAuthentication(user));
		} catch (AssetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}
	
	@Test
	public void test2() {
		UserBean user=new UserBean();
		user.setUserNameId("Dinesh");
		user.setPassword("Dines@123");
		
		try {
			assertEquals(true,iAsset.dataAuthentication(user));
		} catch (AssetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	

	@Test
	public void addNewAssetTest() {
		AssetBean asset=new AssetBean();
		asset.setAssetId(14567);
		asset.setAssetName("Pendrive");
		asset.setAssetDes("Storage media");
		asset.setQuantity(20);
		asset.setStatus("AVAILABLE");
		try{
		assertEquals(1,iAsset.addNewAsset(asset));
		}
	
		catch(AssetException a){
			System.out.println(a);
		}
		}
	
	

	/*@AfterClass
	public void destroy() {
		try {
		connection.close();
		}
		catch(SQLException s) {
			try {
			throw new AssetException("Connection error");
			}
			catch(AssetException e) {
				System.out.println(e);
			}
		
			}
		
		
	}*/
	
	
}