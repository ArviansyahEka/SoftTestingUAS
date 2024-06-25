package org.example.read;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserPage {
    private WebDriver driver;

    // Locators
    private By tableRows = By.xpath("//table[@id='example']/tbody/tr");
    private By usernameColumn = By.xpath("//table[@id='example']/tbody/tr/td[2]");
    private By fullnameColumn = By.xpath("//table[@id='example']/tbody/tr/td[3]");
    private By lihatDetailLinks = By.xpath("//table[@id='example']/tbody/tr/td[4]/ul/li[1]/a");
    private By editLinks = By.xpath("//table[@id='example']/tbody/tr/td[4]/ul/li[2]/a");
    private By deleteLinks = By.xpath("//table[@id='example']/tbody/tr/td[4]/ul/li[3]/a");

    public UserPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo() {
        driver.get("http://127.0.0.1:8000/user");
    }

    public int getRowCount() {
        return driver.findElements(tableRows).size();
    }

    public String getUsername(int index) {
        WebElement usernameElement = driver.findElements(usernameColumn).get(index - 1);
        return usernameElement.getText();
    }

    public String getFullname(int index) {
        WebElement fullnameElement = driver.findElements(fullnameColumn).get(index - 1);
        return fullnameElement.getText();
    }

    public void clickLihatDetail(int index) {
        WebElement lihatDetailLink = driver.findElements(lihatDetailLinks).get(index - 1);
        lihatDetailLink.click();
    }

    public void clickEdit(int index) {
        WebElement editLink = driver.findElements(editLinks).get(index - 1);
        editLink.click();
    }

    public void clickDelete(int index) {
        WebElement deleteLink = driver.findElements(deleteLinks).get(index - 1);
        deleteLink.click();
    }
}
