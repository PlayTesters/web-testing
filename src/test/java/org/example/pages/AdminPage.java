package org.example.pages;

import com.microsoft.playwright.Page;

public class AdminPage {
    private final Page page;

    public AdminPage(Page page) {
        this.page = page;
    }




    public void clickJobMenu() {
        page.click("span.oxd-topbar-body-nav-tab-item:has-text('Job')");
        page.waitForSelector("ul.oxd-dropdown-menu", new Page.WaitForSelectorOptions().setTimeout(5000));
    }

    public void clickJobTitlesOption() {
        page.click("ul.oxd-dropdown-menu a.oxd-topbar-body-nav-tab-link:has-text('Job Titles')");
        page.waitForSelector("h6:has-text('Job Titles')", new Page.WaitForSelectorOptions().setTimeout(10000));
    }


    // Click on the Qualifications tab...
    public void clickQualificationsTab() {
        page.locator("li.oxd-topbar-body-nav-tab:has-text('Qualifications')").click();
    }
    // In AdminPage.java

    public boolean isAdminPageVisible() {
        // Verify if a specific element that signifies the Admin page is visible
        return page.isVisible("h6:has-text('Admin')");  // Modify the selector to suit your page
    }


    // Click on the Education option under Qualifications
    public void clickEducationOption() {
        page.locator("a.oxd-topbar-body-nav-tab-link:has-text('Education')").click();
    }

    // Check if the Education page is visible
    public boolean isEducationPageVisible() {
        return page.isVisible("h6:has-text('Education')");
    }



}
