import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static int port = 1793;
	private ServerSocket server = null;

	public Server() {
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public void listenSocket() {
		System.out.println("Czekanie na polczenie od klienta...");
		while (true) {// główna pętla serwera
			try {
				// stworz gniazdo
				new ConnectionHandler(server.accept());
			} catch (IOException e) {
				System.out.println("Accept failed: 4444");
				System.exit(-1);
			}
		}
	}

	class ConnectionHandler implements Runnable {
		private Socket socket;

		public ConnectionHandler(Socket socket) {
			this.socket = socket;

			Thread t = new Thread(this);
			t.start();
		}

		public void run() {

			System.out.println("nazwiazano polaczenie z klientem " + socket.getInetAddress());
			System.out.println("Czekanie na wiadomosc od klienta... ");

			try {
				String liczbaPierwsza = null;
				String dzialanie = null;
				String liczbaDruga = null;
				String wynik = null;

				ObjectInputStream strumienWejsciowy = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream strumienWyjsciowy = new ObjectOutputStream(socket.getOutputStream());
				String message = (String) strumienWejsciowy.readObject();

				while (!message.equals("end")) {

					String[] tablica = message.split(" ");
					if (tablica.length != 3) {
						// throw new LiczbaException("Wpelnij wszystkie pola");
					}
					liczbaPierwsza = tablica[0];
					dzialanie = tablica[1];
					liczbaDruga = tablica[2];

					// try {
					// wynik = oblicz(liczbaPierwsza, dzialanie, liczbaDruga);
					System.out.println("Odpowiedz do klienta: " + wynik);

					strumienWyjsciowy.writeObject("Wynik obliczeń " + wynik);

					// } catch (LiczbaException e) {
					// System.out.println("Blad przy wiliczaniu wartosci: " +
					// message);
					// strumienWyjsciowy.writeObject("Blad obliczen: " +
					// e.getMessage());
					// }

					message = (String) strumienWejsciowy.readObject();
				}

				System.out.println("Klient konczy polaczenie z adresu: " + socket.getInetAddress());
				strumienWyjsciowy.close();
				strumienWejsciowy.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				// } catch (LiczbaException e) {
				// System.out.println("Blad. " + e.hashCode());
				// strumienWyjsciowy.writeObject("Blad obliczen: " +
				// e.getMessage());
			}
		}
	}

	public static void main(String[] args) {

		if (args.length != 0) {
			if (args.length == 1) {
				try {
					port = Integer.parseInt(args[0]);
				} catch (NumberFormatException ex) {
					System.out.println("nieprawidło podany numer portu");
					System.exit(-1);
				}
			} else
				System.out.println("Nieprawidło podane dane z linii poleceń, \nustawiono domyślny numer portu");
		}
		Server example = new Server();
		example.listenSocket();
		example.stopSerwer();

	}

	private void stopSerwer() {
		try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	}
