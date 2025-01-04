package org.example.stepDefinition;

import com.microsoft.playwright.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.example.pages.*;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class AdminSteps {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private AdminPage adminPage;
    private AddNewUserPage addNewUserPage;
    private LanguagePage languagePage;

    @Given("I am logged into the OrangeHRM system")
    public void i_am_logged_into_the_orange_hrm_system() throws InterruptedException {
        playwright = Playwright.create();
        //browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));

        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();

        loginPage = new LoginPage(page);
        dashboardPage = new DashboardPage(page);
        adminPage = new AdminPage(page);
        addNewUserPage = new AddNewUserPage(page);
        languagePage = new LanguagePage(page);

        loginPage.navigateToLoginPage();
        loginPage.login("Admin", "admin123");
        assertTrue(loginPage.isLoginSuccessful(), "Login was not successful!");
        Thread.sleep(2000);
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/after_login_was_not_successful.png")));
    }

    @When("I navigate to the Admin module")
    public void i_navigate_to_admin_module() throws InterruptedException {
        dashboardPage.navigateToAdminModule();
        Thread.sleep(2000);
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/after_navigate_the_admin_module.png")));
    }

    @And("I click the Add button in Admin")
    public void i_click_the_add_button_in_admin() throws InterruptedException {
        adminPage.clickAddButton();
        Thread.sleep(2000);
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/after_the_add_button.png")));
    }

    @And("I fill in the newuser details:")
    public void i_fill_in_the_newuser_details(DataTable data) throws InterruptedException {
        addNewUserPage.fillNewUserDetails(data);
        Thread.sleep(2000);
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/after_fill_newuser_Details.png")));
    }

    @And("I click Save in Add NewUser")
    public void i_click_save_in_add_newuser() throws InterruptedException {
        addNewUserPage.clickSaveButton();
        Thread.sleep(2000);
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/after_click_save.png")));
        browser.close();
        playwright.close();
    }

    @When("I delete the third displayed user")
    public void iDeleteTheThirdDisplayedUser() throws InterruptedException {
        adminPage.deleteThirdUser();
        Thread.sleep(2000);
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/after_delete_the_third_displayed_user.png")));
    }

    @And("I navigate to Qualification from the Admin module")
    public void i_navigate_to_qualification() throws InterruptedException {
        adminPage.clickQualificationTab();
        Thread.sleep(2000);
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/after_navigate_to_the_qualification.png")));
    }
    @And("I navigate to Languages from the Qualifications menu")
    public void i_navigate_to_language_from_qualifications() throws InterruptedException {
        adminPage.clickLanguageOption();
        Thread.sleep(2000);
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/after_navigate_the_language.png")));
    }
    @And("I click the Add button in Languages")
    public void i_click_the_add_button_in_job_titles() throws InterruptedException {
        languagePage.clickAddButton();
        Thread.sleep(2000);
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/after_click_the_add_button.png")));
    }
    @And("I fill in the Language details")
    public void i_fill_in_the_language_details(DataTable data) throws InterruptedException {
        languagePage.fillLanguageForm(data);
        Thread.sleep(2000);
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/after_fill_the_language_details.png")));
    }

    @And("I click Save in Language")
    public void i_click_save_in_language_valid() throws InterruptedException {
        languagePage.clickSaveButton();
        Thread.sleep(2000);
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/after_click_the_save.png")));
    }



}

