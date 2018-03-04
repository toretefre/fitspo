package tdt4140.gr1806.web.server;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Just a simple helper to convert things like a InputStream
 * to a String. Might've been unnecessary, but can prevent repeating this simple trick.
 * 
 * @author Aasmund
 *
 */
public class ToStringHelper {
	
	public static String InputStreamToString(InputStream in) {
		// Warning because of "in" not being closed, this is best handled in the class using this helper.
		@SuppressWarnings("resource")
		Scanner streamScanner = new Scanner(in).useDelimiter("\\A");
		String result = streamScanner.hasNext() ? streamScanner.next() : "";
		streamScanner.close();
		return result;
		
	}

}
