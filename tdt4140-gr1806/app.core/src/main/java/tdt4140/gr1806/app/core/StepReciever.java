package tdt4140.gr1806.app.core;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

/**
 * Denne klassen håndterer mottak av data, både fra en html-form og fra en 
 * App som automatisk sender inn data.
 * Tenker at datatypen for automatisk data skal være JSON.
 * 
 * 
 * @author Åsmund
 * 
 */
@Path("data")
public class StepReciever {
	
	@Path("manual")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String handleFormPost(@FormParam("id") int id, @FormParam("skritt") int steps) {
		
		try {			
			DatabaseHandler.saveSteps(id, steps); // TODO: Koble logikk til rett database-klasse
		} catch (EnEllerAnnenDatabaseError e) {
			System.err.println("Error encountered while trying to save to database!");
			System.err.println(e);
			
			return "<h2> Beklager! Her skjedde det en feil med lagringen! </h2>";
		}
		
		return "<h2> Takk! Vi har nå registrert " + steps + " skritt på deg </h2>";
	}
	

	@Path("automatic")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String handleAutomaticPost(PersonSteps data) {
		try {
			
		} catch (EnEllerAnnenDatabaseError e) {
			System.err.println();
		}
		return "HEI";
	}
	
	
	
}
