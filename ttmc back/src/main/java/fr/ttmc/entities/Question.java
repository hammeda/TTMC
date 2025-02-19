package fr.ttmc.entities;

import fr.ttmc.generic.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Accessors(chain = true)
public class Question extends BaseEntity {
    private String nomQuestion;
    private String reponse;
    private int difficulte;
    @ManyToOne
    private Theme theme;
}
