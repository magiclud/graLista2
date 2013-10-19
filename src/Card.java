public class Card {

	private CourtEnum court;
	private SuitEnum suit;

	public Card(CourtEnum court, SuitEnum suit) {

		this.court = court;
		this.suit = suit;
	}

	String getKarta() {
		return court + "" + suit;
	}

	CourtEnum getCourt() {
		return court;
	}

	SuitEnum getSuit() {
		return suit;
	}

	CourtEnum equals(Card secondCard) {
		return secondCard.getCourt();

	}

	boolean isWieksza(Card card) {
		if (this.getCourt().equals(card) && this.getSuit().isBigger(card.getSuit())) {
			return true;
		}
		if (this.getCourt().isBigger(card.getCourt())) {
			return true;
		}
		return false;
	}
}
