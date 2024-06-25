package read;

import org.example.read.LoginPage;
import org.example.read.UserDetailPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDetailPageTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private UserDetailPage userDetailPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
        userDetailPage = new UserDetailPage(driver);
    }

    @Test
    public void testUserDetailPage() {
        // Navigate to login page and perform login
        loginPage.navigateTo();
        loginPage.login("arviansyah", "admin");

        // Assuming userId and rowNumber are known beforehand or retrieved dynamically
        String userId = "6";
        int rowNumber = 4; // Replace with the actual row number

        // Navigate to user detail page
        userDetailPage.navigateTo(userId, rowNumber);

        // Example assertions for user detail page
        assertEquals(userId, userDetailPage.getId());
        assertEquals("Arviansyah", userDetailPage.getUsername());
        assertEquals("Standingmendoan@gmail.com", userDetailPage.getEmail());
        assertEquals("1", userDetailPage.getStatus());
        assertEquals("admin", userDetailPage.getJabatan());
        assertEquals("admin", userDetailPage.getRole());
        assertEquals("Arviansyah Eka", userDetailPage.getFullname());
        assertEquals("12345678", userDetailPage.getNip());
        assertEquals("0812345678", userDetailPage.getNomorPonsel());
        // Add more assertions for other fields as needed
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
