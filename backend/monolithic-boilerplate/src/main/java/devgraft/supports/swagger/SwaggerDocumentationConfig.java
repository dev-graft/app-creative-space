package devgraft.supports.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;

//@EnableOpenApi
//@Configuration
public class SwaggerDocumentationConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .components(new Components().addSecuritySchemes("http", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
                .info(new Info()
                        .title("Swagger OpenAPI 3.0")
                        .description("ㅁㄴㅇㅁㄴㅇㅁㅇㅁㅇㅁㅇㅁ")
                        .license(license())
                        .contact(contact()));
    }

    private License license() {
        return new License()
                .name("Apache 2.0")
                .url("http://www.apache.org/licenses/LICENSE-2.0.html");
    }

    private Contact contact() {
        return new Contact()
                .email("devgraftteam@gmail.com");
    }

//    @Bean
//    public Docket customImplementation() {
//        return new Docket(DocumentationType.OAS_30)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("devgraft"))
//                .paths(PathSelectors.ant("/**"))
//                .build()
//                .apiInfo(apiInfo());
//    }

    @Bean
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }
}
