package org.example.stepDefinition;

import com.microsoft.playwright.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.example.pages.*;

import static org.junit.jupiter.api.Assertions.*;

public class JobTitleSteps {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private AdminPage adminPage;
    private JobTitlesPage jobTitlesPage;

    @Given("I am logged into the OrangeHRM system")
    public void i_am_logged_into_the_orange_hrm_system() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();

        loginPage = new LoginPage(page);
        dashboardPage = new DashboardPage(page);
        adminPage = new AdminPage(page);
        jobTitlesPage = new JobTitlesPage(page);

        loginPage.navigateToLoginPage();
        loginPage.login("Admin", "admin123");
        assertTrue(loginPage.isLoginSuccessful(), "Login was not successful!");
    }

    @When("I navigate to the Admin module")
    public void i_navigate_to_admin_module() {
        dashboardPage.navigateToAdminModule();
        assertTrue(adminPage.isAdminPageVisible(), "Admin module not loaded!");
    }

    @And("I navigate to Job Titles from the Admin module")
    public void i_navigate_to_job_titles() {
        adminPage.clickJobMenu();
        adminPage.clickJobTitlesOption();
        assertTrue(jobTitlesPage.isJobTitlesPageVisible(), "Job Titles page not loaded!");
    }

    @And("I click the Add button in Job Titles")
    public void i_click_the_add_button_in_job_titles() {
        jobTitlesPage.clickAddButton();
        assertTrue(jobTitlesPage.isJobTitleFormVisible(), "Add Job Title form not visible!");
    }

    @And("I fill in the Job Title details:")
    public void i_fill_in_the_job_title_details(DataTable data) {
        jobTitlesPage.fillJobTitleForm(data);
    }

    @And("I click Save in Job Titles")
    public void i_click_save_in_job_titles() {
        jobTitlesPage.clickSaveButton();
        //assertTrue(jobTitlesPage.isSaveSuccessful(), "Job Title was not saved successfully!");
    }

    @Then("I should see the newly created Job Title {string} in the Job Titles table")
    public void i_should_see_the_newly_created_job_title_in_the_job_titles_table(String jobTitle) {
        assertTrue(jobTitlesPage.isJobTitlePresent(jobTitle),
                String.format("Job Title '%s' is not visible in the table!", jobTitle));
    }

    @And("I select the existing Job Title {string}")
    public void i_select_the_existing_job_title(String jobTitle) {
        assertTrue(jobTitlesPage.selectJobTitleFromTable(jobTitle),
                String.format("Job Title '%s' is not found in the table!", jobTitle));
    }

    @And("I click the Edit icon for the selected Job Title")
    public void i_click_the_edit_icon_for_the_selected_job_title() {
        jobTitlesPage.clickEditIcon();
        assertTrue(jobTitlesPage.isJobTitleFormVisible(), "Edit Job Title form not visible!");
    }

    @And("I update the Job Title details:")
    public void i_update_the_job_title_details(DataTable data) {
        jobTitlesPage.fillJobTitleForm(data);
    }

//    @And("I delete any job title")
//    public void i_delete_any_job_title() {
//        jobTitlesPage.deleteFirstJobTitle();
//    }

    @When("I delete the first Job Title")
    public void i_delete_the_first_job_title() {
        jobTitlesPage.deleteFirstJobTitle();
    }

//    @Then("I should see the job title is deleted from the Job Titles table")
//    public void i_should_see_the_job_title_is_deleted_from_the_table() {
//        assertTrue(jobTitlesPage.isAnyRecordAvailable(), "The table is empty after deletion!");
//        browser.close();
//        playwright.close();
//    }
//    @Then("I should see an error message {string}")
//    public void i_should_see_an_error_message(String errorMessage) {
//        assertTrue(jobTitlesPage.isErrorMessageVisible(errorMessage),
//                String.format("Expected error message '%s' not displayed!", errorMessage));
//    }

}



