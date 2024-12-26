package org.example.stepDefinition;


import com.microsoft.playwright.*;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {
	private static Playwright playwright;
	private static Browser browser;
	private static Page page;

	@Given("I navigate to the SwagLabs login page")
	public void navigateToLoginPage() {
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		page = browser.newPage();
		page.navigate("https://www.saucedemo.com/v1/");
		System.out.println("Page title: " + page.title());
	}

	@When("I enter username {string} and password {string}")
	public void enterCredentials(String username, String password) {
		page.fill("#user-name", username);
		page.fill("#password", password);
	}

	@And("I click on the login button")
	public void clickLoginButton() {
		page.click("#login-button");
	}

	@Then("I should see the inventory page")
	public void verifyInventoryPage() {
		assertTrue(page.url().contains("inventory.html"), "Login was unsuccessful!");
		browser.close();
		playwright.close();
	}
}
