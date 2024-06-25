package update;

import org.example.read.LoginPage;
import org.example.update.EditDataPenggunaPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditDataPenggunaPageTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private EditDataPenggunaPage editDataPenggunaPage;
    private String baseUrl = "http://127.0.0.1:8000"; // Ganti sesuai URL aplikasi Anda
    private int userId = 10; // Ganti dengan ID pengguna yang ingin diedit

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
        editDataPenggunaPage = new EditDataPenggunaPage(driver);
    }

    @Test
    public void testEditDataPenggunaForm() {
        loginPage.navigateTo();
        loginPage.login("arviansyah", "admin");

        editDataPenggunaPage.navigateTo(baseUrl, userId);

        // Update user data
        editDataPenggunaPage.updateUserData("updatedusername", "Updated User", "updateduser@example.com",
                "Active", "Manager", "Admin", "1234567890", "08123456789");

        // Save changes
        editDataPenggunaPage.saveChanges();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
