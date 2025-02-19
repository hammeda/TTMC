package fr.ttmc.dtos;

import fr.ttmc.entities.Role;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<Role> roles;
}
