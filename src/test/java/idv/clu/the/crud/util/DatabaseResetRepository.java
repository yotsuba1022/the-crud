package idv.clu.the.crud.util;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author Carl Lu
 * <p>
 * This reposetory is for test purpose only.
 */
@Mapper
public interface DatabaseResetRepository {

    @Update("TRUNCATE TABLE ${table}")
    void truncateTable(final DatabaseResetInfo databaseResetInfo);

    @Update("ALTER TABLE ${table} ALTER COLUMN id RESTART WITH 1")
    void resetAutoIncrementForId(final DatabaseResetInfo databaseResetInfo);

    @Select("Select count(*) from ${table}")
    long selectTotalRecordCount(final DatabaseResetInfo databaseResetInfo);

}
