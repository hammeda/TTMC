package fr.ttmc.services;

import fr.ttmc.auth.UserSecurity;
import fr.ttmc.dtos.UserDto;
import fr.ttmc.generic.services.GenericService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService,GenericService<UserDto> {
    UserSecurity loadUserByUsername(String username) throws UsernameNotFoundException;
}