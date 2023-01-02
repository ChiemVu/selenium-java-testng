package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Tương tác Browser thì sẽ thông qua biến WebDriver driver
		//Tương tác Element thì sẽ thông qua biến WebElement element
		

	}

	@Test
	public void TC_01_() {
		//Java document ( cách sử dung hàm này ntn)
		//>= 2tab/window thì sẽ đóng cửa sổ/tab nó đang đứng
		// = 1 : đóng browser
		driver.close();//*
		
		//không quan tâm bao nhiêu tab -> đóng luôn browser
		driver.quit(); //**
		
		//tìm 1 element
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='emai']"));//**)
		
		//tìm nhiều element - Cho phép tìm ra những cái trùng nhau được
		List<WebElement> checkboxs =  driver.findElements(By.xpath(" ")); 
		
		// Mở 1 url nào đó
		driver.get("https://www.facebook.com/"); //**
		// Click vào link đăng ký
		
		//trả về url của page hiện tại
		// có thể lưu vào 1 biến để sử dụng cho các lần tiếp theo -> Dùng lại nhiều lần 
		String regPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(regPageUrl,"https://www.facebook.com/reg/");
		
		//Có thể sử dụng luôn ( không cần tạo biến)
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/reg/");
		//trả về source code của url page hiện tại
		// Dùng verify tương đối
		driver.getPageSource();
		Assert.assertTrue(driver.getPageSource().contains("Tạo tài khoản mới"));
		
		//trả về title của page hiện tại
		Assert.assertEquals(driver.getTitle(),"Facebook - Đăng nhập hoặc đăng ký");
		
		//Học trong bài học WebDriver API - Window/Tabs
		//Lấy ra dc ID của window/Tab mà driver đang đứng
		String loginWindowID = driver.getWindowHandle();
		
		//Lấy ra ID của tất cả window/Tab - Không chấp nhận trùng nhau
		Set<String> allIDs = driver.getWindowHandles(); //*
		
		//trả về interface option
		//Cookie và cake 
		Options opt = driver.manage();
		//lohin thành công -> lưu hết cookie lai
		
		opt.getCookies(); 
		// Testcase khác, lấy cookie ở trên và set vào lại > không cần login nữa
		
		Timeouts time = opt.timeouts();
		
		//Khoảng thời gian chờ element xuất hiện 
		time.implicitlyWait(5, TimeUnit.SECONDS);  //5s = 5000 ms = 5000000 µs
		time.implicitlyWait(5000, TimeUnit.MILLISECONDS);
		time.implicitlyWait(5000000, TimeUnit.MICROSECONDS);
		
		
		//chờ cho 1 page xuất hiện trong vòng bao nhiêu giây
		time.pageLoadTimeout(5, TimeUnit.SECONDS);
		
		//khoảng thời gian chờ script được thực thi xong trong vòng x giấy >> sẽ học trong bài [WedDriver API - Javascript Executor (JavascriptExecutor Libary)]
		time.setScriptTimeout(5, TimeUnit.SECONDS);
		
		Window win = opt.window();
		
		win.fullscreen();
		win.maximize(); //**
		
		//Test FUI: functional
		// Test GUI: font/size/location/position/color
		win.getPosition(); // 
		win.getSize(); // set chiều rộng và chiều cao bên trong trình duyệt
		
		Navigation nav = driver.navigate();
		nav.back();
		nav.refresh();
		nav.forward();
		
		nav.to("https://www.facebook.com/"); //support cho history tốt hơn
		driver.get("https://www.facebook.com/");
		
		
		TargetLocator tar = driver.switchTo();
		tar.alert(); //*
		
		
		tar.frame(""); //*
		
		tar.window(""); //*
		
	}

	@Test
	public void TC_02_() {


	}

	@Test
	public void TC_03_() {


	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}