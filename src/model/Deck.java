package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * ArrayList -moze zmieniac swoj rozmiar (w przeciwienstwie do zwyklej
 * tablicy),przechowuje obiekty ktorych typ okreslamy w <>, wykorzystuje metody
 * add(), remove(), indexOf(), isEmpty(), size(),...
 * 
 */
public class Deck {

	private List<Card> deck;

	/**
	 * tasowanie odbywa sie konstruktorze i nie jest dostepne jako public, czyli
	 * kazda nowa rozgrywka bedzie wymagala utworzenia obeiktu na nowo = tak
	 * jest bezpieczniej ok ok xo xo :)
	 */
	public Deck() {
		deck = new ArrayList<>(52);

		for (CourtEnum court : CourtEnum.values()) {
			for (SuitEnum suit : SuitEnum.values()) {
				this.deck.add(new Card(court, suit));
			}
		}
		shuffle();
	}

	public List<Card> getDeck() {
		return deck;
	}

	public int getSize() {
		return deck.size();
	}

	private void shuffle() {
		Collections.shuffle(this.deck);
	}

	public Card getCard() {
		Card newCard = deck.get(0);
		deck.remove(0);
		return newCard;
	}

	// Ja dodaję metodę, która z talii kart usuwa n najwyższych kart

	public ArrayList<Card> giveCards(int numbOfCards) {
		ArrayList<Card> temp = new ArrayList<Card>(numbOfCards);
		for (int i = 0; i < numbOfCards; ++i) {
			temp.add(deck.get(deck.size() - 1));
			deck.remove(deck.size() - 1);
		}
		return temp;
	}

	// public void print() {
	// // WYŚWIETLENIE PRZY UŻYCIU PĘTLI FOR EACH
	// for (Card card : deck) {
	// System.out.println(card.getKarta());
	// }
	// }
}
