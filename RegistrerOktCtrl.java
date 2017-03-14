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
			statement.executeUpdate("INSERT INTO TRENINGSOKT " + "VALUES (" +java.sql.Date.valueOf(dato) + "," +java.sql.Time.valueOf(tidspunkt) + "," +java.sql.Time.valueOf(varighet) + ")");	
			ResultSet oId=statement.getGeneratedKeys();
			setOktId(oId.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void RegistrerOkt(String dato, String tidspunkt, String varighet, String temperatur){
		Statement statement;
		try {
			statement = conn.createStatement();
			statement.executeUpdate("INSERT INTO TRENINGSOKT " + "VALUES (" +java.sql.Date.valueOf(dato) + "," +java.sql.Time.valueOf(tidspunkt) + "," +java.sql.Time.valueOf(varighet) + "," +temperatur + ")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void RegistrerSOktOvelse(String navn, int sett, int reps, int belastning) {
		int ovelseId;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ovelseId FROM STYRKEOVELSE WHERE navn='"+navn+"'");
			ovelseId=rs.getInt(1);
			Statement stmt2 = conn.createStatement();
			stmt2.executeUpdate("INSERT INTO STYRKEOKTOVELSE " + "VALUES (" +this.getOktId() +"," + ovelseId +", " +sett + ", " +reps + ", " + belastning + ")" );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void RegistrerSSOktOvelse(String navn, String distanse, String tidsbruk) {
		// TODO Auto-generated method stub
		int ovelseId;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ovelseId FROM STEADYSTATEOVELSE WHERE navn='"+navn+"' and distanse='"+distanse+"'");
			ovelseId=rs.getInt(1);
			Statement stmt2 = conn.createStatement();
			stmt2.executeUpdate("INSERT INTO STEADYSTATEOVELSE " + "VALUES (" +this.getOktId() +"," + ovelseId +", " +java.sql.Time.valueOf(tidsbruk) + ")" );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void RegistrerIOktOvelse(String navn, String type, int sett) {
		// TODO Auto-generated method stub
		int ovelseId;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ovelseId FROM INTERVALLOVELSE WHERE navn='"+navn+"' and type ='"+type+"'");
			ovelseId=rs.getInt(1);
			Statement stmt2 = conn.createStatement();
			stmt2.executeUpdate("INSERT INTO INTERVALLOVELSE " + "VALUES (" +this.getOktId() +"," + ovelseId +", " +sett + ")" );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void registrerNotat(String notat){
		try {
			Statement s = conn.createStatement();
			s.executeQuery("INSERT INTO NOTAT " + "VALUES (" +this.getOktId() +", " + notat + ")");
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
