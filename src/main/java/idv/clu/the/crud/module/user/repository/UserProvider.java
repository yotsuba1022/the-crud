package idv.clu.the.crud.module.user.repository;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

/**
 * @author Carl Lu
 * <p>
 * A provider which used to generate dynamic SQL string for Mybatis mapper.
 */
public class UserProvider {

    private final static String TABLE_USER = "USER";

    public String getByQueryCriteria(final UserQueryCriteria queryCriteria) {
        final SQL sql = new SQL().SELECT("*").FROM(TABLE_USER);
        if (null != queryCriteria.getId() && queryCriteria.getId() > 0) {
            sql.WHERE("id = #{id}");
        }
        if (!StringUtils.isEmpty(queryCriteria.getUsername())) {
            sql.WHERE("username LIKE #{username}");
        }
        return sql.toString();
    }

}
