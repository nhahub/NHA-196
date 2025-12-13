package Pages;

import elementactions.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class MapPage {
    private WebDriver driver;
    private ElementActions bot;
    private WebDriverWait wait;

    By leafletContainer = By.cssSelector(".leaflet-container");

    public MapPage(WebDriver driver){
        this.driver = driver;
        this.bot = new ElementActions(driver);
        // 15 sec waiting to load map
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // *** Assertions *** //
    public void checkMapIsDisplayed(){
        System.out.println("Checking if URL contains '/map'...");

        try {

            wait.until(ExpectedConditions.urlContains("/map"));
            System.out.println("URL verified: We are on the Map page.");

            // wait till map loads
            System.out.println("Waiting for Leaflet Map to load...");
            wait.until(ExpectedConditions.visibilityOfElementLocated(leafletContainer));

            Assert.assertTrue(driver.findElement(leafletContainer).isDisplayed(), "Map container is NOT displayed!");
            System.out.println("Map loaded successfully!");

        } catch (Exception e) {

            Assert.fail("Failed to load map page or element: " + e.getMessage());
        }
    }
}