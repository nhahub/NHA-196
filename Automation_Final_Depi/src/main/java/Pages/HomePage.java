package Pages;

import elementactions.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebElement;

public class HomePage {

    private WebDriver driver;
    private ElementActions bot;
    private WebDriverWait wait;

    // --- Locators ---
    By appsLink = By.xpath("(//a[@href=\"/users\"])[1]");
    By cardHeader = By.xpath("(//h3[@class=\"card-header\"])[1]");
    By searchBox = By.xpath("//input[@id=\"typeahead-http\"]");

    By geoMapLink = By.xpath("//a[@href=\"/map\"]");
    By languageLink = By.xpath("//a[@href='/languages']");

    // Locators for "By Popularity" Dropdown
    By byPopularityDropdown = By.xpath("//a[contains(@class, 'nav-link') and contains(., 'By popularity')]");
    By dropdownMenuContainer = By.cssSelector(".dropdown-menu.show");


    public HomePage(WebDriver driver){
        this.driver = driver;
        this.bot = new ElementActions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    // --- Assertions ---
    public HomePage checkThatCardHeaderShouldBeDisplayed(){
        Assert.assertTrue(bot.isDisplayed(cardHeader), "Card header is not displayed!");
        return this;
    }

    // --- Actions ---

    public HomePage clickOnAppsLink(){
        bot.click(appsLink);
        return this;
    }

    public StationsPage searchForStation(String stationName){
        bot.fillField(searchBox, stationName);
        bot.pressKey(searchBox, org.openqa.selenium.Keys.ENTER);
        return new StationsPage(driver);
    }

    public MapPage clickOnGeoMapLink(){
        bot.click(geoMapLink);
        return new MapPage(driver);
    }

    public StationsPage selectFromByPopularity(String optionName) {
        System.out.println("Attempting to open 'By popularity' menu...");

        bot.clickUsingJavaScript(byPopularityDropdown);

        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        // list all elements
        List<WebElement> options = driver.findElements(By.cssSelector(".dropdown-menu.show .dropdown-item"));

        System.out.println("Found " + options.size() + " items in the dropdown.");

        boolean found = false;

        // Loop
        for (WebElement option : options) {
            String text = option.getText().trim();
            // System.out.println("Option found: " + text);

            if (text.contains(optionName)) {
                System.out.println("Clicking on: " + text);

                // click bt java executer
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("ERROR: Could not find option '" + optionName + "' in the list!");
        }

        return new StationsPage(driver);
    }
}