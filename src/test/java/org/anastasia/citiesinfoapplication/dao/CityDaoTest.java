package org.anastasia.citiesinfoapplication.dao;

import org.anastasia.citiesinfoapplication.dbconnector.Connector;
import org.anastasia.citiesinfoapplication.model.City;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.Statement;

public class CityDaoTest extends BaseIntegrationTest{
    private final CityDao cityDao;
    private final Connector connector;

    @Autowired
    public CityDaoTest(CityDao cityDao, Connector connector) {
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
        City cityForSave = new City("Якутск", 14);
        cityDao.save(cityForSave);
        City foundCityById = cityDao.findById(cityForSave.getId()).get();
        Assertions.assertEquals(cityForSave, foundCityById);
    }

    @Test
    void findCityByRegionNumberTest() {
        City city = new City("Калининград", 39);
        cityDao.save(city);
        City foundCity = cityDao.findByRegionNumber(city.getRegionNumber()).get();
        Assertions.assertEquals(city, foundCity);
    }

    @Test
    void findAllTest() {
        cityDao.save(new City("Ярославль", 76));
        cityDao.save(new City("Краснодар", 23));
        Assertions.assertEquals(2, cityDao.findAll().size());
    }

    @Test
    void deleteByIdTest() {
        City city = new City(10L, "Калининград", 39);
        cityDao.deleteById(city.getId());
        Assertions.assertEquals(0, cityDao.findAll().size());
    }

    @Test
    void deleteByRegionNumberTest() {
        City city = new City("Калининград", 39);
        cityDao.deleteByRegionNumber(city.getRegionNumber());
        Assertions.assertEquals(0, cityDao.findAll().size());
    }
}
