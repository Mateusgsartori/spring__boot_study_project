package rest_with_spring_boot_and_java.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.security.core.AuthenticationException;

@ResponseStatus(HttpStatus.FORBIDDEN)

public class InvalidJwtAuthenticationExeption extends AuthenticationException {

    public InvalidJwtAuthenticationExeption(String ex){
        super(ex);
    }
}
