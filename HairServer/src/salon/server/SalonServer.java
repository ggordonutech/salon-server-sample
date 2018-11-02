package salon.server;

public interface SalonServer { 
   /**
    * Initializes Server
    * */
	void initServer();
	
	/**
	 * Wait for Client Requests
	 * */
    void waitForRequests();
}
