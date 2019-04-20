package idv.clu.the.crud.module.user.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author Carl Lu
 */
@Configuration
@PropertySource("product-datasource.properties")
@MapperScan(basePackages = "idv.clu.the.crud.module.user.repository.product", sqlSessionFactoryRef =
        "productSqlSessionFactory")
public class ProductDataSourceConfig {

    @Bean(name = "productDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.productdatasource")
    public DataSource productDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "productSqlSessionFactory")
    public SqlSessionFactory productSqlSessionFactory(@Qualifier("productDataSource") DataSource productDataSource,
            org.apache.ibatis.session.Configuration mybatisConfiguration) throws Exception {
        SqlSessionFactoryBean productSqlSessionFactoryBean = new SqlSessionFactoryBean();
        productSqlSessionFactoryBean.setDataSource(productDataSource);
        productSqlSessionFactoryBean.setConfiguration(mybatisConfiguration);
        return productSqlSessionFactoryBean.getObject();
    }

    @Bean(name = "productTransactionManager")
    public DataSourceTransactionManager productDataSourceTransactionManager(
            @Qualifier("userDataSource") DataSource productDataSource) {
        return new DataSourceTransactionManager(productDataSource);
    }

    @Bean(name = "productSqlSessionTemplate")
    public SqlSessionTemplate productSqlSessionTemplate(
            @Qualifier("productSqlSessionFactory") SqlSessionFactory productSqlSessionFactory) {
        return new SqlSessionTemplate(productSqlSessionFactory);
    }

}
