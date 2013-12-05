package controller;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.ActionsEnum;
import model.ExceptionsInGame;

public class PlayerWindow extends JFrame implements ActionListener {

	// semafor będzie zatrzymywał aplikację, jak będziemy oczekiwać na odpowiedź
	// użytkownika
	private Semaphore SENDsemaphore = new Semaphore(0);
	private Semaphore ACTIONsemaphore = new Semaphore(0);

	// elementy naszej aplikacji
	private JButton sendButton, checkButton, betButton, raiseButton, callButton, foldButton, allInButton;
	private List<JButton> cardButtonsList;

	private List<PublicInformationPanel> publicInformationPanelsList;

	JTextArea inputBox;
	JTextField outputBox;

	String PlayerID;
	public int numPlayers;
	public String lastMove;

	public PlayerWindow(int numPlayers) {
		super("Okno Playera");
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		this.numPlayers = numPlayers;

		// obsługa inputBox i outputBox
		inputBox = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(inputBox);
		scrollPane.setBounds(10, 10, 500, 300);
		add(scrollPane);

		sendButton = new JButton("SEND");
		sendButton.setBounds(10, 320, 80, 30);
		sendButton.addActionListener(this);
		add(sendButton);

		outputBox = new JTextField();
		outputBox.setBounds(100, 320, 410, 30);
		add(outputBox);

		// betButton, checkButton, raiseButton, callButton, foldButton,
		// allInButton;
		betButton = new JButton("BET");
		betButton.setBounds(520, 10, 80, 30);
		betButton.addActionListener(this);
		betButton.setEnabled(false);
		add(betButton);

		checkButton = new JButton("CHECK");
		checkButton.setBounds(610, 10, 80, 30);
		checkButton.addActionListener(this);
		checkButton.setEnabled(false);
		add(checkButton);

		raiseButton = new JButton("RAISE");
		raiseButton.setBounds(700, 10, 80, 30);
		raiseButton.addActionListener(this);
		raiseButton.setEnabled(false);
		add(raiseButton);

		callButton = new JButton("CALL");
		callButton.setBounds(520, 50, 80, 30);
		callButton.addActionListener(this);
		callButton.setEnabled(false);
		add(callButton);

		foldButton = new JButton("FOLD");
		foldButton.setBounds(610, 50, 80, 30);
		foldButton.addActionListener(this);
		foldButton.setEnabled(false);
		add(foldButton);

		allInButton = new JButton("ALL IN");
		allInButton.setBounds(700, 50, 80, 30);
		allInButton.addActionListener(this);
		allInButton.setEnabled(false);
		add(allInButton);

		// przyciski kart
		List<JButton> cardButtonsList = new ArrayList<JButton>();
		for (int i = 0; i < 2; ++i) {
			JButton tempCardButton = new JButton("C" + i);
			tempCardButton.setBounds(565 + i * 90, 120, 80, 30);
			cardButtonsList.add(tempCardButton);
			add(cardButtonsList.get(i));
		}
		for (int i = 2; i < 5; ++i) {
			JButton tempCardButton = new JButton("C" + i);
			tempCardButton.setBounds(520 + (i - 2) * 90, 160, 80, 30);
			cardButtonsList.add(tempCardButton);
			add(cardButtonsList.get(i));
		}
		for (int i = 0; i < 5; ++i) {
			cardButtonsList.get(i).setEnabled(false);
		}

		// public information containers
		publicInformationPanelsList = new ArrayList<PublicInformationPanel>();
		for (int i = 0; i < numPlayers; ++i) {
			PublicInformationPanel tempPublicInformation = new PublicInformationPanel();
			tempPublicInformation.setBounds(10 + i * 195, 380, 120, 160);
			publicInformationPanelsList.add(tempPublicInformation);
			add(tempPublicInformation);
		}

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == sendButton) {
			SENDsemaphore.release();
		}

