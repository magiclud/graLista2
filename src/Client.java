import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client implements WindowListener, ActionListener {
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
	}



	public static void main(String[] args) throws ClassNotFoundException, IOException {

		Client klient = new Client();
		klient.doDziela();

	}

	private void doDziela() throws IOException, ClassNotFoundException {
		try {
			gniazdo = new Socket(adresIP, numerPortu);
			strumienWyjsciowy = new ObjectOutputStream(gniazdo.getOutputStream());
			strumienWejsciowy = new ObjectInputStream(gniazdo.getInputStream());

			strumienWyjsciowy.writeObject("chce grac: ");

			String message = (String) strumienWejsciowy.readObject();

		System.out.println("Message: " + message);

			strumienWejsciowy.close();
		} catch (UnknownHostException e) {
			infoOBledzie(e.getMessage());// dialgo o nieprawidlowym adresie
											// serwera
		} catch (IOException e) {
			infoOBledzie(e.getMessage()); // gdy nieprawidłowo wpisalam numer
											// portu lub adresIp
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
