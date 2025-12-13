import Driverfactory.Driver;
import Pages.HomePage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class LoginPageTest
{
    Driver driver;
    HomePage homepage ;

    @BeforeClass
    public void setup(){

        driver = new Driver("CHROME");
        homepage = new HomePage(driver.get());

    }
    @Test
    public void login() throws InterruptedException {
        driver.browser().navigateTo("https://www.radio-browser.info/");
        //driver.wait(5);
        System.out.println("Waiting 5 seconds...");
        Thread.sleep(5000);

    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
