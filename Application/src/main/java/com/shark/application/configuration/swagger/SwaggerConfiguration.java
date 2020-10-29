package com.shark.application.configuration.swagger;

import com.shark.application.configuration.security.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Value;
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

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Value("${git.commit.id:na}")
    private String gitCommitId;

    @Bean
    public Docket docket() {
        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(new ParameterBuilder().name(SecurityConfiguration.ACCESS_HEADER).description("請輸入JWT")
                .modelRef(new ModelRef("string")).parameterType("header").required(false).build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .globalOperationParameters(parameterList)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.shark.application.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Base Application")
                .version("v1")
                .description("Current commit: " + gitCommitId)
                .build();
    }
}