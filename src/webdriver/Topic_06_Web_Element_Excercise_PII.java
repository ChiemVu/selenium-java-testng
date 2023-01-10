package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Excercise_PII {
	WebDriver driver;
	Random rand;
	String projectPath = System.getProperty("user.dir");
	String emailAddress, firstName, lastName, password, fullName;

	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		//driver = new FirefoxDriver();
		driver = new ChromeDriver();
		rand = new Random();		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		emailAddress = "automation" + rand.nextInt(9999) + "@gmail.com";
		firstName = "Automation";
		lastName = "On28";
		fullName = firstName + " " + lastName;
		password = "12345678";
	}
	
	@Test
	public void Login_01_Empty_Email_Password() {
		driver.get("http://live.techpanda.org/");
		
		//click my account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("#advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.cssSelector("#advice-required-entry-pass")).getText(), "This is a required field.");
	}

	@Test
	public void Login_02_Invalid_Email() {
		driver.get("http://live.techpanda.org/");
		//click my account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("12343434@34343434.1234");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("#advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void Login_03_Invalid_Password() {
		driver.get("http://live.techpanda.org/");
		//click my account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("#advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}
	@Test
	public void Login_04_Incorrect_Email_Password() {
		driver.get("http://live.techpanda.org/");
		//click my account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("1233453656");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText(), "Invalid login or password.");
	}	
	@Test
	public void Login_05_Create_New_User() {
		driver.get("http://live.techpanda.org/");
		//click my account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(3);
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		
		//Click đăng ký
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
		String contextInformationText = driver.findElement(By.xpath("//div[@class= 'box-title']/following-sibling::div[@class='box-content']")).getText();
		
		System.out.println(contextInformationText);
		Assert.assertTrue(contextInformationText.contains(fullName));
		Assert.assertTrue(contextInformationText.contains(emailAddress));
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
	}
	@Test
	public void Login_06_Valid_Email_Password() {
		driver.get("http://live.techpanda.org/");
		//click my account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		driver.findElement(By.id("email")).sendKeys(emailAddress);
		driver.findElement(By.id("pass")).sendKeys(password);
		//Click login
		driver.findElement(By.id("send2")).click();
		sleepInSecond(3);
		String contextInformationText = driver.findElement(By.xpath("//div[@class='box-title']/following-sibling::div[@class='box-content']")).getText();
		Assert.assertTrue(contextInformationText.contains(fullName));
		Assert.assertTrue(contextInformationText.contains(emailAddress));
		
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}

	@AfterClass
	public void afterClass() {
		 //driver.quit();
		driver.close();
	}
}