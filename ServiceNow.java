package assignment.week4;
//ServiceNow- ServiceNow- 

//Refer the attached document below for clarity to create your own instance( sign In credentials) to work with servicenow
//Refer the attached document for the flow of the application to complete the testcase
//
//Step1: Load ServiceNow application URL 
//Step2: Enter username (Check for frame before entering the username)
//Step3: Enter password 
//Step4: Click Login
//Step5: Search “incident “ Filter Navigator
//Step6: Click “All”
//Step7: Click New button
//Step8: Select a value for Caller and Enter value for short_description
//Step9: Read the incident number and save it a variable
//Step10: Click on Submit button
//Step 11: Search the same incident number in the next search screen as below
//Step12: Verify the incident is created successful.

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://dev121343.service-now.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("India@123");
		driver.findElement(By.id("sysverb_login")).click();

		driver.findElement(By.id("filter")).sendKeys("incident");

		driver.findElement(By.xpath("(//div[text()='All'])[2]")).click();
		Thread.sleep(2000);

		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		String attribute = driver.findElement(By.xpath("//*[@name='incident.number']")).getAttribute("value");
		System.out.println("The incident id is : " + attribute);

		driver.findElement(By.xpath("//*[@class='icon icon-search']")).click();

		Set<String> w1 = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(w1);

		driver.switchTo().window(list.get(1));
		driver.findElement(By.xpath("//a[@class='glide_ref_item_link']")).click();
		driver.switchTo().window(list.get(0));
		driver.switchTo().frame("gsft_main");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='lookup.incident.short_description']")).click();

		Set<String> w2 = driver.getWindowHandles();
		List<String> list1 = new ArrayList<String>(w2);
		driver.switchTo().window(list1.get(1));
		driver.findElement(By.xpath("(//a)[4]")).click();

		driver.switchTo().window(list1.get(0));
		driver.switchTo().frame("gsft_main");

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(attribute);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(Keys.ENTER);

		String text = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();

		if (text.equals(attribute))
			System.out.println("Equal Incidents...");
		else
			System.out.println("Not Equal please check");

	}

}
