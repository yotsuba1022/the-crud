package idv.clu.the.crud.module.user.config;

import idv.clu.the.crud.module.user.controller.UserController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carl Lu
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("The CRUD - User Module API Documentation")
                .contact(new Contact("clu", "https://github.com/yotsuba1022", "yotsuba1022@gmail.com"))
                .build();
    }

    @Bean
    public Docket api() {
        final ParameterBuilder requestParameterBuilder = new ParameterBuilder();
        final List<Parameter> params = new ArrayList<>();

        requestParameterBuilder.name("Authorization")
                .description("User module authorization token.")
                .modelRef(new ModelRef("String"))
                .parameterType("header")
                .required(false)
                .build();
        params.add(requestParameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(UserController.class.getPackageName()))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(params);
    }

}
