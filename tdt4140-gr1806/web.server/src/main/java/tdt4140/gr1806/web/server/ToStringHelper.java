package tdt4140.gr1806.web.server;

import java.io.InputStream;
import java.util.Scanner;

public class ToStringHelper {
	
	public static String InputStreamToString(InputStream in) {
		Scanner streamScanner = new Scanner(in).useDelimiter("\\A");
		String result = streamScanner.hasNext() ? streamScanner.next() : "";
		return result;
	}

}
