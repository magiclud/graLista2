public class Card {

	private CourtEnum court;
	private SuitEnum suit;

	public Card(CourtEnum court, SuitEnum suit) {

		this.court = court;
		this.suit = suit;
	}

	// String getKarta() {
	// return court + "" + suit;
	// }

	CourtEnum getCourt() {
		return court;
	}

	SuitEnum getSuit() {
		return suit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((court == null) ? 0 : court.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
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
}
