package controller;

import java.util.ArrayList;
import java.util.List;

import model.Table;

public class Rozgrywka {

	Table table;
	int numPlayers;
	
	List<PlayerWindow> playerWindowsList;
	List<String> playerIDsList;
	
	List<PublicInformation> publicInformationsList;
	
	Rozgrywka(int numHum, int numBots, int startChips, int wpisowe){
		
		numPlayers = numHum + numBots;
		Table table = new Table(numHum, numBots, startChips, wpisowe);
		
		playerWindowsList = new ArrayList<PlayerWindow>();		
		publicInformationsList = new ArrayList<PublicInformation>();
		// Dodawanie humanow
		for(int i = 0; i < numHum; ++i) {
			playerWindowsList.add(new PlayerWindow(numPlayers));
			playerWindowsList.get(i).powiadomUzytkownika("<System> Dzięki, że do nas dołączyłeś !\n" +
					"Podaj nam proszę swój PlayerID\n");
			PublicInformation tempPublicInformation = new PublicInformation();
			// Pobieram nazwę użytkownika
			tempPublicInformation.playerID = playerWindowsList.get(i).getOutputMessage();
			tempPublicInformation.allChips = startChips;
			tempPublicInformation.chipsBidded = 0;
			tempPublicInformation.playerStatus = "CLEAN";
			publicInformationsList.add(tempPublicInformation);
		}
		// Dodawanie botow
		for(int i = numHum; i < numPlayers; ++i) {
			playerWindowsList.add(new PlayerWindow(numPlayers));
			playerWindowsList.get(i).powiadomUzytkownika("<System> Dzięki, że do nas dołączyłeś bocie!\n");
			PublicInformation tempPublicInformation = new PublicInformation();
			// Pobieram nazwę użytkownika
			tempPublicInformation.playerID = "Bot " + (i-numHum);
			tempPublicInformation.allChips = startChips;
			tempPublicInformation.chipsBidded = 0;
			tempPublicInformation.playerStatus = "Clean";
			publicInformationsList.add(tempPublicInformation);
		}
		informAll("Nadałem początkowe wartości.");

		// BARDZO WAŻNE !
		for(int i = 0; i < numHum; ++i) {
			// Wykonaj operacje dla humanow
		}
		for(int i = numHum; i < numPlayers; ++i) {
			// Wykonaj operacje dla botow
		}
		
		
		
		playerWindowsList.get(0).powiadomUzytkownika("Napisz mi jakiś tekst\n");
		String odpowiedz = playerWindowsList.get(0).getOutputMessage();
		
		System.out.println(odpowiedz);
		
		
		
		
	}
	void updateTableInformation(int playerIndex) {
		table.updatePlayerInformation(playerIndex, publicInformationsList.get(playerIndex));
	}
	PublicInformation getTableInformation(int playerIndex) {
		return table.getPlayerInformation(playerIndex, publicInformationsList.get(playerIndex));
	}
	void informAll(String message) {
		for(int i = 0; i < numPlayers; ++i) {
			playerWindowsList.get(i).updatePublicInformations(publicInformationsList, message);
		}
	}
}
