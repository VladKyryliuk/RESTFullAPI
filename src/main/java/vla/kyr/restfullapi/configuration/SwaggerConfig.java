package vla.kyr.restfullapi.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "Documentation",
                description = "RESTFULL API",
                version = "1.0",
                contact = @Contact(
                        name = "Vlad Kyryliuk",
                        email = "vlad.kyryliukr@gmail.com",
                        url = "https://www.linkedin.com/in/vlad-kyryliuk/"
                )
        )
)
public class SwaggerConfig {
}
