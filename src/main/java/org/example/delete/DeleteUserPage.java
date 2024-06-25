package org.example.delete;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DeleteUserPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By deleteLink = By.cssSelector(".delete-link");
    private By confirmDeleteButton = By.cssSelector(".swal2-confirm");
    private By cancelDeleteButton = By.cssSelector(".swal2-cancel");
    private By successMessage = By.xpath("//div[@class='swal2-popup swal2-modal swal2-show']//h2[@class='swal2-title']");

    // Constructor
    public DeleteUserPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Method to click the delete link for a specific user
    public void clickDeleteLink(String userId) {
        WebElement deleteElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-id='" + userId + "']")));
        deleteElement.click();
    }

    // Method to confirm the deletion in the confirmation dialog
    public void confirmDeletion() {
        WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton));
        confirmButton.click();
    }

    // Method to cancel the deletion in the confirmation dialog
    public void cancelDeletion() {
        WebElement cancelButton = wait.until(ExpectedConditions.elementToBeClickable(cancelDeleteButton));
        cancelButton.click();
    }

    // Method to check if the success message is displayed
    public boolean isSuccessMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
    }

    // Method to get the success message text
    public String getSuccessMessageText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
    }
}
