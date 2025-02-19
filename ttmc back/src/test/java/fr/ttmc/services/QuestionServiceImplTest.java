package fr.ttmc.services;

import fr.ttmc.dtos.QuestionDto;
import fr.ttmc.entities.Question;
import fr.ttmc.entities.Theme;
import fr.ttmc.mapper.QuestionMapper;
import fr.ttmc.repositories.QuestionRepository;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionServiceImplTest {
    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private QuestionMapper mapper;

    @InjectMocks
    private QuestionServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllQuestionsByThemeId() {
        // Arrange
        Theme theme = (Theme) new Theme().setId(1L);
        Question question1 = (Question) new Question().setTheme(theme);
        Question question2 = (Question) new Question().setTheme(theme);
        Question question3 = (Question) new Question().setTheme(theme);
        Question question4 = (Question) new Question().setTheme(theme);

        List<Question> questions = List.of(question1, question2, question3, question4);

        when(questionRepository.findByThemeId(1L)).thenReturn(questions);
        when(mapper.toDto(any(Question.class))).thenReturn(new QuestionDto());

        // Act
        List<QuestionDto> result = service.findAllQuestionsByThemeId(1L);

        // Assert
        assertArrayEquals(questions.parallelStream().map(Question::getId).toArray(),
                result.parallelStream().map(QuestionDto::getId).toArray());
    }

    @Test
    void findQuestionsByThemeIdAndDifficulte() {
        // Arrange
        Theme theme = (Theme) new Theme().setId(1L);

        Question question = new Question()
                .setTheme(theme)
                .setDifficulte(2)
                .setNomQuestion("Question 2");

        when(questionRepository.findByThemeIdAndDifficulte(1L, 2)).thenReturn(question);

        when(mapper.toDto(any(Question.class))).thenReturn(new QuestionDto());

        // Act
        QuestionDto result = service.findQuestionsByThemeIdAndDifficulte(1L, 2);

        //Assert
        assertEquals(question.getId(), result.getId(), "The id of the question should be the same");
    }
}