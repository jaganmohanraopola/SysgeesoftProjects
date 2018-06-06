package com.data;

import java.sql.*;
public class DBOperation {

	public static void InsertData
	  (String id,String name,String fathername,String mobile) 
	  {
		  try
		  {
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  Connection con=DriverManager.getConnection
				  ("jdbc:oracle:thin:@localhost:1521:xe","system","123456");
		  PreparedStatement pst = con.prepareStatement
				  ("insert into profileData values(?,?,?,?)");
		  pst.setInt(1, Integer.parseInt(id));
		  pst.setString(2, name);
		  pst.setString(3, fathername);
		  pst.setString(4, mobile);
		  int l=pst.executeUpdate();
		  if(l>0)
		  {
			  System.out.println(id+" Inserted Successfully");
		  }
		  }
		  catch(Exception ex)
		  {
			  System.out.println(id+" Fail to Insert");
		  }
		  
	  }
	  public static ResultSet GetData()
	  {
		  try
		  {
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			  Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123456");
			  Statement stmt=con.createStatement();
			  ResultSet rs =stmt.executeQuery("select * from profileData");
			  return rs;
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
			  return null;
			
		  }
	  }
	}


