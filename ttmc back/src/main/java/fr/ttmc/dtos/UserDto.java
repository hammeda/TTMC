package fr.ttmc.dtos;

import fr.ttmc.entities.Role;
import fr.ttmc.generic.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<Role> roles;

}
