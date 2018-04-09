package tdt4140.gr1806.app.core;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class CustomerRepositoryTest {

	private CustomerRepository cr;
	
	@Before
	public void setUp() {
		final CustomerRepository cr = new CustomerRepository();
		
	}
	
	
	@After
	public void teardown() {
	}
	
	
	
	protected void checkCustomerData(final Customer origCus, final Customer dbCus) {
		Assert.assertNotNull(dbCus);
		//Assert.assertEquals(origCus.getId(), dbCus.getId());
		//Assert.assertEquals(origCus.getDateRegistered(), dbCus.getDateRegistered());
		Assert.assertEquals(origCus.getName(), dbCus.getName());
		Assert.assertEquals(origCus.getGender(), dbCus.getGender());
		Assert.assertEquals(origCus.getTelephone(), dbCus.getTelephone());
		Assert.assertEquals(origCus.getBirthDate(), dbCus.getBirthDate());
		Assert.assertEquals(origCus.getHeight(), dbCus.getHeight());
		Assert.assertEquals(origCus.getWeight(), dbCus.getWeight(), 0);
	}
	
	
	// TODO: Figure out if we need to test private methods.
	
	@Test
	public void testSaveCustomerCreateCustomerFromId() {
		Customer cus = new Customer("Henry", "F", "99352762", "1994-02-15", 172, 68);
		Customer nullCus = new Customer();
		
		Customer cusS = cr.saveCustomer(cus);
		Customer nullCusS = cr.saveCustomer(nullCus);
		
		this.checkCustomerData(cus, cusS);
		this.checkCustomerData(nullCus, nullCusS);
		
		Customer cusG = cr.createCustomerFromId(cusS.getId());
		Customer nullCusG = cr.createCustomerFromId(nullCusS.getId());
		
		this.checkCustomerData(cusG, cusS);
		Assert.assertEquals(cusG.getId(), cusS.getId());
		Assert.assertEquals(cusG.getDateRegistered(), cusS.getDateRegistered());
		Assert.assertSame(cusG, cusS);
		
		this.checkCustomerData(nullCusG, nullCusS);
		Assert.assertEquals(nullCusG.getId(), nullCusS.getId());
		Assert.assertEquals(nullCusG.getDateRegistered(), nullCusS.getDateRegistered());
		Assert.assertSame(nullCusG, nullCusS);
	}
	
	@Test
	public void testAddStepsToCustomer() {
		
	}
	
	@Test
	public void testFindAllCustomer() {
		
	}
	
	@Test
	public void testDeleteCustomer() {
		
	}
	
	
	
	@Test
	public void testGetTotalSteps() {
		
	}
	
	@Test
	public void testGetTotalStepsInDateRange() {
		
	}
	
	
	
	
	
}
