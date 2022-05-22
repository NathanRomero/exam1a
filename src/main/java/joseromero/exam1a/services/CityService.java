package joseromero.exam1a.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import joseromero.exam1a.models.CityModel;
import joseromero.exam1a.repositories.cityRepository;

@Service
public class CityService {
    @Autowired
    cityRepository cityRepository;

    public Optional<CityModel> getCity(Long id) {
        return cityRepository.findById(id);
    }

    public Iterable<CityModel> getAll() {
        return cityRepository.findAll();
    }
}
