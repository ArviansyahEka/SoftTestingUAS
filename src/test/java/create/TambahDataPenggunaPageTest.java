package create;

import org.example.create.TambahDataPenggunaPage;
import org.example.read.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TambahDataPenggunaPageTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private TambahDataPenggunaPage tambahDataPenggunaPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
        tambahDataPenggunaPage = new TambahDataPenggunaPage(driver);
    }

    @Test
    public void testTambahDataPenggunaForm() {
        loginPage.navigateTo();
        loginPage.login("arviansyah", "admin");

        tambahDataPenggunaPage.navigateTo();

        // Fill the form
        tambahDataPenggunaPage.fillForm("testuser", "Test User", "testuser@example.com", "Active", "Manager", "Admin",
                "1234567890", "08123456789", "password123", "password123");

        // Submit the form
        tambahDataPenggunaPage.submitForm();

    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
