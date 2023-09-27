package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class PDPPage {
    public PDPPage() {}
    private SelenideElement addToCartButton = $x("//span[normalize-space()='Add to bag']");

    private SelenideElement productsCartCounter = $x("//div[@data-testid='shopping-bag-badge']//span");

    private SelenideElement chooseYourSize = $x("//button[@id='picker-trigger']//span/span");

    private ElementsCollection sizes = $$x("//div[@role='listbox']//div[@data-indicator='border']//label[@data-testid='pdp-stockAvailable-label']/span");
     public void clickOnAddToCartButton() {
        chooseYourSize.shouldBe(Condition.visible);
        if(chooseYourSize.exists() && chooseYourSize.getText().equals("Choose your size")) {
            addToCartButton.shouldBe(Condition.visible).click();
            sizes.get(0).shouldBe(Condition.visible);
            List<SelenideElement> list = sizes.stream().filter(size -> size.isEnabled()).collect(Collectors.toList());
            if (list.get(2).exists()) {
                list.get(2).shouldBe(Condition.visible);
                actions().moveToElement(list.get(2)).click(list.get(2)).perform();
            }
        }
        addToCartButton.shouldBe(Condition.visible).click();
    }
    public String getNumberOfProductsInCart() {
        return productsCartCounter.shouldBe(Condition.visible).getText();
    }
}
