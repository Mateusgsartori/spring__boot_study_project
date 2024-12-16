package rest_with_spring_boot_and_java.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rest_with_spring_boot_and_java.spring.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
