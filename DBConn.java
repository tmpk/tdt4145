package prosjekt;

import java.sql.*;
import java.util.Properties;

public abstract class DBConn {
	
	    protected Connection conn;
	    public DBConn() {
	    }
	    public void connect() {
	    	try {
	            Class.forName("com.mysql.jdbc.Driver").newInstance();
	            // Properties for user and password. Here the user and password are both 'paulr'
	            Properties p = new Properties();
	            p.put("user", "myuser");
	            p.put("password", "mypassword");           
	            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/prosjekt?autoReconnect=true&useSSL=false",p);
	        } catch (Exception e)
	    	{
	            throw new RuntimeException("Unable to connect", e);
	    	}
	    }
}
