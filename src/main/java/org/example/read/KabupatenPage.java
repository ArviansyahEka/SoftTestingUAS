package org.example.read;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class KabupatenPage {
    private WebDriver driver;
    private By tambahBaruButton = By.xpath("//a[contains(text(), '+ Buat Baru')]");
    private By table = By.id("example");

    public KabupatenPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo() {
        driver.get("http://127.0.0.1:8000/kabupaten");
    }

    public int getRowCount() {
        return driver.findElements(By.xpath("//table[@id='example']//tbody//tr")).size();
    }

    public String getKodePropinsi(int row) {
        String xpath = String.format("//table[@id='example']//tbody//tr[%d]/td[2]", row);
        return driver.findElement(By.xpath(xpath)).getText();
    }

    public String getKodeDati2(int row) {
        String xpath = String.format("//table[@id='example']//tbody//tr[%d]/td[3]", row);
        return driver.findElement(By.xpath(xpath)).getText();
    }

    public String getNamaDati2(int row) {
        String xpath = String.format("//table[@id='example']//tbody//tr[%d]/td[4]", row);
        return driver.findElement(By.xpath(xpath)).getText();
    }

    public void clickLihatDetail(int row) {
        String xpath = String.format("//table[@id='example']//tbody//tr[%d]//a[contains(text(), 'Lihat detail')]", row);
        driver.findElement(By.xpath(xpath)).click();
    }
}
