package read;

import org.example.read.LoginPage;
import org.example.read.UserPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserPageTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private UserPage userPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
        userPage = new UserPage(driver);
    }

    @Test
    public void testUserTableContent() {
        loginPage.navigateTo();
        loginPage.login("arviansyah", "admin");

        userPage.navigateTo();

        int rowCount = userPage.getRowCount();
        assertEquals(4, rowCount); // Adjust this according to your expected row count

        String username = userPage.getUsername(4); // Example row index
        assertEquals("Arviansyah", username); // Adjust this according to your expected username

        String fullname = userPage.getFullname(4); // Example row index
        assertEquals("Arviansyah Eka", fullname); // Adjust this according to your expected fullname

        // Example interaction (click lihat detail link)
        userPage.clickLihatDetail(1); // Example row index
        // Add assertions or further interactions in detail page if necessary
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
