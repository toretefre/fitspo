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

/**
 * Integration test for the web.server
 * @author Aasmund
 *
 */
public class StepReceiverServerIT {
	
	/**
	 * Tests the simplest type of request.
	 * Mostly to verify that the server is responding.
	 */
	@Test
	public void testGet() {
		InputStream urlStream = null;
		String result = null;
		try {
			URL url = new URL("http://localhost:8888/data/hello?name=tester");
			urlStream = url.openStream();
			result = ToStringHelper.InputStreamToString(urlStream);
			urlStream.close();
		} catch (MalformedURLException e) {
			System.err.println("Malformed URL");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertEquals(result, "Hello, tester!");
		
	}
	
	
	/**
	 * Tests sending a simple JSON-object to the server using POST-request.
	 * Checks if status code is 200 OK, (server received and parsed object).
	 */
	@Test
	public void testPost() throws ClientProtocolException, IOException {
		String jsonString = "{\"steps\":524,\"date\":20180302,\"personID\":10}";
		
		HttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8888/data/automatic");

		// Request parameters and other properties.
		StringEntity entity = new StringEntity(jsonString);
		httpPost.setEntity(entity);
		httpPost.setHeader("Content-Type", "application/json");

		// Execute and get the response, also checking if status code is 201 Created.
		HttpResponse response = client.execute(httpPost);
		Assert.assertEquals(201, response.getStatusLine().getStatusCode());
		
		// Checking the response for data
		HttpEntity responseEntity = response.getEntity();
		System.out.println("Entity null? " + (responseEntity== null));

		if (entity != null) {
		    InputStream instream = responseEntity.getContent();
		    try {
		    	System.out.println(ToStringHelper.InputStreamToString(instream));
		    } catch (Exception e) {
		    	e.printStackTrace();
		    } finally {
		        instream.close();
		    }
		}
	}
}
