package salon.server;

public class HairServerDriver {

	public static void main(String[] args) {
		
		
		try {
			SalonServer server = null;
			System.out.print("Enter (S) for single threaded server implementation or (M) for multi");
			System.out.println("threaded server implementation with Request/Response objects : ");
		    char serverType = (char)System.in.read();
		    switch(serverType) {
		    case 'S':case 's': server = new SingleThreadedServer(); break;
		    case 'M':case 'm': server = new MultiThreadedServer(); break;
		    default:
		    	throw new InvalidServerOptionException();
		    }
		    System.out.println("Initializing Server");
			server.initServer();
			System.out.println("Starting Server");
			server.waitForRequests();
		}catch(java.io.IOException e) {
			System.out.println("We were unable to read the option presented.");
			System.out.println("Please re-start the server and try again");
			e.printStackTrace();
		}catch(InvalidServerOptionException e) {
			System.out.println(e.getMessage());
		}
		

	}

}
