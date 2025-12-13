import Driverfactory.Driver;
import Pages.HomePage;
import Pages.StationsPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchTest {
    Driver driver;
    HomePage homePage;
    StationsPage stationsPage;

    @BeforeClass
    public void setup(){
        // 1. Initialize Driver
        driver = new Driver("CHROME");

        // 2. Open URL using your BrowserActions
        driver.browser().navigateTo("https://www.radio-browser.info/");

        // 3. Initialize Page Object
        homePage = new HomePage(driver.get());
    }

    @Test
    public void testSearchForBBC() {

        homePage.checkThatCardHeaderShouldBeDisplayed();

        stationsPage = homePage.searchForStation("BBC");

        //stationsPage.checkStationNameContains("BBC");

    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
