package org.anastasia.citiesinfoapplication.dao;

import org.anastasia.citiesinfoapplication.model.City;

import java.util.List;
import java.util.Optional;

public interface CityDao {
    City save(City city);

    Optional<City> findByRegionCode(String regionCode);

    List<City> findAll();

    void deleteAll();

    void deleteByRegionCode(String regionCode);

    City update (String regionCode, City city);

}
