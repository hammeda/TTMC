package fr.ttmc.dtos;

import fr.ttmc.generic.dto.BaseDto;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class QuestionDto extends BaseDto implements Serializable {
    private String nomQuestion;
    private String reponse;
    private int difficulte;
    ThemeDto1 theme;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ThemeDto1 implements Serializable {
        long id;
    }
}
