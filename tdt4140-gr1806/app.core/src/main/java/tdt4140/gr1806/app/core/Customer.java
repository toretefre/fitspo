package tdt4140.gr1806.app.core;
/**
 * Instances of this class will represent a customer
 */


public class Customer{
	
	
	private int id;
	private String name;
	private String gender;
	private String dateRegistered;
	private String telephone;
	private String birthDate;
	private int height;
	private double weight;
	
	
	public Customer(String name, String gender, String telephone, String birthDate, int height, double weight) {
		this.setName(name);
		this.setGender(gender);
		this.setTelephone(telephone);
		this.setBirthDate(birthDate);
		this.setHeight(height);
		this.setWeight(weight);
	}

	
	public Customer(int id, String name, String gender, String dateRegistered, String telephone, String birthDate, int height, double weight) {
		this.setId(id);
		this.setName(name);
		this.setGender(gender);
		this.setTelephone(telephone);
		this.setDateRegistered(dateRegistered);
		this.setBirthDate(birthDate);
		this.setHeight(height);
		this.setWeight(weight);
	}
	
	public Customer() {
		
	}
	
	
	
	public int getId() {
		return this.id;
	}
	
	// Should only be called via CustomerRepository
	protected void setId(int id) {
		this.id = id;
	}
	

	public String getName() {
		return this.name;
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
	
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	
	public String getDateRegistered() {
		return dateRegistered;
	}
	
	// Should only be called via CustomerReposit
	protected void setDateRegistered(String date) {
		this.dateRegistered = date;
	}
	

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}


	@Override
	public String toString() {
        return "ID: " + this.id + "\nName: " + this.name + "\nBirth Date: " + this.birthDate + 
        		"\nTelephone: " + this.telephone + "\nGender: " + this.gender + "\nDate Registered: " + this.dateRegistered + 
        		"\nHeight: "+ this.height + "\nWeight: "+ this.weight;
    }
	
}