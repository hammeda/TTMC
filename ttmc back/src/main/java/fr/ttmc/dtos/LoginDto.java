package fr.ttmc.dtos;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto implements Serializable {
    private String email;
    private String password;
}
