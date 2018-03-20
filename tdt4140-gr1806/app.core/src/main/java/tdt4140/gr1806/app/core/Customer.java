package tdt4140.gr1806.app.core;
/**
 * Instances of this class will represent a customer
 */



public class Customer{
	
	private int id;
	private int height;
	private double weight;
	private String name;
	private String birthDate;
	private String dateRegistered;
	private String telephone;
	private String gender;
	
	//TODO: Checks for set-methods?

	/**
	 * 
	 * @param name
	 * @param birthDate
	 * @param telephone
	 * @param gender
	 * @param height
	 * @param weight
	 */
	
	public Customer(String name, String gender, String telephone, String birthDate, int height, double weight) {
		this.name = name;
		this.birthDate = birthDate;
		this.telephone = telephone;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
	}
	
	public Customer(int id, String name, String gender, String dateRegistered, String telephone, String birthDate, int height, double weight) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.telephone = telephone;
		this.gender = gender;
		this.dateRegistered = dateRegistered;
		this.height = height;
		this.weight = weight;
	}
	
	public static void main(String[] args) {
		Customer c = new Customer("Navn", null, "Telefon", null, 0, 0);
		System.out.println(c);
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getTelephone() {
		return this.telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthdate(String birthdate) {
		this.birthDate = birthdate;
	}
	
	
	public String getDateRegistered() {
		return dateRegistered;
	}
	
	public void setDateRegistered(String date) {
		this.dateRegistered = date;
	}
	

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	
	
	public String toString() {
        return "ID: " + this.id + "\nName: " + this.name + "\nBirth Date: " + this.birthDate + 
        		"\nTelephone: " + this.telephone + "\nGender: " + this.gender + "\nDate Registered: " + this.dateRegistered + 
        		"\nHeight: "+ this.height + "\nWeight: "+ this.weight;
    }
	

}
