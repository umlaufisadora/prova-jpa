package br.com.ctw.api_monitoramento_transformadores.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração para personalização da documentação OpenAPI / Swagger UI.
 */

@Configuration
public class OpenApiConfig
{
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Monitoramento Térmico de Transformadores")
                        .version("1.0.0")
                        .description("Sistema de gestão de ativos elétricos, medições térmicas e emissão automática de alertas.")
                        .contact(new Contact()
                                .name("Suporte Técnico")
                                .email("suporte@empresa.com.br"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }

}
