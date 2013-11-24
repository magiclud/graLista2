public enum ActionsEnum {
	BET, FOLD, ALL_IN, CHECK, CALL, RAISE;

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
