package org.example.create;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TambahDataPenggunaPage {
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
    private By passwordField = By.id("password");
    private By confirmPasswordField = By.id("password_confirmation");
    private By submitButton = By.xpath("//button[@type='submit']");

    public TambahDataPenggunaPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo() {
        driver.get("http://127.0.0.1:8000/user/create");
    }

    public void fillForm(String username, String fullname, String email, String status, String jabatan,
                         String role, String nip, String nomorPonsel, String password, String confirmPassword) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(fullnameField).sendKeys(fullname);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(statusField).sendKeys(status);
        driver.findElement(jabatanField).sendKeys(jabatan);
        driver.findElement(roleField).sendKeys(role);
        driver.findElement(nipField).sendKeys(nip);
        driver.findElement(nomorPonselField).sendKeys(nomorPonsel);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
    }

    public void submitForm() {
        driver.findElement(submitButton).click();
    }

    public boolean isFormPage() {
        return driver.findElement(usernameField).isDisplayed()
                && driver.findElement(submitButton).isDisplayed();
    }
}
