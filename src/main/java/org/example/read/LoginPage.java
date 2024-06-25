package org.example.read;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    // Locators
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.tagName("button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo() {
        driver.get("http://127.0.0.1:8000/login");
    }

    public void login(String username, String password) {
        WebElement usernameElement = driver.findElement(usernameField);
        usernameElement.sendKeys(username);

        WebElement passwordElement = driver.findElement(passwordField);
        passwordElement.sendKeys(password);

        WebElement loginButtonElement = driver.findElement(loginButton);
        loginButtonElement.click();
    }
}
