package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class LoginTest extends Base {

	public WebDriver driver;
	Logger log;
	
	
	@Test(dataProvider = "getLoginData")
	public void login(String email, String password, String expectedStatus) throws IOException {
		
		
		/*driver = initializeDriver(); // It will return WebDriver //this driver already launched
		driver.get(prop.getProperty("url")); */
		LandingPage landingPage = new LandingPage(driver);
		landingPage.myAccountDropdown().click();
		landingPage.loginOption().click();
		log.debug("clicked on My Account dropdown");
		
		
		

		LoginPage loginPage = new LoginPage(driver);
		loginPage.emailAddressField().sendKeys(email);
		log.debug("Email address got entered");
		loginPage.passwordField().sendKeys(password);
		log.debug("Password got entered");
		loginPage.loginButton().click();
		log.debug("clicked on login button");

		AccountPage accountPage = new AccountPage(driver);

		String actualResult = null;
		try {
			if (accountPage.editYourAccountInformationOption().isDisplayed())
				
			{
				actualResult = "Successfull";
				log.debug("User got logged in");
			}
		} catch (Exception e) {
			actualResult = "Failure";
			log.debug("User not logged in");
		}

		Assert.assertEquals(actualResult, expectedStatus);
		log.info("Login Test got passed");
	}
	
	@BeforeMethod
	public void openApplication() throws IOException   // It will return WebDriver //this driver already launched
	{
		log = LogManager.getLogger(LoginTest.class.getName());
		
		driver = initializeDriver();
		log.debug("browser got launched");
		driver.get(prop.getProperty("url"));
		log.debug("Navigated to application URL");
	}

	
	

	@AfterMethod
	public void closure() {
		driver.close();
		log.debug("Browser got closed");
	}

	@DataProvider
	public Object[][] getLoginData() {
		Object[][] data = { { "amulkathare152@gmail.com", "amul123", "Successfull" },
				 };//{ "dummytest@gmail.com", "dummy", "Failure" }
		return data;
	}
}
