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
	
	private int PersonID, steps;
	private Date date;
	
	public int getPersonID() {
		return PersonID;
	}
	public void setPersonID(int personID) {
		PersonID = personID;
	}
	public int getSteps() {
		return steps;
	}
	public void setSteps(int steps) {
		this.steps = steps;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
