package org.example.pages;

import com.microsoft.playwright.Page;

public class AdminPage {
    private final Page page;

    public AdminPage(Page page) {
        this.page = page;
    }

    // Click on the Qualifications tab
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
