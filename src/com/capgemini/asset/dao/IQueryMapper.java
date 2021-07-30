package com.capgemini.asset.dao;

public interface IQueryMapper {

public final static String RETRIEVE_USER_DATA="select * from user_master";
public final static String UPDATE_PASSWORD="UPDATE user_master SET userpassword=? WHERE username=?";
public final static String INSERT_ASSET_DATA="INSERT INTO asset VALUES(?,?,?,?,?,?)";
public final static String GET_ASSETS="SELECT * FROM asset";
public final static String UPDATE_ASSET_DATA="UPDATE asset SET assetname=?,assetdes=?,quantity=?,status=? where assetid=?";
public final static String HAS_REQUEST_DETAILS="SELECT count(*) from asset_request WHERE requestid=?";
public final static String VIEW_MANAGER_REQUEST_DETAILS1="SELECT requestid,a.assetid,a.assetname,empid,ename,ar.quantity,requeststatus from asset a,asset_request ar,employee emp WHERE a.assetname=ar.assetname and ar.empid=emp.empno AND requestid=?";
public final static String VIEW_MANAGER_REQUEST_DETAILS2="SELECT alloation_date,release_date FROM asset_allocation WHERE assetid=?";
public final static String VIEW_ASSET_REQUEST_DETAILS="SELECT requestid,assetid,empid,al.assetname,al.assetdes,ar.quantity from asset al,asset_request ar WHERE al.assetname=ar.assetname and assetid=? and requeststatus='Awaiting Accept'";
public final static String VIEW_ASSETS="SELECT assetname FROM asset";
public final static String INSERT_REQUEST="INSERT INTO asset_request VALUES(req_seq.NEXTVAL,?,?,?,?,?,?,default)";
public final static String CREATE_REQ_SEQ="CREATE SEQUENCE req_seq START WITH 1000 NOCYCLE";
public final static String UPDATE_ASSET_STATUS="UPDATE asset SET quantity=quantity-(?) WHERE assetid=?";
public final static String INSERT_ALLOCATED_DATA="INSERT INTO asset_allocation VALUES(allocation_id.NEXTVAL,?,?,SYSDATE,SYSDATE+?)";
public final static String UPDATE_STATUS="UPDATE asset_request SET requeststatus=? WHERE requestid=?";
public final static String  CHECK_QUANTITY="SELECT COUNT(*) FROM asset WHERE quantity>0 AND quantity>? AND assetid=?";
public final static String GET_ALLOCATED_ASSETS="SELECT ai.assetid,assetname,assetdes,quantity FROM asset ai,asset_allocation al WHERE ai.assetid=al.assetid";
public final static String GET_UNALLOCATED_ASSETS="select assetid,assetname,assetdes,quantity from asset WHERE assetid NOT IN(Select assetid from asset_allocation)";
public final static String GET_EMPLOYEE_DETAILS="SELECT empno,ename,dept_id FROM employee WHERE mgr=?";
}
