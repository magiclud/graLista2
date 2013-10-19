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
		setCard2(deckOfCard.getCard());
		setCard3(deckOfCard.getCard());
		setCard4(deckOfCard.getCard());
		setCard5(deckOfCard.getCard());
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

	/**
	 * @return the card2
	 */
	public Card getCard2() {
		return card2;
	}

	/**
	 * @param card2 the card2 to set
	 */
	public void setCard2(Card card2) {
		this.card2 = card2;
	}

	/**
	 * @return the card3
	 */
	public Card getCard3() {
		return card3;
	}

	/**
	 * @param card3 the card3 to set
	 */
	public void setCard3(Card card3) {
		this.card3 = card3;
	}

	/**
	 * @return the card4
	 */
	public Card getCard4() {
		return card4;
	}

	/**
	 * @param card4 the card4 to set
	 */
	public void setCard4(Card card4) {
		this.card4 = card4;
	}

	/**
	 * @return the card5
	 */
	public Card getCard5() {
		return card5;
	}

	/**
	 * @param card5 the card5 to set
	 */
	public void setCard5(Card card5) {
		this.card5 = card5;
	}
}
