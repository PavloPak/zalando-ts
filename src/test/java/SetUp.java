import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import java.net.URL;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SetUp {

    private final static String HUB_URL = "http://10.245.206.96:80/wd/hub";

    @BeforeClass
    @Parameters({"browser", "remote", "url", "timeout"})
    protected void configureDriver(String browser, String remote, String url, long timeout) throws Exception{
        final WebDriver webDriver;
        //Selenide configs
        Configuration.timeout = timeout;
        Configuration.screenshots = false;
        Configuration.pageLoadTimeout = 40000L;

        if (Boolean.valueOf(remote)) {
            try {
                webDriver = new RemoteWebDriver(new URL(HUB_URL), capabilities());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        else {
            //Webdriver create
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
            webDriver.manage().deleteAllCookies();
            webDriver.manage().window().maximize();
        }
        //Selenide add webdriver
        WebDriverRunner.setWebDriver(webDriver);
        System.out.println(" >>>>>>>>>>>>  ADDED DRIVER  ");
        Selenide.open(url);
    }

    @AfterClass(alwaysRun = true)
    protected void cleanUp() {
        WebDriverRunner.getWebDriver().quit();
    }

    private static DesiredCapabilities capabilities() {
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("firefox");
        return capabilities;
    }
}
