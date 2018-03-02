package tdt4140.gr1806.web.server;

import java.sql.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
//import tdt4140.gr1806.app.core.DatabaseManager;

/**
 * 
 * This class handles receiving data over "web protocols".
 * It's using Jersey. 
 * 
 * @author Aasmund
 * 
 */
@Path("data")
public class StepReciever {
	
	
	/*
	 * Here's a temporary solution to manual entry of
	 * steps by the data giver. It receives data from a form (which we don't
	 * have to implement). 
	 */
	@Path("manual")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String handleFormPost(@FormParam("id") int id, @FormParam("steps") int steps, @FormParam("date") Date date) {
		
		try {			
			//DatabaseManager.saveSteps(id, steps, date);
		} catch (Exception e) {
			System.err.println("Error encountered while trying to save to database!");
			System.err.println(e);
			
			return "<h2> Beklager! Her skjedde det en feil med lagringen! </h2>";
		}
		
		return "<h2> Takk! Vi har nå registrert " + steps + " skritt på deg </h2>";
	}
	

	/*
	 * The idea here is that the data is automatically sent to
	 * this web server from the app the data-giver is using. 
	 */
	@Path("automatic")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String handleAutomaticPost(PersonSteps data) {
		try {
			int id = data.getPersonID();
			int steps = data.getSteps();
			Date date = data.getDate();
			
			//DatabaseManager.saveSteps(id, steps, date);
			
			return "Data saved";
			
		} catch (Exception e) {
			System.err.println();
			
			return "Error";
		}
	}
	
	
	/*
	 * Testing the GET method as it's the easiest.
	 * Run ServerApp and the open the
	 * browser at localhost:2222/data/helloworld?name=me
	 */
	@GET
	@Path("helloworld")
	@Produces(MediaType.TEXT_PLAIN)
	public String helloWorld(@QueryParam("name") String name) {
		return "Hello, " + name + "!";
	}
	
}
