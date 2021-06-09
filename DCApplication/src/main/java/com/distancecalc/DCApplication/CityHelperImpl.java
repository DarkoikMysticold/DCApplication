package com.distancecalc.DCApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CityHelperImpl implements CityHelper {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private DistanceRepository distanceRepository;

    @Override
    public Cities findAll()
    {
        var cities = (List<City>) cityRepository.findAll();
        var mycities = new Cities();
        mycities.setCities(cities);

        return mycities;
    }

    @Override
    public City findByCityName(String cityName){
        var cities = (List<City>) cityRepository.findByCityName(cityName);
        if (cities.size()==0) {throw new CityNotFound("No such city");}
        else if (cities.size()>1){
            int amount = 0;
            while(amount!=cities.size()){
                if(!cities.get(amount).getCityName().equals(cityName)){
                    amount++;
                }
                else return cities.get(amount);
            }
            throw new CityNotFound("No such city");
        }
        return cities.get(0);
    }

    @Override
    public City findById(Long id)  {
        return cityRepository.findById(id).orElseThrow(()-> new CityNotFound("No city found"));
    }

    @Override
    public Distance findByCities(String city1, String city2){
        City fCity = findByCityName(city1);
        City sCity = findByCityName(city2);
        var distances = (List<Distance>) distanceRepository.findByFromCityAndToCity(fCity, sCity);
        if (distances.size()==0){throw new NoSuchDistance("No distances like that");}
        return distances.get(0);
    }

    public Double Crowflight(String Name1, String Name2) {
        final int R = 6371;
        City city1 = findByCityName(Name1);
        City city2 = findByCityName(Name2);

        double latDistance = Math.toRadians(city1.getLatitude() - city2.getLatitude());
        double lonDistance = Math.toRadians(city1.getLongitude() - city2.getLongitude());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                    + Math.cos(Math.toRadians(city1.getLatitude())) * Math.cos(Math.toRadians(city2.getLatitude()))
                    * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000;

        double height = 0.0;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

    public Integer DistanceMatrix(String Name1, String Name2){
        City fCity = findByCityName(Name1);
        City sCity = findByCityName(Name2);
        var distances = (List<Distance>) distanceRepository.findByFromCityAndToCity(fCity, sCity);
        if (distances.size() == 0) {
            distances = distanceRepository.findByFromCityAndToCity(sCity, fCity);
        }
        if (distances.size()==0){throw new NoSuchDistance("No path between selected cities");}
        return distances.get(0).getDistance();
    }

}
