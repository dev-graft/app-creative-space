package devgraft.supports.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Server;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

@EnableOpenApi
@Configuration
public class SwaggerDocumentationConfig {

    @Bean
    public OpenAPI openApi() {
        final OpenAPI openAPI = new OpenAPI();
        openAPI.info(new Info()
                .title("Swagger OpenAPI 3.0")
                .description("")
                .license(license())
                .contact(contact())
        );

        io.swagger.v3.oas.models.servers.Server local = new io.swagger.v3.oas.models.servers.Server();
        local.setDescription("local");
        local.setUrl("http://localhost:8080");

        io.swagger.v3.oas.models.servers.Server prod = new io.swagger.v3.oas.models.servers.Server();
        prod.setDescription("prod");
        prod.setUrl("https://devgraft.com");

        openAPI.setServers(List.of(local, prod));
        return openAPI;
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

    @Bean
    public Docket customImplementation() {
        final Server local = new Server("local", "http://localhost:8080", "for local usages", Collections.emptyList(), Collections.emptyList());
        final Server prod = new Server("prod", "https://devgraft.com", "for prod", Collections.emptyList(), Collections.emptyList());

        return new Docket(DocumentationType.OAS_30)
                .servers(local, prod)
                .select()
                .apis(RequestHandlerSelectors.basePackage("devgraft"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }
}
