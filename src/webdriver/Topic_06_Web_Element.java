package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	
	//By chưa đi tìm element ngay
	By emailTextboxBy = By.id("Email");
	By passwordTextboxBy = By.id("Pass");
		
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_WebElement() {
		driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
		WebElement element = driver.findElement(By.className(""));
		
		// dùng cho các textbox/ texterea/ dropdown (editable) 
		//Xóa dữ liệu đi trước khi nhập text
		element.clear();  //*
		
		// dùng cho các textbox/ texterea/ dropdown (editable) 
		// nhập liệu
		element.sendKeys("");  //**
		
		//click vào các button/link/checkbox, radio/image...
		element.click();  //**
		
		String searchAttribute = element.getAttribute("placeholder");  //**
		String emailTextboxAttribute = element.getAttribute("value");
		//search store
		
		//GUI: Font/ Size/ Color/ Location/ Position.. >> Ít dùng
		element.getCssValue("background-color");//*
		//#4ab2f1
		
		
		//lấy ra vị trí của element so với web
		element.getLocation();
		
		//kich thước của element ( chiều cao chiều rọng bên trong)
		element.getSize();
		
		//Location + size 
		element.getRect();
		
		
		//chụp hình bị lỗi khi TC fail
		element.getScreenshotAs(OutputType.FILE);  //*
		element.getScreenshotAs(OutputType.BYTES); 
		element.getScreenshotAs(OutputType.BASE64);
		
		//Lấy ra cái tên thẻ html của element đó >> truyền vào cho 1 locator khác
		driver.findElement(By.id("Email")).getTagName();
		driver.findElement(By.name("Email")).getTagName();
		
		
		String emailTextboxTagname = driver.findElement(By.cssSelector("#Email")).getTagName();		
		driver.findElement(By.xpath("//"+ emailTextboxTagname +"[@id='email']"));
		
		
		//Lấy text từ Eror message/success message/label....
		//Khi data cần lấy nằm bên ngoài
		element.getText();  //**
		//Khi value cần lấy nằm bên trong
		element.getAttribute("");
		
		
		//dùng để verify xem 1 elemetn hiển thị hoặc không
		//phạm vi tất cả element
		Assert.assertTrue(element.isDisplayed());  //**
		Assert.assertFalse(element.isDisplayed());
		
		//dùng để verify xem 1 elemetn enable/disable
		//phạm vi tất cả element
		Assert.assertTrue(element.isEnabled());  
		Assert.assertFalse(element.isEnabled());
		
		
		//dùng để verify xem 1 elemetn được chọn hay chưa
		//phạm vi: checkboxt/radio ( chú ý Default Dropdown có thẻ option sẽ có thư viện riêng để xử lý nên k dùng với isSelected) 
		Assert.assertTrue(element.isSelected());  //*
		Assert.assertFalse(element.isSelected());
		
		
		//các element nằm trong thẻ form tương ứng với hành vi của end user (enter)
		element.submit();
		
	
		
		
	}

	@Test
	public void TC_02_Register() {
		driver.get("");
		
		driver.findElement(emailTextboxBy).sendKeys("");
		driver.findElement(passwordTextboxBy).sendKeys("");


	}

	@Test
	public void TC_03_Login() {
		driver.get("");
		
		driver.findElement(emailTextboxBy).sendKeys("");
		driver.findElement(passwordTextboxBy).sendKeys("");


	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}