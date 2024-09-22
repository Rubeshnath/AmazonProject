package stepDefinitions;

import UtilityClasses.PropertiesReader;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static RemoteWebDriver driver;


    public static void openBrowser() {

        //driver = new ChromeDriver();
        String browser = PropertiesReader.getProperty("browser");
        if(browser.equals("Firefox"))
        {
            driver = new FirefoxDriver();

        }
        else
        if(browser.equals("Chrome"))
        {
            driver = new ChromeDriver();

        }
        else
        if(browser.equals("Edge"))
        {
            driver = new EdgeDriver();

        }
        else
        {
            System.out.println("Invalid browser");
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }


    public static void closeBrowser() {
                driver.quit();
    }

}

