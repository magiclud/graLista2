public class Casino {

	private int extraPool; // pula gry

	public Casino(Player chips) {
		// extraPool = extraPool + chips.getOwnChips();
	}

	public void bidding(Player player) {
		int newChips = player.getOwnChips() - player.getChipsForBidding();
		player.setOwnChips(newChips);
	}
}
