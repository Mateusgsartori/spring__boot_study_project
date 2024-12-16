package rest_with_spring_boot_and_java.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class ResourceNotFoundEception extends RuntimeException {

    public ResourceNotFoundEception(String ex){
        super(ex);
    }
}
