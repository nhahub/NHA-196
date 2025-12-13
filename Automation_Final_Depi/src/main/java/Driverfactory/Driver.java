package Driverfactory;

import browseractions.BrowserActions;
import elementactions.ElementActions;
import org.openqa.selenium.WebDriver;

public class Driver {
    private WebDriver driver;
    //private WebDriver driver = new ChromeDriver();
    public Driver(String driverType){
        this.driver = getDriverFactory(driverType).startDriver();
    }

    private DriverAbstract getDriverFactory(String driverType) {


        switch(driverType) {
            case "CHROME": {
                return new ChromeDriverFactory();
            }

            case "FIREFOX": {
                return new FirefoxDriverFactory();
            }

            case "EDGE": {
                return new EdgeDriverFactory();
            }

            default:{
                throw new IllegalStateException("Unexpected Driver: " + driverType);
            }
        }
    }
    public WebDriver get() {
        return driver;
    }

    public void quit() {
        driver.quit();
    }

    public ElementActions element(){
        return new ElementActions(driver);
    }

    public BrowserActions browser() {
        return new BrowserActions(driver);
    }
}
