public enum StatusEnum {
	CLEAN, BET, FOLD, ALL_IN, CALL, RAISE, CHECK;

	private static StatusEnum[] allValues = values();

	public static StatusEnum fromOrdinal(int n) {
		return allValues[n];
	}
}
