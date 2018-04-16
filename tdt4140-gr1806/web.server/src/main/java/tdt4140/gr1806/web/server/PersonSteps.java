/**
 * 
 */
package tdt4140.gr1806.web.server;

import java.sql.Date;

/**
 * This object serves to save PersonID, number of steps and date of steps
 * Needed to create a object from JSON in app.core.StepReceiver using Jersey.
 * It's known as a POJO.
 * 
 * @author Aasmund
 *
 */
public class PersonSteps {
	
	private int personID, steps;
	private String dateString;
	
	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	public int getSteps() {
		return steps;
	}
	public void setSteps(int steps) {
		this.steps = steps;
	}
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	
	

}
