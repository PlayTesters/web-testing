package org.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.cucumber.datatable.DataTable;

import java.util.Map;

public class LanguagePage {
    private final Page page;

    public LanguagePage(Page page) {
        this.page = page;
    }



    public void clickAddButton() {
        page.waitForSelector("button.oxd-button--secondary:has-text('Add')", new Page.WaitForSelectorOptions().setTimeout(10000));
        page.click("button.oxd-button--secondary:has-text('Add')");

    }

    public void fillLanguageForm(DataTable data) {
        // Convert the DataTable into a map
        Map<String, String> formData = data.asMap(String.class, String.class);
        page.locator("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input").fill(formData.get("Name"));


    }


    public void clickSaveButton() {
        // Wait for the Save button to be visible
        page.waitForSelector("button[type='submit']");

        // Click the Save button
        page.click("button[type='submit']");
    }

}
