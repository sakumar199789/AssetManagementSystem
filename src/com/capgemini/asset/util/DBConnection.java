package com.capgemini.asset.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.capgemini.asset.exception.*;

public class DBConnection {
	
	private static Properties properties= new Properties();
	private static Connection connection;

	public static Connection getConnection() throws AssetException{
		
		try {
			/*InputStream inputStream =new FileInputStream("resources/jdbc.properties");
			properties.load(inputStream);
			String url=properties.getProperty("dburl");
			String user=properties.getProperty("username");
			String password=properties.getProperty("password");
			inputStream.close();*/
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); 
			connection = DriverManager.getConnection("jdbc:oracle:thin:@10.219.34.3:1521/orcl", "trg1109", "training1109");
			
		} catch (SQLException e) {
			throw new AssetException(e.getMessage());
		}
		/* catch (FileNotFoundException e2) {
		throw new AssetException("Could not Find properties file to connect to database.");
	} catch (IOException e) {
		throw new AssetException("Could not read the database details from properties file.");*/
		return connection;
	
	}

} 
