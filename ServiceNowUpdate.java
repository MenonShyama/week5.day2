package week5.day1;

import java.io.File;
import java.io.IOException;
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

public class ServiceNowUpdate extends BaseClass {
	@BeforeClass
	public void wbName() {
		filename="EditIncident";
	}
	ServiceNowCreation cs = new ServiceNowCreation();

	@Test(dataProvider="sendData",priority = 1)
	public void serviceNowUpdate(String note) throws InterruptedException, IOException {

		driver.findElement(By.xpath("(//div[text()='Incidents'])[2]")).click();
		Thread.sleep(2000);
//to select the dropdown number to search created incident number
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//b[text()='All']")).click();
		Select dropdown = new Select(driver.findElement(By.xpath("//div[@class='input-group']//select")));
		dropdown.selectByVisibleText("Number");
//search the reuired incident number
		WebElement searchBox = driver.findElement(By.xpath("//div[@class='input-group']/input"));
		searchBox.sendKeys("INC0010056");
		searchBox.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//tbody[@class='list2_body']//td[@class='vt']/a")).click();
		// driver.switchTo().frame("gsft_main");
		WebElement list = driver.findElement(By.xpath("//select[@id='incident.urgency']"));
		Select dd = new Select(list);
		dd.selectByVisibleText("1 - High");
		WebElement statedd = driver.findElement(By.xpath("//select[@id='incident.state']"));
		Select state = new Select(statedd);
		state.selectByVisibleText("In Progress");
		driver.findElement(By.id("sysverb_update")).click();
		Thread.sleep(2000);
		WebElement stateDisplay = driver
				.findElement(By.xpath("//table[@id='incident_table']//tbody[@class='list2_body']//td[8]"));
		String stateDisplayText = stateDisplay.getText();
		System.out.println(stateDisplayText);
		WebElement urgencyDisplay = driver
				.findElement(By.xpath("//table[@id='incident_table']//tbody[@class='list2_body']//td[9]"));
		String urgencyDisplayText = urgencyDisplay.getText();
		System.out.println(urgencyDisplayText);
		if (stateDisplayText.equals("In Progress") && urgencyDisplayText.equals("1 - High")) {
			System.out.println("Update success");
		} else {
			System.out.println("update ail");
		}

	}

}
