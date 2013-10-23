import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.List;


public class Human extends Player {


	public Human(List<Card> someCards, Table currentTable) {
		super(someCards, currentTable);
	}

	void showCards() { 
		for(int i = 0; i < ownCards.size(); ++i) { 
			System.out.print("[" + i + "]" + " " + ownCards.get(i) + " "); 
		} 
	}
	void askCards() {
		System.out.println("Niech użytkownik wpisze jakie chce karty");
	}
	void askEnd() {
		System.out.println("Niech użytkownik czy chce wchodzić w grę <<1>> czy chce pasować <<0>>");
	}
	// Pobiera karty indeksowane numerami 0,1,2,3,4
	// żąda od użytkownika podania indeksów kart w formie np.
	// Chcę wymienić 0,2,4 to wpisuję 0,2,4
	Boolean selectCards(List<Integer> abandonedIndexes) { // Zwraca true jak się uda wymienić karty, false jak użytkownik coś pokręcił :)
		if(abandonedIndexes == null) 
			return true;
		if(abandonedIndexes.size() > 4 )
			return false;
		for(int i = 0; i < abandonedIndexes.size(); ++i) {
			if(abandonedIndexes.get(i) < 0 || abandonedIndexes.get(i) > 4  )
				return false;
		}
		requestCards(abandonedIndexes);
		return true;
	}
	
	List<Card> joinGame() {
		showCards();
		
		Boolean isDone = false;
		do {
		askCards();
		List<Integer> abandonedIndexes = null;
		// Tutaj będziemy pobierać indeksy od użytkownika
		isDone = selectCards(abandonedIndexes);
		}
		while(!isDone);
		
		showCards();
		
		Boolean wannaPlay = false;
		
		askEnd();
		
		if(wannaPlay)
			return ownCards;
		 
		return null;
	}





}
