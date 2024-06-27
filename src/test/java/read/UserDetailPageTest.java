package read;

import org.example.read.LoginPage;
import org.example.read.UserDetailPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDetailPageTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private UserDetailPage userDetailPage;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Initialize ExtentReports and create a test
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extentReport.html");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("ExtentReports - UserDetailPage Test");
        sparkReporter.config().setReportName("UserDetailPage Test Report");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        loginPage = new LoginPage(driver);
        userDetailPage = new UserDetailPage(driver);
    }

    @Test
    public void testUserDetailPage() {
        // Start test in ExtentReports
        test = extent.createTest("testUserDetailPage", "Test to verify User Detail Page content");

        try {
            // Navigate to login page and perform login
            loginPage.navigateTo();
            test.log(Status.INFO, "Navigated to Login Page");

            loginPage.login("arviansyah", "admin");
            test.log(Status.INFO, "Logged in as 'arviansyah'");

            // Assuming userId and rowNumber are known beforehand or retrieved dynamically
            String userId = "6";
            int rowNumber = 1; // Replace with the actual row number

            // Navigate to user detail page
            userDetailPage.navigateTo(userId, rowNumber);
            test.log(Status.INFO, "Navigated to User Detail Page");

            // Example assertions for user detail page
            assertEquals(userId, userDetailPage.getId());
            test.log(Status.PASS, "User ID is correct");

            assertEquals("Arviansyah", userDetailPage.getUsername());
            test.log(Status.PASS, "Username is correct");

            assertEquals("Standingmendoan@gmail.com", userDetailPage.getEmail());
            test.log(Status.PASS, "Email is correct");

            assertEquals("1", userDetailPage.getStatus());
            test.log(Status.PASS, "Status is correct");

            assertEquals("admin", userDetailPage.getJabatan());
            test.log(Status.PASS, "Jabatan is correct");

            assertEquals("admin", userDetailPage.getRole());
            test.log(Status.PASS, "Role is correct");

            assertEquals("Arviansyah Eka", userDetailPage.getFullname());
            test.log(Status.PASS, "Fullname is correct");

            assertEquals("12345678", userDetailPage.getNip());
            test.log(Status.PASS, "NIP is correct");

            assertEquals("0812345678", userDetailPage.getNomorPonsel());
            test.log(Status.PASS, "Nomor Ponsel is correct");

            // Add more assertions for other fields as needed

        } catch (AssertionError e) {
            test.log(Status.FAIL, "Assertion Error occurred: " + e.getMessage());
            captureScreenshot("testUserDetailPage");
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Exception occurred: " + e.getMessage());
            captureScreenshot("testUserDetailPage");
            throw e;
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }

    private void captureScreenshot(String fileName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(screenshot.toPath(), Paths.get("screenshots", fileName + ".png"));
            test.addScreenCaptureFromPath("screenshots/" + fileName + ".png");
        } catch (IOException ex) {
            test.log(Status.FAIL, "Failed to capture screenshot: " + ex.getMessage());
        }
    }
}
