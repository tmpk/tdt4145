package prosjekt;

import java.sql.*;

public class HentStatistikkCtrl extends DBConn{

	public HentStatistikkCtrl(){
		super();
	}

	
	/*metoden henter antall trenings�kter for gjeldende m�ned (i gjeldende �r), samt en oversikt over antall �kter man har trent
	 * hhv (minst en) styrke�velse, steadystate�velse eller intervall�velse. Siden en gitt �kt kan inneholde alle tre typer �velser
	 * er det mulig at summen av disse er st�rre enn det totale antall trenings�kter.
	 */
	public void hentOktAntall(){
		try {
			Statement s=conn.createStatement();
			ResultSet rs= s.executeQuery("SELECT COUNT(*) FROM TRENINGSOKT WHERE MONTH(dato)=MONTH(NOW()) AND YEAR(dato)=YEAR(NOW())");
			rs.next();
			int antall = rs.getInt(1);
			System.out.println("Antall trenings�kter denne m�neden: " + antall);
			rs=s.executeQuery("SELECT COUNT(DISTINCT treningsokt.oktId) FROM treningsokt, styrkeoktovelse WHERE treningsokt.oktId=styrkeoktovelse.oktId AND MONTH(dato)=MONTH(NOW()) AND YEAR(dato)=YEAR(NOW())");
			rs.next();
			int ant_styrke=rs.getInt(1);
			rs=s.executeQuery("SELECT COUNT(DISTINCT treningsokt.oktId) FROM treningsokt, steadystateoktovelse WHERE treningsokt.oktId=steadystateoktovelse.oktId AND MONTH(dato)=MONTH(NOW()) AND YEAR(dato)=YEAR(NOW())");
			rs.next();
			int ant_ss = rs.getInt(1);
			rs=s.executeQuery("SELECT COUNT(DISTINCT treningsokt.oktId) FROM treningsokt, intervalloktovelse WHERE treningsokt.oktId=intervalloktovelse.oktId AND MONTH(dato)=MONTH(NOW()) AND YEAR(dato)=YEAR(NOW())");
			rs.next();
			int ant_int = rs.getInt(1);
			System.out.printf("Antall �kter trent styrke: %d" + "\nAntall �kter trent kondisjon: %d" + "\nAntall �kter trent intervaller: %d\n", ant_styrke, ant_ss, ant_int);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//metoden henter den totale varigheten (i formatet hh:mm:ss) av �ktene i gjeldende m�ned og �r. 
	public void hentTotalVarighet(){
		try {
			Statement s=conn.createStatement();
			ResultSet rs= s.executeQuery("SELECT SEC_TO_TIME( SUM( TIME_TO_SEC( `varighet` ) ) ) AS total_varighet FROM treningsokt WHERE MONTH(dato)=MONTH(NOW()) AND YEAR(dato)=YEAR(NOW())");
			rs.next();
			java.sql.Time varighet = rs.getTime(1);
			System.out.println("Total varighet: " + varighet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	
	

