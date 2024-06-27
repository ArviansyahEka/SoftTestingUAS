package read;

import org.example.read.LoginPage;
import org.example.read.ProvinsiPage;
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

public class ProvinsiPageTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProvinsiPage provinsiPage;
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
        sparkReporter.config().setDocumentTitle("ExtentReports - ProvinsiPage Test");
        sparkReporter.config().setReportName("ProvinsiPage Test Report");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        loginPage = new LoginPage(driver);
        provinsiPage = new ProvinsiPage(driver);
    }

    @Test
    public void testProvinsiTableContent() {
        // Start test in ExtentReports
        test = extent.createTest("testProvinsiTableContent", "Test to verify content of Provinsi table");

        try {
            loginPage.navigateTo();
            test.log(Status.INFO, "Navigated to Login Page");

            loginPage.login("arviansyah", "admin");
            test.log(Status.INFO, "Logged in as 'arviansyah'");

            provinsiPage.navigateTo();
            test.log(Status.INFO, "Navigated to Provinsi Page");

            int rowCount = provinsiPage.getRowCount();
            assertEquals(2, rowCount);
            test.log(Status.PASS, "Row count is correct");

            String kodeProvinsi = provinsiPage.getKodeProvinsi(1);
            assertEquals("13", kodeProvinsi);
            test.log(Status.PASS, "Kode Provinsi is correct");

            String namaProvinsi = provinsiPage.getNamaProvinsi(1);
            assertEquals("Jember", namaProvinsi);
            test.log(Status.PASS, "Nama Provinsi is correct");

            provinsiPage.clickLihatDetail(1);
            test.log(Status.INFO, "Clicked on 'Lihat Detail' link");

        } catch (AssertionError e) {
            test.log(Status.FAIL, "Assertion Error occurred: " + e.getMessage());
            captureScreenshot("testProvinsiTableContent");
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Exception occurred: " + e.getMessage());
            captureScreenshot("testProvinsiTableContent");
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
