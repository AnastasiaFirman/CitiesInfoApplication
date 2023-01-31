package org.anastasia.citiesinfoapplication.dao;

import org.anastasia.citiesinfoapplication.model.City;
import org.anastasia.citiesinfoapplication.service.CityService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

public class CityServiceTest extends BaseIntegrationTest{
    private final CityService cityService;
    private final CityDao cityDao;
    private final DataSource dataSource;

    @Autowired
    public CityServiceTest(CityService cityService, CityDao cityDao, DataSource dataSource) {
        this.cityService = cityService;
        this.cityDao = cityDao;
        this.dataSource = dataSource;
    }

    @AfterEach
    void deleteAll() {
        cityDao.deleteAll();
    }

    @Test
    void saveAndFindByRegionCodeTest() {
        City city = new City("Калининград", "39");
        cityDao.save(city);
        City foundCityByRegionNumber = cityDao.findByRegionCode(city.getRegionCode()).get();
        Assertions.assertEquals(city, foundCityByRegionNumber);
    }

    @Test
    void findAllTest() {
        cityDao.save(new City("Калининград", "39"));
        cityDao.save(new City("Краснодар", "23"));
        Assertions.assertEquals(2, cityDao.findAll().size());
    }

    @Test
    void deleteByRegionNumber() {
        City city = new City("Калининград", "39");
        cityDao.deleteByRegionCode(city.getRegionCode());
        Assertions.assertEquals(0, cityDao.findAll().size());
    }
}
