package UtilityClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.internal.BaseClassFinder;

import java.awt.*;

public class ActionsClass {
    public static  WebDriver driver;
    Actions actions = new Actions(driver);

    public static void click(WebElement element){
        Actions actions = new Actions(driver);
        Actions actionObject = actions.moveToElement(element).click();
        actionObject.perform();
    }

    public static void sendKeys(WebElement element, String text){
        element.sendKeys(text);
    }

    public  static void hover(WebElement element){

       
    }


}
