package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.checkerframework.checker.units.qual.C;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.actions;

public class LoginPage {
    public LoginPage() {}

    private SelenideElement accountIcon = $x("//button[@data-testid='user-account-icon']");
    private SelenideElement loginButton = $x("//a[@href='/login/']");
    private SelenideElement loginEmail =$x("//input[@id='login.email']");
    private SelenideElement loginPassword = $x("//input[@id='login.secret']");
    private SelenideElement logIn = $x("//button[@data-testid='login_button']");
    private SelenideElement myAccount = $x("//a[@href='/myaccount/']//span");

    public void goToLoginProcess() {
        accountIcon.shouldBe(Condition.visible);
        actions().moveToElement(accountIcon).perform();
        loginButton.shouldBe(Condition.visible);
        actions().moveToElement(loginButton).click().perform();
    }
    public void setLoginUsername(String username) {
        loginEmail.shouldBe(Condition.visible).sendKeys(username);
    }
    public void setLoginPassword(String password) {
        loginPassword.shouldBe(Condition.visible).sendKeys(password);
    }
    public void doLogIn() {
        logIn.shouldBe(Condition.visible).click();
    }
    public String verifyLoginProcess() {
        accountIcon.shouldBe(Condition.visible);
        actions().moveToElement(accountIcon).perform();
        return myAccount.shouldBe(Condition.visible).getText();
    }
}
