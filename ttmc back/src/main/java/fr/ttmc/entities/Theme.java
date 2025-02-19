package fr.ttmc.entities;

import fr.ttmc.generic.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Accessors(chain = true)
public class Theme extends BaseEntity {
    private String name;
    @OneToMany(mappedBy = "theme", cascade = CascadeType.REMOVE)
    private List<Question> questions;
}

