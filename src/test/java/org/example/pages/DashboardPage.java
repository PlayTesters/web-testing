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

        public void navigateToAdminModule() {
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
        page.waitForSelector("h6:has-text('Admin')", new Page.WaitForSelectorOptions().setTimeout(60000));
    }
}
