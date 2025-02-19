package fr.ttmc.repositories;

import fr.ttmc.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
List<Question> findByThemeId(long themeId);
Question findByThemeIdAndDifficulte(long themeId, int difficulte);
}
