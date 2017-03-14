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
			System.out.println("Oppgi temperatur i grader: ");
			String temperatur=in.nextLine();
			//if (temperatur==null){
					//reg.RegistrerOkt(dato, tidspunkt, varighet);}
			//else {
					reg.RegistrerOkt(dato, tidspunkt, varighet, temperatur);
			while (in.hasNext()) {
			System.out.println("Hvilken type øvelse ønsker du å registrere?" + "\n" + "1) Styrke, 2) Steady-state, 3) Intervall, 4) Avslutt registrering av øvelser");
				if (in.nextLine().matches("1")){
					System.out.println("Skriv inn navnet på øvelsen: ");
					String navn = in.nextLine();
					System.out.println("Skriv inn antall sett: ");
					int sett = in.nextInt();
					System.out.println("Skriv inn antall repetisjoner: ");
					int reps = in.nextInt();
					System.out.println("Skriv inn belastning: ");
					int belastning = in.nextInt();
					reg.RegistrerSOktOvelse(navn, sett, reps, belastning);
				}
				else if (in.nextLine().matches("2")){
					System.out.println("Skriv inn navnet på øvelsen: ");
					String navn = in.nextLine();
					System.out.println("Skriv inn distanse (f.eks 10000m) : ");
					String distanse = in.nextLine();
					System.out.println("Skriv inn tidsbruk (hh:mm:ss): ");
					String tidsbruk = in.nextLine();
					reg.RegistrerSSOktOvelse(navn, distanse, tidsbruk);
				}
				else if (in.nextLine().matches("3")){
					System.out.println("Skriv inn navnet på øvelsen: ");
					String navn = in.nextLine();
					System.out.println("Skriv inn type intervall (f.eks 100m eller 20s): ");
					String type = in.nextLine();
					System.out.println("Skriv inn antall sett : ");
					int sett = in.nextInt();
					reg.RegistrerIOktOvelse(navn, type, sett);
				}
				else if (in.nextLine().matches("4")){
					break;
				}
				System.out.println("Vil du legge til et notat til økten? y/n: ");
				if (in.nextLine().matches("y")){
					System.out.println("Skriv inn notatet ditt på en linje: ");
					String notat = in.nextLine();
					reg.registrerNotat(notat);
				}
			}
	}
}	
}