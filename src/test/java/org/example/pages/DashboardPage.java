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
        // Directly navigate to the Nationalities page under Admin module
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/admin/nationality");
        // Wait for the Nationalities page to load by checking for an element that's visible on that page
        page.waitForSelector("h6:has-text('Nationalities')", new Page.WaitForSelectorOptions().setTimeout(30000)); // 30 seconds timeout
    }

}
