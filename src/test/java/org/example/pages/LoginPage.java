package org.example.pages;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class LoginPage {
    private final Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void navigateToLoginPage() {
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        // Take a screenshot before login
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/before_login.png")));
    }

    public void login(String username, String password) {
        page.fill("input[name='username']", username);
        page.fill("input[name='password']", password);
        page.click("button[type='submit']");
        page.waitForSelector("a[href='/web/index.php/dashboard/index']", new Page.WaitForSelectorOptions().setTimeout(10000));
        // Take a screenshot after login
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/after_login.png")));
    }

    public boolean isLoginSuccessful() {
        return page.url().contains("/dashboard");
    }
}
