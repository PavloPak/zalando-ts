import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.firefox.FirefoxDriver;

public class SetUp {

    private String isRemote;
    private String remoteUrl;
    private String buildId;

    @BeforeSuite
    protected void getVariables() {
        isRemote = System.getProperty("isRemote");
        String LT_USERNAME = System.getProperty("user");
        String LT_ACCESS_KEY = System.getProperty("token");
        buildId = System.getProperty("buildId");
        remoteUrl = "https://" + LT_USERNAME + ":" + LT_ACCESS_KEY + "@hub.lambdatest.com/wd/hub";
    }

    @BeforeClass
    @Parameters({ "browser", "url", "timeout" })
    protected void configureDriver(String browser, String url, long timeout) throws Exception {
        final WebDriver webDriver;
        // Selenide configs
        Configuration.timeout = timeout;
        Configuration.screenshots = false;
        Configuration.pageLoadTimeout = 40000L;

        if (Boolean.valueOf(isRemote)) {
            System.out.println(" >>>>>> " + isRemote);
            try {
                webDriver = new RemoteWebDriver(new URL(remoteUrl), capabilities(buildId));
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        } else {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
            webDriver.manage().deleteAllCookies();
            webDriver.manage().window().maximize();
        }
        // Selenide add webdriver
        WebDriverRunner.setWebDriver(webDriver);
        Selenide.open(url);
    }

    @AfterClass(alwaysRun = true)
    protected void cleanUp() {
        WebDriverRunner.getWebDriver().quit();
    }

    private static Capabilities capabilities(String buildNumber) {
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("118.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("selenium_version", "4.0.0");
        ltOptions.put("w3c", true);
        ltOptions.put("build", "Jenkins build");
        ltOptions.put("name", "Test run #" + buildNumber);
        browserOptions.setCapability("LT:Options", ltOptions);

        return browserOptions;
    }

}
