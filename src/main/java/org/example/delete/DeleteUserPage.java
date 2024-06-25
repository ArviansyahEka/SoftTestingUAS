package org.example.delete;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DeleteUserPage {
    private WebDriver driver;

    // Constructor
    public DeleteUserPage(WebDriver driver) {
        this.driver = driver;
    }

    // Elements
    private By deleteConfirmationButton = By.xpath("//button[@id='deleteConfirmationButton']");
    private By confirmDeleteButton = By.xpath("//button[@id='confirmDeleteButton']");
    private By successMessage = By.xpath("//div[@id='successMessage']");

    // Methods
    public void clickDeleteConfirmationButton(String userId) {
        WebElement deleteLink = driver.findElement(By.xpath("//a[@data-id='" + userId + "']"));
        deleteLink.click();
    }

    public void confirmDelete() {
        WebElement confirmButton = driver.findElement(confirmDeleteButton);
        confirmButton.click();
    }

    public String getSuccessMessage() {
        WebElement successMsgElement = driver.findElement(successMessage);
        return successMsgElement.getText();
    }
}
