import Listeners.Retry;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;
import static org.testng.Assert.assertEquals;

public class LoginTest extends SetUp {
    @Test
    @Parameters({"username", "password"})
    public void loginTest(String username, String password) {
        LoginPage loginPage = new LoginPage();
        String expected_result = "Your account";

        loginPage.goToLoginProcess();
        loginPage.setLoginUsername(username);
        loginPage.setLoginPassword(password);
        loginPage.doLogIn();
        String actual_result = loginPage.verifyLoginProcess();
        assertEquals(actual_result, expected_result);
    }
}
