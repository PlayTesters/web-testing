package org.example.pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import io.cucumber.datatable.DataTable;

public class AddNewUserPage {
    private final Page page;

    public AddNewUserPage(Page page) {
        this.page = page;
    }

    public void fillNewUserDetails(DataTable data) {
        data.asMaps(String.class, String.class).forEach(row -> {
            String field = row.get("Field");
            String value = row.get("Value");

            switch (field) {

                case "Password":
                    // Update locator if necessary
                    page.locator("//label[text()='Password']/following::input[@type='password' and @autocomplete='off'][1]").fill(value);
                    break;


                case "Confirm Password":
                    // Update locator if necessary
                    page.locator("//label[text()='Confirm Password']/following::input[@type='password' and @autocomplete='off'][1]").fill(value);
                    break;

                default:
                    throw new IllegalArgumentException("Unknown field: " + field);
            }
        });
    }

    public void clickSaveButton() {
        // Wait for the Save button to be visible
        page.waitForSelector("button[type='submit']");

        // Click the Save button
        page.click("button[type='submit']");
    }

}