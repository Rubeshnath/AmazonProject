package hooks;


import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;
import stepDefinitions.BaseClass;

import java.io.File;

public class Hooks extends BaseClass{



    @AfterStep
    public void screenshot(Scenario scenario){
        byte[] screenshotAs = driver.getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshotAs, "image/png", "screenshot");
    }

    @Before
    public void init()
    {
       BaseClass.openBrowser();
    }

     @After
    public void quitWindow(Scenario scenario){
        BaseClass.closeBrowser();
    }
}
