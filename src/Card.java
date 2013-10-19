public class Card {

	private FiguraEnum figura;
	private KolorEnum kolor;

	public Card(FiguraEnum figura, KolorEnum kolor) {

		this.figura = figura;
		this.kolor = kolor;
	}

	String getKarta() {
		return figura + "" + kolor;
	}

	FiguraEnum getCard() {
		return figura;
	}

	KolorEnum getSuit() {
		return kolor;
	}

	boolean isWieksza(Card karta) {
		if (this.getCard().equals(karta.getCard()) && this.getSuit().isWieksza(karta.getSuit())) {
			return true;
		}
		if (this.getCard().isWieksza(karta.getCard())) {
			return true;
		}
		return false;
	}
}
