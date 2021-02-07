package com.hoperun.pesystem.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ljd
 * @Date: 2021/2/7 9:16
 * @description: swagger配置类
 **/
@Slf4j
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger.enabled}")
    private boolean SwaggerSwitch;
    @Value("${swagger.base-package}")
    private String basePackage;
    @Value("${swagger.title}")
    private String title;
    @Value("${swagger.description}")
    private String description;
    @Value("${swagger.version}")
    private String version;
    @Value("${swagger.contact.name}")
    private String name;
    @Value("${swagger.contact.url}")
    private String url;
    @Value("${swagger.contact.email}")
    private String email;
    @Bean
    public Docket createRestApi() {
        log.info("======================== 当前环境是否开启Swagger：" + SwaggerSwitch + " ========================");
        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                //配置是否启用Swagger，如果是false，在浏览器将无法访问，默认是true
                .enable(SwaggerSwitch)
                //apiInfo： 添加api详情信息，参数为ApiInfo类型的参数，这个参数包含了第二部分的所有信息比如标题、描述、版本之类的，开发中一般都会自定义这些信息
                .apiInfo(apiInfo())
                .select()
                //apis： 添加过滤条件,
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .contact(new Contact(name, url, email))
                .version(version)
                .build();
    }

    /**
     * @Author: ljd
     * @Date: 2021/2/7 9:17
     * @description: swagger加入全局Authorization header 将在ui界面右上角新增token输入界面
     **/
    private List<ApiKey> securitySchemes() {
        ApiKey apiKey = new ApiKey("Authorization", "token", "header");
        ArrayList arrayList = new ArrayList();
        arrayList.add(apiKey);
        return arrayList;
    }

    /**
     * 在Swagger2的securityContexts中通过正则表达式，设置需要使用参数的接口（或者说，是去除掉不需要使用参数的接口），
     * 如下列代码所示，通过PathSelectors.regex("^(?!auth).*$")，
     * 所有包含"auth"的接口不需要使用securitySchemes。即不需要使用上文中设置的名为“Authorization”，
     * type为“header”的参数。
     *
     */
    private List<SecurityContext> securityContexts() {
        SecurityContext build = SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
        ArrayList arrayList = new ArrayList();
        arrayList.add(build);
        return arrayList;
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        SecurityReference authorization = new SecurityReference("Authorization", authorizationScopes);
        ArrayList arrayList = new ArrayList<>();
        arrayList.add(authorization);
        return arrayList;
    }
}