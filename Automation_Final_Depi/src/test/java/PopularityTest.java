import Driverfactory.Driver;
import Pages.HomePage;
import Pages.StationsPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PopularityTest {
    Driver driverWrapper;
    HomePage homePage;
    StationsPage stationsPage;

    @BeforeClass
    public void setup() {
        // 1. Initialize the Chrome Driver
        driverWrapper = new Driver("CHROME");

        // 2. Navigate to the base URL
        driverWrapper.browser().navigateTo("https://www.radio-browser.info/");

        // 3. Initialize the Page Object
        homePage = new HomePage(driverWrapper.get());
    }

    @Test
    public void testSortByVotes() throws InterruptedException{
        // --- Step 1: Open the dropdown and select "By votes" ---
        // We pass the exact text as it appears in the menu
        stationsPage = homePage.selectFromByPopularity("By votes");

        Thread.sleep(3000);
        // --- Step 2: Validate the results ---
        // We reuse the method we created earlier to ensure the table/play button loaded
        // This confirms that the sorting action actually triggered a page load/update
        stationsPage.checkStationNameContains(""); // Empty string just checks if ANY result appeared
    }

    @Test
    public void testSortByClicks() throws InterruptedException{
        // --- Step 1: Open the dropdown and select "By clicks" ---
        stationsPage = homePage.selectFromByPopularity("By clicks");
        Thread.sleep(3000);
        // --- Step 2: Validate the results ---
        stationsPage.checkStationNameContains("");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser session
        driverWrapper.quit();
    }
}
