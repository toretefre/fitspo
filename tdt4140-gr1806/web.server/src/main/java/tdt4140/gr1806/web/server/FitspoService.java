package tdt4140.gr1806.web.server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.hk2.api.ServiceLocatorFactory.CreatePolicy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tdt4140.gr1806.app.core.*;

/**
 * 
 * This class handles receiving data over "web protocols".
 * Jersey 2.
 * 
 * @author Aasmund
 * 
 */
@Path("fitspo")
public class FitspoService {
	private CustomerRepository customerRepo = new CustomerRepository();
	
	
	/**
	 * Here's a temporary solution to manual entry of
	 * steps by the data giver. It receives data from a form (which we don't
	 * have to implement). 
	 */
	@Path("manual")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String handleFormPost(@QueryParam("id") int id, @QueryParam("steps") int steps, @QueryParam("date") Date date) {
		System.out.println(id + steps + date.toString());
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
			Date date = Date.valueOf(data.getDateString());

			saveSteps(id, steps, date);
			
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
	 * Often the modules need to be compiled, 
	 * so if there's missing classes run as Maven Install on tdt4140.gr1806
	 */
	@GET
	@Path("hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String Hello(@QueryParam("name") String name) {
		return "Hello, " + name + "!";
	}
	
	
	/**
	 * @author Aasmund
	 * @return All customers in the database.
	 */
	@GET
	@Path("customers")
	@Produces(MediaType.APPLICATION_JSON) 
	public String getCustomers(){
		String jsonString = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			checkConnection();
			jsonString = mapper.writeValueAsString(customerRepo.findAllCustomers());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			// Probably shouldn't show error messages directly to users like this, but eh. 
			return e.getLocalizedMessage();
		}

		return jsonString;
		
	}
	
	
	/**
	 * 
	 * @author Aasmund
	 * @return JSON-file with all customer data found on the id.
	 */
	@GET
	@Path("customers/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCustomer(@PathParam("id") String id) {
		String cus = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			checkConnection();
			Customer customer = customerRepo.createCustomerFromId(Integer.valueOf(id));
			cus = mapper.writeValueAsString(customer);
		} catch (NumberFormatException | JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cus;
	}
	
	
	/**
	 * Helpermethod used to save steps to the database
	 */
	private void saveSteps(int id, int steps, Date date) {
		Customer customer = customerRepo.createCustomerFromId(id);
		customerRepo.addStepsToCustomer(customer, steps, date.toString());
	}
	
	/**
	 * Solving a problem where it didn't connect to the db on the first request.
	 */
	private void checkConnection() {
		if (customerRepo.conn == null) {
			customerRepo.connect();
		}
	}
	
	public static void main(String[] args) {
		FitspoService fs = new FitspoService();
		System.out.println(fs.getCustomer("5"));
	}
}
