package vitor.dev.com.Cadastro.Usuario.Swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI buildOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Gerênciamento de Cadastros")
                        .version("1.0.0")
                        .description("API feita por Vitor Ramos, para o gerênciamento de cadastros")
                        .contact(new Contact()
                                .name("Vitor Ramos")
                                .email("vitor.sramos04@gmail.com")
                                .url("https://github.com/vitor-sramos")
                        )
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")
                        )
                );
    }
}
