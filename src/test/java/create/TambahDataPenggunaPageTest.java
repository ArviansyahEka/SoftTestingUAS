package create;

import org.example.create.TambahDataPenggunaPage;
import org.example.read.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

@ExtendWith(TambahDataPenggunaPageTest.TestResultProcessor.class)
public class TambahDataPenggunaPageTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private TambahDataPenggunaPage tambahDataPenggunaPage;
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static class TestResultProcessor implements AfterEachCallback {
        @Override
        public void afterEach(ExtensionContext context) throws Exception {
            WebDriver driver = ((TambahDataPenggunaPageTest) context.getRequiredTestInstance()).driver;
            ExtentTest extentTest = test.get();
            if (context.getExecutionException().isPresent()) {
                Throwable throwable = context.getExecutionException().get();
                extentTest.log(Status.FAIL, throwable);
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                try {
                    Files.copy(screenshot.toPath(), Paths.get("screenshot", context.getDisplayName() + ".png"));
                    extentTest.addScreenCaptureFromPath("screenshot/" + context.getDisplayName() + ".png");
                } catch (IOException e) {
                    extentTest.log(Status.FAIL, "Failed to capture screenshot: " + e.getMessage());
                }
            } else {
                extentTest.log(Status.PASS, "Test completed successfully");
            }
            extent.flush();
        }
    }

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extentReport.html");
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle("ExtentReports - TambahDataPengguna Test");
            sparkReporter.config().setReportName("TambahDataPengguna Test Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }

        ExtentTest extentTest = extent.createTest("testTambahDataPenggunaForm", "Test to add a new user");
        test.set(extentTest);

        loginPage = new LoginPage(driver);
        tambahDataPenggunaPage = new TambahDataPenggunaPage(driver);
    }

    @Test
    public void testTambahDataPenggunaForm() {
        try {
            loginPage.navigateTo();
            test.get().log(Status.INFO, "Navigated to Login Page");

            loginPage.login("arviansyah", "admin");
            test.get().log(Status.INFO, "Logged in as 'arviansyah'");

            tambahDataPenggunaPage.navigateTo();
            test.get().log(Status.INFO, "Navigated to Tambah Data Pengguna Page");

            tambahDataPenggunaPage.fillForm("testuser", "Test User", "testuser@example.com", "Active", "Manager", "Admin",
                    "1234567890", "08123456789", "password123", "password123");
            test.get().log(Status.INFO, "Filled the form with test data");

            tambahDataPenggunaPage.submitForm();
            test.get().log(Status.INFO, "Submitted the form");

        } catch (Exception e) {
            test.get().log(Status.FAIL, "Test failed: " + e.getMessage());
            throw e;
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