		if (source == betButton) {
			lastMove = "BET";
			ACTIONsemaphore.release();
		}
		if (source == checkButton) {
			lastMove = "CHECK";
			ACTIONsemaphore.release();
		}
		if (source == raiseButton) {
			lastMove = "RAISE";
			ACTIONsemaphore.release();
		}
		if (source == callButton) {
			lastMove = "CALL";
			ACTIONsemaphore.release();
		}
		if (source == foldButton) {
			lastMove = "FOLD";
			ACTIONsemaphore.release();
		}
		if (source == allInButton) {
			lastMove = "ALL_IN";
			ACTIONsemaphore.release();
		}
	}

	public String getOutputMessage() {
		try {
			SENDsemaphore.acquire();
		} catch (InterruptedException e) {
			System.out.println("Coś nie tak z naszym  SENDsemaforem");
		}
		return outputBox.getText();
	}

	public void powiadomUzytkownika(String s) {
		inputBox.append(s);
		// Scroll to bottom
		inputBox.setCaretPosition(inputBox.getDocument().getLength());

	}

	public void updatePublicInformations(List<PublicInformation> publicInformationsList, String message) {
		inputBox.append("<System> " + message + "\n");
		for (int i = 0; i < numPlayers; ++i) {

			publicInformationPanelsList.get(i).playerID.setText(publicInformationsList.get(i).playerID);
			publicInformationPanelsList.get(i).chipsBidded.setText("" + publicInformationsList.get(i).chipsBidded);
			publicInformationPanelsList.get(i).allChips.setText("" + publicInformationsList.get(i).allChips);
			publicInformationPanelsList.get(i).playerStatus.setText(publicInformationsList.get(i).getPlayerStatus());
		}
	}

	public PublicInformation makeMoveHuman(int currentMax, List<ActionsEnum> possibleActions, PublicInformation in) throws ExceptionsInGame {
		// włącz odpowiednie mozliwe przyciski
		if (possibleActions.contains(ActionsEnum.BET)) {
			betButton.setEnabled(true);
		}
		if (possibleActions.contains(ActionsEnum.FOLD)) {
			foldButton.setEnabled(true);
		}
		if (possibleActions.contains(ActionsEnum.ALL_IN)) {
			allInButton.setEnabled(true);
		}
		if (possibleActions.contains(ActionsEnum.CHECK)) {
			checkButton.setEnabled(true);
		}
		if (possibleActions.contains(ActionsEnum.CALL)) {
			callButton.setEnabled(true);
		}
		if (possibleActions.contains(ActionsEnum.RAISE)) {
			raiseButton.setEnabled(true);
		}
		try {
			ACTIONsemaphore.acquire();// dopoki nie nacisne przyciskow
		} catch (InterruptedException e) {
			System.out.println("Cos nie tak poszlo z ActionSemaforem");
		}
		// betButton, checkButton, raiseButton, callButton, foldButton,
		// allInButton;
		int bidded = 0;
		if (lastMove == "BET" || lastMove == "RAISE") {

			try {
				bidded = Integer.parseInt(getOutputMessage());
			} catch (NumberFormatException ex) {
				throw new ExceptionsInGame("Nie pobrano parametru chips z pola wyjściowego");
			}
			if (bidded < currentMax) {
				throw new ExceptionsInGame("Za mało obstawiasz");
			}
		}
		if (lastMove == "CHECK" || lastMove == "CALL") {
			if (in.allChips < currentMax) {
				throw new ExceptionsInGame("Za mało obstawiasz");
			}
		}
		if (lastMove == "BET") {
			in.setPlayerStatus("BET");
			in.chipsBidded = bidded;
			return in;
		}
		if (lastMove == "RAISE") {
			in.setPlayerStatus("RAISE");
			in.chipsBidded = bidded;
			return in;
		}
		if (lastMove == "CHECK") {
			in.setPlayerStatus("CHECK");
			in.chipsBidded = currentMax;
			return in;
		}
		if (lastMove == "CALL") {
			in.setPlayerStatus("CALL");
			in.chipsBidded = currentMax;
			return in;
		}
		if (lastMove == "FOLD") {
			in.setPlayerStatus("FOLD");
			return in;
		}
		if (lastMove == "ALL_IN") {
			in.setPlayerStatus("ALL_IN");
			in.chipsBidded = in.allChips;
			return in;
		}
		return null;

	}

	public PublicInformation makeMoveBot(int currentMax, List<ActionsEnum> possibleActions, PublicInformation publicInformation) {
		// TODO Auto-generated method stub
		return null;
	}
}

class PublicInformationPanel extends JPanel {
	public JLabel playerID, chipsBidded, allChips, playerStatus;
	private JLabel playerIDTag, chipsBiddedTag, allChipsTag, lastMoveTag;

	PublicInformationPanel() {

		super();
		setLayout(null);
		setSize(120, 160);

		playerIDTag = new JLabel("PlayerID :");
		Font font = playerIDTag.getFont();
		Font bold = new Font(font.getName(), Font.BOLD, font.getSize() + 2);
		Font normal = new Font(font.getName(), Font.PLAIN, font.getSize());
		playerIDTag.setFont(bold);
		playerIDTag.setBounds(0, 0, 100, 15);
		playerID = new JLabel("default");
		playerID.setBounds(20, 20, 100, 15);
		playerID.setFont(normal);
		add(playerIDTag);
		add(playerID);

		chipsBiddedTag = new JLabel("ChipsBidded :");
		chipsBiddedTag.setBounds(0, 40, 100, 15);
		chipsBidded = new JLabel("default");
		chipsBidded.setBounds(20, 60, 100, 15);
		chipsBidded.setFont(normal);

		add(chipsBiddedTag);
		add(chipsBidded);

		allChipsTag = new JLabel("AllChips :");
		allChipsTag.setBounds(0, 80, 120, 15);
		allChips = new JLabel("default");
		allChips.setBounds(20, 100, 120, 15);
		allChips.setFont(normal);
		add(allChipsTag);
		add(allChips);

		lastMoveTag = new JLabel("LastMove :");
		lastMoveTag.setBounds(0, 120, 140, 15);
		playerStatus = new JLabel("default");
		playerStatus.setBounds(20, 140, 140, 15);
		playerStatus.setFont(normal);
		add(playerStatus);
		add(lastMoveTag);
	}

	void setMove(String s) {
		this.playerStatus.setText(s);
	}

}
