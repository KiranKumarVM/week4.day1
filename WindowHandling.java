package assignment.week4;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
 * //Pseudo Code
 * 
 * 1. Launch URL "http://leaftaps.com/opentaps/control/login"
 * 
 * 2. Enter UserName and Password Using Id Locator
 * 
 * 3. Click on Login Button using Class Locator
 * 
 * 4. Click on CRM/SFA Link
 * 
 * 5. Click on contacts Button
 * 	
 * 6. Click on Merge Contacts using Xpath Locator
 * 
 * 7. Click on Widget of From Contact
 * 
 * 8. Click on First Resulting Contact
 * 
 * 9. Click on Widget of To Contact
 * 
 * 10. Click on Second Resulting Contact
 * 
 * 11. Click on Merge button using Xpath Locator
 * 
 * 12. Accept the Alert
 * 
 * 13. Verify the title of the page
 */
public class WindowHandling {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.findElement(By.id("username")).sendKeys("DemoCSR");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();

		Thread.sleep(2000);
		driver.findElement(By.partialLinkText("CRM/SFA")).click();

		driver.findElement(By.linkText("Contacts")).click();

		driver.findElement(By.linkText("Merge Contacts")).click();

		driver.findElement(By.xpath("//input[@name='ComboBox_partyIdFrom']/following::img[1]")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(windowHandles);
		String secondWindow = list.get(1);
		driver.switchTo().window(secondWindow);
		driver.findElement(By.xpath("(//td[contains(@class,'td-partyId')]//a)[2]")).click();
		driver.switchTo().window(list.get(0));
		driver.findElement(By.xpath("//input[@name='ComboBox_partyIdFrom']/following::img[2]")).click();
		Set<String> windowHandle = driver.getWindowHandles();
		List<String> list1 = new ArrayList<String>(windowHandle);
		String secondWindow1 = list1.get(1);
		driver.switchTo().window(secondWindow1);
		driver.findElement(By.xpath("(//td[contains(@class,'td-partyId')]//a)[3]")).click();
		driver.switchTo().window(list1.get(0));
		driver.findElement(By.className("buttonDangerous")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
        String title = driver.getTitle();
        System.out.println("The title is : "+title);
        if(title.contains("View Contact"))
        	System.out.println("Title matches");
        else
        	System.out.println("Not matching Title");
	}

}
