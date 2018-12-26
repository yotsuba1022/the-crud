package idv.clu.the.crud.module.user.repository;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

/**
 * @author Carl Lu
 * <p>
 * A provider which used to generate dynamic SQL string for Mybatis mapper.
 */
@Slf4j
public class UserProvider {

    private final static String TABLE_USER = "USER";

    public String getByQueryCriteria(final UserQueryCriteria queryCriteria) {
        final SQL sql = new SQL().SELECT("*").FROM(TABLE_USER);

        if (queryCriteria.isVip()) {
            sql.WHERE("is_vip = #{isVip}");
        }

        constructNonNullQueryParameter(sql, queryCriteria.getId(), "id = #{id}");
        constructNonNullQueryParameter(sql, queryCriteria.getAge(), "age = #{age}");
        constructStringTypeQueryParameter(sql, queryCriteria.getUsername(), "username = #{username}");
        constructStringTypeQueryParameter(sql, queryCriteria.getFirstName(), "first_name = #{firstName}");
        constructStringTypeQueryParameter(sql, queryCriteria.getLastName(), "last_name = #{lastName}");
        constructStringTypeQueryParameter(sql, queryCriteria.getGender(), "gender = #{gender}");

        String orderBy = queryCriteria.getOrderBy() + ( queryCriteria.isDesc() ? " desc" : " asc" );
        sql.ORDER_BY(orderBy);

        String query = sql.toString() + " limit " + queryCriteria.getLimit() + " offset " + queryCriteria.getOffset();

        log.debug("Query users by SQL: {}", query);

        return query;
    }

    private void constructNonNullQueryParameter(final SQL sql, final Object parameter, final String query) {
        if (null != parameter) {
            sql.WHERE(query);
        }
    }

    private void constructStringTypeQueryParameter(final SQL sql, final String strValue, final String query) {
        if (!StringUtils.isEmpty(strValue)) {
            sql.WHERE(query);
        }
    }

}
