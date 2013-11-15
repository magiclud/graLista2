import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameTest {

	public static void main(String[] args) {

		System.out.println("System: Jeżeli chcesz zacząć grę, wpisz START i naciśnij ENTER,\n" +
				"w przeciwnym razie naciśnij ENTER i wyjdziesz z gry");
		String inString;
		Scanner scanIn = new Scanner(System.in);
		inString = scanIn.nextLine();
		if(!inString.equals("START")) {
			System.out.println("System: Wychodzę z gry");
			scanIn.close();
		}
		else {
			int numHum = 0, numBot= 0;
			Table myTable = null;
			System.out.println("System: Zaczynam grę");
			System.out.println("System: Wpisz ilu ma grać ludzi << 2<=numHum+numBot<=4 >>:");
			try {
			numHum = scanIn.nextInt();
			}
			catch(Exception e) {
				System.out.println("System:\nWprowadzono śmieciowe dane, kończę !");
				scanIn.close();
				System.exit(0);
			}
			System.out.println("System: Wpisz ile ma grać botów << 2<=numHum+numBot<=4 >>:");
			try {
				numBot = scanIn.nextInt();
				}
				catch(Exception e) {
					System.out.println("System:\nWprowadzono śmieciowe dane, kończę !");
					scanIn.close();
					System.exit(0);
				}
			if(numBot < 0 || numHum < 0 || numHum + numBot > 4 || numHum + numBot < 2) {
				System.out.println("System:\nLącznie liczba botów i ludzi ma spełniać warunek << 2<=numHum+numBot<=4 >>, kończę !");
				scanIn.close();
				System.exit(0);
			}
			inString = scanIn.nextLine();

			
			myTable = new Table(numHum,numBot);
			for(int i = 0; i < numHum; ++i) { 
				System.out.println("System: Partię rozgrywa human, ID: " + (i + 1)); 
				System.out.println("Human: Masz na ręce:");
				myTable.players.get(i).showCards();
				System.out.println("\nSystem: Wpisz jakie karty chcesz pobrać wpisując ich indeksy.\n" +
						"Na przykład wpisz <<0,1,3>> aby pobrać karty [0], [1], [3]\n" +
						"Aby nic nie pobierać, po prostu naciśnij ENTER.");
				
				inString = scanIn.nextLine();
				Pattern pattern = 
						Pattern.compile("^(([0-9],?)+)$");
				Matcher matcher = pattern.matcher(inString);
				Boolean properIn = false;
		    	List<Integer> splittedInt = new ArrayList<Integer>();
				 while (matcher.find()) {
				      if(matcher.group().length() == inString.length()) {
				    	  String[] splitted = inString.split(",");
				    	  properIn = true;
				    	  if(splitted.length > 4) {
				    		  properIn = false;
				    		  System.out.println("System: możesz wymienić od 1 do 4 kart !");
				    	  }
				    	  for(int j = 0; j < splitted.length; ++j) {
				    		  splittedInt.add(Integer.parseInt(splitted[j]));
				    		  if(splittedInt.get(j)>4 || splittedInt.get(j)<0) {
				    			  properIn = false;
				    			  System.out.println("System: Podałeś niepoprawne indeksy.");
				    		  }
				    		  for(int k = 0; k < splittedInt.size()-1; ++k) {
				    			  if(splittedInt.get(j).equals(splittedInt.get(k))) {
				    				  System.out.println("System: Wystąpiły powtórzenia w twoim ciągu.");
				    				  properIn = false;
				    			  }
				    		  }
				    	  } 
				    	  
				      }
				    }
				 if(properIn == true) {
					 myTable.players.get(i).requestCards(splittedInt);
				 }
				 else if(inString.equals("")) {
					 System.out.println("System: Gracz nie chce wymieniać kart.");
				 }
				 else System.out.println("System: Gracz wprowadził niepoprawne dane.\n" +
				 		"Gracz nie wymienia kart.");
				 System.out.println("Gracz: Wchodzę do gry z kartami:");
					myTable.players.get(i).showCards();
					System.out.println("\n");
				 System.out.println();
			}
			for(int i = numHum; i < numHum + numBot; ++i) { 
				System.out.println("System: Partię rozgrywa bot, ID: " + (i + 1));
				System.out.println("Bot: Mam na ręce:");
				myTable.players.get(i).showCards();
				System.out.println("\nBot: Prowadzę rozgrywkę: ");
				myTable.players.get(i).joinGame();
				System.out.println("Bot: Wchodzę do gry z kartami:");
				myTable.players.get(i).showCards();
				System.out.println("\n");
			}
			List<Integer> winners = Judge.selectWinners(myTable.players);
			for(int i =  0; i < winners.size(); ++i) {
				System.out.println("System: Player " + (winners.get(i)+1) + "<< wygrywa" );
			}
		}
		// TUTAJ PROSZĘ WPISZ METODĘ JAK MAJĄ BYĆ OBSŁUGIWANE BOTY KTÓRY, JAK I CZY WYGRYWA
		//		
		//
		
		
		System.out.println("Doszedłem do końca");
		scanIn.close();

	}

}
