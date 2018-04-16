/**
 * 
 */
package tdt4140.gr1806.web.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.junit.Test;

import tdt4140.gr1806.app.core.Customer;
import tdt4140.gr1806.app.core.CustomerRepository;

/**
 * Integration test for the web.server
 * @author Aasmund
 *
 */
public class FitspoServiceIT {
	
	/**
	 * Tests the simplest type of request.
	 * Mostly to verify that the server is responding.
	 */
	@Test
	public void testGet() {
		InputStream urlStream = null;
		String helloTest = null;
		try {
			URL url = new URL("http://localhost:8888/fitspo/hello?name=tester");
			urlStream = url.openStream();
			helloTest = ToStringHelper.InputStreamToString(urlStream);
			urlStream.close();

		} catch (MalformedURLException e) {
			System.err.println("Malformed URL");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assert.assertEquals(helloTest, "Hello, tester!");
		
	}
	
	
	/**
	 * Tests sending a simple JSON-object to the server using POST-request.
	 * Checks if status code is 200 OK, (server received and parsed object).
	 */
	@Test
	public void testPost() throws ClientProtocolException, IOException {
		CustomerRepository customerRepository = new CustomerRepository();
		
		Customer testPerson = new Customer("Hans WebServer Test", "O", "91765567", "1996-02-02", 170, 70);
		// saveCustomer() returns Customer object with the ID given by DB
		testPerson = customerRepository.saveCustomer(testPerson);
		
		String jsonString = "{\"steps\":1330,\"dateString\":\"2000-01-01\",\"personID\":"+testPerson.getId()+"}";
		
		HttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8888/fitspo/automatic");

		// Request parameters and other properties.
		StringEntity entity = new StringEntity(jsonString);
		httpPost.setEntity(entity);
		httpPost.setHeader("Content-Type", "application/json");

		// Execute and get the response, also checking if status code is 201 Created.
		HttpResponse response = client.execute(httpPost);
		Assert.assertEquals(201, response.getStatusLine().getStatusCode());
		
		// Checking the response for data
		HttpEntity responseEntity = response.getEntity();

		if (responseEntity != null) {
		    InputStream instream = responseEntity.getContent();
		    try {
		    	System.out.println(ToStringHelper.InputStreamToString(instream));
		    } catch (Exception e) {
		    	e.printStackTrace();
		    } finally {
		        instream.close();
		    }
		}
		
		// Check if the steps actually has been added
		Assert.assertEquals(1330, customerRepository.getTotalSteps(testPerson));
		
		// Clean up
		customerRepository.deleteCustomer(testPerson);
	}
}
