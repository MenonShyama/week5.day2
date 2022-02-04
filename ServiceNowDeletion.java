package week5.day1;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class ServiceNowDeletion extends BaseClass{
	
	@Test(priority=3)
	public void serviceNowDeletion() throws InterruptedException {
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
		driver.findElement(By.id("sysverb_delete")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("ok_button")).click();
		Thread.sleep(5000);
		WebElement mainPageIncodentNumber = driver.findElement(By.xpath("//table[@id='incident_table']//tr[@class='list2_no_records']/td"));
		String textForNoData = mainPageIncodentNumber.getText();
		System.out.println(textForNoData);
		if(textForNoData.equals("No records to display"))
		{
			System.out.println("Deletion success");
		}else {
			System.out.println("Deletion Failure");
		}
	}

}
