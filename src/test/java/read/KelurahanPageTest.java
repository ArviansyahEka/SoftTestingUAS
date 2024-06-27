package read;

import org.example.read.KelurahanPage;
import org.example.read.LoginPage;
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

public class KelurahanPageTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private KelurahanPage kelurahanPage;
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
            sparkReporter.config().setDocumentTitle("ExtentReports - KelurahanPage Test");
            sparkReporter.config().setReportName("KelurahanPage Test Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }

        test = extent.createTest("testKelurahanTableContent", "Test to verify content of Kelurahan table");

        loginPage = new LoginPage(driver);
        kelurahanPage = new KelurahanPage(driver);
    }

    @Test
    public void testKelurahanTableContent() {
        try {
            loginPage.navigateTo();
            test.log(Status.INFO, "Navigated to Login Page");

            loginPage.login("arviansyah", "admin");
            test.log(Status.INFO, "Logged in as 'arviansyah'");

            kelurahanPage.navigateTo();
            test.log(Status.INFO, "Navigated to Kelurahan Page");

            int rowCount = kelurahanPage.getRowCount();
            assertEquals(22, rowCount); // Adjust this according to your expected row count
            test.log(Status.PASS, "Row count is correct");

            String kodePropinsi = kelurahanPage.getKodePropinsi(1); // Example row index
            assertEquals("51", kodePropinsi); // Adjust this according to your expected kode propinsi
            test.log(Status.PASS, "Kode Propinsi is correct");

            String kodeDati2 = kelurahanPage.getKodeDati2(1); // Example row index
            assertEquals("71", kodeDati2); // Adjust this according to your expected kode dati2
            test.log(Status.PASS, "Kode Dati2 is correct");

            String kodeKecamatan = kelurahanPage.getKodeKecamatan(1); // Example row index
            assertEquals("010", kodeKecamatan); // Adjust this according to your expected kode kecamatan
            test.log(Status.PASS, "Kode Kecamatan is correct");

            String kodeKelurahan = kelurahanPage.getKodeKelurahan(1); // Example row index
            assertEquals("001", kodeKelurahan); // Adjust this according to your expected kode kelurahan
            test.log(Status.PASS, "Kode Kelurahan is correct");

            String kodeSektor = kelurahanPage.getKodeSektor(1); // Example row index
            assertEquals("20", kodeSektor); // Adjust this according to your expected kode sektor
            test.log(Status.PASS, "Kode Sektor is correct");

            String namaKelurahan = kelurahanPage.getNamaKelurahan(1); // Example row index
            assertEquals("PEMOGAN", namaKelurahan); // Adjust this according to your expected nama kelurahan
            test.log(Status.PASS, "Nama Kelurahan is correct");

            String nomorKelurahan = kelurahanPage.getNomorKelurahan(1); // Example row index
            assertEquals("3099", nomorKelurahan); // Adjust this according to your expected nomor kelurahan
            test.log(Status.PASS, "Nomor Kelurahan is correct");

            String kodePosKelurahan = kelurahanPage.getKodePosKelurahan(1); // Example row index
            assertEquals("", kodePosKelurahan); // Adjust this according to your expected kode pos kelurahan
            test.log(Status.PASS, "Kode Pos Kelurahan is correct");

        } catch (AssertionError e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            handleAssertionError(e);
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            handleException(e);
            throw e;
        }
    }

    private void handleAssertionError(AssertionError e) {
        test.log(Status.FAIL, "Assertion Error occurred: " + e.getMessage());
        // Additional error handling code can be added here, e.g., taking a screenshot
        captureScreenshot("testKelurahanTableContent");
    }

    private void handleException(Exception e) {
        test.log(Status.FAIL, "Exception occurred: " + e.getMessage());
        // Additional exception handling code can be added here, e.g., taking a screenshot
        captureScreenshot("testKelurahanTableContent");
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

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }
}
