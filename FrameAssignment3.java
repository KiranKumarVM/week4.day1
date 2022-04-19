package assignment.week4;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FrameAssignment3 {

	public static void main(String[] args) throws InterruptedException {
	  
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		Thread.sleep(2000);
		driver.switchTo().frame("frame1");
		driver.findElement(By.xpath("//input")).sendKeys("Selenium");
		driver.switchTo().frame(0);
		driver.findElement(By.id("a")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("frame2");
		WebElement animals = driver.findElement(By.id("animals"));
		Select select = new Select(animals);
		select.selectByValue("avatar");
	}

}
