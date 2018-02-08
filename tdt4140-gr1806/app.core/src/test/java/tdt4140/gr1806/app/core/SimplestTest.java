package tdt4140.gr1806.app.core;

import org.junit.Assert;
import org.junit.Test;

public class SimplestTest {
	
	private Simplest simple;

	@Test
	public void test() {
		simple = new Simplest(20);
		Assert.assertEquals(20, simple.getNumber());
	}

}
