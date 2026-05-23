package at.technikumwien.mse25.awt.hotelmario.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Boutique Hotel Technikum API")
                        .description("REST API for the Boutique Hotel Technikum booking application.")
                        .version("1.0.0")
                        .license(new License().name("").url("http://unlicense.org"))
                        .contact(new Contact().email("")));
    }
}
