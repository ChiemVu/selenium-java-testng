package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		//driver.get("https://www.facebook.com/");
		
		//mo trang register ra
		driver.get("https://demo.nopcommerce.com/register");
	}
	
	//HTML cuar FirstName textbox
	//<input type="text" data-val="true" data-val-required="First name is required." 
	//id="FirstName" name="FirstName">
	
	//tên thẻ của element *Tagname HTML: input
	//tên của thuộc tính (attribute name): type data-val data-val-required id name 
	// giá trị của thuộc tính tương ứng

	@Test
	public void TC_01_ID() {
        driver.findElement(By.id("FirstName")).sendKeys("Automation");

	}

	@Test
	public void TC_02_Class() {
		// mở màn hình search
		driver.get("https://demo.nopcommerce.com/search");
		
		//nhập text vào search textbox
		driver.findElement(By.className("search-text")).sendKeys("Macbook");
	}

	@Test
	public void TC_03_Name() {
		//Click vào Advanced Search checkbox
		driver.findElement(By.name("advs")).click();

	}
	@Test
	public void TC_04_TagName() {
		
		// tìm ra bao nhiêu thẻ input trên màn hình hiện tại
		System.out.println(driver.findElement(By.tagName("input")).getSize());
		

	}
	@Test
	public void TC_05_LinkText() {
		//click vào đường link Addresses (link tuyệt đối)
		driver.findElement(By.linkText("Addresses")).click();

	}
	@Test
	public void TC_06_PartialLinkText() {

		//click vào đường link Addresses (link khớp 1 phần - cách làm tương đối, chạy chậm hơn)
		driver.findElement(By.partialLinkText("vendor account")).click();

	}
	@Test
	public void TC_07_CSS() {
		
		// đc viết với nhiều kỹ thuật nhất
		//mo lại trang register ra
		driver.get("https://demo.nopcommerce.com/register");
		
		//cách 1 lấy css
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Selenium");
		//Cách 2 cú pháp chuẩn của css
		driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("Locator");
		
		//cachs 3
		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("automation@gmail.com");

	}
	@Test
	public void TC_08_Xpath() {
		// đc viết với nhiều kỹ thuật nhất
		//mo lại trang register ra
		driver.get("https://demo.nopcommerce.com/register");
		
		//cách 1 lấy css
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Selenium");
		//Cách 2 cú pháp chuẩn của css
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Locator");
		
		//cachs 3
		driver.findElement(By.xpath("//label[text()='Email:']/following-sibling::input")).sendKeys("automation@gmail.com");


	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}