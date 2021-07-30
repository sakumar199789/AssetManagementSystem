package com.capgemini.asset.exception;

public class AssetException extends Exception {
String excep=null;
	public AssetException(String str)
{
	excep=str;
}
	public String toString(){
		return excep;
	}
}
