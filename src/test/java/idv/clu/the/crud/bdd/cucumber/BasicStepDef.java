package idv.clu.the.crud.bdd.cucumber;

import idv.clu.the.crud.TheCrudApplication;
import idv.clu.the.crud.bdd.cucumber.component.objectmapper.UnirestObjectMapper;
import idv.clu.the.crud.bdd.cucumber.config.ProductDataSourceConfig;
import idv.clu.the.crud.bdd.cucumber.config.UserDatasourceConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Carl Lu
 */
@Slf4j
@SpringBootTest(classes = {TheCrudApplication.class, UserDatasourceConfig.class,
        ProductDataSourceConfig.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration
public class BasicStepDef {

    protected final static String CONTENT_TYPE_APPLICATION_JSON = "application/json";

    @Autowired
    private UnirestObjectMapper unirestObjectMapper;

    /**
     * An instance of {@link UnirestObjectMapper}
     *
     * @return unirestObjectMapper
     */
    protected UnirestObjectMapper getUnirestObjectMapper() {
        return unirestObjectMapper;
    }

}
