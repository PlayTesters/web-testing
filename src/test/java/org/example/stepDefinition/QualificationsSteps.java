package org.example.stepDefinition;//package org.example.stepDefinition;

import com.microsoft.playwright.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.example.pages.*;

//import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;



public class QualificationsSteps<WebElement> {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private AdminPage adminPage;
    private EducationPage educationPage;

    @Given("I am logged into the OrangeHRM system for Qualifications")
    public void i_am_logged_into_the_orange_hrm_system_for_qualifications() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();

        loginPage = new LoginPage(page);
        dashboardPage = new DashboardPage(page);
        adminPage = new AdminPage(page);
        educationPage = new EducationPage(page);

        loginPage.navigateToLoginPage();
        loginPage.login("Admin", "admin123");
        assertTrue(loginPage.isLoginSuccessful(), "Login was not successful!");
    }

    @When("I navigate to the Admin module")
    public void i_navigate_to_admin_module() {
        dashboardPage.navigateToAdminModule();
        //assertTrue(adminPage.isAdminPageVisible(), "Admin module not loaded!");
    }

    @And("I navigate to Qualifications from the Admin module")
    public void i_navigate_to_qualifications() {
        adminPage.clickQualificationsTab();
        // Ensure the Qualifications dropdown is visible
        assertTrue(page.isVisible("ul.oxd-dropdown-menu"), "Qualifications dropdown not visible!");
    }

    @And("I navigate to Education from the Qualifications menu")
    public void i_navigate_to_education_from_qualifications() {
        adminPage.clickEducationOption();
        assertTrue(educationPage.isEducationPageVisible(), "Education page not loaded!");
    }

    @And("I click the Add button in Qualifications")
    public void i_click_the_add_button_in_job_titles() {
        educationPage.clickAddButton();
        assertTrue(educationPage.isEducationFormVisible(), "Add Education form not visible!");
    }

    @And("I fill in the Education details:")
    public void i_fill_in_the_education_details(DataTable data) {
        educationPage.fillEducationForm(data);
    }

    @And("I click Save in Education Valid")
    public void i_click_save_in_education_valid() {
        educationPage.clickSaveButton();
        assertTrue(educationPage.isSaveSuccessful(), "Education was not saved successfully!");
    }



    @And("I click Save in Education")
    public void i_click_save_in_education() {
        educationPage.clickSaveButton();

        // Wait briefly to allow the error message to render
        try {
            Thread.sleep(3000); // Sleep for 2 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Verify the "Required" error message is displayed
        assertTrue(educationPage.isErrorMessageDisplayed(), "The 'Required' error message was not displayed!");

//        // Close the form
//        educationPage.closeForm();
    }


    @When("I click the Delete button for the first displayed Education record")
    public void iClickTheDeleteButtonForTheFirstDisplayedEducationRecord() {
        educationPage.deleteFirstEducationRecord();  // Calls the method to click the delete button
    }

}

