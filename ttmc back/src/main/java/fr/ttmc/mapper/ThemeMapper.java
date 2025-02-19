package fr.ttmc.mapper;

import fr.ttmc.dtos.ThemeDto;
import fr.ttmc.entities.Theme;
import fr.ttmc.generic.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ThemeMapper extends GenericMapper<ThemeDto, Theme> {
}
