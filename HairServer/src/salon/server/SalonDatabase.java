package salon.server;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import salon.models.Shampoo;

public class SalonDatabase {
	private static Map<Integer,Shampoo> db;
	private static int nextAutoIncrementId = 0;
	
	static { //static constructor block
		db = new HashMap<Integer,Shampoo>();
		//insert some default records
		for(int id=1;id<=40;id++) {
			db.put(id, new Shampoo(id, id*25,"Scent "+id,"Brand "+id));
			nextAutoIncrementId++;
		}
	}
	
	private static SalonDatabase instance=null;
	public static SalonDatabase getInstance() {
		if(instance == null) {
			instance = new SalonDatabase();
		}
		return instance;
	}
	
	//private to prevent a develop from instantiating a new instance
	private SalonDatabase() {
		
	}
	
	public Shampoo get(int id) {
		return db.get(id);
	}
	
	public boolean remove(int id) {
		if(!db.containsKey(id)) {
			db.remove(id);
			return true;
		}
		return false;
	}
	
	public boolean update(Shampoo item) {
		if(!db.containsKey(item.getId())) {
			db.replace(item.getId(), item);
			return true;
		}
		return false;
	}
	
	public boolean add(Shampoo item) {
		if(!db.containsKey(nextAutoIncrementId+1)) {
		    item.setId(++nextAutoIncrementId);
			db.put(nextAutoIncrementId, item);
			return true;
		}
		return false;
	}
	
	public List<Shampoo> retrieveAll(){
		List<Shampoo> records = new ArrayList<Shampoo>();
		for(Shampoo item : db.values()) {
			records.add(item);
		}
		return records;
		
	}

}
