public enum KolorEnum {

	SPADE, HEART, DIAMOND, CLUB;

	public String toString() {
		switch (this) {
		case SPADE:
			return "\u2660";
		case HEART:
			return "\u2665";
		case DIAMOND:
			return "\u2666";
		case CLUB:
			return "\u2663";

		}
		throw new IllegalStateException("nieprawidlowy kolor karty");
	}
}
