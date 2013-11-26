import java.util.List;

public class Human extends Player {

	public Human(List<Card> someCards, Table currentTable, int playerID) throws ExceptionsInGame {
		super(someCards, currentTable, playerID);
	}

	@Override
	public boolean isHuman() {
		return true;
	}

	void askCards() {
		System.out.println("Niech użytkownik wpisze jakie chce karty");
	}

	void askEnd() {
		System.out.println("Niech użytkownik czy chce wchodzić w grę <<1>> czy chce pasować <<0>>");
	}

	@Override
	void gameStrategy() {
		// TODO Auto-generated method stub

	}

	@Override
	int zacznijLicytacje(int currentMax, List<ActionsEnum> possibleMoves, List<StatusEnum> playersMoves) {
		// TODO Auto-generated method stub
		return 0;
	}

}
