import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class LoginTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Setup WebDriverManager untuk mengelola driver Chrome
        WebDriverManager.chromedriver().setup();

        // Membuat instance WebDriver
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testLogin() {
        // Buka halaman login
        driver.get("http://127.0.0.1:8000/login");
        System.out.println("Halaman login dibuka.");

        // Melakukan login
        login("arviansyah", "admin");

        // Tunggu beberapa saat untuk memastikan halaman baru terbuka
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("http://127.0.0.1:8000/"));

        // Verifikasi URL halaman baru setelah login
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
        assert currentUrl.contains("http://127.0.0.1:8000/") : "Login failed or redirected to incorrect URL";
    }

    private void login(String username, String password) {
        // Masukkan username
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(username);
        System.out.println("Username dimasukkan: " + username);

        // Masukkan password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);
        System.out.println("Password dimasukkan.");

        // Tunggu hingga elemen tombol login muncul dan klik
        WebElement loginButton = driver.findElement(By.tagName("button"));
        loginButton.click();
        System.out.println("Tombol login diklik.");
    }

    @AfterEach
    public void tearDown() {
        // Tutup browser
        if (driver != null) {
            driver.quit();
            System.out.println("Browser ditutup.");
        }
    }
}
