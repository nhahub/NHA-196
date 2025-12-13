package browseractions;

import org.openqa.selenium.WebDriver;

public class BrowserActions {

    private WebDriver driver;

    public BrowserActions(WebDriver driver){
        this.driver = driver;
    }

    public void getToUrl(String url){
        System.out.println("Get to " + url);
        driver.get(url);
    }

    public void navigateTo(String url){
        System.out.println("Navigating to " + url);
        driver.navigate().to(url);
    }

    public void navigateForward(){
        System.out.println("Navigating forward...");
        driver.navigate().forward();
    }

    public void refresh(){
        System.out.println("Refreshing...");
        driver.navigate().refresh();
    }

}
