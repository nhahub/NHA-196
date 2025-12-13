package elementactions;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ElementActions {
    private WebDriver driver;
    private JavascriptExecutor javascriptExecutor;
    private WebDriverWait wait;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        javascriptExecutor = (JavascriptExecutor) this.driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Visable
    private void waitForVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Clickable
    private void waitForClickability(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public ElementActions click(By locator) {
        waitForClickability(locator); // Wait until visibilityً
        System.out.println("Click on: " + locator.toString());
        try {
            driver.findElement(locator).click();
        } catch (ElementClickInterceptedException | NoSuchElementException
                 | StaleElementReferenceException | TimeoutException exception) {
            scrollIntoElement(locator);
            clickUsingJavaScript(locator);
        }
        return this;
    }

    public ElementActions clickUsingJavaScript(By locator) {
        System.out.println("Click on: " + locator.toString() + " Using JavaScript");
        javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(locator));
        return this;
    }

    public ElementActions pressKey(By locator, Keys key) {
        waitForVisibility(locator);
        System.out.println("Pressing key: " + key.name() + " on locator: " + locator.toString());
        driver.findElement(locator).sendKeys(key);
        return this;
    }

    public ElementActions scrollIntoElement(By locator) {
        System.out.println("Scrolling to Element: " + locator.toString());
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));
        return this;
    }

    public ElementActions fillField(By locator, String text) {
        waitForVisibility(locator);
        clearField(locator);
        System.out.println("Fill " + text + " in " + locator);
        driver.findElement(locator).sendKeys(text);
        return this;
    }

    public ElementActions clearField(By locator) {
        waitForVisibility(locator);
        System.out.println("Clearing " + locator);
        driver.findElement(locator).clear();
        return this;
    }

    public String getTextOf(By locator) {
        waitForVisibility(locator);
        System.out.println("Getting text from locator: " + locator.toString());
        return driver.findElement(locator).getText();
    }

    public Boolean isDisplayed(By locator) {

        try {
            System.out.println("Checking " + locator.toString() + " if Displayed");
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


}
