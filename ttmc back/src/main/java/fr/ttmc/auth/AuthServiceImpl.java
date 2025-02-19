package fr.ttmc.auth;

import fr.ttmc.dtos.LoginDto;
import fr.ttmc.dtos.LoginResponseDto;
import fr.ttmc.dtos.RegisterDto;
import fr.ttmc.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthMapper mapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void register(RegisterDto register) {
        register.setPassword(passwordEncoder.encode(register.getPassword()));
        userRepository.saveAndFlush(mapper.fromRegister(register));
    }

    @Override
    public LoginResponseDto authenticate(LoginDto login) throws SecurityException {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword(), new ArrayList<>()));
        if (authenticate.isAuthenticated()) {
            log.info("Successful authentication for user with email {} at {}", login.getEmail(), LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));
            UserSecurity principal = (UserSecurity) authenticate.getPrincipal();
            return mapper.toResponse(principal);
        }
        throw new SecurityException("Invalid Credentials");
    }
}