package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class ElementDetailsToHTML {
    public static void main(String[] args) {
        // Set up WebDriver (make sure to specify the correct path to your chromedriver)

        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the desired page
            driver.get("https://amazon.in");

            // Wait until the page loads completely
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

            // Create HTML report
            try (FileWriter writer = new FileWriter("element_report.html")) {
                writer.write("<html><head><title>Element Report</title></head><body>");
                writer.write("<h1>Element Report</h1>");

                // Extract and write details of various elements to the report
                writeElementDetailsToHTML(driver, By.tagName("input"), writer);
                writeElementDetailsToHTML(driver, By.tagName("button"), writer);
                writeElementDetailsToHTML(driver, By.tagName("a"), writer);

                writer.write("</body></html>");
            } catch (IOException e) {
                System.err.println("Error writing HTML report: " + e.getMessage());
            }

        } finally {
            // Close the browser
            driver.quit();
        }
    }

    public static void writeElementDetailsToHTML(WebDriver driver, By locator, FileWriter writer) throws IOException {
        List<WebElement> elements = driver.findElements(locator);
        for (WebElement element : elements) {
            try {
                writer.write("<h2>Element Details</h2>");
                writer.write("<p><strong>Displayed Name:</strong> " + getDisplayedName(element) + "</p>");
                writer.write("<p><strong>Relative XPath:</strong> " + getRelativeXPath(driver, element) + "</p>");
                writer.write("<p><strong>Absolute XPath:</strong> " + getAbsoluteXPath(driver, element) + "</p>");
                writer.write("<p><strong>CSS Selector:</strong> " + getCssSelector(driver, element) + "</p>");
                writer.write("<p><strong>Name:</strong> " + element.getAttribute("name") + "</p>");
                writer.write("<p><strong>ID:</strong> " + element.getAttribute("id") + "</p>");
                writer.write("<p><strong>Class:</strong> " + element.getAttribute("class") + "</p>");
                writer.write("<hr>");
            } catch (Exception e) {
                writer.write("<p>Error retrieving details for an element: " + e.getMessage() + "</p>");
            }
        }
    }

    public static String getDisplayedName(WebElement element) {
        String text = element.getText();
        return (text != null && !text.isEmpty()) ? text : "[No Displayed Name]";
    }

    public static String getRelativeXPath(WebDriver driver, WebElement element) {
        return (String) ((JavascriptExecutor) driver).executeScript(
                "function getRelativeXPath(element) {" +
                        "   var xpath = '';" +
                        "   for (; element && element.nodeType == 1; element = element.parentNode) {" +
                        "       var tagName = element.nodeName.toLowerCase();" +
                        "       var index = Array.prototype.indexOf.call(element.parentNode.children, element) + 1;" +
                        "       xpath = '/' + tagName + '[' + index + ']' + xpath;" +
                        "   }" +
                        "   return xpath || '/';" +
                        "}" +
                        "return getRelativeXPath(arguments[0]);", element);
    }

    public static String getAbsoluteXPath(WebDriver driver, WebElement element) {
        return (String) ((JavascriptExecutor) driver).executeScript(
                "function getAbsoluteXPath(element) {" +
                        "   var comp, comps = [];" +
                        "   var parent = null;" +
                        "   var xpath = '';" +
                        "   var getPos = function(element) {" +
                        "       var position = 1, curNode;" +
                        "       if (element.nodeType == Node.ATTRIBUTE_NODE) {" +
                        "           return null;" +
                        "       }" +
                        "       for (curNode = element.previousSibling; curNode; curNode = curNode.previousSibling) {" +
                        "           if (curNode.nodeName == element.nodeName) {" +
                        "               ++position;" +
                        "           }" +
                        "       }" +
                        "       return position;" +
                        "   };" +
                        "   for (; element && element.nodeType == 1; element = element.parentNode) {" +
                        "       comp = comps[comps.length] = {};" +
                        "       comp.name = element.nodeName;" +
                        "       comp.position = getPos(element);" +
                        "   }" +
                        "   for (var i = comps.length - 1; i >= 0; i--) {" +
                        "       comp = comps[i];" +
                        "       xpath += '/' + comp.name.toLowerCase();" +
                        "       if (comp.position !== null && comp.position > 1) {" +
                        "           xpath += '[' + comp.position + ']';" +
                        "       }" +
                        "   }" +
                        "   return xpath;" +
                        "}" +
                        "return getAbsoluteXPath(arguments[0]);", element);
    }

    public static String getCssSelector(WebDriver driver, WebElement element) {
        return (String) ((JavascriptExecutor) driver).executeScript(
                "function getCssSelector(element) {" +
                        "   var path = [];" +
                        "   for (; element; element = element.parentNode) {" +
                        "       var name = element.nodeName.toLowerCase();" +
                        "       if (element.id) {" +
                        "           path.unshift('#' + element.id);" +
                        "           break;" +
                        "       }" +
                        "       var index = Array.prototype.indexOf.call(element.parentNode.children, element) + 1;" +
                        "       path.unshift(name + ':nth-of-type(' + index + ')');" +
                        "   }" +
                        "   return path.join(' > ');" +
                        "}" +
                        "return getCssSelector(arguments[0]);", element);
    }
}
