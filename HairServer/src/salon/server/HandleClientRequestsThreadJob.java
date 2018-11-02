package salon.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import java.util.List;

import salon.models.Shampoo;
import salon.communication.Request;
import salon.communication.Response;

public class HandleClientRequestsThreadJob extends Thread {
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private SalonDatabase db;

	public HandleClientRequestsThreadJob(Socket socket) {
		this.socket = socket;
		db = SalonDatabase.getInstance();
	}

	public void run() {
		try {
			System.out.println("Processing new client");
			getStreams();
			Request request = null;
			Response response = null;
			try {
				do {
					try {
						request= (Request) ois.readObject();
						System.out.println("Received new request "+request);
						if (request.getAction().equals("add_shampoo")) {
							response = new Response();
							Shampoo data = (Shampoo)request.getObject();
							response.setSuccess(db.add(data));
							System.out.println("Sending response "+response);
							oos.writeObject(response);
						}
						if (request.getAction().equals("get_shampoos")) {
							response = new Response();
							List<Shampoo> records = db.retrieveAll();
							response.setData(records);
							response.setSuccess(true);
							System.out.println("Sending response with "+records.size()+" records");
							oos.writeObject(response);
						}
					} catch (ClassCastException | ClassNotFoundException | NullPointerException e) {
						e.printStackTrace();
					}
				} while (!request.getAction().equals("EXIT"));
			} catch (EOFException e) {
				// ignore - unable to communicate with client
			}
			
			closeConnection();
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
		System.out.println("Closing connection with client");
		try {
			ois.close();
			oos.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
