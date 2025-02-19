package fr.ttmc.controllers;

import fr.ttmc.dtos.UserDto;
import fr.ttmc.generic.controller.GenericController;
import fr.ttmc.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController extends GenericController<UserDto, UserService> {
    public UserController(UserService service) {
        super(service);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Override
    @GetMapping
    public Page<UserDto> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Override
    @GetMapping("/{id}")
    public Optional<UserDto> findById(@PathVariable long id) {
        return service.findById(id);
    }
}