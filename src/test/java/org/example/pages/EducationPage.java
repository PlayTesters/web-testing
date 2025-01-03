package org.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.cucumber.datatable.DataTable;

import java.util.Map;

public class EducationPage {
    private final Page page;

    public EducationPage(Page page) {
        this.page = page;
    }

    public boolean isEducationPageVisible() {
        // Wait for the h6 element containing the text "Education" to be visible
        page.locator("h6:has-text('Education')").waitFor(new Locator.WaitForOptions().setTimeout(10000));
        return page.isVisible("h6:has-text('Education')");
    }


    public void clickAddButton() {
        // Click the "Add" button using the unique selector
        page.click("button.oxd-button--secondary:has-text('Add')");

        // Wait for the "Education" header to appear with a timeout of 5000 ms
        page.waitForSelector("h6:has-text('Education')",
                new Page.WaitForSelectorOptions().setTimeout(5000));
    }


    public boolean isEducationFormVisible() {
        return page.isVisible("h6:has-text('Add Education')") || page.isVisible("h6:has-text('Edit Education')");
    }

    public void fillEducationForm(DataTable data) {
        Map<String, String> formData = data.asMap(String.class, String.class);
        page.fill("input.oxd-input:below(label:has-text('Level'))", formData.get("Level"));
    }

    public void clickSaveButton() {
        // Click the Save button
        page.click("button.oxd-button--secondary:has-text('Save')");

        // Add a delay to account for any processing time
        try {
            Thread.sleep(2000); // Sleep for 2 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
    }

    public boolean isErrorMessageDisplayed() {
        // Updated selector for the "Required" error message
        return page.isVisible("span.oxd-input-field-error-message");
    }


    public void closeForm() {
        // Click the Cancel button to close the form
        page.click("button.oxd-button--secondary:has-text('Cancel')");
    }


    public boolean isSaveSuccessful() {
        return page.isVisible("div.oxd-toast-container:has-text('Successfully Saved')");
    }

    public void deleteFirstEducationRecord() {
        // Wait for the first delete button in the Education table to be visible
        page.waitForSelector("button.oxd-icon-button:has(i.oxd-icon.bi-trash)");

        // Click the first delete button
        page.click("button.oxd-icon-button:has(i.oxd-icon.bi-trash)");

        // Wait for the "Yes, Delete" confirmation button to appear
        page.waitForSelector("button.oxd-button--label-danger:has-text('Yes, Delete')");

        // Click the "Yes, Delete" confirmation button
        page.click("button.oxd-button--label-danger:has-text('Yes, Delete')");
    }


}





