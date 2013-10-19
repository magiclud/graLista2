public enum CourtEnum {
	TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;
	public String toString() {

		switch (this) {
		case TWO:
			return "2";
		case THREE:
			return "3";
		case FOUR:
			return "4";
		case FIVE:
			return "5";
		case SIX:
			return "6";
		case SEVEN:
			return "7";
		case EIGHT:
			return "8";
		case NINE:
			return "9";
		case TEN:
			return "10";
		case JACK:
			return "J";
		case QUEEN:
			return "Q";
		case KING:
			return "K";
		case ACE:
			return "A";
		}
		throw new IllegalStateException("nieprawidlowa figura karty");
	}

	public boolean isGreater(CourtEnum secondCourt) {
		if (this.ordinal() > secondCourt.ordinal()) {
			return true;
		}
		return false;
	}

}
