public class Card {

	private CourtEnum court; // cyfra
	private SuitEnum suit; // kolor

	public Card(CourtEnum court, SuitEnum suit) {

		this.court = court;
		this.suit = suit;
	}

	CourtEnum getCourt() {
		return court;
	}

	SuitEnum getSuit() {
		return suit;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (court != other.court)
			return false;
		if (suit != other.suit)
			return false;
		return true;
	}

	// CourtEnum equals(Card secondCard) {
	// return secondCard.getCourt();
	//
	// }

	boolean isGreater(Card card) {
		if (court.isGreater(card.getCourt())) {
			return true;
		}
		if (court.equals(card.getCourt())) {
			return suit.isGreater(card.getSuit());
		}
		return false;

	}

	@Override
	public String toString() {
		return court + "" + suit;
	}
}
