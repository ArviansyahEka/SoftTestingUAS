package read;

import org.example.read.KelurahanPage;
import org.example.read.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KelurahanPageTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private KelurahanPage kelurahanPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
        kelurahanPage = new KelurahanPage(driver);
    }

    @Test
    public void testKelurahanTableContent() {
        loginPage.navigateTo();
        loginPage.login("arviansyah", "admin");

        kelurahanPage.navigateTo();

        int rowCount = kelurahanPage.getRowCount();
        assertEquals(21, rowCount); // Adjust this according to your expected row count

        String kodePropinsi = kelurahanPage.getKodePropinsi(1); // Example row index
        assertEquals("51", kodePropinsi); // Adjust this according to your expected kode propinsi

        String kodeDati2 = kelurahanPage.getKodeDati2(1); // Example row index
        assertEquals("71", kodeDati2); // Adjust this according to your expected kode dati2

        String kodeKecamatan = kelurahanPage.getKodeKecamatan(1); // Example row index
        assertEquals("010", kodeKecamatan); // Adjust this according to your expected kode kecamatan

        String kodeKelurahan = kelurahanPage.getKodeKelurahan(1); // Example row index
        assertEquals("001", kodeKelurahan); // Adjust this according to your expected kode kelurahan

        String kodeSektor = kelurahanPage.getKodeSektor(1); // Example row index
        assertEquals("20", kodeSektor); // Adjust this according to your expected kode sektor

        String namaKelurahan = kelurahanPage.getNamaKelurahan(1); // Example row index
        assertEquals("PEMOGAN", namaKelurahan); // Adjust this according to your expected nama kelurahan

        String nomorKelurahan = kelurahanPage.getNomorKelurahan(1); // Example row index
        assertEquals("3099", nomorKelurahan); // Adjust this according to your expected nomor kelurahan

        String kodePosKelurahan = kelurahanPage.getKodePosKelurahan(1); // Example row index
        assertEquals("", kodePosKelurahan); // Adjust this according to your expected kode pos kelurahan

    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
