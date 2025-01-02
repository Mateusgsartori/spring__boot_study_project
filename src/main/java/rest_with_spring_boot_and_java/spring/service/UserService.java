package rest_with_spring_boot_and_java.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rest_with_spring_boot_and_java.spring.controller.PersonController;
import rest_with_spring_boot_and_java.spring.exceptions.RequiredObjectIsNullException;
import rest_with_spring_boot_and_java.spring.exceptions.ResourceNotFoundExeption;
import rest_with_spring_boot_and_java.spring.mapper.PersonMapper;
import rest_with_spring_boot_and_java.spring.repository.PersonRepository;
import rest_with_spring_boot_and_java.spring.repository.UserRepository;
import rest_with_spring_boot_and_java.spring.vo.PersonVO;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserService implements UserDetailsService {

    private final Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Finding one user by name");
        var user = repository.findByUserName(username);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException(username + "not found!");
    }
}
