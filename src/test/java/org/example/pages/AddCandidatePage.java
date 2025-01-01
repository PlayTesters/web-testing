package org.example.pages;

import com.microsoft.playwright.*;
import io.cucumber.datatable.DataTable;

import java.nio.file.Paths;

public class AddCandidatePage {
    private final Page page;

    public AddCandidatePage(Page page) {
        this.page = page;
    }

    public void fillCandidateDetails(DataTable data) {
        // Take a screenshot before filling the form
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/before_filling_candidate_form.png")));

        data.asMaps(String.class, String.class).forEach(row -> {
            String field = row.get("Field");
            String value = row.get("Value");

            switch (field) {
                case "First Name":
                    page.fill("input[name='firstName']", value);
                    break;
                case "Middle Name":
                    page.fill("input[name='middleName']", value);
                    break;
                case "Last Name":
                    page.fill("input[name='lastName']", value);
                    break;
                case "Email":
                    page.fill("input[placeholder='Type here']:nth-of-type(1)", value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown field: " + field);
            }
        });

        // Take a screenshot after filling the form
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/after_filling_candidate_form.png")));
    }

    public void selectVacancyByIndex(int index) {
        // Take a screenshot before opening the dropdown
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/before_opening_vacancy_dropdown.png")));

        // Click the dropdown arrow to expand the options
        page.click(".oxd-select-text--arrow");

        // Wait for the dropdown to load and be visible
        Locator dropdown = page.locator("div[role='listbox']");
        dropdown.waitFor(new Locator.WaitForOptions().setTimeout(5000));

        // Select the option by index
        Locator option = dropdown.locator("div").nth(index);

        // Scroll the option into view and click it
        option.scrollIntoViewIfNeeded(new Locator.ScrollIntoViewIfNeededOptions().setTimeout(2000));
        option.click();

        // Take a screenshot after selecting the vacancy
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/after_selecting_vacancy.png")));
    }

    public void clickSaveButton() {
        // Take a screenshot before clicking Save
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/before_click_save_button.png")));

        // Click the Save button
        page.waitForSelector("button[type='submit']:has-text('Save')", new Page.WaitForSelectorOptions().setTimeout(10000));
        page.click("button[type='submit']");

        // Wait for the success toast to appear
        page.waitForSelector(".oxd-toast-container", new Page.WaitForSelectorOptions().setTimeout(12000));

    }
}
