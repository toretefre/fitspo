package tdt4140.gr1806.web.server;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Resource Config for Jersey. 
 * 
 * @author Aasmund
 */
public class ResourceConfiguration extends ResourceConfig{
	
	public ResourceConfiguration() {
		packages("tdt4140.gr1806.web.server");
		register(StepReciever.class);
	}

}
