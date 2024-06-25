package delete;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.delete.DeleteUserPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class DeleteUserTest {
    private WebDriver driver;
    private String baseUrl;
    private DeleteUserPage deleteUserPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseUrl = "http://127.0.0.1:8000"; // Sesuaikan dengan URL aplikasi Anda
        driver.get(baseUrl + "/login");
        // Lakukan login
        login();

        // Initialize DeleteUserPage
        deleteUserPage = new DeleteUserPage(driver);
    }

    private void login() {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

        usernameField.sendKeys("arviansyah");
        passwordField.sendKeys("admin");
        loginButton.click();
    }

    @Test
    public void testDeleteLastUser() {
        // Lakukan navigasi ke halaman pengguna
        navigateToUsersPage();

        // Memanggil fungsi delete user terakhir
        deleteUserPage.clickDeleteConfirmationButton("lastUserId");
        deleteUserPage.confirmDelete();

        // Verifikasi pesan sukses
        String successMessage = deleteUserPage.getSuccessMessage();
        Assert.assertEquals("User successfully deleted.", successMessage);
    }

    private void navigateToUsersPage() {
        driver.get(baseUrl + "/users");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
