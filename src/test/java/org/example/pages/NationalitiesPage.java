package org.example.pages;

import com.microsoft.playwright.Page;
import io.cucumber.datatable.DataTable;
import java.util.Map;

public class NationalitiesPage {
    private final Page page;

    public NationalitiesPage(Page page) {
        this.page = page;
    }

    public boolean isNationalitiesPageVisible() {
        return page.isVisible("h6:has-text('Nationalities')");
    }

    public void navigateToNationalities() {
        page.click("li.oxd-topbar-body-nav-tab a:has-text('Nationalities')");
        page.waitForSelector("h6:has-text('Nationalities')", new Page.WaitForSelectorOptions().setTimeout(5000));
    }

    public void navigateToAddNationality() {
        // Click the "Add" button to navigate to the Add Nationality page
        page.click("button.oxd-button--secondary");
        // Wait for the Add Nationality page to load by checking for an element that confirms the page has loaded
        page.waitForSelector("h6:has-text('Add Nationality')", new Page.WaitForSelectorOptions().setTimeout(5000));
    }

    public void fillNationalityForm(DataTable data) {
        Map<String, String> formData = data.asMap(String.class, String.class);
        page.fill("input.oxd-input:below(label:has-text('Name'))", formData.get("Name"));
    }
}