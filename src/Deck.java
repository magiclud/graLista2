import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

	private List<Card> talia;

	public Deck() {
		talia = new ArrayList<>(52);

		for (FiguraEnum figura : FiguraEnum.values()) {
			for (KolorEnum kolor : KolorEnum.values()) {
				this.talia.add(new Card(figura, kolor)); // I find this
															// readable.
			}
		}

		if (talia.size() != 52) {
			throw new IndexOutOfBoundsException("Deck does not contain 52 cards.");
		}

	}

	public void tasuj() {
		Collections.shuffle(this.talia);
	}

	public void wypisz() {
		for (Card karta : talia) {
			System.out.println(karta.getKarta());
		}
	}
}
