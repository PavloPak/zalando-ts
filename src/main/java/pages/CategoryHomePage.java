package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CategoryHomePage {
    public CategoryHomePage() {
    }

    private ElementsCollection customerCategory = $$x("//span[@data-testid='genderLink']//span");

    private SelenideElement bannerContent = $x("//button[@id='uc-btn-accept-banner']");

    private ElementsCollection categoryClothing = $$x("//li[@data-testid='top-category']//a//span");

    private ElementsCollection subCategories = $$x("//div[@data-testid='categories-container']//div//a[@color='primary']");

    public int generateRandom(ElementsCollection array) {
        return (int) Math.floor(Math.random()*array.size());
    }
    public void clickOnBanner() {
        if (bannerContent.isDisplayed()) {
            bannerContent.click();
        }
    }
    public void clickOnCustomerCategoryTab() {
        customerCategory.get(generateRandom(customerCategory)).shouldBe(Condition.visible).click();
        bannerContent.shouldBe(Condition.visible);
        clickOnBanner();
    }
    public void searchByCategory() {
        int random = generateRandom(categoryClothing);
        if(random==1 || random==8) {
            categoryClothing.get(random+1).hover();
        } else {
            categoryClothing.get(random).shouldBe(Condition.visible).hover();
        }
    }
    public void searchBySubCategory() {
        subCategories.get(generateRandom(subCategories)).shouldBe(Condition.visible).hover().click();
    }
}
