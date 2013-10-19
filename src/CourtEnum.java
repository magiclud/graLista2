public enum CourtEnum {
	DWOJKA, TROJKA, CZWORKA, PIATKA, SZOSTKA, SIODEMKA, OSEMKA, DZIEWIATKA, DZIESIATKA, JOPEK, DAMA, KROL, AS;
	public String toString() {

		switch (this) {
		case DWOJKA:
			return "2";
		case TROJKA:
			return "3";
		case CZWORKA:
			return "4";
		case PIATKA:
			return "5";
		case SZOSTKA:
			return "6";
		case SIODEMKA:
			return "7";
		case OSEMKA:
			return "8";
		case DZIEWIATKA:
			return "9";
		case DZIESIATKA:
			return "10";
		case JOPEK:
			return "J";
		case DAMA:
			return "Q";
		case KROL:
			return "K";
		case AS:
			return "A";
		}
		throw new IllegalStateException("nieprawidlowa figura karty");
	}

	public boolean isBigger(CourtEnum secondCourt) {
		if (this.ordinal() > secondCourt.ordinal()) {
			return true;
		}
		return false;
	}

}
