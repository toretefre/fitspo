package tdt4140.gr1806.web.server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import tdt4140.gr1806.app.core.ConnectionManager;

/**
 * 
 * This class handles receiving data over "web protocols".
 * Jersey 2.
 * 
 * @author Aasmund
 * 
 */
@Path("data")
public class StepReciever {
	
	
	/**
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
			//saveSteps(id, steps, date);
		} catch (Exception e) {
			System.err.println("Error encountered while trying to save to database!");
			System.err.println(e);
			
			return "<h2> Beklager! Her skjedde det en feil med lagringen! </h2>";
		}
		
		return "<h2> Takk! Vi har nå registrert " + steps + " skritt på deg </h2>";
	}
	

	/**
	 * The idea here is that the data is automatically sent to
	 * this web server from the app the data-giver is using. 
	 */
	@Path("automatic")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response handleAutomaticPost(PersonSteps data) {
		try {
			int id = data.getPersonID();
			int steps = data.getSteps();
			Date date = data.getDate();
			
			// TODO: Actually do something with the incoming data
			//saveSteps(id, steps, date);
			
			return Response.status(201).entity("Received:\nID: "+id+"\nSteps: "+steps+"\nDate: "+date.toString()).build();
			
			
		} catch (Exception e) {
			System.err.println();
			return Response.status(500).entity("A servererror occured").build();
		}
	}
	
	
	/**
	 * Used for easily testing the connection to the server. 
	 * Run server using mvn jetty:run in cmd/terminal
	 * from folder /tdt4140-gr1806/web.server/
	 */
	@GET
	@Path("hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String Hello(@QueryParam("name") String name) {
		return "Hello, " + name + "!";
	}
	
	/**
	 * Helpermethod used to save steps to the database
	 */
	private void saveSteps(int id, int steps, Date date) {
		Connection DBConnection = ConnectionManager.connect();
		
		String sql = "insert into Customer values ("
				+ id + ","
				+ steps + ","
				+ date
				+ ")";
		
		try {
			PreparedStatement pstmt = DBConnection.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
