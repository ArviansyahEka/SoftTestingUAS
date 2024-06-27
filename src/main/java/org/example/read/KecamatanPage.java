package org.example.read;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class KecamatanPage {
    private WebDriver driver;

    public KecamatanPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo() {
        driver.get("http://127.0.0.1:8000/kecamatan");
    }

    public int getRowCount() {
        return driver.findElements(By.cssSelector("table#example tbody tr")).size();
    }

    public String getKodePropinsi(int rowIndex) {
        return driver.findElement(By.cssSelector("table#example tbody tr:nth-child(" + rowIndex + ") td:nth-child(1)")).getText();
    }

    public String getKodeDati2(int rowIndex) {
        return driver.findElement(By.cssSelector("table#example tbody tr:nth-child(" + rowIndex + ") td:nth-child(2)")).getText();
    }

    public String getKodeKecamatan(int rowIndex) {
        return driver.findElement(By.cssSelector("table#example tbody tr:nth-child(" + rowIndex + ") td:nth-child(3)")).getText();
    }

    public String getNamaKecamatan(int rowIndex) {
        return driver.findElement(By.cssSelector("table#example tbody tr:nth-child(" + rowIndex + ") td:nth-child(4)")).getText();
    }

    public void clickLihatDetail(int rowIndex) {
        driver.findElement(By.cssSelector("table#example tbody tr:nth-child(" + rowIndex + ") td:nth-child(5) a")).click();
    }
}
