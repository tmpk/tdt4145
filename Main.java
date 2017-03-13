package prosjekt;

import java.sql.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Hva vil du gjøre? 1) Registrere trening");
		if (in.nextLine().matches("1")){
			RegistrerOktCtrl reg = new RegistrerOktCtrl();
			System.out.println("Vennligst oppgi dato (yyyy-mm-dd): ");
			String dato=in.nextLine();
			System.out.println("Vennligst oppgi tidspunkt (hh:mm): ");
			String tidspunkt=in.nextLine(); 
			tidspunkt.concat(":00");
			System.out.println("Vennligst oppgi varighet (hh:mm): ");
			String varighet=in.nextLine();
			varighet.concat(":00");
			System.out.println("Oppgi eventuelt temperatur i grader: ");
			Integer temperatur=in.nextInt();
			if (temperatur==null){
					reg.RegistrerOkt(dato, tidspunkt, varighet);}
			else {
					reg.RegistrerOkt(dato, tidspunkt, varighet, temperatur);}
			while (in.hasNext()) {
			System.out.println("Hvilken type øvelse ønsker du å registrere?" + "\n" + "1) Styrke, 2) Steady-state, 3) Intervall");
			if (in.nextLine().matches("1")){
				System.out.println("Skriv inn navnet på øvelsen: ");
				String ov = in.nextLine();
				Statement stmt = null;
				try {
					ResultSet rs = stmt.executeQuery("SELECT navn FROM STYRKEØVELSE WHERE STYRKEØVELSE.navn" + "ov");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Skriv inn antall sett: ");
				int sett = in.nextInt();
				System.out.println("Skriv inn antall repetisjoner: ");
				int reps = in.nextInt();
				System.out.println("Skriv inn belastning: ");
				int belastning = in.nextInt();
				reg.RegistrerSOktOvelse(sett, reps, belastning);
				
			}
			}
	}
	}
	}

