package fr.ttmc.services;

import fr.ttmc.dtos.QuestionDto;
import fr.ttmc.entities.Question;
import fr.ttmc.generic.services.GenericServiceImpl;
import fr.ttmc.mapper.QuestionMapper;
import fr.ttmc.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl extends GenericServiceImpl<Question, QuestionRepository, QuestionDto, QuestionMapper> implements QuestionService{

    public QuestionServiceImpl(QuestionRepository repository, QuestionMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public List<QuestionDto> findAllQuestionsByThemeId(long id) {
        return toDto(repository.findByThemeId(id));
    }

    @Override
    public QuestionDto findQuestionsByThemeIdAndDifficulte(long id, int difficulte) {
        return toDto(repository.findByThemeIdAndDifficulte(id, difficulte));
    }
}
