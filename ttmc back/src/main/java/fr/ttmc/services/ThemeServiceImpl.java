package fr.ttmc.services;

import fr.ttmc.dtos.ThemeDto;
import fr.ttmc.entities.Theme;
import fr.ttmc.generic.services.GenericServiceImpl;
import fr.ttmc.mapper.ThemeMapper;
import fr.ttmc.repositories.ThemeRepository;
import org.springframework.stereotype.Service;

@Service
public class ThemeServiceImpl extends GenericServiceImpl<Theme, ThemeRepository, ThemeDto, ThemeMapper> implements ThemeService {
    public ThemeServiceImpl(ThemeRepository repository, ThemeMapper mapper) {
        super(repository, mapper);
    }
}
