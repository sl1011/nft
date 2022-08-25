package com.shitouren.core.config.swagger;

import com.github.xiaoymin.knife4j.spring.annotations.EnableSwaggerBootstrapUi;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUi
public class SwaggerConfiguration {



    @Bean
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                //分组名称
                .securitySchemes(securitySchemes())
                .select()
                //这里指定Controller扫描包路径
                //.apis(RequestHandlerSelectors.basePackage("com.swagger.bootstrap.ui.demo.new2"))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }


    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeys = new ArrayList<>();
        apiKeys.add(new ApiKey("Authorization", "token", "header"));
        return apiKeys;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("AHL-接口文档")
                .description("TMax Api Documentation")
                .termsOfServiceUrl("http://www.shitouren.com")
                .contact(new Contact("shitouren", "http://www.shitouren.com", "shitouren@163.com"))
                .version("1.0.0")
                .build();
    }

}