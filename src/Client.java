import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Client extends JFrame implements WindowListener, ActionListener {

	private JTextField tekstPiwerwszaLiczba;
	private JButton buttonRozwiaz;
	private JTextField wyswietlacz;

	private static String pierwszaLiczba;

	static String poprawnyAdresIPRegexp = "^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$";
	static String poprawnyPortRegexp = "^[0-9]{4}+$";

	private static int numerPortu = 1793;// z domyslna wartoscia
	private static String adresIP = "localhost";// localhost: 127.0.0.1 adres IP
												// używany podczas testownia
												// kienta i serwera na tym samym
												// komputerze
	private Socket gniazdo;
	private ObjectOutputStream strumienWyjsciowy;
	private ObjectInputStream strumienWejsciowy;


	public Client() {
		try {
			gniazdo = new Socket(adresIP, numerPortu);
			strumienWyjsciowy = new ObjectOutputStream(gniazdo.getOutputStream());
			strumienWejsciowy = new ObjectInputStream(gniazdo.getInputStream());
		} catch (UnknownHostException e) {
			infoOBledzie(e.getMessage());// dialgo o nieprawidlowym adresie
											// serwera
		} catch (IOException e) {
			infoOBledzie(e.getMessage()); // gdy nieprawidłowo wpisalam numer
											// portu lub adresIp
		}
		GridLayout gridLayout = new GridLayout(5, 0);
		setSize(400, 400);
		setLocation(300, 200);
		setTitle("Klient");
		getContentPane().setBackground(new Color(230, 230, 250));
		setLayout(gridLayout);
		setResizable(false); // blokada zmiany rozmiaru

		tekstPiwerwszaLiczba = new JTextField();
		tekstPiwerwszaLiczba.setEnabled(true);
		tekstPiwerwszaLiczba.setForeground(new Color(139, 0, 139));
		tekstPiwerwszaLiczba.setBackground(new Color(135, 206, 235));
		tekstPiwerwszaLiczba.setFont(new Font("SansSerif", Font.BOLD, 16));

		buttonRozwiaz = new JButton("Podaj wynik");
		buttonRozwiaz.setEnabled(true);
		buttonRozwiaz.setVisible(true);
		buttonRozwiaz.setForeground(new Color(75, 0, 130));
		buttonRozwiaz.setFont(new Font("Dialog", Font.BOLD, 16));

		wyswietlacz = new JTextField();
		wyswietlacz.setVisible(true);
		wyswietlacz.setEnabled(true);
		wyswietlacz.setForeground(new Color(75, 0, 130));
		wyswietlacz.setFont(new Font("Dialog", Font.BOLD, 16));

		buttonRozwiaz.addActionListener(this);

		addWindowListener(this);
		add(tekstPiwerwszaLiczba);
		add(buttonRozwiaz);
		add(wyswietlacz);

	}

	public void actionPerformed(ActionEvent e) {
		Object zrodlo = e.getSource();
		if (zrodlo == buttonRozwiaz) {
			pierwszaLiczba = tekstPiwerwszaLiczba.getText();

			listenSocket(pierwszaLiczba);
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException {

		Client klient = new Client();
		klient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		klient.setVisible(true);

	}

	public void listenSocket(String pierwszaLiczba) {
		try {

			// Send a message to the client application
			// OutputStream abstrakcyjną klasą obsługującą strumień wyjściowy
			strumienWyjsciowy.writeObject(pierwszaLiczba + "od clienta");

			// Read and display the response message sent by server application
			// abstrakcyjną klasą obsługującą strumień wejściowy
			String message = (String) strumienWejsciowy.readObject();

			System.out.println("Message: " + message);
			wyswietlacz.setText(message);

			/*********** wyjątek nieznanego hosta **********/
		} catch (UnknownHostException e) {// w przypadku braku identyfikacji
											// komputera o podanej nazwie lub
											// adresie, wyjatek do metody
											// getLocalHost()
			e.printStackTrace();// Jeżeli nazwa hosta jest nieznana lub serwer
								// nazw nie działa
			/*********** wyjątek wejścia-wyjścia *********/
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	private void infoOBledzie(String message) {
		// TODO wiadomosc w Swingu
		JOptionPane.showMessageDialog(null, "Klient nie mógł połaczyć się z serwerem.\nNieprawidłowy adres IP lub numer portu.", "Blad",
				JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}


}
