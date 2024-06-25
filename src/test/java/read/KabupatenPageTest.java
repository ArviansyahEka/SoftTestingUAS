package read;

import org.example.read.KabupatenPage;
import org.example.read.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KabupatenPageTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private KabupatenPage kabupatenPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
        kabupatenPage = new KabupatenPage(driver);
    }

    @Test
    public void testKabupatenTableContent() {
        loginPage.navigateTo();
        loginPage.login("arviansyah", "admin");

        kabupatenPage.navigateTo();

        int rowCount = kabupatenPage.getRowCount();
        assertEquals(1, rowCount); // Adjust this according to your expected row count

        String kodePropinsi = kabupatenPage.getKodePropinsi(1); // Example row index
        assertEquals("51", kodePropinsi); // Adjust this according to your expected kode propinsi

        String kodeDati2 = kabupatenPage.getKodeDati2(1); // Example row index
        assertEquals("71", kodeDati2); // Adjust this according to your expected kode dati2

        String namaDati2 = kabupatenPage.getNamaDati2(1); // Example row index
        assertEquals("KONOHA", namaDati2); // Adjust this according to your expected nama dati2

        // Example interaction (click lihat detail link)
        kabupatenPage.clickLihatDetail(1); // Example row index
        // Add assertions or further interactions in detail page if necessary
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
