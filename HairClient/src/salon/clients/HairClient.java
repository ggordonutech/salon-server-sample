package salon.clients;

import salon.models.Shampoo;
import salon.communication.Request;
import salon.communication.Response;

import java.net.InetAddress;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class HairClient {
   private Socket socket;
   private ObjectOutputStream oos;
   private ObjectInputStream ois;
   
   public boolean initConnection() {
	   boolean success = false;
	   try {
		   socket = new Socket(InetAddress.getLocalHost(),8888);
		   getStreams();
		   success = true;
	   }catch(IOException e) {
		   e.printStackTrace();
	   }
	   return success;
   }
   
   public boolean addShampoo(Shampoo shampoo) {
	   Request req = new Request();req.setAction("add_shampoo");
	   req.setObject(shampoo);
	   try {
		   oos.writeObject(req);
		   Response resp = (Response)ois.readObject();
		   if(resp != null) {
			   if(!resp.isSuccess()) {
				   System.out.println(
				 "Client error:"+resp.getErrorMessage());
			   }
			   return resp.isSuccess();
		   }
	   }catch(ClassCastException | ClassNotFoundException 
			   | IOException e) { e.printStackTrace();
	   }
	   return false;
   }
   
   public java.util.List<Shampoo> getShampoos() {
	   Request req = new Request();
	   req.setAction("get_shampoos");
	   try {
		   oos.writeObject(req);
		   Response resp = (Response)ois.readObject();
		   if(resp != null) {
			   if(!resp.isSuccess()) {
				   System.out.println(
				 "Client error:"+resp.getErrorMessage());
			   }
			   return (java.util.List<Shampoo>)resp.getData();
		   }
	   }catch(ClassCastException | ClassNotFoundException 
			   | IOException e) { e.printStackTrace();
	   }
	   return new java.util.ArrayList<Shampoo>();
   }
   
//   public String getTodaysSpecial() {
//	   String todaysSpecial="No Special Today";
//	   try {
//		   oos.writeObject("get_special");
//		   todaysSpecial = (String)ois.readObject();
//		   
//	   }catch(NullPointerException | 
//			  ClassCastException |
//			  ClassNotFoundException |
//			  IOException e) {
//		   e.printStackTrace();
//	   }
//	   return todaysSpecial;
//   }
   
   public void getStreams() {
		try {
			oos = new ObjectOutputStream(
					socket.getOutputStream()
					);
			ois = new ObjectInputStream(
					socket.getInputStream()
					);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			oos.writeObject(new Request("EXIT"));
			ois.close();
			oos.close();
			socket.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
