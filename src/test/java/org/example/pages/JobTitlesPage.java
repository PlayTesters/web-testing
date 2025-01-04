package org.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.cucumber.datatable.DataTable;

import java.util.Map;

public class JobTitlesPage {
    private final Page page;

    public JobTitlesPage(Page page) {
        this.page = page;
    }

    public boolean isJobTitlesPageVisible() {
        return page.isVisible("h6:has-text('Job Titles')");
    }

    public void clickAddButton() {
        page.click("button.oxd-button--secondary:has-text('Add')");
        page.waitForSelector("h6:has-text('Add Job Title')", new Page.WaitForSelectorOptions().setTimeout(5000));
    }

    public boolean isJobTitleFormVisible() {
        return page.isVisible("h6:has-text('Add Job Title')") || page.isVisible("h6:has-text('Edit Job Title')");
    }

    public void fillJobTitleForm(DataTable data) {
        Map<String, String> formData = data.asMap(String.class, String.class);
        page.fill("input.oxd-input:below(label:has-text('Job Title'))", formData.get("Job Title"));
        page.fill("textarea:below(label:has-text('Job Description'))", formData.get("Job Description"));
        page.fill("textarea:below(label:has-text('Note'))", formData.get("Note"));
    }

    public void clickSaveButton() {
        page.click("button.oxd-button--secondary:has-text('Save')");
        page.waitForSelector("div.oxd-toast-container:has-text('Successfully Saved')",
                new Page.WaitForSelectorOptions().setTimeout(5000));
    }

    public boolean isSaveSuccessful() {
        return page.isVisible("div.oxd-toast-container:has-text('Successfully Saved')");
    }

    public boolean isJobTitlePresent(String jobTitle) {
        return page.isVisible(String.format("div.oxd-table-cell:has-text('%s')", jobTitle));
    }

    public boolean selectJobTitleFromTable(String jobTitle) {
        String jobRowSelector = String.format("div.oxd-table-cell:has-text('%s')", jobTitle);
        if (page.isVisible(jobRowSelector)) {
            page.click(jobRowSelector);
            return true;
        }
        return false;
    }

    public void clickEditIcon() {
        page.click("button:has(i.bi-pencil-fill)");
        page.waitForSelector("h6:has-text('Edit Job Title')", new Page.WaitForSelectorOptions().setTimeout(5000));
    }


    public boolean isAnyRecordAvailable() {
        return page.locator("div.oxd-table-body div.oxd-table-card").count() > 0;
    }

//    public void deleteFirstJobTitle() {
//        if (!isAnyRecordAvailable()) {
//            throw new RuntimeException("No records found in the Job Titles table to delete!");
//        }
//
//        // Locate the first delete button in the table
//        Locator deleteButton = page.locator("button.oxd-icon-button.oxd-table-cell-action-space").first();
//
//        // Click the delete button
//        deleteButton.click();
//
//        // Wait for the confirmation dialog to appear
//        Locator confirmationDialog = page.locator("div.oxd-dialog-sheet.orangehrm-dialog-popup");
//        confirmationDialog.waitFor(new Locator.WaitForOptions().setTimeout(10000));
//
//        // Click the "Yes, Delete" button
//        Locator yesDeleteButton = page.locator("button.oxd-button--medium.oxd-button--label-danger.orangehrm-button-margin");
//        yesDeleteButton.click();
//
//        // Wait for the success notification
//        page.waitForSelector("div.oxd-toast-container:has-text('Successfully Deleted')",
//                new Page.WaitForSelectorOptions().setTimeout(5000));
//    }
//    public boolean isErrorMessageVisible(String errorMessage) {
//        // Check if the error message is visible on the page
//        return page.isVisible(String.format("div.oxd-alert-content:has-text('%s')", errorMessage));
//    }
public void deleteFirstJobTitle() {
    // Wait for the delete button to be visible and clickable
    page.waitForSelector("button.oxd-icon-button:has(i.oxd-icon.bi-trash)");
    page.click("button.oxd-icon-button:has(i.oxd-icon.bi-trash)");

    // Wait for the confirmation button to appear and click "Yes, Delete"
    page.waitForSelector("button.oxd-button--label-danger:has-text('Yes, Delete')");
    page.click("button.oxd-button--label-danger:has-text('Yes, Delete')");
}

}