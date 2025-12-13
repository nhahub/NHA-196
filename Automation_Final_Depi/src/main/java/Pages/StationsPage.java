package Pages;

import elementactions.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class StationsPage {

    private WebDriver driver;
    private ElementActions bot;


    // search for any element which contains "BBC"
    By stationName = By.xpath("//*[contains(text(), 'BBC')]");
    By playButton = By.xpath("(//button[contains(@title, 'play') or .//mat-icon[contains(text(),'play')]])[1]");
    By firstRowLanguage = By.xpath("(//table//tr//td)[5]");

    public StationsPage(WebDriver driver){
        this.driver = driver;
        this.bot = new ElementActions(driver);
    }

    // *** Assertions *** //
    public void checkStationNameContains(String expectedName){

        // wait till any element contains the name
        By dynamicStationLocator = By.xpath("//*[contains(text(), '" + expectedName + "')]");

        System.out.println("Checking if station exists: " + expectedName);
        Assert.assertTrue(bot.isDisplayed(dynamicStationLocator), "Station " + expectedName + " was not found in results!");
    }

    public void checkLanguageOfFirstStation(String expectedLanguage){
        String actualLanguage = bot.getTextOf(firstRowLanguage);
        System.out.println("Language found: " + actualLanguage);

        org.testng.Assert.assertTrue(actualLanguage.contains(expectedLanguage),
                "Expected language " + expectedLanguage + " but found " + actualLanguage);
    }

    // *** Actions *** //
    public void playFirstStation(){
        System.out.println("Attempting to play the first station...");
        bot.click(playButton);
    }
}