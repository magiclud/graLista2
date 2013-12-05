package model;
/**
 * Enum - typ wyliczeniowy -pozwala na podanie nazwy typu (KolorEnum) oraz
 * wartosci, jakie moga byc stosowane w ramach tego typu (SPADE, HEART,..). Typ
 * wyliczeniowy zabezpiecza metody w ktorej jest uzywany przed przekazaniem
 * wartosci niezdefionowanych w danym typie. Odwolanie do konkretnej wartosci ma
 * postac: KolorEnum.SPADE.
 * 
 * 
 */

public enum SuitEnum {

	SPADE, CLUB, DIAMOND, HEART;

	public String toString() {
		switch (this) {
		case SPADE:
			return "\u2660";// graficzna reprezentacja koloru karty
		case HEART:
			return "\u2665";
		case DIAMOND:
			return "\u2666";
		case CLUB:
			return "\u2663";

		}
		throw new IllegalStateException("nieprawidlowy kolor karty");
	}

	/**
	 * 
	 * @param drugiKolor
	 * @return
	 */
	boolean isGreater(SuitEnum secondColour) {
		// metoda ordinal zwraca pozycje na ktorej znajduje sie Enum
		if (this.ordinal() > secondColour.ordinal()) {
			return true;
		}
		return false;
	}
}
