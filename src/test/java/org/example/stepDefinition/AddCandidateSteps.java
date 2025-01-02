package org.example.stepDefinition;

import com.microsoft.playwright.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.example.pages.*;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class AddCandidateSteps {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private RecruitmentPage recruitmentPage;
    private AddCandidatePage addCandidatePage;

    @Given("I am logged into the OrangeHRM system")
    public void i_am_logged_into_the_orange_hrm_system() throws InterruptedException {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();

        loginPage = new LoginPage(page);
        dashboardPage = new DashboardPage(page);
        recruitmentPage = new RecruitmentPage(page);
        addCandidatePage = new AddCandidatePage(page);

        loginPage.navigateToLoginPage();
        Thread.sleep(2000); // Adding delay for login validation
        loginPage.login("Admin", "admin123");
        Thread.sleep(2000); // Adding delay for login validation
        assertTrue(loginPage.isLoginSuccessful(), "Login was not successful!");
    }

    @When("I navigate to the Recruitment module")
    public void i_navigate_to_recruitment_module() throws InterruptedException {
        dashboardPage.navigateToRecruitmentModule();
        Thread.sleep(2000); // Adding delay after navigation
    }

    @And("I click the Add button in Recruitment")
    public void i_click_the_add_button_in_recruitment() throws InterruptedException {
        recruitmentPage.clickAddButton();
        Thread.sleep(2000); // Adding delay after clicking "Add"
    }

    @And("I fill in the candidate details:")
    public void i_fill_in_the_candidate_details(DataTable data) throws InterruptedException {
        addCandidatePage.fillCandidateDetails(data);
        Thread.sleep(3000); // Adding delay after filling candidate details
    }

    @When("I select a vacancy by index {int}")
    public void i_select_a_vacancy_by_index(int index) throws InterruptedException {
        addCandidatePage.selectVacancyByIndex(index);
        Thread.sleep(2000); // Adding delay after selecting vacancy
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/after_selecting_vacancy.png")));
    }

    @And("I click Save in Add Candidate")
    public void i_click_save_in_add_candidate() throws InterruptedException {
        addCandidatePage.clickSaveButton();
        Thread.sleep(3000); // Adding delay after clicking "Save"

    }

    @And("I shortlist the candidate")
    public void i_shortlist_the_candidate() throws InterruptedException {
        // Invoke the method from the page object
        addCandidatePage.clickShortlistButton();
        Thread.sleep(2000); // Adding delay for navigation to the next page
    }

    @And("I fill in the notes {string} and save")
    public void i_fill_in_the_notes_and_save(String notes) throws InterruptedException {
        // Invoke the method from the page object
        addCandidatePage.fillNotesAndSave(notes);
        Thread.sleep(2000); // Adding delay after saving
    }

    @And("I delete a candidate")
    public void i_delete_a_candidate() throws InterruptedException {
        recruitmentPage.deleteAnyRecord();
        Thread.sleep(3000); // Adding delay to ensure deletion is complete
        browser.close();
        playwright.close();
    }

    @Then("I should see the email error message {string}")
    public void i_should_see_the_email_error_message(String expectedMessage) {
        // Call the new method to get the error message under the email field
        String actualMessage = addCandidatePage.getEmailErrorMessage();
        // Assert that the actual error message matches the expected one
        assertEquals(expectedMessage, actualMessage, "The email error message is not as expected.");
    }


}





