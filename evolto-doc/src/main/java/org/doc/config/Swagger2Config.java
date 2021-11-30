/**
 * 版权所有：IFallowed
 * 项目名称：Evolto
 * 包名称：org.doc.config
 * 类名称：Swagger2Config
 * <p>
 * 创建日期：2021/2/8
 * 创建人：IFallowed
 * 版本号：v1.0
 * 描述：N/A
 */
package org.doc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Description：swagger2配置类<br>
 * Date：2021/2/8 10:01<br>
 * @author IFallowed
 * @version 1.0
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Value("${swagger2.enable}")
    private boolean swaggerEnble;

    /**
     * Description：创建API应用 <br>
     * Author： IFallowed<br>
     * Date： 2021/2/8 10:06<br>
     * @return springfox.documentation.spring.web.plugins.Docket
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(swaggerEnble)
                .select()
                //扫描项目方式：按包扫描、按类注解扫描、按方案注解扫描
                //any，任何请求
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Description：设置API信息 <br>
     * Author： IFallowed<br>
     * Date： 2021/2/8 10:07<br>
     * @return springfox.documentation.service.ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Evolto Swagger-UI")
                .description("Evolto 项目的api文档")
                //服务条款url
                .termsOfServiceUrl("")
                //文档维护者联系方式
                .contact(new Contact("evolto","http://www.baidu.com","simple@gamil.com"))
                .version("1.0")
                .build();
    }
}
