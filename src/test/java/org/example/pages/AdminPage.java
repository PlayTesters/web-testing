package org.example.pages;

import com.microsoft.playwright.*;
import io.cucumber.java.en.And;

public class AdminPage {
    private final Page page;

    public AdminPage(Page page) {
        this.page = page;
    }

    public void clickAddButton() {
        page.waitForSelector("button.oxd-button--secondary:has-text('Add')", new Page.WaitForSelectorOptions().setTimeout(10000));
        page.click("button.oxd-button--secondary:has-text('Add')");

    }
    public void clickQualificationTab(){
        page.locator("li.oxd-topbar-body-nav-tab:has-text('Qualifications')").click();
    }
    public void clickLanguageOption() {
        page.locator("a.oxd-topbar-body-nav-tab-link:has-text('Languages')").click();
    }

//    public void deleteFirstUser() {
//
//        // Confirm deletion (assumes confirmation modal appears)
//        page.waitForSelector("button.oxd-button--label-danger");
//        page.click("button.oxd-button--label-danger");
//    }
    private final String thirdDeleteButton = "button.oxd-icon-button.oxd-table-cell-action-space >> nth=2"; // Index 2 for the third button
    private final String confirmDeleteButton = "button.oxd-button--label-danger";

    public void deleteThirdUser() {
        // Wait for the third delete button to be visible
        page.waitForSelector(thirdDeleteButton);

        // Click the third delete button
        page.click(thirdDeleteButton);

        // Wait for the confirmation popup
        page.waitForSelector(confirmDeleteButton);

        // Click "Yes, Delete" in the confirmation popup
        page.click(confirmDeleteButton);
    }
}


