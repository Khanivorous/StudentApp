package cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:build/reports/cucumber", "json:build/reports/cucumber.json","junit:build/reports/cucumber.xml"})
public class RunCucumberTest {
}
