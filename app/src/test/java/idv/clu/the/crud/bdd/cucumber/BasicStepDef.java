package idv.clu.the.crud.bdd.cucumber;

import idv.clu.the.crud.TheCrudApplication;
import idv.clu.the.crud.bdd.cucumber.component.objectmapper.UnirestObjectMapper;
import idv.clu.the.crud.util.DatabaseResetInfo;
import idv.clu.the.crud.util.DatabaseResetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Carl Lu
 */
@Slf4j
@SpringBootTest(classes = TheCrudApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration
public class BasicStepDef {

    protected final static String CONTENT_TYPE_APPLICATION_JSON = "application/json";

    @Autowired
    private UnirestObjectMapper unirestObjectMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private DatabaseResetRepository databaseResetRepository;

    /**
     * An instance of {@link UnirestObjectMapper}
     *
     * @return unirestObjectMapper
     */
    protected UnirestObjectMapper getUnirestObjectMapper() {
        return unirestObjectMapper;
    }

    /**
     * For some scenarios, we might want to reset the table content, include the auto increment key.
     * To achieve this purpose in H2 database, we need to execute the following two SQL commands:
     * <p>
     * 1. TRUNCATE the table
     * 2. Alter the auto increment column to start with 1
     *
     * @param table The table you want to reset.
     */
    void resetTable(final String table) {
        log.trace("Truncate table: {}", table);
        DatabaseResetInfo resetInfo = new DatabaseResetInfo();
        resetInfo.setTable(table);
        databaseResetRepository.truncateTable(resetInfo);
        databaseResetRepository.resetAutoIncrementForId(resetInfo);
        log.trace("Truncate table {} done.", table);
        log.trace("Record count for {} table after reset: {}", table, databaseResetRepository.selectTotalRecordCount(resetInfo));
    }

}
