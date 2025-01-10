package rest_with_spring_boot_and_java.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rest_with_spring_boot_and_java.spring.service.AuthService;
import rest_with_spring_boot_and_java.spring.vo.security.AccountCredentialsVO;
import rest_with_spring_boot_and_java.spring.util.Utils;

@Tag(name = "Authentication endpoint")
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    AuthService service;

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Authenticate a user and returns a token")
    @PostMapping(value = "/signin")
    public ResponseEntity signin (@RequestBody AccountCredentialsVO data) {
        if (checkIfParamsIsNotNull(data)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Invalid client request!");
        }

        var token = service.signin(data);

        if (token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Invalid client request!");
        }

        return token;

    }

    private boolean checkIfParamsIsNotNull(AccountCredentialsVO data) {
        return data == null || Utils.isEmpty(data.getUserName())
                || Utils.isEmpty(data.getPassWord());
    }

}
