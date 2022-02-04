package week5.day1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNowCreation extends BaseClass {
	@BeforeClass
	public void wbName() {
		filename="ServiceNow";
	}
	public String resultincidentNumber;

	@Test(dataProvider="sendData")
	public void serviceNowCreation(String name,String data) throws InterruptedException, IOException {

//To click on create new link from side menu
		driver.findElement(By.xpath("//ul[@id='concourse_application_tree']//li//div[text()='Create New']/ancestor::a"))
				.click();
		Thread.sleep(2000);
		// Move to incident create form
		driver.switchTo().frame("gsft_main");
		// click on caller serach icon
		driver.findElement(By.xpath("//span[@class='input-group-btn']/button")).click();
		// move to popup window of search caller
		String parentWindow = driver.getWindowHandle();
		Set<String> windowsHandles = driver.getWindowHandles();
		List<String> windows = new ArrayList<String>(windowsHandles);
		String childWindow = windows.get(1);
		driver.switchTo().window(childWindow);
		// select the first resulting caller
		driver.findElement(By.xpath("//tbody[@class='list2_body']//a")).click();
		// move back to parent window
		driver.switchTo().window(parentWindow);
		// move back to frame
		driver.switchTo().frame("gsft_main");

		// enter description
		driver.findElement(By.xpath("(//input[contains(@id,'short_description')])[4]")).sendKeys(data);
		// get incident number
		WebElement element = driver.findElement(By.xpath("//input[@id='incident.number']"));
		String incidentNumber = element.getAttribute("value");
		System.out.println("Incident Number:" + incidentNumber);
		// click submit button
		driver.findElement(By.xpath("//button[@id='sysverb_insert_bottom']")).click();
		Thread.sleep(2000);
		// to select the dropdown number to search created incident number
		Select dropdown = new Select(driver.findElement(By.xpath("//div[@class='input-group']//select")));
		dropdown.selectByVisibleText("Number");
		// enter newly created incident number
		driver.findElement(By.xpath("//div[@class='input-group']//select"));
		WebElement searchBox = driver.findElement(By.xpath("//div[@class='input-group']/input"));
		searchBox.sendKeys(incidentNumber);
		searchBox.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		String resultincidentNumber = driver.findElement(By.xpath("//tbody[@class='list2_body']//td[@class='vt']/a"))
				.getText();
		if (resultincidentNumber.equals(incidentNumber)) {
			File source = driver.getScreenshotAs(OutputType.FILE);
			File destination = new File("./Assignment/successimage.png");
			FileUtils.copyFile(source, destination);
			System.out.println("Incident Created Successully");
		} else {
			System.out.println("Error occurred.execution failed");
		}

	}

}
