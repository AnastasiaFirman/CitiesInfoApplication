package org.anastasia.citiesinfoapplication.dao;

import org.anastasia.citiesinfoapplication.model.City;
import org.anastasia.citiesinfoapplication.service.CityService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CityServiceTest {
    private final CityService cityService;
    private final CityDao cityDao;

    @Autowired
    public CityServiceTest(CityService cityService, CityDao cityDao) {
        this.cityService = cityService;
        this.cityDao = cityDao;
    }

    @AfterEach
    void deleteAll() {
        cityDao.deleteAll();
    }

    @Test
    void saveAndFindByIdTest() {
        City city = new City("Калининград", 39);
        cityDao.save(city);
        City foundCityById = cityDao.findById(city.getId()).get();
        Assertions.assertEquals(city, foundCityById);
    }

    @Test
    void findByRegionNumberTest() {
        City city = new City("Калининград", 39);
        cityDao.save(city);
        City foundCityByRegionNumber = cityDao.findByRegionNumber(city.getRegionNumber()).get();
        Assertions.assertEquals(city, foundCityByRegionNumber);
    }

    @Test
    void findAllTest() {
        cityDao.save(new City("Калининград", 39));
        cityDao.save(new City("Краснодар", 23));
        Assertions.assertEquals(2, cityDao.findAll().size());
    }

    @Test
    void deleteByIdTest() {
        City city = new City(1L, "Краснодар", 23);
        cityDao.deleteById(city.getId());
        Assertions.assertEquals(0, cityDao.findAll().size());
    }

    @Test
    void deleteByRegionNumber() {
        City city = new City("Калининград", 39);
        cityDao.deleteByRegionNumber(city.getRegionNumber());
        Assertions.assertEquals(0, cityDao.findAll().size());
    }
}
