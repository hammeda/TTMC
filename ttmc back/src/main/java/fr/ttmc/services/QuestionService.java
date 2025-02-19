package fr.ttmc.services;

import fr.ttmc.dtos.QuestionDto;
import fr.ttmc.generic.services.GenericService;
import java.util.List;

public interface QuestionService extends GenericService<QuestionDto> {
    List<QuestionDto> findAllQuestionsByThemeId(long id);
    QuestionDto findQuestionsByThemeIdAndDifficulte(long id, int difficulte);
}
