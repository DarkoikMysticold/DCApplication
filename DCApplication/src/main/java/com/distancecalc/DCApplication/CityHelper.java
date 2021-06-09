package com.distancecalc.DCApplication;

public interface CityHelper {
    Cities findAll();
    City findById(Long id);
    City findByCityName(String cityName);
    Distance findByCities(String city1, String city2);
}
