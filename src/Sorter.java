import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sorter {

	public void sort(List<Card> cardsToSort) {

		Collections.sort(cardsToSort, new cardsSuitComparator());
		Collections.sort(cardsToSort, new cardsCourtComparator());

	}

	class cardsCourtComparator implements Comparator<Card> {
		public int compare(Card card1, Card card2) {
			return card1.getCourt().ordinal() - card2.getCourt().ordinal();
		}
	}

	class cardsSuitComparator implements Comparator<Card> {
		public int compare(Card card1, Card card2) {
			return card1.getSuit().ordinal() - card2.getSuit().ordinal();
		}
	}
}
