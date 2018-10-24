package idv.clu.the.crud.module.user.config;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Carl Lu
 * <p>
 * Configuration settings of the application.
 */
@Configuration
public class ApplicationConfig {
    
    @Bean
    public BasicPasswordEncryptor basicPasswordEncryptor() {
        return new BasicPasswordEncryptor();
    }

}
