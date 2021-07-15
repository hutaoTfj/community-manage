package ink.hutao.manage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * <p></p>
 * @author tfj
 * @since 2021/6/28
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2).pathMapping("/")
//                .host("hutao.ink/c/")
                // 开启个人信息
                .apiInfo(apiInfo())
                // 选择哪些接口作为swagger的doc发布
                .select()
                .build();
    }

    /**
     * 个人信息
     */
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //api文本
                .title("智慧社区API")
                //说明
                .description("hutao api")
                //用户名 + 网址 + 邮箱
                .contact(new Contact("" ,
                        "hutao.ink" ,
                        "489516067@qq.com"))
                //版本
                .version("2.0")
                //运行
                .build();
    }
}
