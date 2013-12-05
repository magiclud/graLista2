package controller;

public class PublicInformation {
	public String playerID;
	public int chipsBidded;
	public int allChips;
	public String playerStatus;

	public String getPlayerStatus() {
		return playerStatus;
	}

	/**
	 * @param playerStatus
	 *            the playerStatus to set
	 */
	public void setPlayerStatus(String playerStatus) {
		this.playerStatus = playerStatus;
	}
}
