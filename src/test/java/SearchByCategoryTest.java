import org.testng.annotations.Test;
import pages.CategoryHomePage;
import pages.PDPPage;
import pages.SearchResultPage;

import static org.testng.Assert.assertEquals;

public class SearchByCategoryTest extends SetUp {
    @Test
    public void searchByCategoryTest () {
        CategoryHomePage categoryHomePage = new CategoryHomePage();
        SearchResultPage searchResultPage = new SearchResultPage();
        PDPPage pdpPage = new PDPPage();

        categoryHomePage.clickOnCustomerCategoryTab();
        categoryHomePage.searchByCategory();
        categoryHomePage.searchBySubCategory();

        searchResultPage.scrollDownToThePaginationAndClickNextPageIfPaginationExist();
        searchResultPage.clickFirstProductOnThePage();

        pdpPage.clickOnAddToCartButton();
        String actual_result = pdpPage.getNumberOfProductsInCart();
        System.out.println("Number of products in Cart: " + actual_result);
        assertEquals(actual_result, "1");
    }
}
