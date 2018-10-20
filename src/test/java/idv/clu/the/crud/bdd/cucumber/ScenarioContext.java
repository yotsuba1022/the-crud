package idv.clu.the.crud.bdd.cucumber;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Carl Lu
 */
@Component
public class ScenarioContext {

    private final Map<String, Object> contextData;

    public ScenarioContext(Map<String, Object> contextData) {
        this.contextData = contextData;
    }

    public Map<String, Object> getContextData() {
        return contextData;
    }

}
