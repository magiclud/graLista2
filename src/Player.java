public class Player {
	
	private Card card1;
	private Card card2;
	private Card card3;
	private Card card4;
	private Card card5;

	Deck deckOfCard;

	public Player() {
		deckOfCard = new Deck();
		setCard1(deckOfCard.getCard());
		card2 = deckOfCard.getCard();
		card3 = deckOfCard.getCard();
		card4 = deckOfCard.getCard();
		card5 = deckOfCard.getCard();
	}

	/**
	 * @return the card1
	 */
	public Card getCard1() {
		return card1;
	}

	/**
	 * @param card1
	 *            the card1 to set
	 */
	public void setCard1(Card card1) {
		this.card1 = card1;
	}
}
