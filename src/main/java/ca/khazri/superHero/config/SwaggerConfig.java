package ca.khazri.superHero.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
        @Bean
        public Docket api() {
            return new Docket( DocumentationType.SWAGGER_2)
                    .select()
                    .apis( RequestHandlerSelectors.basePackage ( "ca.khazri.superHero.controller" ))
                    .paths( PathSelectors.regex("/.*"))
                    .build()
                    .apiInfo(apiInfo());
        }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Super Hero Company REST API",
                "Custom description of Super Hero Company REST API",
                "0.0.1-SNAPSHOT",
                "Terms of service",
                 new Contact ("KHAZRI SARRA", "https://portfolio-sara.herokuapp.com", "khazri.sarra.2@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }

}
