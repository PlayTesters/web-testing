package org.example.pages;

import com.microsoft.playwright.Page;

public class AdminPage {
    private final Page page;

    public AdminPage(Page page) {
        this.page = page;
    }

    public boolean isAdminPageVisible() {
        return page.isVisible("span.oxd-text:has-text('Admin')");
    }

    public void clickJobMenu() {
        page.click("span.oxd-topbar-body-nav-tab-item:has-text('Job')");
        page.waitForSelector("ul.oxd-dropdown-menu", new Page.WaitForSelectorOptions().setTimeout(5000));
    }

    public void clickJobTitlesOption() {
        page.click("ul.oxd-dropdown-menu a.oxd-topbar-body-nav-tab-link:has-text('Job Titles')");
        page.waitForSelector("h6:has-text('Job Titles')", new Page.WaitForSelectorOptions().setTimeout(10000));
    }
}
