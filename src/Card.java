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
}
