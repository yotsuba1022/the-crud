package idv.clu.the.crud.bdd.user;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import idv.clu.the.crud.bdd.user.steps.BasicStepDef;
import org.junit.runner.RunWith;

/**
 * @author Carl Lu
 * <p>
 * The annotation @RunWith here means that JUnit runner uses the JUnit framework to run the Cucumber test.
 * <p>
 * The annotation @CucumberOptions indicates the location of the Gherkin file which is also known as the feature file.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"})
public class CucumberIntegrationTest extends BasicStepDef {
}
