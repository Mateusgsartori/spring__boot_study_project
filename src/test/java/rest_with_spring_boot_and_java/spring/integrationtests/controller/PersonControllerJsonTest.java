package rest_with_spring_boot_and_java.spring.integrationtests.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import rest_with_spring_boot_and_java.spring.configs.TestsConfigs;
import rest_with_spring_boot_and_java.spring.integrationtests.testcontainers.AbstractIntegrationTest;
import rest_with_spring_boot_and_java.spring.integrationtests.vo.PersonVO;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerJsonTest extends AbstractIntegrationTest {

	private static RequestSpecification specification;
	private static ObjectMapper objectMapper;
	private static PersonVO person;


	@BeforeAll
	public static void setup() {
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		person = new PersonVO();

	}

	@Test
	@Order(1)
	public void testCreate() throws JsonProcessingException {
		mockPerson();

		specification = new RequestSpecBuilder()
				.addHeader(TestsConfigs.HEADER_PARAM_ORIGIN, TestsConfigs.ORIGIN_ERUDIO)
				.setBasePath("/api/person/v1")
				.setPort(TestsConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
		
	var content = given().spec(specification)
			.contentType(TestsConfigs.CONTENT_TYPE_JSON)
			.body(person)
			.when()
			.post()
			.then()
			.statusCode(200)
			.extract()
			.body()
			.asString();

	PersonVO createdPerson = objectMapper.readValue(content, PersonVO.class);

	person = createdPerson;

	assertNotNull(createdPerson);
	assertNotNull(createdPerson.getId());
	assertNotNull(createdPerson.getFirstName());
	assertNotNull(createdPerson.getLastName());
	assertNotNull(createdPerson.getAddress());
	assertNotNull(createdPerson.getGender());
	assertTrue(createdPerson.getId() > 0);

	assertEquals("Richard", createdPerson.getFirstName());
	assertEquals("Stallman", createdPerson.getLastName());
	assertEquals("New York", createdPerson.getAddress());
	assertEquals("Male", createdPerson.getGender());



	}

	@Test
	@Order(2)
	public void testCreateWithWrongOrigin() throws JsonProcessingException {
		mockPerson();

		specification = new RequestSpecBuilder()
				.addHeader(TestsConfigs.HEADER_PARAM_ORIGIN, TestsConfigs.ORIGIN_SEMERU)
				.setBasePath("/api/person/v1")
				.setPort(TestsConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();

		var content = given().spec(specification)
				.contentType(TestsConfigs.CONTENT_TYPE_JSON)
				.body(person)
				.when()
				.post()
				.then()
				.statusCode(403)
				.extract()
				.body()
				.asString();



		assertNotNull(content);
		assertEquals("Invalid CORS request", content);



	}

	private void mockPerson() {
		person.setFirstName("Richard");
		person.setLastName("Stallman");
		person.setAddress("New York");
		person.setGender("Male");
	}

	@Test
	@Order(3)
	public void testFindById() throws JsonProcessingException {
		mockPerson();

		specification = new RequestSpecBuilder()
				.addHeader(TestsConfigs.HEADER_PARAM_ORIGIN, TestsConfigs.ORIGIN_ERUDIO)
				.setBasePath("/api/person/v1")
				.setPort(TestsConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();

		var content = given().spec(specification)
				.contentType(TestsConfigs.CONTENT_TYPE_JSON)
				.pathParam("id", person.getId())
				.when()
				.get("{id}")
				.then()
				.statusCode(200)
				.extract()
				.body()
				.asString();

		PersonVO createdPerson = objectMapper.readValue(content, PersonVO.class);

		person = createdPerson;

		assertNotNull(createdPerson);
		assertNotNull(createdPerson.getId());
		assertNotNull(createdPerson.getFirstName());
		assertNotNull(createdPerson.getLastName());
		assertNotNull(createdPerson.getAddress());
		assertNotNull(createdPerson.getGender());
		assertTrue(createdPerson.getId() > 0);

		assertEquals("Richard", createdPerson.getFirstName());
		assertEquals("Stallman", createdPerson.getLastName());
		assertEquals("New York", createdPerson.getAddress());
		assertEquals("Male", createdPerson.getGender());





	}

	@Test
	@Order(4)
	public void testFindByIdWithWrongOrigin() throws JsonProcessingException {
		mockPerson();

		specification = new RequestSpecBuilder()
				.addHeader(TestsConfigs.HEADER_PARAM_ORIGIN, TestsConfigs.ORIGIN_SEMERU)
				.setBasePath("/api/person/v1")
				.setPort(TestsConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();

		var content = given().spec(specification)
				.contentType(TestsConfigs.CONTENT_TYPE_JSON)
				.pathParam("id", person.getId())
				.when()
				.get("{id}")
				.then()
				.statusCode(403)
				.extract()
				.body()
				.asString();

		assertNotNull(content);
		assertEquals("Invalid CORS request", content);



	}

}
