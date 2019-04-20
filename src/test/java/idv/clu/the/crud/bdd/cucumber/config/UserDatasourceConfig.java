package idv.clu.the.crud.bdd.cucumber.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * @author Carl Lu
 */
@Configuration
@PropertySource("user-datasource.properties")
@MapperScan(basePackages = "idv.clu.the.crud.module.user.repository.user", sqlSessionFactoryRef =
        "userSqlSessionFactory")
public class UserDatasourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    @Scope("prototype")
    public org.apache.ibatis.session.Configuration mybatisGlobalConfiguration() {
        return new org.apache.ibatis.session.Configuration();
    }

    @Bean(name = "userDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.embeddeduserdatasource")
    public DataSource userDataSource() {
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        return embeddedDatabaseBuilder.setType(EmbeddedDatabaseType.H2).addScript("database/schema.sql").build();
    }

    @Bean(name = "userSqlSessionFactory")
    public SqlSessionFactory userSqlSessionFactory(@Qualifier("userDataSource") DataSource userDataSource,
            org.apache.ibatis.session.Configuration mybatisConfiguration) throws Exception {
        SqlSessionFactoryBean userSqlSessionFactoryBean = new SqlSessionFactoryBean();
        userSqlSessionFactoryBean.setDataSource(userDataSource);
        userSqlSessionFactoryBean.setConfiguration(mybatisConfiguration);
        return userSqlSessionFactoryBean.getObject();
    }

    @Bean(name = "userTransactionManager")
    public DataSourceTransactionManager userDataSourceTransactionManager(
            @Qualifier("userDataSource") DataSource userDataSource) {
        return new DataSourceTransactionManager(userDataSource);
    }

    @Bean(name = "userSqlSessionTemplate")
    public SqlSessionTemplate userSqlSessionTemplate(
            @Qualifier("userSqlSessionFactory") SqlSessionFactory userSqlSessionFactory) {
        return new SqlSessionTemplate(userSqlSessionFactory);
    }

}
