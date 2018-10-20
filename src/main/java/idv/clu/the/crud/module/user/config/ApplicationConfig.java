package idv.clu.the.crud.module.user.config;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Carl Lu
 * <p>
 * Configuration settings of the application.
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public BasicPasswordEncryptor basicPasswordEncryptor() {
        return new BasicPasswordEncryptor();
    }

}
