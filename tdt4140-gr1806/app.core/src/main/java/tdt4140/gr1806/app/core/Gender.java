package tdt4140.gr1806.app.core;

import java.util.Arrays;
import java.util.List;

/**
 * M: Male, F: Female, O: Other.
 * @author henriette_andersen
 *
 */
public enum Gender {

	M, F, O;
	
	public static Gender cast(String s) {
		List<String> m = Arrays.asList("M","m","Male", "MALE", "male");
		List<String> f = Arrays.asList("F","f","Female", "FEMALE", "female");
		List<String> o = Arrays.asList("O","o","Other", "OTHER", "other");
		if (m.contains(s)) {
			return Gender.M;
		} else if (f.contains(s)) {
			return Gender.F;
		} else if (o.contains(s)) {
			return Gender.O;
		} else {
			throw new IllegalArgumentException("Cannot cast this string.");
		}
	}
}
