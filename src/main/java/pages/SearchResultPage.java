package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.*;

import java.util.*;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class SearchResultPage {
    //constructor
    public SearchResultPage () {}
    //elements
    private ElementsCollection searchResultElements = $$x("//article[@role='link']//h3[last()]");

    private ElementsCollection productPricesList = $$x("//article[@role='link']//header/div/following-sibling::section//span");

    private ElementsCollection productsTitles = $$x("//div[@data-zalon-partner-target='true']//article[@role='link']//a//header");

    private SelenideElement paginationArrow = $x("//a[@title='next page']");

    private SelenideElement bannerContent = $x("//button[@id='uc-btn-accept-banner']");

    private ElementsCollection productsList = $$x("//article[@role='link']");

    //methods
    public String getSearchResultsFirstElementText() {

        String text = "";
        try {
            text = searchResultElements.first().shouldBe(Condition.visible).getText();
        }
        catch (Exception e) {
            System.out.println(e.getClass() + " -- " + e.getMessage());
        }

        return text;
    }
    public String findProductWithHighestPrice() {

        if(productPricesList.size()!=0) {

           return "£" + productPricesList.stream().map(entry -> Double.parseDouble(entry.getText().
                   replace("£", ""))).sorted(Collections.reverseOrder()).toList().get(0);
        } else
        {
            return ("Array productPricesList is empty");
        }
    }

    public String getTitleAndPriceOfMostExpensiveProduct() {
        TreeMap<Double, String> hm = new TreeMap<>();

        if(productsTitles.size()!=0) {
            for (WebElement product:productsTitles) {
                String productTitle = product.findElement(By.tagName("h3")).getText();
                String productPrice = product.findElement(By.tagName("span")).getText();
                Double productPriceDouble = Double.parseDouble(productPrice.replace("£",""));
                hm.put(productPriceDouble,productTitle);
            }

            double lastEntry = hm.lastKey();

            return(hm.get(lastEntry) + " " + " - " + "£" + lastEntry);
        } else
        {
            return ("Array productsTitles is empty");
        }
    }

    public void clickOnBanner() {
        if (bannerContent.isDisplayed()) {
            bannerContent.click();
        }
    }
    public void scrollDownToThePaginationAndClickNextPageIfPaginationExist() {
        if(paginationArrow.exists()) {
            Selenide.executeJavaScript("arguments[0].scrollIntoView();", paginationArrow);
            clickOnBanner();
            if(paginationArrow.is(Condition.enabled)) {
                paginationArrow.shouldBe(Condition.visible).click();
                searchResultElements.first().shouldBe(Condition.visible);
            }
            else {
                Selenide.executeJavaScript("window.scrollBy(0, -document.body.scrollHeight)");
            }
        }
    }
    public void clickFirstProductOnThePage() {
        productsList.first().shouldBe(Condition.exist);
        productsList.first().shouldBe(Condition.visible).click();
    }
}
