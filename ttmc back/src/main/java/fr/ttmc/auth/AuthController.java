package fr.ttmc.auth;

import fr.ttmc.dtos.LoginDto;
import fr.ttmc.dtos.LoginResponseDto;
import fr.ttmc.dtos.RegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/authenticate", consumes = "application/json")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginDto login) throws Exception {
        return ResponseEntity.ok(authService.authenticate(login));
    }

    @PostMapping(value = "/register", consumes = "application/json")
    public RegisterDto register(@RequestBody RegisterDto newUser) {
        authService.register(newUser);
        return newUser;
    }

}