package org.anastasia.citiesinfoapplication.dao;

import org.anastasia.citiesinfoapplication.dbconnector.Connector;
import org.anastasia.citiesinfoapplication.model.City;
import org.anastasia.citiesinfoapplication.service.CityService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.Statement;

public class CityServiceTest extends BaseIntegrationTest{
    private final CityService cityService;
    private final CityDao cityDao;
    private final Connector connector;

    @Autowired
    public CityServiceTest(CityService cityService, CityDao cityDao, Connector connector) {
        this.cityService = cityService;
        this.cityDao = cityDao;
        this.connector = connector;
    }

    @BeforeAll
    void initDb() throws Exception{
        Connection connection = connector.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("create table city (\n" +
                "id serial primary key,\n" +
                "name text,\n" +
                "region_number int\n" +
                ");");
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
