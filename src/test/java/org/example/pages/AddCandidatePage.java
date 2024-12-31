package org.example.pages;

import com.microsoft.playwright.*;
import io.cucumber.datatable.DataTable;

public class AddCandidatePage {
    private final Page page;

    public AddCandidatePage(Page page) {
        this.page = page;
    }

    public void fillCandidateDetails(DataTable data) {
        data.asMaps(String.class, String.class).forEach(row -> {
            String field = row.get("Field");
            String value = row.get("Value");

            switch (field) {
                case "First Name":
                    page.fill("input[name='firstName']", value);
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
    }

    public void clickSaveButton() {
        page.waitForSelector("button[type='submit']:has-text('Save')", new Page.WaitForSelectorOptions().setTimeout(10000));
        page.click("button[type='submit']");
        page.waitForSelector(".oxd-toast-container", new Page.WaitForSelectorOptions().setTimeout(12000));
    }

    public String getConfirmationMessage() {
        return page.innerText(".oxd-toast-container");
    }
}
