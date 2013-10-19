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

	private List<Card> talia;

	public Deck() {
		talia = new ArrayList<>(52);

		for (FiguraEnum figura : FiguraEnum.values()) {
			for (KolorEnum kolor : KolorEnum.values()) {
				this.talia.add(new Card(figura, kolor));
			}
		}

		if (talia.size() != 52) {
			throw new IndexOutOfBoundsException("Talia nie zawiera 52 kart.");
		}

	}

	public void tasuj() {
		Collections.shuffle(this.talia);
	}

	public void wypisz() {
		// WYŚWIETLENIE PRZY UŻYCIU PĘTLI FOR EACH
		for (Card karta : talia) {
			System.out.println(karta.getKarta());
		}
	}
}
