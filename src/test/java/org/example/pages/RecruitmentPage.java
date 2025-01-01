package org.example.pages;

import com.microsoft.playwright.*;

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

    public boolean isCandidatePresentInTable(String candidateName) {
        // Take a screenshot of the candidate table for validation
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/candidate_table.png")));

        // Check if the candidate is present in the table
        return page.locator("table").textContent().contains(candidateName);
    }
}
