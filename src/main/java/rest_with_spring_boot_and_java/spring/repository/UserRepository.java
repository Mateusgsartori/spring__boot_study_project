package rest_with_spring_boot_and_java.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rest_with_spring_boot_and_java.spring.model.Person;
import rest_with_spring_boot_and_java.spring.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT e FROM User e WHERE e.userName = :userName")
    User findByUserName(@Param("userName") String userName);
}
