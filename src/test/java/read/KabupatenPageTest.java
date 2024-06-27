package read;

import org.example.read.KabupatenPage;
import org.example.read.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KabupatenPageTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private KabupatenPage kabupatenPage;
    private static ExtentReports extent;
    private ExtentTest test;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extentReport.html");
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle("ExtentReports - KabupatenPage Test");
            sparkReporter.config().setReportName("KabupatenPage Test Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }

        test = extent.createTest("testKabupatenTableContent", "Test to verify content of Kabupaten table");

        loginPage = new LoginPage(driver);
        kabupatenPage = new KabupatenPage(driver);
    }

    @Test
    public void testKabupatenTableContent() {
        try {
            loginPage.navigateTo();
            test.log(Status.INFO, "Navigated to Login Page");

            loginPage.login("arviansyah", "admin");
            test.log(Status.INFO, "Logged in as 'arviansyah'");

            kabupatenPage.navigateTo();
            test.log(Status.INFO, "Navigated to Kabupaten Page");

            int rowCount = kabupatenPage.getRowCount();
            assertEquals(2, rowCount); // Adjust this according to your expected row count
            test.log(Status.PASS, "Row count is correct");

            String kodePropinsi = kabupatenPage.getKodePropinsi(1); // Example row index
            assertEquals("13", kodePropinsi); // Adjust this according to your expected kode propinsi
            test.log(Status.PASS, "Kode Propinsi is correct");

            String kodeDati2 = kabupatenPage.getKodeDati2(1); // Example row index
            assertEquals("21", kodeDati2); // Adjust this according to your expected kode dati2
            test.log(Status.PASS, "Kode Dati2 is correct");

            String namaDati2 = kabupatenPage.getNamaDati2(1); // Example row index
            assertEquals("Denpasar", namaDati2); // Adjust this according to your expected nama dati2
            test.log(Status.PASS, "Nama Dati2 is correct");

            kabupatenPage.clickLihatDetail(1); // Example row index
            test.log(Status.INFO, "Clicked on 'Lihat Detail' link");

        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                Files.copy(screenshot.toPath(), Paths.get("screenshot", "testKabupatenTableContent.png"));
                test.addScreenCaptureFromPath("screenshot/testKabupatenTableContent.png");
            } catch (IOException ex) {
                test.log(Status.FAIL, "Failed to capture screenshot: " + ex.getMessage());
            }
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
}
