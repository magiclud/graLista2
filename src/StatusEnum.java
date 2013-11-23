


	public enum StatusEnum {
		CLEAN, SB_PLAYED, SB_ALL_IN;

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


