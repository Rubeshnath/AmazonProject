package stepDefinitions;


//import Pages.LoginPage;
import UtilityClasses.ActionsClass;
import UtilityClasses.PropertiesReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

//import static Pages.LoginPage.accountAndListsTab;
import static hooks.Hooks.driver;


public class TestSteps extends BaseClass{

    //LoginPage loginPage = new LoginPage();

    //WebDriver driver = InitiateDrivers.driver;
    @Given("Launch the given URL Amazon Url")
    public void launchTheGivenURL() {

    String url = PropertiesReader.getProperty("base.url");
    System.out.println(url);
        driver.get(url);

    }
    @Given("validate whether the corresponding URL has been launched successfully.")
    public void validateWhetherTheCorrespondingURLHasBeenLaunchedSuccessfully() {

      String currentUrl = driver.getCurrentUrl();
      String expectedUrl = "amazon.in";
      if (currentUrl.contains(expectedUrl)){
          System.out.println(expectedUrl + " is navigated successfully ");
      }else {
          System.out.println(expectedUrl + " is not navigated and the navigeted url is " + currentUrl);
      }
    }

    @Given("Enter the Password")
    public void enterThePassword() throws InterruptedException {
//        Thread.sleep(3000);
        WebElement passwordInputField = driver.findElement(By.xpath("//input[@id='ap_password']"));
        String password = PropertiesReader.getProperty("password");
        ActionsClass.sendKeys(passwordInputField, password );
    }
    @Given("Navigate to {string}")
    public void navigateTo(String Tab) {

        Actions actions = new Actions(driver);
       WebElement accountAndListsTab = driver.findElement(By.xpath("//span[normalize-space()='"+Tab+"']"));
        actions.moveToElement(accountAndListsTab).perform();
    }
    @Given("Check whether the 'Sign in' has been displayed or not.")
    public void checkWhetherTheSigninHasBeenDisplayedOrNot() {
        WebElement signInButton = driver.findElement(By.xpath("//div[@id=\"nav-flyout-ya-signin\"]//span[@class='nav-action-inner'][normalize-space()='Sign in']"));
        if (signInButton.isDisplayed()){
            System.out.println("Sign in button is displayed");
        }else{
            System.out.println("Sign in button is not displayed");
        }
    }
    @Given("Click on 'Sign in'")
    public void clickOn() {
        WebElement signInButton = driver.findElement(By.xpath("//div[@id=\"nav-flyout-ya-signin\"]//span[@class='nav-action-inner'][normalize-space()='Sign in']"));
        signInButton.click();

    }
    @Given("Enter the Mobile number")
    public void enterTheMobileNumber() {
        WebElement emailInputField = driver.findElement(By.xpath("//input[@name='email']"));
        //emailInputField.sendKeys("8190905002");
        String username = PropertiesReader.getProperty("username");
        ActionsClass.sendKeys(emailInputField, username);
    }
    @Given("Check whether the Country code has been displayed")
    public void checkWhetherTheCountryCodeHasBeenDisplayed() {
        try {
        WebElement countryCode = driver.findElement(By.xpath("//span[@class='a-dropdown-prompt']"));
        if (countryCode.isDisplayed()){
            System.out.println("Country code is displayed and the value is " + countryCode.getText());
        }
        }
        catch (Exception e){
            System.out.println("Country code is not displayed");
        }
    }
    @Given("Click on Continue button")
    public void clickOnContinueButton() throws InterruptedException {
        WebElement continueButon = driver.findElement(By.xpath("//input[@type='submit']"));

        continueButon.click();
    }
    @Given("Click on “Get an OTP on your phone”.")
    public void clickOnGetAnOTPOnYourPhone() {
     WebElement getOTPButton = driver.findElement(By.xpath("//span[@id='auth-login-via-otp-btn']"));
     getOTPButton.click();
    }
    @Given("Wait until the OTP Entered in the Text box")
    public void waitUntilTheOTPEnteredInTheTextBox() throws InterruptedException {
        Thread.sleep(3000);
        WebElement otpInputField = driver.findElement(By.xpath("//input[@id='cvf-input-code']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        try {
            wait.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    String text = otpInputField.getAttribute("value");
                    return text.matches("\\d{6}");
                }
            });
        }catch(Exception e){
            System.out.println("OTP not Entered");
        }

    }
    @When("Click on Sign in")
    public void clickOnSignIn() throws InterruptedException {
        WebElement signButton = driver.findElement(By.xpath("(//input[@type='submit'])[1]"));
        signButton.click();
        Thread.sleep(5000);
        WebElement profileName = driver.findElement(By.xpath("//span[@id='nav-link-accountList-nav-line-1']"));
        if (!profileName.getText().contains("sign in")){
           String name = profileName.getText().replace("Hello, ", "");
            System.out.println("User " + name  + " logged in sucessfully");
        }
    }
    @Given("Validate whether the title has been matched with it’s expected title")
    public void validateWhetherTheTitleHasBeenMatchedWithItSExpectedTitle() {
        String currentTitle = driver.getTitle();
        String expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
        if (expectedTitle.equals(currentTitle)) {
            System.out.println("Title is as expected");
        }else {
            System.out.println("Title is not as expected and the current title is " + currentTitle);
        }
    }
    @Given("Navigate to 'Deliver to' location")
    public void navigateToDeliverToLocation() {
        WebElement deliverToLink = driver.findElement(By.xpath("//div[@id='glow-ingress-block']"));
        deliverToLink.click();
    }
    @Given("Check whether the {string} pop-up has been displayed or not.")
    public void checkWhetherThePopUpHasBeenDisplayedOrNot(String popupTitle) {
        WebElement popup = driver.findElement(By.xpath("//div[@class='a-popover-wrapper']"));
        if (popup.isDisplayed()){
            System.out.println(popupTitle + " popup is displayed");
        }else {
            System.out.println(popupTitle + " popup is not displayed");
        }
    }
    @Given("Enter the pin code")
    public void enterThePinCode() {
       WebElement pincodeInputField = driver.findElement(By.xpath("//input[@id='GLUXZipUpdateInput']"));
       pincodeInputField.sendKeys("600091");
    }
    @Given("validate whether the entered pin code has been matched with it’s expected.")
    public void validateWhetherTheEnteredPinCodeHasBeenMatchedWithItSExpected() throws InterruptedException {
        Thread.sleep(3000);
        WebElement pincodeInputField = driver.findElement(By.xpath("//input[@id='GLUXZipUpdateInput']"));
        if (pincodeInputField.getAttribute("value").equals("600091")){
            System.out.println("Pincode matches the expected");
        }else{
            System.out.println("Pincode does not matches the expected");
        }
    }
    @When("Click on Apply")
    public void clickOnApply() {
       WebElement applyButton = driver.findElement(By.xpath("//input[@aria-labelledby='GLUXZipUpdate-announce']"));
       applyButton.click();
    }
    @Then("Check whether the Pin code has been reflected in the “Deliver to”.")
    public void checkWhetherThePinCodeHasBeenReflectedInTheDeliverTo() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Thread.sleep(3000);
        WebElement deliverToPincode = driver.findElement(By.xpath("//div[@id='glow-ingress-block']"));
       if (deliverToPincode.getText().contains("600091")){
           System.out.println("Pincode is reflected");
       }else {
           System.out.println("Pincode is not reflected");
       }
    }

    @Given("Select {string} in the Search in Drop down.")
    public void selectUnder₹500InTheSearchInDropDown(String filter) throws InterruptedException {
        WebElement searchFilterDropdown = driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
//        Actions actions = new Actions(driver);
//        actions.moveToElement(searchFilterDropdown).click().perform();
        Select select = new Select(searchFilterDropdown);
        select.selectByVisibleText(filter);
        System.out.println("Selected");
        Thread.sleep(5000);

    }
    @Given("Enter {string}")
    public void enterHeadphones(String keyWord) throws InterruptedException {
        WebElement searchInputField = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        searchInputField.sendKeys(keyWord);

        Thread.sleep(5000);
    }
    @Given("Search headphones without clicking on the 'Search' icon.")
    public void searchHeadphonesWithoutClickingOnTheSearchIcon() throws InterruptedException {
        WebElement searchInputField = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        searchInputField.sendKeys(Keys.ENTER);
        Thread.sleep(5000);
    }
    @Given("Select any of the product under ₹500")
    public void selectAnyOfTheProductUnder₹500() {
        List<WebElement> results = driver.findElements(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])"));
        Random random = new Random();
        int productIndex = random.nextInt(results.size());
        WebElement selectedProduct = results.get(productIndex);
        selectedProduct.click();


    }
    @Given("Click on “Add to cart”")
    public void clickOnAddToCart() throws InterruptedException {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Thread.sleep(3000);
       WebElement addToCartButton = driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
       Actions actions = new Actions(driver);
       actions.moveToElement(addToCartButton).click().perform();
       Thread.sleep(3000);
    }
    @Given("Click on “Skip”")
    public void clickOnSkip() {

        WebElement cartButton = driver.findElement(By.xpath("//input[@aria-labelledby='attach-sidesheet-view-cart-button-announce']"));
        cartButton.click();
    }
    @Given("Switch to parent window")
    public void switchToParentWindow() throws InterruptedException {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        System.out.println("Window 1 title " + driver.getTitle());
        Thread.sleep(3000);
    }
    @When("Close the child window")
    public void closeTheChildWindow() throws InterruptedException {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Thread.sleep(3000);
        System.out.println("Window 2 title "+driver.getTitle());
        driver.close();
        Thread.sleep(5000);
        driver.switchTo().window(tabs.get(0));
        Thread.sleep(5000);
    }




}
