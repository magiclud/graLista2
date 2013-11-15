public class Casino {

	private int pool; // pula gry

	public Casino(Player chips) {
		pool = pool + chips.getOwnChips();
	}

	public void bidding(Player player) {
		int newChips = player.getOwnChips() - player.chipsForBidding();
		player.setOwnChips(newChips);
	}
}
