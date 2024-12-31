package org.example.pages;

import com.microsoft.playwright.*;

public class RecruitmentPage {
    private final Page page;

    public RecruitmentPage(Page page) {
        this.page = page;
    }

    public void clickAddButton() {
        page.waitForSelector("button.oxd-button--secondary:has-text('Add')", new Page.WaitForSelectorOptions().setTimeout(10000));
        page.click("button.oxd-button--secondary:has-text('Add')");
        page.waitForSelector("h6:has-text('Add Candidate')", new Page.WaitForSelectorOptions().setTimeout(10000));
    }
}
