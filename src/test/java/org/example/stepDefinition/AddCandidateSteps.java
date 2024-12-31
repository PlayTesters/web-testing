//package org.example.stepDefinition;
//
//import com.microsoft.playwright.*;
//import io.cucumber.datatable.DataTable;
//import io.cucumber.java.en.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class AddCandidateSteps {
//    private static Playwright playwright;
//    private static Browser browser;
//    private static Page page;
//
//    @Given("I am logged into the OrangeHRM system")
//    public void i_am_logged_into_the_orange_hrm_system() {
//        playwright = Playwright.create();
//        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//        page = browser.newPage();
//        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
//
//        page.fill("input[name='username']", "Admin");
//        page.fill("input[name='password']", "admin123");
//        page.click("button[type='submit']");
//        page.waitForSelector("a[href='/web/index.php/dashboard/index']", new Page.WaitForSelectorOptions().setTimeout(10000));
//        assertTrue(page.url().contains("/dashboard"), "Login was not successful!");
//    }
//
//    @When("I navigate to the Recruitment module")
//    public void i_navigate_to_recruitment_module() {
//        page.click("a[href='/web/index.php/recruitment/viewRecruitmentModule']");
//        page.waitForSelector("h6:has-text('Recruitment')", new Page.WaitForSelectorOptions().setTimeout(10000));
//    }
//
//    @And("I click the Add button in Recruitment")
//    public void i_click_the_add_button_in_recruitment() {
//        page.waitForSelector("button.oxd-button--secondary:has-text('Add')", new Page.WaitForSelectorOptions().setTimeout(10000));
//        page.click("button.oxd-button--secondary:has-text('Add')");
//        page.waitForSelector("h6:has-text('Add Candidate')", new Page.WaitForSelectorOptions().setTimeout(10000));
//    }
//
////    @And("I fill in the candidate details:")
////    public void i_fill_in_the_candidate_details(DataTable data) {
////        data.asMaps(String.class, String.class).forEach(row -> {
////            String field = row.get("Field");
////            String value = row.get("Value");
////
////            switch (field) {
////                case "First Name":
////                    page.fill("input[name='firstName']", value);
////                    break;
////                case "Last Name":
////                    page.fill("input[name='lastName']", value);
////                    break;
////                case "Email":
////                    page.fill("input[placeholder='Type here']:nth-of-type(1)", value);
////                    break;
////                default:
////                    throw new IllegalArgumentException("Unknown field: " + field);
////            }
////        });
////    }
////
////    @And("I click Save in Add Candidate")
////    public void i_click_save_in_add_candidate() {
////        page.waitForSelector("button[type='submit']:has-text('Save')", new Page.WaitForSelectorOptions().setTimeout(10000));
////        page.click("button[type='submit']");
////        page.waitForSelector(".oxd-toast-container", new Page.WaitForSelectorOptions().setTimeout(12000));
////    }
////
////    @Then("I should see a confirmation message {string}")
////    public void i_should_see_a_confirmation_message(String message) {
////        String confirmationMessage = page.innerText(".oxd-toast-container");
////        assertTrue(confirmationMessage.contains(message), "Expected confirmation message not found!");
////    }
////
////    @And("the candidate should be listed in the candidates table")
////    public void the_candidate_should_be_listed_in_the_candidates_table() throws InterruptedException {
////        // Wait for the table body to exist
////        page.waitForSelector("table tbody", new Page.WaitForSelectorOptions().setTimeout(10000));
////
////        // Retry mechanism for dynamic row loading
////        boolean isRowFound = false;
////        for (int i = 0; i < 10; i++) {
////            if (page.locator("table tbody tr:last-child").count() > 0) {
////                isRowFound = true;
////                break;
////            }
////            Thread.sleep(1000); // Wait 1 second before retrying
////        }
////        assertTrue(isRowFound, "Candidate row was not found in the table!");
////
////        // Verify candidate details
////        String firstName = page.innerText("table tbody tr:last-child td:nth-child(2)");
////        assertTrue(firstName.contains("kam"), "First Name not found!");
////
////        String lastName = page.innerText("table tbody tr:last-child td:nth-child(3)");
////        assertTrue(lastName.contains("dum"), "Last Name not found!");
////
////        String email = page.innerText("table tbody tr:last-child td:nth-child(4)");
////        assertTrue(email.contains("kam.doe@example.com"), "Email not found!");
////
////        browser.close();
////        playwright.close();
////    }
////
////
////    @And("I scroll down to Candidate History")
////    public void i_scroll_down_to_candidate_history() {
////        page.waitForSelector("h6.oxd-text--h6.orangehrm-main-title:has-text('Candidate History')", new Page.WaitForSelectorOptions().setTimeout(12000));
////        page.locator("h6.oxd-text--h6.orangehrm-main-title:has-text('Candidate History')").scrollIntoViewIfNeeded();
////    }
//
//}



package org.example.stepDefinition;

import com.microsoft.playwright.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.example.pages.*;

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
    public void i_am_logged_into_the_orange_hrm_system() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();

        loginPage = new LoginPage(page);
        dashboardPage = new DashboardPage(page);
        recruitmentPage = new RecruitmentPage(page);
        addCandidatePage = new AddCandidatePage(page);

        loginPage.navigateToLoginPage();
        loginPage.login("Admin", "admin123");
        assertTrue(loginPage.isLoginSuccessful(), "Login was not successful!");
    }

    @When("I navigate to the Recruitment module")
    public void i_navigate_to_recruitment_module() {
        dashboardPage.navigateToRecruitmentModule();
    }

    @And("I click the Add button in Recruitment")
    public void i_click_the_add_button_in_recruitment() {
        recruitmentPage.clickAddButton();
    }

    @And("I fill in the candidate details:")
    public void i_fill_in_the_candidate_details(DataTable data) {
        addCandidatePage.fillCandidateDetails(data);
    }

    @And("I click Save in Add Candidate")
    public void i_click_save_in_add_candidate() {
        addCandidatePage.clickSaveButton();
        browser.close();
       playwright.close();
    }

//    @Then("I should see a confirmation message {string}")
//    public void i_should_see_a_confirmation_message(String message) {
//        assertEquals(message, addCandidatePage.getConfirmationMessage());
//        browser.close();
//        playwright.close();
//    }

//    @Then("I should see a confirmation message {string}")
//    public void i_should_see_a_confirmation_message(String message) {
//        // Debugging: Check if the selector is correct
//        Locator successMessage = page.locator("div.success-message-class"); // Replace with your actual CSS selector
//
//        // Wait for the success message or fail
//        if (successMessage.isVisible(new Locator.IsVisibleOptions().setTimeout(15000))) {
//            String actualMessage = successMessage.textContent();
//            assertEquals(message, actualMessage, "The confirmation message does not match!");
//        } else {
//            fail("Success message did not appear within the timeout!");
//        }
//
//        // Cleanup
//        browser.close();
//        playwright.close();
//    }


}
