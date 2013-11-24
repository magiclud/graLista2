import java.util.List;

public class Human extends Player {

	public Human(List<Card> someCards, Table currentTable, int playerID) {
		super(someCards, currentTable, playerID);
	}

	@Override
	public boolean isHuman() {
		return true;
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

	// void selectCards(List<Integer> abandonedIndexes) {
	// if (abandonedIndexes == null)
	// throw new IllegalStateException("Nie wybrano żadnych kart do wymiany");
	//
	// if (abandonedIndexes.size() > 4)
	// throw new IllegalStateException("Wybrano za dużo kart do wymiany");
	//
	// for (int i = 0; i < abandonedIndexes.size(); ++i) {
	// if (abandonedIndexes.get(i) < 0 || abandonedIndexes.get(i) > 4)
	// throw new IllegalStateException("Wprowadzono niepoprawny indeks kart");
	// }
	// requestCards(abandonedIndexes);
	// }

// Klasa bezpośrednio związana z interfacem, ma być implementowana ?

}
