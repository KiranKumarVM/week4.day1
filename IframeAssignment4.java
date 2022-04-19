package assignment.week4;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

//http://leafground.com/pages/frame.html
//1.Take the the screenshot of the click me button of first frame
//2.Find the number of frames
//  - find the Elements by tagname - iframe
//  - Store it in a list
//  - Get the size of the list. (This gives the count of the frames visible to the main page)
public class IframeAssignment4 {

	public static void main(String[] args) throws IOException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leafground.com/pages/frame.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// 1.Take the the screenshot of the click me button of first frame
		driver.switchTo().frame(0);
		WebElement clickMe = driver.findElement(By.xpath("//button"));
		File as = clickMe.getScreenshotAs(OutputType.FILE);
		File dest = new File("./snap/element.png");
		FileUtils.copyFile(as, dest);

		driver.switchTo().defaultContent();
		// 2.Find the number of frames
		// - find the Elements by tagname - iframe
		// - Store it in a list
		// - Get the size of the list. (This gives the count of the frames visible to
		// the main page)
		List<WebElement> list = driver.findElements(By.xpath("//iframe"));
		int size = list.size();
		System.out.println("Size of the iframe is " + size);

	}

}
