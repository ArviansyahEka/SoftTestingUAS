package delete;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.delete.DeleteUserPage;
import org.example.read.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeleteUserTest {
    private WebDriver driver;
    private DeleteUserPage deleteUserPage;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://127.0.0.1:8000/login");

        // Login to the application
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("arviansyah", "admin");

        deleteUserPage = new DeleteUserPage(driver);
    }

    @Test
    public void testDeleteUser() {
        // Navigate to the user management page
        driver.get("http://127.0.0.1:8000/user");

        // Click the delete link for a specific user (assuming user ID is 1 for this example)
        deleteUserPage.clickDeleteLink("9");

        // Confirm the deletion
        deleteUserPage.confirmDeletion();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
