package org.anastasia.citiesinfoapplication.service;

import org.anastasia.citiesinfoapplication.model.City;

import java.util.List;

public interface CityService {

    City findById (Long id);

    City findByRegionNumber (int regionNumber);

    List<City> findAll ();

    void deleteAll();

    void deleteById(Long id);

    void deleteByRegionNumber(int regionNumber);

}
