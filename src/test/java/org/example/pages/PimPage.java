//package org.example.pages;
//
//import com.microsoft.playwright.*;
//import com.microsoft.playwright.options.WaitForSelectorState;
//import io.cucumber.datatable.DataTable;
//
//public class PimPage {
//    private Page page;
//
//    public PimPage(Page page) {
//        this.page = page;
//    }
//
//    public void clickAddButton() {
//        page.waitForSelector("button.oxd-button--secondary:has-text('Add')", new Page.WaitForSelectorOptions().setTimeout(10000));
//        page.click("button.oxd-button--secondary:has-text('Add')");
//        page.waitForSelector("h6:has-text('Add Employee')", new Page.WaitForSelectorOptions().setTimeout(10000));
//    }
//
//    public void fillEmployeeDetails(DataTable data) {
//        data.asMaps(String.class, String.class).forEach(row -> {
//            String field = row.get("Field");
//            String value = row.get("Value");
//
//            switch (field) {
//                case "First Name":
//                    page.fill("input[name='firstName']", value);
//                    break;
//                case "Last Name":
//                    page.fill("input[name='lastName']", value);
//                    break;
////                case "Employee ID":
////                    page.fill("input[name='employeeId']", value);
////                    break;
//                default:
//                    throw new IllegalArgumentException("Unknown field: " + field);
//            }
//        });
//    }
//
//    public void clickSaveButton() {
//        // Wait for the button to be visible
//        page.waitForSelector("button.oxd-button--secondary:has-text('Save')", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(10000));
//
//        // Click the button
//        page.click("button.oxd-button--secondary:has-text('Save')");
//    }
//
//
//    public void navigateToEmployeeList() {
//        page.waitForSelector("li a:has-text('Employee List')", new Page.WaitForSelectorOptions().setTimeout(10000));
//        page.click("li a:has-text('Employee List')");
//        page.waitForSelector("h6:has-text('Employee List')", new Page.WaitForSelectorOptions().setTimeout(10000));
//    }
//
//    public String getErrorMessageForFirstName() {
//        page.waitForSelector("span:has-text('Required')", new Page.WaitForSelectorOptions().setTimeout(5000));
//        return page.textContent("span:has-text('Required')");
//    }
//}

package org.example.pages;

import com.microsoft.playwright.*;
        import io.cucumber.datatable.DataTable;

public class PimPage {
    private Page page;

    public PimPage(Page page) {
        this.page = page;
    }

    public void clickAddButton() {
        page.waitForSelector("button.oxd-button--secondary:has-text('Add')");
        page.click("button.oxd-button--secondary:has-text('Add')");
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
//                case "Employee ID":
//                    page.fill("input[name='employeeId']", value);
//                    break;
                default:
                    throw new IllegalArgumentException("Unknown field: " + field);
            }
        });
    }

    public void clickSaveButton() {
        page.click("button.oxd-button--secondary:has-text('Save')");
    }

    public void navigateToEmployeeList() {
        page.waitForSelector("li a:has-text('Employee List')");
        page.click("li a:has-text('Employee List')");


    }

    public void deleteFirstEmployee() {
        page.waitForSelector("button.oxd-icon-button:has(i.oxd-icon.bi-trash)");
        page.click("button.oxd-icon-button:has(i.oxd-icon.bi-trash)");

        page.waitForSelector("button.oxd-button--label-danger:has-text('Yes, Delete')");
        page.click("button.oxd-button--label-danger:has-text('Yes, Delete')");
    }
}

