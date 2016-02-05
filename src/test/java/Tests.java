import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import utils.Const;
import utils.WebDriverHelper;

/**
 * Created by DeLuTz on 2/5/2016.
 */
public class Tests {

    WebDriver driver;
    HomePage homePage;
    public static final String TEXT_TO_SEARCH = "Dental";
    public static final String HAPPY_PATH_TEXT_SEARCH = "energy";
    public static final String UNHAPPY_PATH_TEXT_SEARCH = "NoWordToBeFound";

    @BeforeClass
    private void openWebDriver() {
        //open a new instance of the webdriver
        driver = WebDriverHelper.open();
    }

    @BeforeMethod
    private void goToHomePage() {
        //navigate to Homepage
        WebDriverHelper.goToHomePage();

        //initialise elements on the HomePage
        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    @AfterClass
    private void closeWebDriver() {
        driver.close();
    }

    @Test
    private void clickSearchWithoutText() {
        //click search without any text
        homePage.clickSearch();
        Assert.assertTrue(homePage.getNumberOfResults() > 0);
    }

    @Test
    private void happyPathTest() {
        //search after a happy path text. Expected result: results found > 0
        homePage.searchFor(HAPPY_PATH_TEXT_SEARCH);
        Assert.assertTrue(homePage.getNumberOfResults() > 0);
    }

    @Test
    private void unHappyPathTest() {
        //search unhappy path text => results found=0
        homePage.searchFor(UNHAPPY_PATH_TEXT_SEARCH);
        Assert.assertTrue(homePage.getNumberOfResults() == 0);
    }

    @Test
    private void testNewSearchButton() {
        //Search for some text, click new search, make sure text was cleared
        homePage.searchFor(HAPPY_PATH_TEXT_SEARCH);
        homePage.clearSearch();
        Assert.assertEquals(homePage.getSearchText(), "");
    }

    @Test
    private void testSearchedWord() {
        //Search for a word -> Make sure url like search?query=<word> and also <word> is present on the page
        homePage.searchFor(TEXT_TO_SEARCH);
        Assert.assertTrue(driver.getCurrentUrl().equals(Const.URL + "/search?query=" + TEXT_TO_SEARCH));
        Assert.assertTrue(homePage.getSearchWordOnPage().contains("'" + TEXT_TO_SEARCH + "'"));
    }
}
