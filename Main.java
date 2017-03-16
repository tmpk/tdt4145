package prosjekt;

import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean b = true;
		while (b){
		System.out.println("Hva vil du gj�re? 1) Registrere trening, 2) Registrer treningsm�l, 3) Se �kt og time-statistikk for denne m�ned, 4) Avslutt" + "\nTast 1, 2, 3 eller 4");
		String str=in.nextLine();
		if (str.matches("1")){
			oktRegistrering(in);
		}
		else if (str.matches("2")){
			maalRegistrering(in);
		}
		else if (str.matches("3")){
			printStatistikk();
		}
		else if (str.matches("4")){
			break;
		}
		}
		in.close();
	}
	
	
	//definerer brukergrensesnitt ved registrering av �kter. Usecaselogikk delegeres til controller
	public static void oktRegistrering(Scanner in){
		RegistrerOktCtrl reg = new RegistrerOktCtrl();
		System.out.println("Vennligst oppgi dato (yyyy-mm-dd): ");
		String dato=in.nextLine();
		System.out.println("Vennligst oppgi tidspunkt (hh:mm:ss): ");
		String tidspunkt=in.nextLine(); 
		System.out.println("Vennligst oppgi �ktens varighet (hh:mm:ss): ");
		String varighet=in.nextLine();
		System.out.println("Oppgi eventuelt temperatur: ");
		String temperatur=in.nextLine();
		if (temperatur!=null && !temperatur.trim().isEmpty())
			reg.RegistrerOkt(dato, tidspunkt, varighet);
		else 
			reg.RegistrerOkt(dato, tidspunkt, varighet, temperatur);
		boolean b = true;
		while(b){
		System.out.println("Hvilken type �velse �nsker du � registrere? 1) Styrke, 2) Steady-state, 3) Intervall, 4) Avslutt registrering av �velser" + "\nTast 1, 2, 3 eller 4");
		String s = in.nextLine();
		if (s.matches("1")){
			System.out.println("Skriv inn navnet p� �velsen: ");
			String navn = in.nextLine();
			System.out.println("Skriv inn antall sett: ");
			int sett = in.nextInt();
			System.out.println("Skriv inn antall repetisjoner: ");
			int reps = in.nextInt();
			System.out.println("Skriv inn belastning: ");
			int belastning = in.nextInt();
			reg.RegistrerOktOvelse(navn, sett, reps, belastning);
			in.nextLine();
		}
		else if (s.matches("2")){
			System.out.println("Skriv inn navnet p� �velsen: ");
			String navn = in.nextLine();
			System.out.println("Skriv inn distanse (f.eks 3000m) : ");
			String distanse = in.nextLine();
			System.out.println("Skriv inn tidsbruk (hh:mm:ss): ");
			String tidsbruk = in.nextLine();
			reg.RegistrerOktOvelse(navn, distanse, tidsbruk);
		}
		else if (s.matches("3")){
			System.out.println("Skriv inn navnet p� �velsen: ");
			String navn = in.nextLine();
			System.out.println("Skriv inn type intervall (f.eks 100m eller 20s): ");
			String type = in.nextLine();
			System.out.println("Skriv inn antall sett : ");
			int sett = in.nextInt();
			reg.RegistrerOktOvelse(navn, type, sett);
			in.nextLine();
			}
		else if (s.matches("4")){
			break;
			}
		}	
		System.out.println("Vil du legge til et notat til �kten? y/n: ");
		if (in.nextLine().matches("y")){
			System.out.println("Skriv inn notatet ditt p� en linje: ");
			String notat = in.nextLine();
			reg.registrerNotat(notat);
		}
		System.out.println("Trenings�kten din er n� registrert." + "\n--------------------------");
		return;
	}
	
	//definerer brukergrensesnitt ved registrering av m�l. Usecaselogikk delegeres til controller
	public static void maalRegistrering(Scanner in){
		RegistrerMaalCtrl regM = new RegistrerMaalCtrl();
		System.out.println("Hvilke type �velse �nsker du � registrere et m�l for? 1) Styrke�velse, 2) Steady-state-�velse, 3) Intervall�velse" +"\nTast 1, 2 eller 3");
		String s = in.nextLine();
		if (s.matches("1")){
			System.out.println("Skriv inn navnet p� �velsen: ");
			String ovelse = in.nextLine();
			System.out.println("Skriv inn m�l for sett: ");
			int sett = in.nextInt();
			System.out.println("Skriv inn m�l for antall reps: ");
			int reps = in.nextInt();
			System.out.println("Skriv inn m�l for belastning: ");
			int belastning = in.nextInt();
			in.nextLine();
			System.out.println("Skriv inn startdato for m�let (yyyy-mm-dd): ");
			String dato_fra = in.nextLine();
			System.out.println("Skriv inn sluttdato (yyyy-mm-dd): ");
			String dato_til = in.nextLine();
			regM.registrerMaal(ovelse, dato_fra, dato_til, sett, reps, belastning);
			System.out.println("--------------------------");
			
		}
		else if (s.matches("2")){
			System.out.println("Skriv inn navnet p� �velsen: ");
			String ovelse = in.nextLine();
			System.out.println("Skriv inn distanse: ");
			String distanse = in.nextLine();
			System.out.println("Skriv inn m�l for tidsbruk (hh:mm:ss): ");
			String tidsbruk = in.nextLine();
			System.out.println("Skriv inn startdato for m�let (yyyy-mm-dd): ");
			String dato_fra = in.nextLine();
			System.out.println("Skriv inn sluttdato (yyyy-mm-dd): ");
			String dato_til = in.nextLine();
			regM.registrerMaal(ovelse, dato_fra, dato_til, distanse, tidsbruk);
			System.out.println("--------------------------");
		}
		else if (s.matches("3")){
			System.out.println("Skriv inn navnet p� �velsen: ");
			String ovelse = in.nextLine();
			System.out.println("Skriv inn type intervall (tid eller distanse): ");
			String type = in.nextLine();
			System.out.println("Skriv inn m�l for antall sett: ");
			int sett = in.nextInt();
			in.nextLine();
			System.out.println("Skriv inn startdato for m�let (yyyy-mm-dd): ");
			String dato_fra = in.nextLine();
			System.out.println("Skriv inn sluttdato (yyyy-mm-dd): ");
			String dato_til = in.nextLine();
			regM.registrerMaal(ovelse, dato_fra, dato_til, type, sett);
			System.out.println("--------------------------");
		}
	}
	
	//henter �kt- og timestatistikk gjennom controller
	private static void printStatistikk() {
		HentStatistikkCtrl h = new HentStatistikkCtrl();
		h.hentOktAntall();
		h.hentTotalVarighet();
		System.out.println("--------------------------");
	}	
}