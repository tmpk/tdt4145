package prosjekt;
import java.sql.*;

public class RegistrerOktCtrl extends DBConn {
		private int oktId;
	
	public RegistrerOktCtrl(){
		
	}
	
	public void RegistrerOkt(String dato, String tidspunkt, String varighet){
		Statement statement;
		try {
			statement = conn.createStatement();
			statement.executeUpdate("INSERT INTO TRENINGSØKT " + "VALUES (" +dato + "," +tidspunkt + "," +varighet + ")");	
			ResultSet oId=statement.getGeneratedKeys();
			setOktId(oId.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void RegistrerOkt(String dato, String tidspunkt, String varighet, int temperatur){
		Statement statement;
		try {
			statement = conn.createStatement();
			statement.executeUpdate("INSERT INTO TRENINGSØKT " + "VALUES (" +dato + "," +tidspunkt + "," +varighet + "," +temperatur + ")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void RegistrerSOktOvelse(int sett, int reps, int belastning) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO STYRKEOKTOVELSE " + "VALUES (" +this.getOktId() +", " +sett + ", " +reps + ", " + belastning + ")" );
		} catch (SQLException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}

	public int getOktId() {
		return oktId;
	}

	public void setOktId(int oktId) {
		this.oktId = oktId;
	}
}
