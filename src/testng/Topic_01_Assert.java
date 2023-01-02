package testng;

import org.testng.Assert;

public class Topic_01_Assert {
	public static void main(String[] args) {
		String fullName = "Automation test";
		
		// Mong đợi một điều kienj trả về là đúng (true)
		Assert.assertTrue(fullName.contains("Automation"));
		
		//Mong đợi 1 điều kiện trả về là sai (false)
		Assert.assertFalse(fullName.contains("Manual"));
		
		// Mong đợi 2 điều kiện bằng nhau
		// Expected result và Actual result
		
		Assert.assertEquals(fullName, "Automation test");
		
	}
	

}
