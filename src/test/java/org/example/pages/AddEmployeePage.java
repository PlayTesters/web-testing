package org.example.pages;

import com.microsoft.playwright.*;
        import io.cucumber.datatable.DataTable;

public class AddEmployeePage {
    private final Page page;

    public AddEmployeePage(Page page) {
        this.page = page;
    }

    public void fillEmployeeDetails(DataTable data) {
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
                case "Employee ID":
                    page.fill("input[name='employeeId']", value);
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
