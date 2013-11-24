
public enum StatusEnum {
	CLEAN, CHECK, BET, RAISE, CALL, ALL_IN, FOLD;

	public String toString() {

		switch (this) {
		case CLEAN:
			return "CLEAN";
		case CHECK:
			return "CHECK";
		case BET:
			return "BET";
		case RAISE:
			return "RAISE";
		case CALL:
			return "CALL";
		case ALL_IN:
			return "ALL-IN";
		case FOLD:
			return "FOLD";
		}
		throw new IllegalStateException("nieprawidlowy ruch");
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
