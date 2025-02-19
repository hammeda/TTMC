package fr.ttmc.controllers;

import fr.ttmc.dtos.QuestionDto;
import fr.ttmc.generic.controller.GenericController;
import fr.ttmc.services.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController extends GenericController<QuestionDto, QuestionService> {

    public QuestionController(QuestionService service) {
        super(service);
    }

    @GetMapping("/theme/{themeId}")
    public List<QuestionDto> getQuestionsByThemeId(@PathVariable long themeId) {
        return service.findAllQuestionsByThemeId(themeId);
    }
    @GetMapping("/theme/{themeId}/difficulte/{difficulte}")
    public QuestionDto getQuestionsByThemeIdAndDifficulte(@PathVariable long themeId, @PathVariable int difficulte) {
        return service.findQuestionsByThemeIdAndDifficulte(themeId, difficulte);
    }
}
