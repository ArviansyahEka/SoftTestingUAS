package read;

import org.example.read.KecamatanPage;
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

public class KecamatanPageTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private KecamatanPage kecamatanPage;
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
            sparkReporter.config().setDocumentTitle("ExtentReports - KecamatanPage Test");
            sparkReporter.config().setReportName("KecamatanPage Test Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }

        test = extent.createTest("testKecamatanTableContent", "Test to verify content of Kecamatan table");

        loginPage = new LoginPage(driver);
        kecamatanPage = new KecamatanPage(driver);
    }

    @Test
    public void testKecamatanTableContent() {
        try {
            loginPage.navigateTo();
            test.log(Status.INFO, "Navigated to Login Page");

            loginPage.login("arviansyah", "admin");
            test.log(Status.INFO, "Logged in as 'arviansyah'");

            kecamatanPage.navigateTo();
            test.log(Status.INFO, "Navigated to Kecamatan Page");

            int rowCount = kecamatanPage.getRowCount();
            assertEquals(3, rowCount); // Adjust this according to your expected row count
            test.log(Status.PASS, "Row count is correct");

            String kodePropinsi = kecamatanPage.getKodePropinsi(1); // Example row index
            assertEquals("1", kodePropinsi); // Adjust this according to your expected kode propinsi
            test.log(Status.PASS, "Kode Propinsi is correct");

            String kodeDati2 = kecamatanPage.getKodeDati2(1); // Example row index
            assertEquals("51", kodeDati2); // Adjust this according to your expected kode dati2
            test.log(Status.PASS, "Kode Dati2 is correct");

            String kodeKecamatan = kecamatanPage.getKodeKecamatan(1); // Example row index
            assertEquals("99", kodeKecamatan); // Adjust this according to your expected kode kecamatan
            test.log(Status.PASS, "Kode Kecamatan is correct");

            String namaKecamatan = kecamatanPage.getNamaKecamatan(1); // Example row index
            assertEquals("KONOHA SELATAN", namaKecamatan); // Adjust this according to your expected nama kecamatan
            test.log(Status.PASS, "Nama Kecamatan is correct");

            kecamatanPage.clickLihatDetail(1); // Example row index
            test.log(Status.INFO, "Clicked on 'Lihat Detail' link");

        } catch (AssertionError e) {
            handleTestFailure(e);
            throw e;
        } catch (Exception e) {
            handleTestFailure(e);
            throw e;
        }
    }

    private void handleTestFailure(Throwable e) {
        test.log(Status.FAIL, "Test failed: " + e.getMessage());
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(screenshot.toPath(), Paths.get("screenshot", "testKecamatanTableContent.png"));
            test.addScreenCaptureFromPath("screenshot/testKecamatanTableContent.png");
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
