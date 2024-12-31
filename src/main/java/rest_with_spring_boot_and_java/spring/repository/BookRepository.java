package rest_with_spring_boot_and_java.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rest_with_spring_boot_and_java.spring.model.Books;

public interface BookRepository extends JpaRepository<Books, Long> {
}
