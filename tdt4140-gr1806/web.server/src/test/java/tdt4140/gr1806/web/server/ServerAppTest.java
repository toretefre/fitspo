/**
 * 
 */
package tdt4140.gr1806.web.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Asmund
 *
 */
public class ServerAppTest {
	
	
	@Test
	public void testGet() {
		Scanner streamScanner = null;
		InputStream urlStream = null;
		String result = null;
		try {
			URL url = new URL("http://localhost:2222/data/helloworld");
			urlStream = url.openStream();
			streamScanner = new Scanner(urlStream).useDelimiter("\\A");
			result = streamScanner.hasNext() ? streamScanner.next() : "";
			urlStream.close();
		} catch (MalformedURLException e) {
			System.err.println("Malformed URL");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			streamScanner.close();
		}
		
		Assert.assertEquals(result, "Hello, null!");
		
	}

}
