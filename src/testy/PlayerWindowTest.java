package testy;

import java.util.ArrayList;
import java.util.List;

import model.ActionsEnum;

import org.junit.Test;

import controller.PlayerWindow;


public class PlayerWindowTest {
	private PlayerWindow testPlayerWindow = new PlayerWindow(3);
	private int currentMax = 50;

	List<ActionsEnum> possibleActions = new ArrayList();

	@Test
	public void testPlayerWindowTest() {
		possibleActions.add(ActionsEnum.ALL_IN);
		possibleActions.add(ActionsEnum.RAISE);
		possibleActions.add(ActionsEnum.FOLD);
		possibleActions.add(ActionsEnum.CALL);
		testPlayerWindow.makeMoveHuman(currentMax, possibleActions, null);
	}

}
