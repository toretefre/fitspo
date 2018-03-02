/**
 * 
 */
package tdt4140.gr1806.web.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Asmund
 *
 */
public class ServerAppIT {
	
	
	@Test
	public void testGet() {
		InputStream urlStream = null;
		String result = null;
		try {
			URL url = new URL("http://localhost:8888/data/helloworld");
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
		
		Assert.assertEquals(result, "Hello, null!");
		
	}
	
	@Test
	public void testPost() throws ClientProtocolException, IOException {
		InputStream urlStream = null;
		ObjectMapper mapper = new ObjectMapper();
		PersonSteps steps = new PersonSteps();
		
		String CORRECTSTRING = "{\"steps\":524,\"date\":20180302,\"personID\":10}";
		
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://localhost:8888/data/automatic");

		// Request parameters and other properties.
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>(2);
		params.add(new BasicNameValuePair("Content-Type", "application/json"));
		params.add(new BasicNameValuePair("data", "{\"steps\":524,\"date\":20180302,\"personID\":10}"));
		params.add(new BasicNameValuePair("datatype", "json"));
		httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		//Execute and get the response.
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();
		System.out.println("Entity is null? " + entity != null);

		if (entity != null) {
		    InputStream instream = entity.getContent();
		    try {
		    	System.out.println(ToStringHelper.InputStreamToString(instream));
		    } finally {
		        instream.close();
		    }
		}
		
//		steps.setPersonID(10);
//		steps.setDate(new Date(20180302));
//		steps.setSteps(524);
//		
//		System.out.println(mapper.canSerialize(steps.getClass()));
//		
//		try {
//			URL url = new URL("http://localhost:8888/data/helloworld");
//			System.out.println(mapper.writeValueAsString(steps));
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
