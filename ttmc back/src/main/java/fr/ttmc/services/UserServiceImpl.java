package fr.ttmc.services;

import fr.ttmc.auth.UserSecurity;
import fr.ttmc.dtos.UserDto;
import fr.ttmc.entities.User;
import fr.ttmc.generic.services.GenericServiceImpl;
import fr.ttmc.mapper.UserMapper;
import fr.ttmc.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends GenericServiceImpl<User, UserRepository, UserDto, UserMapper> implements UserService{

    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public UserSecurity loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username).map(UserSecurity::new).orElseThrow();
    }


}
