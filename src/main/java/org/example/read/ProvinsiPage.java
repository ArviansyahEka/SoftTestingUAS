package org.example.read;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProvinsiPage {
    private WebDriver driver;

    // Locators
    private By tableRows = By.xpath("//table[@id='example']/tbody/tr");
    private By kodeProvinsiColumn = By.xpath("//table[@id='example']/tbody/tr/td[2]");
    private By namaProvinsiColumn = By.xpath("//table[@id='example']/tbody/tr/td[3]");
    private By lihatDetailLinks = By.xpath("//table[@id='example']/tbody/tr/td[4]/a");

    public ProvinsiPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo() {
        driver.get("http://127.0.0.1:8000/provinsi");
    }

    public int getRowCount() {
        return driver.findElements(tableRows).size();
    }

    public String getKodeProvinsi(int index) {
        WebElement kodeProvinsiElement = driver.findElements(kodeProvinsiColumn).get(index - 1);
        return kodeProvinsiElement.getText();
    }

    public String getNamaProvinsi(int index) {
        WebElement namaProvinsiElement = driver.findElements(namaProvinsiColumn).get(index - 1);
        return namaProvinsiElement.getText();
    }

    public void clickLihatDetail(int index) {
        WebElement lihatDetailLink = driver.findElements(lihatDetailLinks).get(index - 1);
        lihatDetailLink.click();
    }
}
