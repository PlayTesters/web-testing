//package org.example.stepDefinition;
//
//import com.microsoft.playwright.*;
//import io.cucumber.datatable.DataTable;
//import io.cucumber.java.en.*;
//import org.example.pages.*;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class PimAddEmployeeSteps {
//    private Playwright playwright;
//    private Browser browser;
//    private Page page;
//
//    private LoginPage loginPage;
//    private DashboardPage dashboardPage;
//    private PimPage pimPage;
//
//    @Given("I am logged into the OrangeHRM system")
//    public void i_am_logged_into_the_orange_hrm_system() {
//        playwright = Playwright.create();
//        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//        page = browser.newPage();
//
//        loginPage = new LoginPage(page);
//        dashboardPage = new DashboardPage(page);
//        pimPage = new PimPage(page);
//
//        loginPage.navigateToLoginPage();
//        loginPage.login("Admin", "admin123");
//        assertTrue(loginPage.isLoginSuccessful(), "Login was not successful!");
//    }
//
//    @When("I navigate to the PIM module")
//    public void i_navigate_to_pim_module() {
//        dashboardPage.navigateToPimModule();
//    }
//
//    @And("I click the Add button in PIM")
//    public void i_click_the_add_button_in_pim() {
//        pimPage.clickAddButton();
//    }
//
//    @And("I fill in the employee details:")
//    public void i_fill_in_the_employee_details(DataTable data) {
//        pimPage.fillEmployeeDetails(data);
//    }
//
//    @And("I click Save in Add Employee")
//    public void i_click_save_in_add_employee() {
//        pimPage.clickSaveButton();
//
//    }
//
//    @Then("I navigate to the Employee List")
//    public void i_navigate_to_employee_list() {
//        pimPage.navigateToEmployeeList();
//        browser.close();
//        playwright.close();
//    }
//}

package org.example.stepDefinition;

import com.microsoft.playwright.*;
        import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
        import org.example.pages.*;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class PimAddEmployeeSteps {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private PimPage pimPage;

    @Given("I am logged into the OrangeHRM system")
    public void i_am_logged_into_the_orange_hrm_system() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();

        loginPage = new LoginPage(page);
        dashboardPage = new DashboardPage(page);
        pimPage = new PimPage(page);

        loginPage.navigateToLoginPage();
        loginPage.login("Admin", "admin123");
        assertTrue(loginPage.isLoginSuccessful(), "Login was not successful!");
    }

    @When("I navigate to the PIM module")
    public void i_navigate_to_the_pim_module() throws InterruptedException {
        dashboardPage.navigateToPIMModule();
        Thread.sleep(2000);
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/after_navigate_to_PIM_Module.png")));
    }

    @And("I click the Add button in PIM")
    public void i_click_the_add_button_in_pim() throws InterruptedException {
        pimPage.clickAddButton();
        Thread.sleep(2000);
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/after_navigate_to_PIM_Module.png")));
    }

    @And("I fill in the employee details:")
    public void i_fill_in_the_employee_details(DataTable data) throws InterruptedException {
        pimPage.fillEmployeeDetails(data);
        Thread.sleep(2000);
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/after_fill_Employee_Details.png")));
    }

    @And("I click Save in Add Employee")
    public void i_click_save_in_add_employee() throws InterruptedException {
        pimPage.clickSaveButton();
        Thread.sleep(2000);
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/after_click_Save_the_employee.png")));
    }

    @And("I click the Employee List in the menu bar")
    public void i_click_the_employee_list_in_the_menu_bar() throws InterruptedException {
        pimPage.navigateToEmployeeList();
        Thread.sleep(2000);
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/after_navigate_to_the_employee_list.png")));
    }

    @And("I delete the first displayed employee")
    public void i_delete_the_first_displayed_employee() throws InterruptedException {
        pimPage.deleteFirstEmployee();
        Thread.sleep(3000);
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/after_delete_the_employee_record.png")));
    }



}
