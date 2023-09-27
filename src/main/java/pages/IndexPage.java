package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class IndexPage {

    public IndexPage() {
    }
    //elements
    private SelenideElement searchInput = $x("//input[@id='header-search-input']");

    private ElementsCollection searchResultsAutoSuggestions = $$x("//div[@id='header-search-dropdown']//ul//li");

    //action methods
    public void setSearchInput(String keyword) {
        searchInput.shouldBe(Condition.visible).sendKeys(keyword);
    }

    public void clickSearchResultsAutoSuggestions() {
        searchResultsAutoSuggestions.first().isDisplayed();
        searchResultsAutoSuggestions.get(0).shouldBe(Condition.exist).click();
    }

    public SearchResultPage getSearchResultsPage() { //one more technique
        return new SearchResultPage();
    }
}
