package stepdefinitions;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class Login extends Base {
	WebDriver driver;
	LandingPage landingPage;
	LoginPage loginPage;
	AccountPage accountPage;
	
	@Given("^Open any Browser$")
	public void openAnyBrowser() throws IOException
	{
		driver = initializeDriver();
	}
	
	@And("^Navigate to Login page$")
	public void navigateToLoginPage()
	{
		driver.get(prop.getProperty("url"));
		landingPage = new LandingPage(driver);
		landingPage.myAccountDropdown().click();
		landingPage.loginOption().click();
	}
	
	@When("User enters username as {string} and password as {string} into the fields")
	public void  userEntersUsernameAndPasswordIntoTheFields(String email,String password) throws InterruptedException
	{
		loginPage = new LoginPage(driver);
		loginPage.emailAddressField().sendKeys(email);
		loginPage.passwordField().sendKeys(password);
		Thread.sleep(2000);
		
	}
	
	@And("^User clicks on Login button$")
	public void userClicksOnLoginButton()
	{
		loginPage.loginButton().click();
	}
	
	@Then("^Verify user is able to successfully login$")
	public void verifyUserIsAbleToSuccessfullyLogin()
	{

      accountPage = new AccountPage(driver);
      Assert.assertTrue(accountPage.editYourAccountInformationOption().isDisplayed());
	}
	
	@After
	public void closeBrowser()
	{
		driver.close();
	}
	
}
