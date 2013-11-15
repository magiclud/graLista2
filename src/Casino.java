public class Casino {

	private int pool; // pula gry

	public Casino(Player chips) {
		pool = pool + chips.getOwnChips();
	}

}
