package joseromero.exam1a.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import joseromero.exam1a.models.CityModel;

@Repository
public interface cityRepository extends CrudRepository<CityModel, Long> {
    
}
