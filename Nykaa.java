package assignment.week4;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

//1) Go to https://www.nykaa.com/
//2) Mouseover on Brands and Search L'Oreal Paris
//3) Click L'Oreal Paris
//4) Check the title contains L'Oreal Paris(Hint-GetTitle)
//5) Click sort By and select customer top rated
//6) Click Category and click Hair->Click haircare->Shampoo
//7) Click->Concern->Color Protection
//8)check whether the Filter is applied with Shampoo
//9) Click on L'Oreal Paris Colour Protect Shampoo
//10) GO to the new window and select size as 175ml
//11) Print the MRP of the product
//12) Click on ADD to BAG
//13) Go to Shopping Bag 
//14) Print the Grand Total amount
//15) Click Proceed
//16) Click on Continue as Guest
//17) Check if this grand total is the same in step 14
//18) Close all windows
public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.nykaa.com/");
		
		WebElement brand = driver.findElement(By.xpath("//a[text()='brands']"));
		Actions builder= new Actions(driver);
		builder.moveToElement(brand).perform();
		
		driver.findElement(By.xpath("//a[contains(text(),'Oreal Paris')]")).click();
        String title = driver.getTitle();
        System.out.println(title);
        if(title.contains("L'Oreal Paris"))
        	System.out.println("Same Title");
        else
        	System.out.println("Not same title");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'Sort By')]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'customer top rated')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Category')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Hair')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Hair Care')]")).click();
		driver.findElement(By.xpath("//span[text()='Shampoo']/following::div[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
		  List<WebElement> check = driver.findElements(By.xpath("//span[text()='Filters Applied']/following::span[@class='filter-value']"));
	      for (WebElement element : check) {
		     String text = element.getText();
		     System.out.println(text);
		     if(text.contains("Shampoo")) {
		    	 System.out.println("Filter contains Shampoo");
		    	 break;
		     }
		     else
		    	 System.out.println("Np shampoo in filter");
	      }
		     Thread.sleep(3000);
		     driver.findElement(By.xpath("//div[contains(text(),'Oreal Paris Colour Protect Shampoo')]")).click();
		     
		     Set<String> windowHandles = driver.getWindowHandles();
		     List<String> list = new ArrayList<String>(windowHandles);
		     String w2 = list.get(1);
		     
		     driver.switchTo().window(w2);
		    System.out.println(driver.getTitle());
		    Thread.sleep(2000);
		    WebElement size = driver.findElement(By.xpath("//select[@title='SIZE']"));
		    Select sel = new Select(size);
		    sel.selectByVisibleText("175ml");
		    Thread.sleep(2000);
		    String text = driver.findElement(By.xpath("//span[text()='MRP:']/following::span")).getText();
		    System.out.println("MRP of product 175ml is : "+text);
		    
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//span[text()='ADD TO BAG']")).click();
	        driver.findElement(By.xpath("//button[@type='button']")).click();  
	        driver.switchTo().frame(0);
	        String text2 = driver.findElement(By.xpath("//span[text()='Grand Total']/following::div")).getText();
	        System.out.println("Grand Rate is: "+text2);
	        driver.findElement(By.xpath("//span[text()='PROCEED']")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
	        String text3 = driver.findElement(By.xpath("//div[text()='Grand Total']/following::div")).getText();
	        if(text2.equals(text3))
	        	System.out.println("Amount is Equal");
	        else
	        	System.out.println("Not same ");
	        driver.quit();
	}

}
