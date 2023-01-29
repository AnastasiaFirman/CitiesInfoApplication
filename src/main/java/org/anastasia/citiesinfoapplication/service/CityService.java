package org.anastasia.citiesinfoapplication.service;

import org.anastasia.citiesinfoapplication.model.City;

import java.util.List;

public interface CityService {

    City save(City city);

    City findByRegionCode(String regionCode);

    List<City> findAll ();

    void deleteByRegionCode(String regionCode);

    City update(String regionCode, City city);
}
