package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import resources.Base;

public class TwoTest extends Base {
	@Test
	public void twoTest() throws IOException, InterruptedException {
		System.out.println("Krish has updated the code");
		System.out.println("TwoTest");
		WebDriver driver = initializeDriver();
		driver.get("https://tutorialsninja.com/demo/");
		Thread.sleep(2000);
		driver.close();
		
	}
}
