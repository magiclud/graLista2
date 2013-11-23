import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client implements WindowListener {
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
		/************** Próba połączenia z serwerem *************************/
		// getHostName() - metoda klasy InetAddress umożliwiającą operacje
		// na adresie
		// Metoda ta zwraca nazwę komputera jako obiekt klasy String
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
	}

	private void infoOBledzie(String message) {
		// TODO wiadomosc w Swingu
		JOptionPane.showMessageDialog(null, "Klient nie mógł połaczyć się z serwerem.\nNieprawidłowy adres IP lub numer portu.", "Blad",
				JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}

	public static void main(String[] args) {

		if (args.length != 0) {
			if (args.length == 2) {// args[0] - adresIp, args[1] - adres portu
				// try {

				// if (args[0].matches(poprawnyAdresIPRegexp) &&
				// args[1].matches(poprawnyPortRegexp)) {
				// / numerPortu = Integer.parseInt(args[1]);
				// } else
				// throw new
				// LiczbaException("Niepoprawwnie wpisany adresIP lub port serwera");
				// } catch (NumberFormatException ex) {
				System.out.println("nieprawidło wprowadzony numer portu");
				// } catch (LiczbaException e) {
				// System.out.println(e.getMessage());
				// }
			} else
				System.out.println("nieprawidło wprowadzony adres serwera i numer portu");
		}
		// KlientGUI kalkulator = new KlientGUI();
		// kalkulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// kalkulator.setVisible(true);
		// kalkulator.pack(); - ustala minimalny rozmiar okna

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
