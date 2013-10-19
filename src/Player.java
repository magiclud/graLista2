import java.util.List;

public class Player {
	
	List<Card> cardsForPlayer;

	Deck deckOfCard;

	public Player(List<Card> cardsForPlayer) {
		if (cardsForPlayer.size() > 5) {
			throw new IllegalStateException("Niepoprawna ilosc kart dla gracza");


		}
	}

	// public Card exchangeTheCard(Card cardToExChange, int
	// countCardsToExchange){
	// for (int i=0; i<countCards; i++){
	// Card newCard = deckOfCard.getCard();
	// }
	// }

}
