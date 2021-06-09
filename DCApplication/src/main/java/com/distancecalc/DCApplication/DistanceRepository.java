package com.distancecalc.DCApplication;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.List;

@RepositoryRestController
public interface DistanceRepository extends CrudRepository<Distance, Long> {
    List<Distance> findByFromCityAndToCity(City fromCity, City toCity);
}
