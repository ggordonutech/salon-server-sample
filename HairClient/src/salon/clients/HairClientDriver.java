package salon.clients;

import salon.models.Shampoo;
import java.util.List;

public class HairClientDriver {
	
	public static void main(String[] args) {
		HairClient client = new HairClient();
		if(client.initConnection()) {
			System.out.println("Client Connection Successful");
			int randomNum = new java.util.Random().nextInt(500)+10;
			Shampoo entry = new Shampoo(randomNum,randomNum*25,"Scent "+randomNum,"Brand "+randomNum);
			if(client.addShampoo(entry)) {
				System.out.println("Entry "+entry+" added successfully.");
			}else {
				System.out.println("We were unable to add entry "+entry);
			}
			List<Shampoo> results = client.getShampoos();
			System.out.println("Retrieved "+results.size()+" from the server. Printing results");
			for(Shampoo item : results) {
				System.out.println(item);
			}
			client.closeConnection();

		
		}
		
	}

}
