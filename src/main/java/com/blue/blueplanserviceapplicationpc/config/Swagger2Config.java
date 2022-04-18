package com.blue.blueplanserviceapplicationpc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket api() {
        //添加heads参数配置---start
//        ParameterBuilder tokenPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<Parameter>();
//        tokenPar.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
//        pars.add(tokenPar.build());
        //添加head参数配置---end

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.blue.blueplanserviceapplicationpc.Controller"))
                .paths(PathSelectors.any())
                .build();
//        		.globalOperationParameters(pars);//添加请求头(heads)参数
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("蓝色文档")
                .description("项目地址：http://localhost:9999/conviction")
                .version("1.0")//版本
                .build();
    }

}
