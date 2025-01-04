package org.example.pages;

import com.microsoft.playwright.*;

public class DashboardPage {
    private final Page page;

    public DashboardPage(Page page) {
        this.page = page;
    }

    public void navigateToRecruitmentModule() {
        page.click("a[href='/web/index.php/recruitment/viewRecruitmentModule']");
        page.waitForSelector("h6:has-text('Recruitment')", new Page.WaitForSelectorOptions().setTimeout(10000));
    }

    public void navigateToPIMModule() {
        page.click("a[href='/web/index.php/pim/viewPimModule']");
        page.waitForSelector("h6:has-text('PIM')", new Page.WaitForSelectorOptions().setTimeout(10000));
    }


    //Navigate to admin
    public void navigateToAdminModule() {
        page.click("a[href='/web/index.php/admin/viewAdminModule']");
        page.waitForSelector("h6:has-text('Admin')", new Page.WaitForSelectorOptions().setTimeout(10000));
    }


}
