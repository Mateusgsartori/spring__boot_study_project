package rest_with_spring_boot_and_java.spring.unittest;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import rest_with_spring_boot_and_java.spring.exceptions.RequiredObjectIsNullException;
import rest_with_spring_boot_and_java.spring.model.Person;
import rest_with_spring_boot_and_java.spring.repository.PersonRepository;
import rest_with_spring_boot_and_java.spring.service.PersonService;
import rest_with_spring_boot_and_java.spring.unittest.mock.MockPerson;
import rest_with_spring_boot_and_java.spring.vo.PersonVO;

import java.util.List;
import java.util.Optional;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonService service;

    @Mock
    PersonRepository repository;

    @Before
    public void setUpMocks() throws Exception {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void testFindById() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    public void testFindAll() {
        List<Person> list = input.mockEntityList();


        when(repository.findAll()).thenReturn(list);

        var people = service.findAll();

        assertNotNull(people);
        assertEquals(14, people.size());

        var personOne = people.get(1);

        assertNotNull(personOne);
        assertNotNull(personOne.getKey());
        assertNotNull(personOne.getLinks());
        assertTrue(personOne.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", personOne.getAddress());
        assertEquals("First Name Test1", personOne.getFirstName());
        assertEquals("Last Name Test1", personOne.getLastName());
        assertEquals("Female", personOne.getGender());

        var personTwo = people.get(2);

        assertNotNull(personTwo);
        assertNotNull(personTwo.getKey());
        assertNotNull(personTwo.getLinks());
        assertTrue(personTwo.toString().contains("links: [</api/person/v1/2>;rel=\"self\"]"));
        assertEquals("Addres Test2", personTwo.getAddress());
        assertEquals("First Name Test2", personTwo.getFirstName());
        assertEquals("Last Name Test2", personTwo.getLastName());
        assertEquals("Male", personTwo.getGender());

        var personFour = people.get(4);

        assertNotNull(personFour);
        assertNotNull(personFour.getKey());
        assertNotNull(personFour.getLinks());
        assertTrue(personFour.toString().contains("links: [</api/person/v1/4>;rel=\"self\"]"));
        assertEquals("Addres Test4", personFour.getAddress());
        assertEquals("First Name Test4", personFour.getFirstName());
        assertEquals("Last Name Test4", personFour.getLastName());
        assertEquals("Male", personFour.getGender());

        var personSeven = people.get(7);

        assertNotNull(personSeven);
        assertNotNull(personSeven.getKey());
        assertNotNull(personSeven.getLinks());
        assertTrue(personSeven.toString().contains("links: [</api/person/v1/7>;rel=\"self\"]"));
        assertEquals("Addres Test7", personSeven.getAddress());
        assertEquals("First Name Test7", personSeven.getFirstName());
        assertEquals("Last Name Test7", personSeven.getLastName());
        assertEquals("Female", personSeven.getGender());

    }



   @Test
   public  void testCreate() {
       Person entity = input.mockEntity(1);
       Person persisted = entity;
       persisted.setId(1L);
       PersonVO vo = input.mockVO(1);
       vo.setKey(1L);
       when(repository.save(entity)).thenReturn(persisted);

       var result = service.create(vo);

       assertNotNull(result);
       assertNotNull(result.getKey());
       assertNotNull(result.getLinks());
       assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
       assertEquals("Addres Test1", result.getAddress());
       assertEquals("First Name Test1", result.getFirstName());
       assertEquals("Last Name Test1", result.getLastName());
       assertEquals("Female", result.getGender());

   }

    @Test
    public  void testCreateNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(null);
        });

        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));


    }

    @Test
    public  void testUpdateNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.update(null);
        });

        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));


    }

    @Test
    public  void testUpdate() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        Person persisted = entity;
        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        when(repository.save(entity)).thenReturn(persisted);

        var result = service.update(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    public void testDelete() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        service.delete(1L);
    }

}
