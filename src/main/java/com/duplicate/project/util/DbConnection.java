package com.duplicate.project.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	
	 public static Connection getConnection(){  
	        Connection con=null;  
	        try{  
	            Class.forName("org.postgresql.Driver");  
	          // con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Animal_husbandary_AutoSubmit","postgres","root"); 
	             // con=DriverManager.getConnection("jdbc:postgresql://10.100.101.157:5432/MIDC2019NEW_AFTER_AUTOSUBMIT","mumbai_test","db@mumbai157"); 
	            con=DriverManager.getConnection("jdbc:postgresql://10.25.4.46:5432/REVENUE","postgres","root"); 
	            System.out.println("Db Connected");
	        }
	        catch(Exception e)
	        {
	        	System.out.println(e);
	        }  
	        return con;  
	    }  
	

}
