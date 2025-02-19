package fr.ttmc.dtos;

import fr.ttmc.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto implements Serializable {

   private User user;
   private String token;

}
