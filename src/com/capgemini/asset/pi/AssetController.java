package com.capgemini.asset.pi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capgemini.asset.bean.AssetBean;
import com.capgemini.asset.bean.AssetRequestBean;
import com.capgemini.asset.bean.AssetRequestFormBean;
import com.capgemini.asset.bean.EmployeeBean;
import com.capgemini.asset.bean.UserBean;
import com.capgemini.asset.exception.AssetException;
import com.capgemini.asset.service.AssetImpl;
import com.capgemini.asset.service.IAssetInterface;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/**
 * Servlet implementation class AssetController
 */
@WebServlet("/AssetController")
public class AssetController extends HttpServlet {
	IAssetInterface ie=new AssetImpl();

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssetController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=null;
		ArrayList<AssetBean> al=new ArrayList();
		ArrayList<EmployeeBean> employee=new ArrayList();
		try{
		switch(request.getParameter("action")) {
		
		case "Find":
			session=request.getSession(false);
			if(session!=null) {
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
				response.setHeader("Pragma", "no-cache"); 
				response.setHeader("Expires", "0"); 
			ArrayList<AssetRequestBean> assetRequest=new ArrayList();
			
				assetRequest=ie.viewAssetRequestDetails(Integer.parseInt(request.getParameter("id")));
			
			
			if(assetRequest.size()==0){
				session.setAttribute("requestId",request.getParameter("id"));
				request.getRequestDispatcher("/AdminRequestError.jsp").forward(request, response);
				System.out.println("No Requests available for entered asset id: "+request.getParameter("id"));
			}
			else
			{
				request.setAttribute("assetRequest", assetRequest);
				request.getRequestDispatcher("/viewrequest2.jsp").forward(request, response);
			}
			}
			else
			{
				request.getRequestDispatcher("sessionerrorpage.jsp").forward(request, response);
				
			}
			break;
		
		case "RaiseRequest":
			session=request.getSession(false);
			if(session!=null) {
					
					al=ie.viewAssets();
					
					employee=ie.getEmployeeDetails((int)session.getAttribute("mgr"));
					
					
					session.setAttribute("assetlist",al);
					session.setAttribute("employeeId", employee);
					request.getRequestDispatcher("/RaiseRequest.jsp").forward(request, response);
					}
					else
					{
						request.getRequestDispatcher("sessionerrorpage.jsp").forward(request, response);
					}
					break;
				
			case "export":
				session=request.getSession(false);
				response.setContentType("text/csv");
				PrintWriter out=response.getWriter();
				if(session!=null) {
					if(request.getParameter("operation").equals("AllocatedAssets")){
						response.setHeader("Content-Disposition", "attachment; fileName=AllocatedAssets.xls");
						out.println("<html><body><table border=2><tr><td colspan=4 align=center>Allocated Asset Details</td></tr><tr><th>Asset Id</th><th>Asset Name</th><th>Asset Description</th><th>Quantity</th></tr>");
						ArrayList<AssetBean> allocatedAssets=ie.allocatedDetails();
						
						for(AssetBean asset:allocatedAssets){
							out.println("<tr><td>"+asset.getAssetId()+"</td><td>"+asset.getAssetName()+"</td><td>"+asset.getAssetDes()+"</td><td>"+asset.getQuantity()+"</td></tr>");
						}
						out.println("</table></body></html>");
					}	
					
					if(request.getParameter("operation").equals("UnallocatedAssets")){
						response.setHeader("Content-Disposition", "attachment; fileName=NonAllocatedAssets.xls");
						ArrayList<AssetBean> nonAllocatedAssets=ie.nonAllocatedDetails();
						out.println("<html><body><table border=2><tr><td colspan=4 align=center>Non Allocated Asset Details</td></tr><tr><th>Asset Id</th><th>Asset Name</th><th>Asset Description</th><th>Quantity</th></tr>");
						for(AssetBean asset:nonAllocatedAssets){
							out.println("<tr><td>"+asset.getAssetId()+"</td><td>"+asset.getAssetName()+"</td><td>"+asset.getAssetDes()+"</td><td>"+asset.getQuantity()+"</td></tr>");
						}
						out.println("</table></body></html>");
					}
				}
				else
				{
					request.getRequestDispatcher("sessionerrorpage.jsp").forward(request, response);
				}
					
					
				break;
				
			case "modify":
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
				response.setHeader("Pragma", "no-cache"); 
				response.setHeader("Expires", "0"); 
				
				session=request.getSession(false);
				if(session!=null) {
					System.out.println(session.getId());
				
					ArrayList<AssetBean> assets=ie.getAssetDetails();
					session.setAttribute("assetlist", assets);
					request.getRequestDispatcher("/DisplayAssetsAdmin.jsp").forward(request, response);
					
				}
				else
				{
					request.getRequestDispatcher("sessionerrorpage.jsp").forward(request, response);
				}
				break;
			
			case "edit":
				session=request.getSession(false);
				if(session!=null) {
				request.setAttribute("id", request.getParameter("id"));
				request.setAttribute("name", request.getParameter("name"));
				request.setAttribute("des", request.getParameter("des"));
				request.setAttribute("quantity", request.getParameter("quantity"));
				request.setAttribute("status", request.getParameter("status"));
				
				request.getRequestDispatcher("/ModifyAsset.jsp").include(request, response);
				}
				else
				{
					request.getRequestDispatcher("sessionerrorpage.jsp").forward(request, response);
				}
				break;
				
			case "viewrequest":
				session=request.getSession(false);
			
				if(session!=null) {
					ArrayList<AssetBean> assetlist=ie.getAssetDetails();
					session.setAttribute("assetList", assetlist);
				request.getRequestDispatcher("/viewrequest1.jsp").include(request, response);}
				else
					request.getRequestDispatcher("sessionerrorpage.jsp").forward(request, response);
				
				
				break;
				
			case "AssetAlloc":
				session=request.getSession(false);
				if(session!=null) {
				
					AssetRequestBean assetRequest=new AssetRequestBean();
					assetRequest.setRequestId(Integer.parseInt(request.getParameter("requestid")));
					assetRequest.setAssetId(Integer.parseInt(request.getParameter("assetid")));
					assetRequest.setEmpid(Integer.parseInt(request.getParameter("empid")));
					assetRequest.setAssetName(request.getParameter("assetname"));
					assetRequest.setAssetDes(request.getParameter("assetdes"));
					assetRequest.setQuantity(Integer.parseInt(request.getParameter("quantity")));
					
					if(ie.isValidQuantity(assetRequest)){
						if(request.getParameter("operation").equals("Accept")){
							System.out.println(ie.modifyStatus("Accept",assetRequest)+"for Request Id:"+assetRequest.getRequestId());
						}
						
					}
					else
					{
						System.out.println("Asset is Not Available");
						//ie.modifyStatus("Reject",ar);
					}
					if(request.getParameter("operation").equals("Reject"))
					{
						System.out.println(ie.modifyStatus("Reject",assetRequest)+"for Request Id:"+assetRequest.getRequestId());
					}
					
					}
				else
				{
					request.getRequestDispatcher("sessionerrorpage.jsp").forward(request, response);
				}
				break;
				
			case "GenerateReport":
				session=request.getSession(false);
				if(session!=null) {
				
					if(request.getParameter("operation").equals("AllocatedAssets")){
						ArrayList<AssetBean> assets=ie.allocatedDetails();
						session.setAttribute("operation",request.getParameter("operation"));
						session.setAttribute("allocated", assets);
						request.getRequestDispatcher("/Reports.jsp").forward(request, response);
						}
						else
						{
							ArrayList<AssetBean> assets=ie.nonAllocatedDetails();
							session.setAttribute("operation",request.getParameter("operation"));
							session.setAttribute("allocated", assets);
							request.getRequestDispatcher("/Reports.jsp").forward(request, response);
						}
				}
				else
				{
					request.getRequestDispatcher("sessionerrorpage.jsp").forward(request, response);
				}
				break;
				
			case "viewrequeststatus":
				session=request.getSession(false);
				if(session!=null)
				request.getRequestDispatcher("viewrequeststatus1.jsp").forward(request, response);
				else
					request.getRequestDispatcher("sessionerrorpage.jsp").forward(request, response);
				break;
				
			case "ViewStatus":
				session=request.getSession(false);
				if(session!=null) {
				ArrayList<AssetRequestBean> requestStatus=new ArrayList();
				
					//System.out.println("hai in status");
					requestStatus=ie.viewRequestDetails(Integer.parseInt(request.getParameter("requestId")));		
					request.setAttribute("requestStatus", requestStatus);
					request.getRequestDispatcher("viewrequeststatus2.jsp").forward(request, response);
			}
				else
				{
					request.getRequestDispatcher("sessionerrorpage.jsp").forward(request, response);
				}
				break;
			case "addnewasset":
				
				session=request.getSession(false);
				
				if(session!=null){ 
					response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
					response.setHeader("Pragma", "no-cache"); 
					response.setHeader("Expires", "0"); 
						
					request.getRequestDispatcher("AddNewAsset.jsp").forward(request, response);
				}
					else
					request.getRequestDispatcher("sessionerrorpage.jsp").forward(request, response);
				break;
				
			case "logout":
			
				 session=request.getSession(false);
				//System.out.println(session.isNew()+" "+session.getId());
				if(session==null){
						
				}
				else{
					System.out.println("Logout");
			session.invalidate();
				request.getRequestDispatcher("/Logout.jsp").include(request, response);
				
					}
				break;
				
	}
		}
		catch(AssetException ae){
			session.setAttribute("errormsg", ae);
			request.getRequestDispatcher("/errorpage.jsp").forward(request, response);
		}
}	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AssetBean asset;
		HttpSession session=null;
		try{
		switch(request.getParameter("action")) {
		
		case "login":
			
			 UserBean a=new UserBean();
			
			 session=request.getSession();
				System.out.println(session.isNew()+" "+session.getId());
				session.setAttribute("uname", request.getParameter("UserName"));
				session.setAttribute("pass", request.getParameter("password"));
			
			a.setUserNameId(request.getParameter("UserName"));
			a.setPassword(request.getParameter("password"));
			if(ie.dataAuthentication(a))
			{
				
				if(a.getUserType().equals("ADMIN")){
					if(session!=null){
					request.getRequestDispatcher("/Admin.jsp").forward(request, response);
					}
					else
					{
					request.getRequestDispatcher("/sessionerrorpage.jsp").include(request, response);
					}
				}
				else
				{
					if(session!=null){
					session.setAttribute("mgr", a.getMgr());
					request.getRequestDispatcher("/Manager.jsp").forward(request, response);
					}
					else
					{
					request.getRequestDispatcher("/sessionerrorpage.jsp").include(request, response);
					}
				}
			}
			else
			{
				request.setAttribute("error", "invalid");
				request.getRequestDispatcher("/LoginPage.jsp").include(request, response);
				//response.sendRedirect("LoginPage.jsp");
			}
			break;
			
		case "addnewasset":
			session=request.getSession(false);
			if(session!=null) {
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
				response.setHeader("Pragma", "no-cache"); 
				response.setHeader("Expires", "0"); 
				
			asset=new AssetBean();
			System.out.println("Insert");
			asset.setAssetId(Long.parseLong(request.getParameter("assetid")));
			asset.setAssetName(request.getParameter("assetname"));
			asset.setAssetDes(request.getParameter("assetdes"));
			asset.setQuantity(Integer.parseInt(request.getParameter("quantity")));
			asset.setStatus( request.getParameter("status"));
			asset.setImage(request.getParameter("assetimage"));
			System.out.println(asset.getImage());
			
				ie.addNewAsset(asset);
				session.setAttribute("assetId", request.getParameter("assetid"));
				request.getRequestDispatcher("AddNewAssetmsg.jsp").include(request, response);
			}
			else
			{
				request.getRequestDispatcher("sessionerrorpage.jsp").forward(request, response);
			}
			break;
			
			
		case "save":
			session=request.getSession(false);
			if(session!=null) { 
			asset=new AssetBean();
			System.out.println("Modify");
			asset.setAssetId(Long.parseLong(request.getParameter("assetid")));
			asset.setAssetName(request.getParameter("assetname"));
			asset.setAssetDes(request.getParameter("assetdes"));
			asset.setQuantity(Integer.parseInt(request.getParameter("quantity")));
			asset.setStatus( request.getParameter("status"));
			
			
				ie.modifyAssetDetails(asset);
				
				
			}
			else
			{
				request.getRequestDispatcher("sessionerrorpage.jsp").forward(request, response);
			}
			break;
		
		
		case "RaiseRequest":
			session=request.getSession(false);
			if(session!=null) { 
				AssetRequestFormBean raiseRequest=new AssetRequestFormBean();
			
				raiseRequest.setEmpId(Integer.parseInt(request.getParameter("empid")));
				raiseRequest.setEmpName(request.getParameter("empName"));
				raiseRequest.setDeptId(Integer.parseInt(request.getParameter("deptid")));
				raiseRequest.setAssetName(request.getParameter("assetname"));
				raiseRequest.setAssetPurpose(request.getParameter("assetpurpose"));
				raiseRequest.setQuan(Integer.parseInt(request.getParameter("quantity")));
			
				
				
					int requestId=ie.raiseRequest(raiseRequest);
					session.setAttribute("requestId", requestId);
					request.getRequestDispatcher("RaiseRequestMsg.jsp").include(request, response);
					
				}
				else
				{
					request.getRequestDispatcher("sessionerrorpage.jsp").forward(request, response);
				}
				break;
			}
	}
		catch(AssetException ae){
			session.setAttribute("errormsg", ae);
			request.getRequestDispatcher("errorpage.jsp").forward(request, response);
		}
}
}		
	

