package prosjekt;
import java.sql.*;

public class RegistrerMaalCtrl extends DBConn {
	
	public RegistrerMaalCtrl(){
		super();
	}
	
	
	public void registrerMaal(String ovelse, String dato_fra, String dato_til, int sett, int reps, int belastning){
		int ovelseId=0;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT ovelseId FROM STYRKEOVELSE " + "WHERE navn = ?");
			ps.setString(1, ovelse);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) ovelseId = rs.getInt(1);
			PreparedStatement s = conn.prepareStatement("INSERT INTO STYRKEMAAL (ovelseId, dato_fra, dato_til, sett, reps, belastning) " + "VALUES (?, ?, ?, ?, ?, ?)");
			s.setInt(1, ovelseId);
			s.setDate(2, java.sql.Date.valueOf(dato_fra));
			s.setDate(3, java.sql.Date.valueOf(dato_til));
			s.setInt(4, sett);
			s.setInt(5, reps);
			s.setInt(6, belastning);
			s.executeUpdate();
			System.out.println("Målet er registrert!");
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void registrerMaal(String ovelse, String dato_fra, String dato_til, String distanse, String tidsbruk){
		int ovelseId=0;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT ovelseId FROM STEADYSTATEOVELSE " + "WHERE navn = ? AND distanse = ?");
			ps.setString(1, ovelse);
			ps.setString(2, distanse);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) ovelseId = rs.getInt(1);
			PreparedStatement s = conn.prepareStatement("INSERT INTO STEADYSTATEMAAL (ovelseId, dato_fra, dato_til, tidsbruk) " + "VALUES (?, ?, ?, ?)");
			s.setInt(1, ovelseId);
			s.setDate(2, java.sql.Date.valueOf(dato_fra));
			s.setDate(3, java.sql.Date.valueOf(dato_til));
			s.setString(4, tidsbruk);
			s.executeUpdate();
			System.out.println("Målet er registrert!");
		} catch (SQLException e){
			e.printStackTrace();
		}
	}


	public void registrerMaal(String ovelse, String dato_fra, String dato_til, String type, int sett) {
		int ovelseId=0;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT ovelseId FROM INTERVALLOVELSE " + "WHERE navn = ? AND type = ?");
			ps.setString(1, ovelse);
			ps.setString(2, type);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) ovelseId = rs.getInt(1);
			PreparedStatement s = conn.prepareStatement("INSERT INTO INTERVALLMAAL (ovelseId, dato_fra, dato_til, sett) " + "VALUES (?, ?, ?, ?)");
			s.setInt(1, ovelseId);
			s.setDate(2, java.sql.Date.valueOf(dato_fra));
			s.setDate(3, java.sql.Date.valueOf(dato_til));
			s.setInt(4, sett);
			s.executeUpdate();
			System.out.println("Målet er registrert!");
		} catch (SQLException e){
			e.printStackTrace();
		}
	}



}
