package prosjekt;

import java.sql.*;

public class HentStatistikkCtrl extends DBConn{

	public HentStatistikkCtrl(){
		super();
	}

	
	/*metoden henter antall treningsøkter for gjeldende måned (i gjeldende år), samt en oversikt over antall økter man har trent
	 * hhv (minst en) styrkeøvelse, steadystateøvelse eller intervalløvelse. Siden en gitt økt kan inneholde alle tre typer øvelser
	 * er det mulig at summen av disse er større enn det totale antall treningsøkter.
	 */
	public void hentOktAntall(){
		try {
			Statement s=conn.createStatement();
			ResultSet rs= s.executeQuery("SELECT COUNT(*) FROM TRENINGSOKT WHERE MONTH(dato)=MONTH(NOW()) AND YEAR(dato)=YEAR(NOW())");
			rs.next();
			int antall = rs.getInt(1);
			System.out.println("Antall treningsøkter denne måneden: " + antall);
			rs=s.executeQuery("SELECT COUNT(DISTINCT treningsokt.oktId) FROM treningsokt, styrkeoktovelse WHERE treningsokt.oktId=styrkeoktovelse.oktId AND MONTH(dato)=MONTH(NOW()) AND YEAR(dato)=YEAR(NOW())");
			rs.next();
			int ant_styrke=rs.getInt(1);
			rs=s.executeQuery("SELECT COUNT(DISTINCT treningsokt.oktId) FROM treningsokt, steadystateoktovelse WHERE treningsokt.oktId=steadystateoktovelse.oktId AND MONTH(dato)=MONTH(NOW()) AND YEAR(dato)=YEAR(NOW())");
			rs.next();
			int ant_ss = rs.getInt(1);
			rs=s.executeQuery("SELECT COUNT(DISTINCT treningsokt.oktId) FROM treningsokt, intervalloktovelse WHERE treningsokt.oktId=intervalloktovelse.oktId AND MONTH(dato)=MONTH(NOW()) AND YEAR(dato)=YEAR(NOW())");
			rs.next();
			int ant_int = rs.getInt(1);
			System.out.printf("Antall økter trent styrke: %d" + "\nAntall økter trent kondisjon: %d" + "\nAntall økter trent intervaller: %d\n", ant_styrke, ant_ss, ant_int);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//metoden henter den totale varigheten (i formatet hh:mm:ss) av øktene i gjeldende måned og år. 
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
	
	

