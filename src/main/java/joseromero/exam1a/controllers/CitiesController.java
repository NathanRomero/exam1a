package joseromero.exam1a.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import joseromero.exam1a.models.CityModel;
import joseromero.exam1a.services.CityService;

@Controller
public class CitiesController {

    @Autowired
    private CityService cityService;

    @GetMapping("/cities")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Iterable<CityModel> index() {
        return cityService.getAll();
    }
}
