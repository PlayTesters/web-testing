
package org.example.stepDefinition;

import com.microsoft.playwright.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.example.pages.*;

import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class NationalitiesSteps {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private AdminPage adminPage;
    private NationalitiesPage nationalitiesPage;

    @Given("I am logged into the OrangeHRM system for Nationalities")
    public void i_am_logged_into_the_orange_hrm_system_for_nationalities() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();

        loginPage = new LoginPage(page);
        dashboardPage = new DashboardPage(page);
        adminPage = new AdminPage(page);
        nationalitiesPage = new NationalitiesPage(page);

        loginPage.navigateToLoginPage();
        loginPage.login("Admin", "admin123");
        assertTrue(loginPage.isLoginSuccessful(), "Login was not successful!");

        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/logged_into_the_OrangeHRM_system_for_Nationalities")));
    }

    @When("I navigate to the Admin module for Nationalities")
    public void i_navigate_to_the_admin_module_for_nationalities() {
        dashboardPage.navigateToAdminModule();
        assertTrue(adminPage.isAdminPageVisible(), "Admin module not loaded!");

        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/Admin_module_for_Nationalities")));
    }

    @And("I navigate to Nationalities from the Admin module")
    public void i_navigate_to_nationalities() {
        nationalitiesPage.navigateToNationalities();
        assertTrue(nationalitiesPage.isNationalitiesPageVisible(), "Nationalities page not loaded!");

        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/navigate_to_Nationalities")));
    }

    @And("I click the Add button in Nationalities")
    public void i_click_the_add_button_in_nationalities() {
        nationalitiesPage.navigateToAddNationality();
        assertTrue(page.isVisible("h6:has-text('Add Nationality')"), "Add Nationality form not visible!");

        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/click_the_Add_button_in_Nationalities")));

    }

    @And("I fill in the Nationality details:")
    public void i_fill_in_the_nationality_details(DataTable data) {
        Map<String, String> formData = data.asMap(String.class, String.class);
        page.fill("input.oxd-input:below(label:has-text('Name'))", formData.get("Name"));

        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/fill_in_the_Nationality_details")));
    }

    @And("I click Save in Nationalities")
    public void i_click_save_in_nationalities() {
        page.click("button.oxd-button--secondary:has-text('Save')");

                new Page.WaitForSelectorOptions().setTimeout(10000); // Increased timeout

        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/Save_in_Nationalities")));
    }

    @And("I delete the first displayed Nationality")
    public void i_delete_the_first_displayed_nationality() {
        // Click the delete button for the first nationality in the table
        page.click("button.oxd-icon-button.oxd-table-cell-action-space");

        // Wait for the confirmation dialog to appear and confirm deletion
        page.waitForSelector("button.oxd-button--label-danger:has-text('Yes')", new Page.WaitForSelectorOptions().setTimeout(5000));
        page.click("button.oxd-button--label-danger:has-text('Yes')");

        // Wait for the toast message confirming successful deletion
        page.waitForSelector("div.oxd-toast-container:has-text('Successfully Deleted')",
                new Page.WaitForSelectorOptions().setTimeout(10000)); // Increased timeout

        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/delete_the_first_displayed_Nationality")));

    }

    @Then("I should not see the deleted Nationality in the Nationalities table")
    public void i_should_not_see_the_deleted_nationality_in_the_nationalities_table() {
        // Ensure the nationality is no longer visible in the table
        assertFalse(page.isVisible("div.oxd-table-cell:has-text('Albanianaa')"),
                "Deleted nationality 'Albanianaa' is still visible in the table!");

        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/should_not_see_the_deleted_Nationality")));
    }

    @Then("I should see a validation message for required fields in Nationalities")
    public void i_should_see_a_validation_message_for_required_fields_in_nationalities() {
        // Locate and capture the validation message
        String validationMessage = page.textContent("span:below(input.oxd-input:below(label:has-text('Name')))");

        // Assert the validation message
        assertEquals("Required", validationMessage.trim(), "Validation message does not match!");

        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/validation_message_for_required_fields")));

    }






}
