package com.distancecalc.DCApplication;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.List;

@RepositoryRestController()
public interface CityRepository extends CrudRepository<City, Long> {
    List<City> findByCityName(String cityName);
}
