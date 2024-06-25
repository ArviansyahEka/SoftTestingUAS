package org.example.update;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditDataPenggunaPage {
    private WebDriver driver;

    // Locators
    private By usernameField = By.id("username");
    private By fullnameField = By.id("fullname");
    private By emailField = By.id("email");
    private By statusField = By.id("status");
    private By jabatanField = By.id("jabatan");
    private By roleField = By.id("role");
    private By nipField = By.id("nip");
    private By nomorPonselField = By.id("nomorponsel");
    private By saveButton = By.xpath("//button[@type='submit']");

    public EditDataPenggunaPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo(String baseUrl, int userId) {
        driver.get(baseUrl + "/user/" + userId + "/edit");
    }

    public void updateUserData(String username, String fullname, String email, String status, String jabatan,
                               String role, String nip, String nomorPonsel) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(fullnameField).clear();
        driver.findElement(fullnameField).sendKeys(fullname);
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(statusField).clear();
        driver.findElement(statusField).sendKeys(status);
        driver.findElement(jabatanField).clear();
        driver.findElement(jabatanField).sendKeys(jabatan);
        driver.findElement(roleField).clear();
        driver.findElement(roleField).sendKeys(role);
        driver.findElement(nipField).clear();
        driver.findElement(nipField).sendKeys(nip);
        driver.findElement(nomorPonselField).clear();
        driver.findElement(nomorPonselField).sendKeys(nomorPonsel);
    }

    public void saveChanges() {
        driver.findElement(saveButton).click();
    }

    public boolean isEditPageDisplayed() {
        return driver.findElement(usernameField).isDisplayed()
                && driver.findElement(saveButton).isDisplayed();
    }
}
