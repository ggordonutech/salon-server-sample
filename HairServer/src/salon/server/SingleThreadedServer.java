package salon.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.EOFException;

public class SingleThreadedServer implements SalonServer {
	private ServerSocket servSocket;
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;

	public void initServer() {
		try {
			servSocket = new ServerSocket(8888, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void waitForRequests() {
		if (servSocket == null) {
			System.out.println("Server not initialized");
			return;
		}
		try {
			while (true) {
				socket = servSocket.accept();
				getStreams();
				String action = "";
				try {
					do {
						try {
							action = (String) ois.readObject();
							if (action.equals("get_special")) {
								oos.writeObject("Buy One Get One Free!");
							}
						} catch (ClassCastException | ClassNotFoundException e) {
							e.printStackTrace();
						}
					} while (!action.equals("EXIT"));
				} catch (EOFException e) {
					// ignore - unable to communicate with client
				}
				closeConnection();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getStreams() {
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			ois.close();
			oos.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
