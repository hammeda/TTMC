package fr.ttmc.dtos;

import fr.ttmc.generic.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThemeDto extends BaseDto implements Serializable {
    private String name;
}