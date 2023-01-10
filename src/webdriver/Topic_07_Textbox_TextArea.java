package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_TextArea {
	WebDriver driver;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String employeeID = String.valueOf(rand.nextInt(99999));
	String passportNumber = "40517-402-96-7202";
	String comment ="This is generated data \nof real people";
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Create_New_Imployee() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		//driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
		driver.findElement(By.name("username")).sendKeys("Admin");
		//driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		sleepInSecond(5);
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		sleepInSecond(5);
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		sleepInSecond(5);
		driver.findElement(By.name("firstName")).sendKeys("Automation");
		driver.findElement(By.name("lastName")).sendKeys("On28");
		
		WebElement employeeIDTextbox = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"));
		employeeIDTextbox.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		employeeIDTextbox.sendKeys(Keys.DELETE);
		employeeIDTextbox.sendKeys(employeeID);
		//driver.findElement(By.cssSelector("div.oxd-switch-wrapper")).click();
		driver.findElement(By.xpath("//p[text()='Create Login Details']/parent::div//span")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys("vtc028" +employeeID);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys("Password123!!!");
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys("Password123!!!");
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		sleepInSecond(12);
		//pass: AaBb123!
		//id: 0291
		//verify dữ liệu đã nhập ở màn hình add Employee đúng với dữ liệu hiển thị ở trang Personal
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "Automation");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), "On28");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(5);
		
		//click button Add
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
		sleepInSecond(5);
		// nhập giá trị passportNumber
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportNumber);
		//nhập giá trị comment
		driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(comment);
		//click button save
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		sleepInSecond(6);
		//click icon edit
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepInSecond(3);
		//verifu dữ liệu passportNumber và comment  đã tạo hiển thị đúng
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), comment);
		//click logout
		driver.findElement(By.cssSelector("p.oxd-userdropdown-name")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		sleepInSecond(3);
		//click login bằng acc đã tạo
		driver.findElement(By.name("username")).sendKeys("vtc028" + employeeID);
		driver.findElement(By.name("password")).sendKeys("Password123!!!");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		sleepInSecond(5);
		
		//Click my info
		driver.findElement(By.xpath("//span[text()='My Info']")).click();
		sleepInSecond(5);
		//Verify thông tin hiển thị đúng: Firstname/LastNAme/Employee ID
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "Automation");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), "On28");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
		//click vào immigration
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		//click icon edit
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepInSecond(3);
		//verifu dữ liệu passportNumber và comment  đã tạo hiển thị đúng
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), comment);		
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
	}
}