package idv.clu.the.crud.bdd.cucumber;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Carl Lu
 */
@Component
public class ScenarioContext {

    private final Map<String, Object> contextData = new HashMap<>();

    public ScenarioContext() {
    }

    public Map<String, Object> getContextData() {
        return contextData;
    }

}
