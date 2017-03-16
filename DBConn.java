package prosjekt;

import java.sql.*;

//klassen oppretter en forbindelse til en lokal database på localhost:3306, med brukernavn=admin og passord=passord

public abstract class DBConn {
	protected Connection conn;
	protected int oktId;

	public DBConn(){
		try {
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/min_database?autoReconnect=true&useSSL=false", "admin", "passord"); 
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}	
}
