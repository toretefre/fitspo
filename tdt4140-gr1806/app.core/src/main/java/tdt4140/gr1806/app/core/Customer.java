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
	
	
	
	private final String illegalNameCharacters = "!$@*^?><&%#/\\}{:\"'+";
	private final String illegalPhoneCharacters = "!$@*^?><&%#/\\}{:\"'´`";
	
	private void checkIllegalCharacters(String string, String illegalCharacters) {
		for (int i=0; i<string.length(); i++) {
			String c = string.substring(i, i+1);
			if (illegalCharacters.contains(c)) {
				throw new IllegalArgumentException(name + "contains the illegal character " + c);
			}
		}
	}
	
	public int getId() {
		return this.id;
	}
	
	// Should only be called via CustomerReposit
	protected void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.checkIllegalCharacters(name, this.illegalNameCharacters);
		if (name.length() > 300) {
			throw new IllegalArgumentException("Name is too long.");
		}
		this.name = name;
	}	
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		if (height > 500 || height < 10) {
			throw new IllegalArgumentException("The height seems wrong. The integer is in cm.");
		}
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		if (weight > 500 || weight < 10) {
			throw new IllegalArgumentException("The weight seems wrong. The double is in kg.");
		}
		this.weight = weight;
	}

	public String getTelephone() {
		return this.telephone;
	}
	public void setTelephone(String telephone) {
		if (telephone.length() > 20) {
			throw new IllegalArgumentException("Too long phone number.");
		}
		this.checkIllegalCharacters(telephone, this.illegalPhoneCharacters);
		this.telephone = telephone;
	}


	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		String[] parts = birthDate.split("-");
		if (parts.length != 3 || parts[0].length() != 4 || parts[1].length() != 2 || parts[2].length() != 2) {
			throw new IllegalArgumentException("The birthDate needs to be of the form \"yyyy-mm-dd\"");
		}
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
		if (gender != "M" && gender != "F" && gender != "O") {
			throw new IllegalArgumentException("Only \"M\", \"F\", and \"O\" are legal gender inputs. You inserted: " + gender);
		}
		this.gender = gender;
	}

	
	
	public String toString() {
        return "ID: " + this.id + "\nName: " + this.name + "\nBirth Date: " + this.birthDate + 
        		"\nTelephone: " + this.telephone + "\nGender: " + this.gender + "\nDate Registered: " + this.dateRegistered + 
        		"\nHeight: "+ this.height + "\nWeight: "+ this.weight;
    }
}