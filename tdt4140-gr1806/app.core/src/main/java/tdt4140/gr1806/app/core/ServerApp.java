/**
 * 
 */
package tdt4140.gr1806.app.core;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * @author Asmund
 *
 */
public class ServerApp {
	
	public static void main(String[] args) {
		ResourceConfig config = new ResourceConfig();
		config.packages("tdt4140.gr1806.app.core");
		ServletHolder servlet = new ServletHolder(new ServletContainer(config));
		
		Server server = new Server(2222);
		ServletContextHandler context = new ServletContextHandler(server, "/*");
		context.addServlet(servlet, "/*");
		
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			server.destroy();
		}
	}
	
}
