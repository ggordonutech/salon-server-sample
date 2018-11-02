package salon.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.EOFException;

public class MultiThreadedServer implements SalonServer {
	private ServerSocket servSocket;
	

	public void initServer() {
		try {
			servSocket = new ServerSocket(8888, 10);
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
				Socket socket = servSocket.accept();
				HandleClientRequestsThreadJob job = 
						new HandleClientRequestsThreadJob(socket);
				job.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

}
