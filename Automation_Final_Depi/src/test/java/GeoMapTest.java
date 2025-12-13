import Driverfactory.Driver;
import Pages.HomePage;
import Pages.MapPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GeoMapTest {

    Driver driverWrapper;
    HomePage homePage;
    MapPage mapPage;

    @BeforeClass
    public void setup(){
        driverWrapper = new Driver("CHROME");
        driverWrapper.browser().navigateTo("https://www.radio-browser.info/");
        homePage = new HomePage(driverWrapper.get());
    }

    @Test
    public void testOpenGeoMapVisualization() {

        mapPage = homePage.clickOnGeoMapLink();
        mapPage.checkMapIsDisplayed();
    }

    @AfterClass
    public void tearDown() throws InterruptedException { // throws InterruptedException

        System.out.println("Waiting 5 seconds before closing...");
        Thread.sleep(5000);

        driverWrapper.quit();
    }
}
