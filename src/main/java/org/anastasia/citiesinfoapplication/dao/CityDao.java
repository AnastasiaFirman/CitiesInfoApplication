package org.anastasia.citiesinfoapplication.dao;

import org.anastasia.citiesinfoapplication.model.City;

import java.util.List;
import java.util.Optional;

public interface CityDao {
    City save(City city);

    Optional<City> findById(Long id);

    Optional<City> findByRegionNumber(int regionNumber);

    List<City> findAll();

    void deleteAll();

    void deleteById(Long id);

    void deleteByRegionNumber(int regionNumber);

    City update (Long id, City city);

}
