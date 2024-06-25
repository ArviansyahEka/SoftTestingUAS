package read;

import org.example.read.KecamatanPage;
import org.example.read.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KecamatanPageTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private KecamatanPage kecamatanPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
        kecamatanPage = new KecamatanPage(driver);
    }

    @Test
    public void testKecamatanTableContent() {
        loginPage.navigateTo();
        loginPage.login("arviansyah", "admin");

        kecamatanPage.navigateTo();

        int rowCount = kecamatanPage.getRowCount();
        assertEquals(2, rowCount); // Adjust this according to your expected row count

        String kodePropinsi = kecamatanPage.getKodePropinsi(1); // Example row index
        assertEquals("51", kodePropinsi); // Adjust this according to your expected kode propinsi

        String kodeDati2 = kecamatanPage.getKodeDati2(1); // Example row index
        assertEquals("71", kodeDati2); // Adjust this according to your expected kode dati2

        String kodeKecamatan = kecamatanPage.getKodeKecamatan(1); // Example row index
        assertEquals("010", kodeKecamatan); // Adjust this according to your expected kode kecamatan

        String namaKecamatan = kecamatanPage.getNamaKecamatan(1); // Example row index
        assertEquals("KONOHA SELATAN", namaKecamatan); // Adjust this according to your expected nama kecamatan

        // Example interaction (click lihat detail link)
        kecamatanPage.clickLihatDetail(1); // Example row index
        // Add assertions or further interactions in detail page if necessary
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
