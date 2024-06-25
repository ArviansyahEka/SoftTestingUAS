package org.example.read;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserDetailPage {
    private WebDriver driver;

    // Locators
    private By idField = By.xpath("//table//th[contains(text(), 'ID')]/following-sibling::td");
    private By usernameField = By.xpath("//table//th[contains(text(), 'Username')]/following-sibling::td");
    private By emailField = By.xpath("//table//th[contains(text(), 'Email')]/following-sibling::td");
    private By statusField = By.xpath("//table//th[contains(text(), 'Status')]/following-sibling::td");
    private By jabatanField = By.xpath("//table//th[contains(text(), 'Jabatan')]/following-sibling::td");
    private By roleField = By.xpath("//table//th[contains(text(), 'Role')]/following-sibling::td");
    private By fullnameField = By.xpath("//table//th[contains(text(), 'Nama Lengkap')]/following-sibling::td");
    private By nipField = By.xpath("//table//th[contains(text(), 'NIP')]/following-sibling::td");
    private By nomorPonselField = By.xpath("//table//th[contains(text(), 'Nomor Ponsel')]/following-sibling::td");
    private By createdAtField = By.xpath("//table//th[contains(text(), 'Created At')]/following-sibling::td");
    private By updatedAtField = By.xpath("//table//th[contains(text(), 'Updated At')]/following-sibling::td");
    private By updateButton = By.xpath("//button[contains(text(), 'Update')]");
    private By deleteButton = By.xpath("//form/button[contains(@class, 'bg-danger')]");

    public UserDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo(String userId, int rowNumber) {
        String url = String.format("http://127.0.0.1:8000/user/%s/%d", userId, rowNumber);
        driver.get(url);
    }


    public String getId() {
        return driver.findElement(idField).getText();
    }

    public String getUsername() {
        return driver.findElement(usernameField).getText();
    }

    public String getEmail() {
        return driver.findElement(emailField).getText();
    }

    public String getStatus() {
        return driver.findElement(statusField).getText();
    }

    public String getJabatan() {
        return driver.findElement(jabatanField).getText();
    }

    public String getRole() {
        return driver.findElement(roleField).getText();
    }

    public String getFullname() {
        return driver.findElement(fullnameField).getText();
    }

    public String getNip() {
        return driver.findElement(nipField).getText();
    }

    public String getNomorPonsel() {
        return driver.findElement(nomorPonselField).getText();
    }

    public String getCreatedAt() {
        return driver.findElement(createdAtField).getText();
    }

    public String getUpdatedAt() {
        return driver.findElement(updatedAtField).getText();
    }

    public void clickUpdateButton() {
        driver.findElement(updateButton).click();
    }

    public void clickDeleteButton() {
        driver.findElement(deleteButton).click();
    }
}
