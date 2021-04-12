package timeManagement.Acceptance.Test;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = "summary"
		, monochrome = true
		, snippets = SnippetType.CAMELCASE
		, features = "features"
		)
public class AcceptanceTest {

}
