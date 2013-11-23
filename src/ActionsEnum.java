


	public enum ActionsEnum {
		CLEAN, SB_PLAYED, SB_ALL_IN, SB_CALL, FOLD;

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


