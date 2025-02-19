package fr.ttmc.mapper;

import fr.ttmc.dtos.QuestionDto;
import fr.ttmc.entities.Question;
import fr.ttmc.generic.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface QuestionMapper extends GenericMapper<QuestionDto, Question> {
}