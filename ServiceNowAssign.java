package week5.day1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ServiceNowAssign extends BaseClass {
	
	@BeforeClass
	public void wbName() {
		filename="AssignIncident";
	}
	@Test(dataProvider="sendData" , priority = 2)
	public void serviceNowAssign(String group) throws InterruptedException {
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
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@class='input-group-btn']/button[@id='lookup.incident.assignment_group']"))
				.click();
		// move to popup window of search caller
		String parentWindow = driver.getWindowHandle();
		Set<String> windowsHandles = driver.getWindowHandles();
		List<String> windows = new ArrayList<String>(windowsHandles);
		String childWindow = windows.get(1);
		driver.switchTo().window(childWindow);
		Thread.sleep(2000);
		WebElement dropdownSelect = driver.findElement(By.xpath("//div[@class='input-group']//select"));
		Select dd=new Select(dropdownSelect);
		dd.selectByVisibleText("Name");
		WebElement textfindEle = driver.findElement(By.xpath("//div[@class='input-group']//input"));
		textfindEle.sendKeys(group);
		textfindEle.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
		
		driver.findElement(By.xpath("//table[@id='sys_user_group_table']//tbody[@class='list2_body']//td[3]/a")).click();
		driver.switchTo().window(parentWindow);
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//div[@class='sn-stream-textarea-container']//textarea[@id='activity-stream-textarea']")).sendKeys("All done");
		driver.findElement(By.id("sysverb_update")).click();
		Thread.sleep(2000);
		WebElement groupDisplay = driver
				.findElement(By.xpath("//table[@id='incident_table']//tbody[@class='list2_body']//td[11]"));
		String groupDisplayText = groupDisplay.getText();
		System.out.println(groupDisplayText);
		if(groupDisplayText.equals("Software")) {
			System.out.println("Assign success");
		}else {
			System.out.println("Assign Fail");
		}
	}

}
