package salon.models;



public class Shampoo implements java.io.Serializable{
	private int id;
	private float price;
	private String scent;
	private String brand;
	
	public Shampoo() {
		this.id = 0;
		this.price = 0.0f;
		this.scent = "";
		this.brand = "";
	}
	
	public Shampoo(int id, float price, String scent, String brand) {
		super();
		this.id = id;
		this.price = price;
		this.scent = scent;
		this.brand = brand;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getScent() {
		return scent;
	}

	public void setScent(String scent) {
		this.scent = scent;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Shampoo [id=" + id + ", price=" + price + ", scent=" + scent + ", brand=" + brand + "]";
	}
	
	
	
	
	
	

}
