package fr.ttmc.auth;

import fr.ttmc.dtos.LoginDto;
import fr.ttmc.dtos.LoginResponseDto;
import fr.ttmc.dtos.RegisterDto;

public interface AuthService {
    void register(RegisterDto register);

    LoginResponseDto authenticate(LoginDto login) throws SecurityException;
}
