package read;

import org.example.read.LoginPage;
import org.example.read.ProvinsiPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProvinsiPageTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProvinsiPage provinsiPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
        provinsiPage = new ProvinsiPage(driver);
    }

    @Test
    public void testProvinsiTableContent() {
        loginPage.navigateTo();
        loginPage.login("arviansyah", "admin");

        provinsiPage.navigateTo();

        int rowCount = provinsiPage.getRowCount();
        assertEquals(1, rowCount); // Adjust this according to your expected row count

        String kodeProvinsi = provinsiPage.getKodeProvinsi(1); // Example row index
        assertEquals("51", kodeProvinsi); // Adjust this according to your expected kode provinsi

        String namaProvinsi = provinsiPage.getNamaProvinsi(1); // Example row index
        assertEquals("WAKANDA", namaProvinsi); // Adjust this according to your expected nama provinsi

        // Example interaction (click lihat detail link)
        provinsiPage.clickLihatDetail(1); // Example row index
        // Add assertions or further interactions in detail page if necessary
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
