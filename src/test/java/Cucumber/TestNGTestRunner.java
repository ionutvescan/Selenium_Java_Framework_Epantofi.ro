package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "/Users/Ionut/IdeaProjects/CommerceWebApp/src/test/java/Cucumber", glue = "StepDefinitions",
        monochrome = true, plugin = {"html: reportsCucumber/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}
