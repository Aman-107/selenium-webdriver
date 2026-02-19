package Synchronisation_waiting;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class FluentWait_1 {
// has messy code
	// lec 84,85 and 86
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
		driver.findElement(By.cssSelector("[id='start'] button")).click();
		
		//maximum time to wait -> withTimeout 
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
							   .pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
		
		WebElement fu = wait.until(new Function<WebDriver,WebElement>(){
			public WebElement apply(WebDriver driver) {
				if(driver.findElement(By.cssSelector("[id='finish'] h4")).isDisplayed()) {
					return driver.findElement(By.cssSelector("[id='finish'] h4"));
				}
				else
					return null;
			}
		});
		System.out.println(driver.findElement(By.cssSelector("[id='finish'] h4")).isDisplayed());
	}

}

/* There is another explicit wait mechanism type called Fluent wait
 
 Explicit wait can be achieved in 2 ways : 
 1. WebDriverWait -> 10 seconds -> found at 3rd sec -> stops
 2. FluentWait  -> 10 seconds , polling 4 seconds.
 
 Fluent wait finds the web element repeatedly at regular intervals of time until the timeout or till the object gets found.
 Unlike WebDriver wait, we need to build customized wait methods based on condition.
 Both WebDriverWait and FluentWait classes implement Wait interface	

Fluent gives finds element at the polling interval used. Eg -> Riya developed a code of linkedIn in which linkedIn fetch message
appears at interval of 15,30,60 and 90 seconds. There we can use fluent wait. 

One more Example : In E-commerce website when we done shopping and are on the payment page then adding card these are the message
we get in that span of time ->
Your card is accepted (3 sec) -> Your order is being processed (7th sec) -> Confirmation.
Hence we will use fluent wait of pooling interval 3 sec.
*/
