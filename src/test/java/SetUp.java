import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class SetUp {

    @BeforeClass
    @Parameters({"browser","url", "timeout"})
    protected void configureDriver(String browser, String url, long timeout) {
        //Selenide configs
        Configuration.timeout = timeout;
        Configuration.screenshots = false;
        //Webdriver create
        WebDriverManager.chromedriver().setup();
        final WebDriver webDriver = new ChromeDriver();
        webDriver.manage().deleteAllCookies();
        webDriver.manage().window().maximize();
        //Selenide add webdriver
        WebDriverRunner.setWebDriver(webDriver);
        Selenide.open(url);
    }

    @AfterClass(alwaysRun = true)
    protected void cleanUp() {
        WebDriverRunner.getWebDriver().quit();
    }
}
