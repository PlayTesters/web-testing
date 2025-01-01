package org.example.pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;

public class RecruitmentPage {
    private final Page page;

    public RecruitmentPage(Page page) {
        this.page = page;
    }

    public void clickAddButton() {
        // Take a screenshot before clicking the Add button
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/before_click_add_button.png")));

        // Click the Add button
        page.waitForSelector("button.oxd-button--secondary:has-text('Add')", new Page.WaitForSelectorOptions().setTimeout(10000));
        page.click("button.oxd-button--secondary:has-text('Add')");

        // Wait for the Add Candidate page to load
        page.waitForSelector("h6:has-text('Add Candidate')", new Page.WaitForSelectorOptions().setTimeout(10000));

        // Take a screenshot after navigating to Add Candidate
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/after_click_add_button.png")));
    }

    public void clickCandidatesTab() {
        // Take a screenshot before navigating to the Candidates tab
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/before_click_candidates_tab.png")));

        // Click the Candidates tab
        page.click("li.oxd-topbar-body-nav-tab.--visited a.oxd-topbar-body-nav-tab-item");

        // Take a screenshot after navigating to the Candidates tab
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/after_click_candidates_tab.png")));
    }


    public void deleteAnyRecord() {
        // Wait for the table body to load
        Locator tableBody = page.locator("div.oxd-table-body");
        tableBody.waitFor(new Locator.WaitForOptions().setTimeout(10000));

        // Locate the first row's delete button
        Locator deleteButton = tableBody.locator("button.oxd-icon-button:has(i.bi-trash)").first();

        // Check if any delete button exists
        if (deleteButton.count() == 0) {
            throw new RuntimeException("No records found with a delete button!");
        }

        // Click the delete button
        deleteButton.click();

        // Add a delay to allow time for the dialog to appear
        try {
            Thread.sleep(2000); // Delay for 2 seconds (you can adjust the time as needed)
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // Handle interruption
        }

        // Wait for the dialog container to become visible
        Locator dialogContainer = page.locator("div.oxd-sheet.oxd-sheet--rounded.oxd-sheet--white.oxd-dialog-sheet.oxd-dialog-sheet--shadow.oxd-dialog-sheet--gutters.orangehrm-dialog-popup");
        dialogContainer.waitFor(new Locator.WaitForOptions().setTimeout(10000));

        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/before_record_deletion.png")));

        // Locate and click the "Yes, Delete" button
        Locator yesDeleteButton = page.locator("button:has-text('Yes, Delete')");
        yesDeleteButton.waitFor(new Locator.WaitForOptions().setTimeout(10000));  // Wait until it's clickable
        yesDeleteButton.click();

        // Optionally, take a screenshot for verification
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/after_any_record_deletion.png")));
    }

}
