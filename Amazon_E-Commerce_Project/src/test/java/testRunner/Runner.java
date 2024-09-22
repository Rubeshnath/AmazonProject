package testRunner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/features",
        dryRun = !true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"stepDefinitions","hooks"},
        monochrome = true,
        tags = "@test4",
        plugin = {"html:reports/result.html"}
)


public class Runner extends AbstractTestNGCucumberTests {
}
