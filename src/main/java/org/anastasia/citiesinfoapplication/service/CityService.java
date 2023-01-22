package org.anastasia.citiesinfoapplication.service;

import org.anastasia.citiesinfoapplication.model.City;

import java.util.List;

public interface CityService {

    City save(City city);

    City findById (Long id);

    City findByRegionNumber (int regionNumber);

    List<City> findAll ();

    void deleteById(Long id);

    void deleteByRegionNumber(int regionNumber);

    City update(Long id, City city);
}
