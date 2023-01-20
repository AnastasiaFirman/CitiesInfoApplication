package org.anastasia.citiesinfoapplication.dao;

import org.anastasia.citiesinfoapplication.model.City;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CityDaoTest {
    private final CityDao cityDao;

    @Autowired
    public CityDaoTest(CityDao cityDao) {
        this.cityDao = cityDao;
    }
    @AfterEach
    void deleteAll() {
        cityDao.deleteAll();
    }

    @Test
    void SaveAndFindByIdTest() {
        City cityForSave = new City("Якутск", 14);
        cityDao.save(cityForSave);
        City foundCityById = cityDao.findById(cityForSave.getId()).get();
        Assertions.assertEquals(cityForSave, foundCityById);
    }

    @Test
    void FindCityByRegionNumber() {
        City city = new City("Калининград", 39);
        cityDao.save(city);
        City foundCity = cityDao.findByRegionNumber(city.getRegionNumber()).get();
        Assertions.assertEquals(city, foundCity);
    }




}
