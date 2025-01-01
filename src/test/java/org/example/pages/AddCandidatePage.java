//package org.example.pages;
//
//import com.microsoft.playwright.*;
//import io.cucumber.datatable.DataTable;
//
//import java.nio.file.Paths;
//
//public class AddCandidatePage {
//    private final Page page;
//
//    public AddCandidatePage(Page page) {
//        this.page = page;
//    }
//
//    public void fillCandidateDetails(DataTable data) {
//        // Take a screenshot before filling the form
//        page.screenshot(new Page.ScreenshotOptions()
//                .setPath(Paths.get("screenshots/before_filling_candidate_form.png")));
//
//        data.asMaps(String.class, String.class).forEach(row -> {
//            String field = row.get("Field");
//            String value = row.get("Value");
//
//            switch (field) {
//                case "First Name":
//                    page.fill("input[name='firstName']", value);
//                    break;
//                case "Middle Name":
//                    page.fill("input[name='middleName']", value);
//                    break;
//                case "Last Name":
//                    page.fill("input[name='lastName']", value);
//                    break;
//                case "Email":
//                    page.fill("input[placeholder='Type here']:nth-of-type(1)", value);
//                    break;
//                default:
//                    throw new IllegalArgumentException("Unknown field: " + field);
//            }
//        });
//
//    }
//
//    public void selectVacancyByIndex(int index) {
//        // Click the dropdown arrow to expand the options
//        page.click(".oxd-select-text--arrow");
//
//        // Wait for the dropdown to load and be visible
//        Locator dropdown = page.locator("div[role='listbox']");
//        dropdown.waitFor(new Locator.WaitForOptions().setTimeout(5000));
//
//        // Select the option by index
//        Locator option = dropdown.locator("div").nth(index);
//
//        // Scroll the option into view and click it
//        option.scrollIntoViewIfNeeded(new Locator.ScrollIntoViewIfNeededOptions().setTimeout(2000));
//        option.click();
//
//        // Take a screenshot after selecting the vacancy
//        page.screenshot(new Page.ScreenshotOptions()
//                .setPath(Paths.get("screenshots/after_selecting_vacancy.png")));
//    }
//
//    public void clickSaveButton() {
//        // Click the Save button
//        page.waitForSelector("button[type='submit']:has-text('Save')", new Page.WaitForSelectorOptions().setTimeout(10000));
//        page.click("button[type='submit']");
//
//        // Wait for the success toast to appear
//        page.waitForSelector(".oxd-toast-container", new Page.WaitForSelectorOptions().setTimeout(12000));
//
//    }
//}




package org.example.pages;

import com.microsoft.playwright.*;
import io.cucumber.datatable.DataTable;

import java.nio.file.Paths;

public class AddCandidatePage {
    private final Page page;

    public AddCandidatePage(Page page) {
        this.page = page;
    }

    public void fillCandidateDetails(DataTable data) {
        // Take a screenshot before filling the form
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/before_filling_candidate_form.png")));

        data.asMaps(String.class, String.class).forEach(row -> {
            String field = row.get("Field");
            String value = row.get("Value");

            switch (field) {
                case "First Name":
                    page.fill("input[name='firstName']", value);
                    break;
                case "Middle Name":
                    page.fill("input[name='middleName']", value);
                    break;
                case "Last Name":
                    page.fill("input[name='lastName']", value);
                    break;
                case "Email":
                    page.fill("input[placeholder='Type here']:nth-of-type(1)", value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown field: " + field);
            }
        });
    }

    public void selectVacancyByIndex(int index) {
        // Click the dropdown arrow to expand the options
        page.click(".oxd-select-text--arrow");

        // Wait for the dropdown to load and be visible
        Locator dropdown = page.locator("div[role='listbox']");
        dropdown.waitFor(new Locator.WaitForOptions().setTimeout(5000));

        // Select the option by index
        Locator option = dropdown.locator("div").nth(index);

        // Scroll the option into view and click it
        option.scrollIntoViewIfNeeded(new Locator.ScrollIntoViewIfNeededOptions().setTimeout(2000));
        option.click();

        // Take a screenshot after selecting the vacancy
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/after_selecting_vacancy.png")));
    }

//    public void clickSaveButton() {
//        // Click the Save button
//        page.waitForSelector("button[type='submit']:has-text('Save')", new Page.WaitForSelectorOptions().setTimeout(10000));
//        page.click("button[type='submit']");
//
//        // Wait for the required message to appear under the email field
//        page.waitForSelector("span.oxd-input-field-error", new Page.WaitForSelectorOptions().setTimeout(5000));
//
//        // You can take a screenshot to capture the state after submitting the form
//        page.screenshot(new Page.ScreenshotOptions()
//                .setPath(Paths.get("screenshots/after_submit_without_email.png")));
//    }

    public void clickSaveButton() {
        // Wait for the Save button to appear and click
        page.waitForSelector("button[type='submit']:has-text('Save')", new Page.WaitForSelectorOptions().setTimeout(10000));
        page.click("button[type='submit']");

        // Wait for navigation to the next page (if applicable)
        page.waitForURL("**/recruitment/addCandidate/**", new Page.WaitForURLOptions().setTimeout(10000));

        // Take a screenshot for debugging
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/after_save_candidate.png")));
    }



//
//    public boolean isEmailExpectedFormatMessageVisible() {
//        // Locate the error message using the specific text
//        Locator errorMessage = page.locator("span.oxd-input-field-error-message");
//
//        // Verify the error message visibility and content
//        return errorMessage.isVisible() &&
//                errorMessage.textContent().trim().equals("Expected format: admin@example.com");
//    }
//
//
//
//    public void fillCandidateEmail(String email) {
//        page.fill("input[placeholder='Type here']:nth-of-type(1)", email);
//    }

    public void clickShortlistButton() {
        // Wait for the Shortlist button to be visible and click
        page.waitForSelector("button.oxd-button--success", new Page.WaitForSelectorOptions().setTimeout(5000));
        page.click("button.oxd-button--success");

        // Wait for navigation to the Shortlist page
        page.waitForURL("**/changeCandidateVacancyStatus**", new Page.WaitForURLOptions().setTimeout(10000));

        // Take a screenshot for debugging
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/after_shortlist_click.png")));
    }



    public void fillNotesAndSave(String notes) {
        // Wait for the notes textarea field to be visible
        page.waitForSelector("textarea[placeholder='Type here']", new Page.WaitForSelectorOptions().setTimeout(5000));

        // Fill in the notes
        page.fill("textarea[placeholder='Type here']", notes);

        // Take a screenshot for debugging
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/before_saving_notes.png")));

        // Click the Save button
        page.waitForSelector("button.oxd-button--secondary.orangehrm-left-space", new Page.WaitForSelectorOptions().setTimeout(5000));
        page.click("button.oxd-button--secondary.orangehrm-left-space");

        // Wait for confirmation or next page load
        page.waitForTimeout(3000); // Adjust if a specific confirmation is needed
    }


    // New method to validate the email error message when an invalid email is entered
    public String getEmailErrorMessage() {
        // Wait for the error message to appear under the email field
        page.waitForSelector("span.oxd-input-field-error-message", new Page.WaitForSelectorOptions().setTimeout(5000));

        // Get the error message text
        return page.locator("span.oxd-input-field-error-message").textContent();
    }

}
