package rest_with_spring_boot_and_java.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rest_with_spring_boot_and_java.spring.repository.UserRepository;
import rest_with_spring_boot_and_java.spring.security.jwt.JwtTokenProvider;
import rest_with_spring_boot_and_java.spring.vo.security.AccountCredentialsVO;
import rest_with_spring_boot_and_java.spring.vo.security.TokenVO;

@Service
public class AuthService {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @SuppressWarnings("rawtypes")
    public ResponseEntity signin(AccountCredentialsVO data) {
        try {
            var userName = data.getUserName();
            var password = data.getPassWord();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userName, password));

            var user = repository.findByUserName(userName);
            var tokenResponse = new TokenVO();

            if (user != null) {
                tokenResponse = tokenProvider.createAccessToken(userName, user.getRoles());

            } else {
                throw new UsernameNotFoundException("Usernmae " + userName + " not found!");
            }

            return ResponseEntity.ok(tokenResponse);

        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username or password!");
        }

    }

}
