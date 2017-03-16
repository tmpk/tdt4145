package prosjekt;
import java.sql.*;

public class RegistrerOktCtrl extends DBConn {
		private int oktId;
	
	public RegistrerOktCtrl(){
		super();
	}
	
	//metode for å registrere økt uten oppgitt temperatur
	public void RegistrerOkt(String dato, String tidspunkt, String varighet){
		PreparedStatement ps;
		java.sql.Date date = java.sql.Date.valueOf(dato);
		java.sql.Time time = java.sql.Time.valueOf(tidspunkt);
		java.sql.Time duration = java.sql.Time.valueOf(varighet);
		try {
			ps = conn.prepareStatement("INSERT INTO TRENINGSOKT (dato, tidspunkt, varighet) "
					+ " VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1, date);
			ps.setTime(2, time);
			ps.setTime(3, duration);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()){
				int oId=rs.getInt(1);
				this.setOktId(oId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//metode for å registrere økt 
	public void RegistrerOkt(String dato, String tidspunkt, String varighet, String temperatur){
		PreparedStatement ps;
		java.sql.Date date = java.sql.Date.valueOf(dato);
		java.sql.Time time = java.sql.Time.valueOf(tidspunkt);
		java.sql.Time duration = java.sql.Time.valueOf(varighet);
		try {
			ps = conn.prepareStatement("INSERT INTO TRENINGSOKT (dato, tidspunkt, varighet, temperatur) "
					+ " VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1, date);
			ps.setTime(2, time);
			ps.setTime(3, duration);
			ps.setString(4, temperatur);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()){
				int oId=rs.getInt(1);
				this.setOktId(oId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//denne metoden registrerer styrkeøvelser tilhørende økten
	public void RegistrerOktOvelse(String navn, int sett, int reps, int belastning) {
		int ovelseId=0;
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT ovelseId FROM STYRKEOVELSE " + "WHERE navn = ?");
			stmt.setString(1, navn);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) ovelseId = rs.getInt(1);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO STYRKEOKTOVELSE (oktId, ovelseId, belastning, sett, reps)  " + "VALUES (?, ?, ?, ?, ?)");
			ps.setInt(1, this.getOktId());
			ps.setInt(2, ovelseId);
			ps.setInt(3, belastning);
			ps.setInt(4, sett);
			ps.setInt(5, reps);
			ps.executeUpdate();
			System.out.println("Øvelse registrert!\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//denne metoden registrerer steadystateøvelser tilhørende økten
	public void RegistrerOktOvelse(String navn, String distanse, String tidsbruk) {
		int ovelseId=0;
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT ovelseId FROM STEADYSTATEOVELSE " + "WHERE navn = ? AND distanse = ?");
			stmt.setString(1, navn);
			stmt.setString(2, distanse);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())ovelseId = rs.getInt("ovelseId");
			PreparedStatement ps = conn.prepareStatement("INSERT INTO STEADYSTATEOKTOVELSE (oktId, ovelseId, tidsbruk) " + "VALUES (?, ?, ?)");
			ps.setInt(1, this.getOktId());
			ps.setInt(2, ovelseId);
			ps.setTime(3, java.sql.Time.valueOf(tidsbruk));
			ps.executeUpdate();
			System.out.println("Øvelse registrert!\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//denne metoden registrerer intervalløvelser tilhørende økten
	public void RegistrerOktOvelse(String navn, String type, int sett) {
		int ovelseId=0;
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT ovelseId FROM INTERVALLOVELSE " + "WHERE navn = ? AND type = ?");
			stmt.setString(1, navn);
			stmt.setString(2, type);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())ovelseId = rs.getInt("ovelseId");
			PreparedStatement ps = conn.prepareStatement("INSERT INTO INTERVALLOKTOVELSE (oktId, ovelseId, sett) " + "VALUES (?, ?, ?)");
			ps.setInt(1, this.getOktId());
			ps.setInt(2, ovelseId);
			ps.setInt(3, sett);
			ps.executeUpdate();
			System.out.println("Øvelse registrert!\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	//denne metoden registrerer et notat for økten
	public void registrerNotat(String notat){
		try {
			PreparedStatement s = conn.prepareStatement("INSERT INTO NOTAT " + "VALUES (?, ?)");
			s.setInt(1, this.getOktId());
			s.setString(2, notat);
			s.executeUpdate();
			System.out.println("Notat registrert!\n");
		} catch (SQLException e) {
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
