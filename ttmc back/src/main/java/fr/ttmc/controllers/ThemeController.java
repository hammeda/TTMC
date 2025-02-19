package fr.ttmc.controllers;

import fr.ttmc.dtos.ThemeDto;
import fr.ttmc.generic.controller.GenericController;
import fr.ttmc.services.ThemeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/themes")
public class ThemeController extends GenericController<ThemeDto, ThemeService> {

    public ThemeController(ThemeService service) {
        super(service);
    }

}
