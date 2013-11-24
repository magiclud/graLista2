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
			System.out.println("Czekanie na graczy... ");

			try {

				ObjectInputStream strumienWejsciowy = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream strumienWyjsciowy = new ObjectOutputStream(socket.getOutputStream());
				System.out
						.println("System:  Jeżeli chcesz zacząć grę, wpisz START i kliknij Wyslij wiadomosc.\n Jeżeli chcesz zakonczyc gre wpisz KONIEC.");

				String message = (String) strumienWejsciowy.readObject();
				strumienWyjsciowy.writeObject(" System: Wpisz ilu ma grać ludzi << 2<=numHum+numBot<=4 >>:");
				String liczbaHumanow = (String) strumienWejsciowy.readObject();
				strumienWyjsciowy.writeObject(" Liczba \"humanow\": " + liczbaHumanow
						+ "\n System: Wpisz ile ma grać botów << 2<=numHum+numBot<=4 >>:");
				String liczbaBotow = (String) strumienWejsciowy.readObject();
				strumienWyjsciowy.writeObject(" Liczba botow: " + liczbaBotow + "\n");

				while (message != "KONIEC") {
					String[] tablica = message.split(" ");
					System.out.println("Odczytano: " + message);
					strumienWyjsciowy.writeObject("Wynik. Wygral: " + tablica[0]);
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
			}
		}
	}

	public static void main(String[] args) {

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
