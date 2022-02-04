package week5.day1;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public ChromeDriver driver;
	public String filename;
@Parameters({"url","UserName","password"})
 @BeforeMethod()
  public void beforeMethod(String url,String uName,String pswd) throws InterruptedException {
	 WebDriverManager.chromedriver().setup();
	   driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(url);
		Thread.sleep(5000);
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys(uName);
		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys(pswd);
		driver.findElement(By.xpath("//button[@id='sysverb_login']")).click();
		Thread.sleep(5000);
		WebElement filter = driver.findElement(By.xpath("//input[@id='filter']"));
		filter.sendKeys("incident");
		 Thread.sleep(5000);
	
  }
@AfterMethod()
public void afterMethod()
{
	driver.close();
	}

@DataProvider(name="sendData")
public String[][] data() throws IOException{
	return ReadFile.readExcel(filename);
}

}
