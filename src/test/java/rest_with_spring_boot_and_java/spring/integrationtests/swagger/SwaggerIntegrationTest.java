package rest_with_spring_boot_and_java.spring.integrationtests.swagger;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import rest_with_spring_boot_and_java.spring.configs.TestsConfigs;
import rest_with_spring_boot_and_java.spring.integrationtests.testcontainers.AbstractIntegrationTest;
import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest {

	@Test
	public void shouldDisplaySwaggerUiPage() {
	var content =
				given().basePath("/swagger-ui/index.html")
					.port(TestsConfigs.SERVER_PORT)
					.when()
					.get()
					.then()
					.statusCode(200)
					.extract()
					.body()
					.asString();

		assertTrue(content.contains("Swagger UI"));

	}

}
