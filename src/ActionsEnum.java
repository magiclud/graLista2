public enum ActionsEnum {
	BET, FOLD, ALL_IN, CHECK, CALL, RAISE;

	public String toString() {

		switch (this) {
		case BET:
			return "BET";
		case FOLD:
			return "FOLD";
		case ALL_IN:
			return "ALL_IN";
		case CHECK:
			return "CHECK";
		case CALL:
			return "CALL";
		case RAISE:
			return "RAISE";
		}
		throw new IllegalStateException("nieprawidlowa figura karty");
	}
	public boolean isGreater(CourtEnum secondCourt) {
		if (this.ordinal() > secondCourt.ordinal()) {
			return true;
		}
		return false;
	}

	public boolean isLess(CourtEnum court) {
		return this.ordinal() < court.ordinal();
	}

}
