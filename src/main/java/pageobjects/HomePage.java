package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by DeLuTz on 2/5/2016.
 */
public class HomePage {
    @FindBy(how = How.CSS, css = "input#query")
    private WebElement searchBox;

    @FindBy(how = How.CSS, css = "input[value=\"Submit\"]")
    private WebElement searchSubmit;

    @FindBy(how = How.CSS, css = "a#global-search-new")
    public WebElement clearSearch;

    @FindBy(how = How.CSS, css = "h1.number-of-search-results-and-search-terms strong:nth-of-type(1)")
    private WebElement numberOfResults;

    @FindBy(how = How.CSS, css = "h1.number-of-search-results-and-search-terms strong:nth-of-type(2)")
    private WebElement searchedWordOnPage;

    public void clickSearch() {
        searchSubmit.click();
    }

    public void searchFor(String text) {
        searchBox.sendKeys(text);
        clickSearch();
    }

    public void typeSearch(String text) {
        searchBox.sendKeys(text);
    }

    public void clearSearch() {
        clearSearch.click();
    }

    public int getNumberOfResults() {
        //Extract the number of results from the custom String displayed on the page
        int nrOfResults = Integer.parseInt(numberOfResults.getText().replaceAll(",", ""));
        System.out.println("Were founded " + nrOfResults + " results");
        return nrOfResults;
    }

    public String getSearchText() {
        return searchBox.getAttribute("value");
    }

    public String getSearchWordOnPage() {
        return searchedWordOnPage.getText();
    }
}
