package idv.clu.the.crud.bdd.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author Carl Lu
 * <p>
 * The annotation @RunWith here means that JUnit runner uses the JUnit framework to run the Cucumber test.
 * <p>
 * The annotation @CucumberOptions indicates the location of the Gherkin file which is also known as the feature file.
 * <p>
 * To run a specific scenario, please use annotation parameter, for example:
 * tags = {"@user_query"}
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"}, glue = {"idv.clu.the.crud.bdd.module"})
public class CucumberIntegrationTest extends BasicStepDef {
}
