package read;

import org.example.read.LoginPage;
import org.example.read.UserPage;
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

public class UserPageTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private UserPage userPage;
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
        sparkReporter.config().setDocumentTitle("ExtentReports - UserPage Test");
        sparkReporter.config().setReportName("UserPage Test Report");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        loginPage = new LoginPage(driver);
        userPage = new UserPage(driver);
    }

    @Test
    public void testUserTableContent() {
        // Start test in ExtentReports
        test = extent.createTest("testUserTableContent", "Test to verify content of User table");

        try {
            loginPage.navigateTo();
            test.log(Status.INFO, "Navigated to Login Page");

            loginPage.login("arviansyah", "admin");
            test.log(Status.INFO, "Logged in as 'arviansyah'");

            userPage.navigateTo();
            test.log(Status.INFO, "Navigated to User Page");

            int rowCount = userPage.getRowCount();
            assertEquals(5, rowCount); // Adjust this according to your expected row count
            test.log(Status.PASS, "Row count is correct");

            String username = userPage.getUsername(4); // Example row index
            assertEquals("Arviansyah", username); // Adjust this according to your expected username
            test.log(Status.PASS, "Username is correct");

            String fullname = userPage.getFullname(4); // Example row index
            assertEquals("Arviansyah Eka", fullname); // Adjust this according to your expected fullname
            test.log(Status.PASS, "Fullname is correct");

            // Example interaction (click lihat detail link)
            userPage.clickLihatDetail(1); // Example row index
            test.log(Status.INFO, "Clicked on 'Lihat Detail' link");

        } catch (AssertionError e) {
            test.log(Status.FAIL, "Assertion Error occurred: " + e.getMessage());
            captureScreenshot("testUserTableContent");
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Exception occurred: " + e.getMessage());
            captureScreenshot("testUserTableContent");
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
